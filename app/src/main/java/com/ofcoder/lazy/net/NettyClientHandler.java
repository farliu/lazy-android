package com.ofcoder.lazy.net;

import android.util.Log;

import com.ofcoder.lazy.domain.enums.ConnectStatusEnum;
import com.ofcoder.lazy.listener.callback.NettyCallbackListener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by ofcoder on 2019/2/18.
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final String TAG = "NettyClientHandler";
    private NettyCallbackListener listener;

    public NettyClientHandler(NettyCallbackListener listener) {
        this.listener = listener;
    }

    /**
     * 每次给服务器发送的东西， 让服务器知道我们在连接中哎
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                ctx.channel().writeAndFlush("Heartbeat" + System.getProperty("line.separator"));
            }
        }
    }

    /**
     * 连接成功
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.e(TAG, "channelActive");
        super.channelActive(ctx);
        listener.onServiceStatusConnectChanged(ConnectStatusEnum.STATUS_CONNECT_SUCCESS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Log.e(TAG, "channelInactive");
    }

    /**
     * 接收消息的地方， 接口调用返回到activity了
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端开始读取服务端过来的信息");
        listener.onMessageResponse(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当引发异常时关闭连接。
        Log.e(TAG, "exceptionCaught");
        listener.onServiceStatusConnectChanged(ConnectStatusEnum.STATUS_CONNECT_ERROR);
        cause.printStackTrace();
        ctx.close();
    }

}

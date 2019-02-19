package com.ofcoder.lazy.listener;

import android.view.MotionEvent;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ofcoder.lazy.R;
import com.ofcoder.lazy.domain.enums.BehaviorEnum;
import com.ofcoder.lazy.domain.vo.VO;
import com.ofcoder.lazy.domain.vo.request.KeyboardReqVO;
import com.ofcoder.lazy.net.NettyClient;
import com.ofcoder.lazy.util.ThreadUtil;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * Created by ofcoder on 2019/2/19.
 */
public class KeyboardTouchListener implements View.OnTouchListener {

    private static Map<Integer, String> routeMap = new HashMap<>();
    private NettyClient nettyClient;

    static {
        routeMap.put(R.id.btn_line1_1, "btn_line1_1");
        routeMap.put(R.id.btn_line1_2, "btn_line1_2");
        routeMap.put(R.id.btn_line1_3, "btn_line1_3");
        routeMap.put(R.id.btn_line1_4, "btn_line1_4");
        routeMap.put(R.id.btn_line1_5, "btn_line1_5");
        routeMap.put(R.id.btn_line1_6, "btn_line1_6");
        routeMap.put(R.id.btn_line1_7, "btn_line1_7");
        routeMap.put(R.id.btn_line1_8, "btn_line1_8");
        routeMap.put(R.id.btn_line1_9, "btn_line1_9");
        routeMap.put(R.id.btn_line1_10, "btn_line1_10");
        routeMap.put(R.id.btn_line1_11, "btn_line1_11");
        routeMap.put(R.id.btn_line1_12, "btn_line1_12");
        routeMap.put(R.id.btn_line1_13, "btn_line1_13");
        routeMap.put(R.id.btn_line1_14, "btn_line1_14");
        routeMap.put(R.id.btn_line1_15, "btn_line1_15");

        routeMap.put(R.id.btn_line2_1, "btn_line2_1");
        routeMap.put(R.id.btn_line2_2, "btn_line2_2");
        routeMap.put(R.id.btn_line2_3, "btn_line2_3");
        routeMap.put(R.id.btn_line2_4, "btn_line2_4");
        routeMap.put(R.id.btn_line2_5, "btn_line2_5");
        routeMap.put(R.id.btn_line2_6, "btn_line2_6");
        routeMap.put(R.id.btn_line2_7, "btn_line2_7");
        routeMap.put(R.id.btn_line2_8, "btn_line2_8");
        routeMap.put(R.id.btn_line2_9, "btn_line2_9");
        routeMap.put(R.id.btn_line2_10, "btn_line2_10");
        routeMap.put(R.id.btn_line2_11, "btn_line2_11");
        routeMap.put(R.id.btn_line2_12, "btn_line2_12");
        routeMap.put(R.id.btn_line2_13, "btn_line2_13");
        routeMap.put(R.id.btn_line2_14, "btn_line2_14");

        routeMap.put(R.id.btn_line3_1, "btn_line3_1");
        routeMap.put(R.id.btn_line3_2, "btn_line3_2");
        routeMap.put(R.id.btn_line3_3, "btn_line3_3");
        routeMap.put(R.id.btn_line3_4, "btn_line3_4");
        routeMap.put(R.id.btn_line3_5, "btn_line3_5");
        routeMap.put(R.id.btn_line3_6, "btn_line3_6");
        routeMap.put(R.id.btn_line3_7, "btn_line3_7");
        routeMap.put(R.id.btn_line3_8, "btn_line3_8");
        routeMap.put(R.id.btn_line3_9, "btn_line3_9");
        routeMap.put(R.id.btn_line3_10, "btn_line3_10");
        routeMap.put(R.id.btn_line3_11, "btn_line3_11");
        routeMap.put(R.id.btn_line3_12, "btn_line3_12");
        routeMap.put(R.id.btn_line3_13, "btn_line3_13");

        routeMap.put(R.id.btn_line4_1, "btn_line4_1");
        routeMap.put(R.id.btn_line4_2, "btn_line4_2");
        routeMap.put(R.id.btn_line4_3, "btn_line4_3");
        routeMap.put(R.id.btn_line4_4, "btn_line4_4");
        routeMap.put(R.id.btn_line4_5, "btn_line4_5");
        routeMap.put(R.id.btn_line4_6, "btn_line4_6");
        routeMap.put(R.id.btn_line4_7, "btn_line4_7");
        routeMap.put(R.id.btn_line4_8, "btn_line4_8");
        routeMap.put(R.id.btn_line4_9, "btn_line4_9");
        routeMap.put(R.id.btn_line4_10, "btn_line4_10");
        routeMap.put(R.id.btn_line4_11, "btn_line4_11");
        routeMap.put(R.id.btn_line4_12, "btn_line4_12");

        routeMap.put(R.id.btn_line5_1, "btn_line5_1");
        routeMap.put(R.id.btn_line5_2, "btn_line5_2");
        routeMap.put(R.id.btn_line5_3, "btn_line5_3");
        routeMap.put(R.id.btn_line5_4, "btn_line5_4");
        routeMap.put(R.id.btn_line5_5, "btn_line5_5");
        routeMap.put(R.id.btn_line5_6, "btn_line5_6");
        routeMap.put(R.id.btn_line5_7, "btn_line5_7");
        routeMap.put(R.id.btn_line5_8, "btn_line5_8");
        routeMap.put(R.id.btn_line5_9, "btn_line5_9");
        routeMap.put(R.id.btn_line5_10, "btn_line5_10");
        routeMap.put(R.id.btn_line5_11, "btn_line5_11");

    }

    public KeyboardTouchListener(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        String code = routeMap.get(view.getId());
        if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
            KeyboardReqVO reqVO = new KeyboardReqVO("ACTION_DOWN", code);
            VO vo = new VO(BehaviorEnum.CONTROL_KEYBOARD, reqVO);
            sendToPC(vo);
        } else if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
            KeyboardReqVO reqVO = new KeyboardReqVO("ACTION_UP", code);
            VO vo = new VO(BehaviorEnum.CONTROL_KEYBOARD, reqVO);
            sendToPC(vo);
        } else {
            //do nothing.
        }
        return true;
    }

    private void sendToPC(final VO request) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                nettyClient.sendMsgToServer(JSON.toJSONString(request), new ChannelFutureListener() {
                    @Override
                    public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                        // do nothing....
                    }
                });
            }
        });
    }
}

package com.ofcoder.lazy.listener.callback;

import com.ofcoder.lazy.domain.enums.ConnectStatusEnum;

/**
 * Created by ofcoder on 2019/2/18.
 */
public interface NettyCallbackListener {



    /**
     * 当接收到系统消息
     */
    void onMessageResponse(Object msg);

    /**
     * 当连接状态发生变化时调用
     */
    void onServiceStatusConnectChanged(ConnectStatusEnum status);

}

package com.ofcoder.lazy.window.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ofcoder.lazy.R;
import com.ofcoder.lazy.domain.dto.PositionDTO;
import com.ofcoder.lazy.domain.enums.BehaviorEnum;
import com.ofcoder.lazy.domain.enums.ConnectStatusEnum;
import com.ofcoder.lazy.domain.enums.IntentExtraEnum;
import com.ofcoder.lazy.domain.enums.TouchTypeEnum;
import com.ofcoder.lazy.domain.vo.VO;
import com.ofcoder.lazy.domain.vo.request.TouchReqVO;
import com.ofcoder.lazy.listener.KeyboardTouchListener;
import com.ofcoder.lazy.listener.MouseOnTouchListener;
import com.ofcoder.lazy.listener.callback.NettyCallbackListener;
import com.ofcoder.lazy.listener.callback.TouchCallbackListener;
import com.ofcoder.lazy.net.NettyClient;
import com.ofcoder.lazy.util.ActivityUtil;
import com.ofcoder.lazy.util.DensityUtil;
import com.ofcoder.lazy.util.ThreadUtil;
import com.ofcoder.lazy.window.view.GracefullyToast;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * Created by ofcoder on 2019/2/18.
 */
public class MainActivity extends BaseActivity implements NettyCallbackListener,
        View.OnClickListener, TouchCallbackListener {


    private final String TAG = "MainActivity.class";
    private NettyClient nettyClient;
    private ImageView ivWindowsScreen;
    private Boolean isExit = false;
    private ImageView iv_bottom_menu, iv_bottom_menu_keyboard, iv_bottom_menu_set, iv_bottom_menu_help, iv_bottom_menu_file, iv_bottom_menu_feedback;
    private List<ImageView> bottomMenus = new ArrayList<>();
    private float radius;
    private boolean isOpenMenu = false;
    private Animation animShow;
    private Animation animClose;
    private Button btnLine1_1, btnLine1_2, btnLine1_3, btnLine1_4, btnLine1_5, btnLine1_6, btnLine1_7, btnLine1_8, btnLine1_9, btnLine1_10, btnLine1_11, btnLine1_12, btnLine1_13, btnLine1_14, btnLine1_15;
    private Button btnLine2_1, btnLine2_2, btnLine2_3, btnLine2_4, btnLine2_5, btnLine2_6, btnLine2_7, btnLine2_8, btnLine2_9, btnLine2_10, btnLine2_11, btnLine2_12, btnLine2_13, btnLine2_14;
    private Button btnLine3_1, btnLine3_2, btnLine3_3, btnLine3_4, btnLine3_5, btnLine3_6, btnLine3_7, btnLine3_8, btnLine3_9, btnLine3_10, btnLine3_11, btnLine3_12, btnLine3_13;
    private Button btnLine4_1, btnLine4_2, btnLine4_3, btnLine4_4, btnLine4_5, btnLine4_6, btnLine4_7, btnLine4_8, btnLine4_9, btnLine4_10, btnLine4_11, btnLine4_12;
    private Button btnLine5_1, btnLine5_2, btnLine5_3, btnLine5_4, btnLine5_5, btnLine5_6, btnLine5_7, btnLine5_8, btnLine5_9, btnLine5_10, btnLine5_11;
    private LinearLayout ll_keyboard;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private void initKeyboardView() {
        ll_keyboard = findViewById(R.id.ll_keyboard);

        btnLine1_1 = findViewById(R.id.btn_line1_1);
        btnLine1_2 = findViewById(R.id.btn_line1_2);
        btnLine1_3 = findViewById(R.id.btn_line1_3);
        btnLine1_4 = findViewById(R.id.btn_line1_4);
        btnLine1_5 = findViewById(R.id.btn_line1_5);
        btnLine1_6 = findViewById(R.id.btn_line1_6);
        btnLine1_7 = findViewById(R.id.btn_line1_7);
        btnLine1_8 = findViewById(R.id.btn_line1_8);
        btnLine1_9 = findViewById(R.id.btn_line1_9);
        btnLine1_10 = findViewById(R.id.btn_line1_10);
        btnLine1_11 = findViewById(R.id.btn_line1_11);
        btnLine1_12 = findViewById(R.id.btn_line1_12);
        btnLine1_13 = findViewById(R.id.btn_line1_13);
        btnLine1_14 = findViewById(R.id.btn_line1_14);
        btnLine1_15 = findViewById(R.id.btn_line1_15);

        btnLine2_1 = findViewById(R.id.btn_line2_1);
        btnLine2_2 = findViewById(R.id.btn_line2_2);
        btnLine2_3 = findViewById(R.id.btn_line2_3);
        btnLine2_4 = findViewById(R.id.btn_line2_4);
        btnLine2_5 = findViewById(R.id.btn_line2_5);
        btnLine2_6 = findViewById(R.id.btn_line2_6);
        btnLine2_7 = findViewById(R.id.btn_line2_7);
        btnLine2_8 = findViewById(R.id.btn_line2_8);
        btnLine2_9 = findViewById(R.id.btn_line2_9);
        btnLine2_10 = findViewById(R.id.btn_line2_10);
        btnLine2_11 = findViewById(R.id.btn_line2_11);
        btnLine2_12 = findViewById(R.id.btn_line2_12);
        btnLine2_13 = findViewById(R.id.btn_line2_13);
        btnLine2_14 = findViewById(R.id.btn_line2_14);

        btnLine3_1 = findViewById(R.id.btn_line3_1);
        btnLine3_2 = findViewById(R.id.btn_line3_2);
        btnLine3_3 = findViewById(R.id.btn_line3_3);
        btnLine3_4 = findViewById(R.id.btn_line3_4);
        btnLine3_5 = findViewById(R.id.btn_line3_5);
        btnLine3_6 = findViewById(R.id.btn_line3_6);
        btnLine3_7 = findViewById(R.id.btn_line3_7);
        btnLine3_8 = findViewById(R.id.btn_line3_8);
        btnLine3_9 = findViewById(R.id.btn_line3_9);
        btnLine3_10 = findViewById(R.id.btn_line3_10);
        btnLine3_11 = findViewById(R.id.btn_line3_11);
        btnLine3_12 = findViewById(R.id.btn_line3_12);
        btnLine3_13 = findViewById(R.id.btn_line3_13);

        btnLine4_1 = findViewById(R.id.btn_line4_1);
        btnLine4_2 = findViewById(R.id.btn_line4_2);
        btnLine4_3 = findViewById(R.id.btn_line4_3);
        btnLine4_4 = findViewById(R.id.btn_line4_4);
        btnLine4_5 = findViewById(R.id.btn_line4_5);
        btnLine4_6 = findViewById(R.id.btn_line4_6);
        btnLine4_7 = findViewById(R.id.btn_line4_7);
        btnLine4_8 = findViewById(R.id.btn_line4_8);
        btnLine4_9 = findViewById(R.id.btn_line4_9);
        btnLine4_10 = findViewById(R.id.btn_line4_10);
        btnLine4_11 = findViewById(R.id.btn_line4_11);
        btnLine4_12 = findViewById(R.id.btn_line4_12);

        btnLine5_1 = findViewById(R.id.btn_line5_1);
        btnLine5_2 = findViewById(R.id.btn_line5_2);
        btnLine5_3 = findViewById(R.id.btn_line5_3);
        btnLine5_4 = findViewById(R.id.btn_line5_4);
        btnLine5_5 = findViewById(R.id.btn_line5_5);
        btnLine5_6 = findViewById(R.id.btn_line5_6);
        btnLine5_7 = findViewById(R.id.btn_line5_7);
        btnLine5_8 = findViewById(R.id.btn_line5_8);
        btnLine5_9 = findViewById(R.id.btn_line5_9);
        btnLine5_10 = findViewById(R.id.btn_line5_10);
        btnLine5_11 = findViewById(R.id.btn_line5_11);
    }

    @Override
    protected void initView() {
        initKeyboardView();
        ivWindowsScreen = findViewById(R.id.iv_windowsScreen);
        iv_bottom_menu = findViewById(R.id.iv_bottom_menu);
        iv_bottom_menu_keyboard = findViewById(R.id.iv_bottom_menu_keyboard);
        iv_bottom_menu_set = findViewById(R.id.iv_bottom_menu_set);
        iv_bottom_menu_help = findViewById(R.id.iv_bottom_menu_help);
        iv_bottom_menu_file = findViewById(R.id.iv_bottom_menu_file);
        iv_bottom_menu_feedback = findViewById(R.id.iv_bottom_menu_feedback);
    }

    private void initKeyboardLoad(KeyboardTouchListener listener) {
        btnLine1_1.setOnTouchListener(listener);
        btnLine1_2.setOnTouchListener(listener);
        btnLine1_3.setOnTouchListener(listener);
        btnLine1_4.setOnTouchListener(listener);
        btnLine1_5.setOnTouchListener(listener);
        btnLine1_6.setOnTouchListener(listener);
        btnLine1_7.setOnTouchListener(listener);
        btnLine1_8.setOnTouchListener(listener);
        btnLine1_9.setOnTouchListener(listener);
        btnLine1_10.setOnTouchListener(listener);
        btnLine1_11.setOnTouchListener(listener);
        btnLine1_12.setOnTouchListener(listener);
        btnLine1_13.setOnTouchListener(listener);
        btnLine1_14.setOnTouchListener(listener);
        btnLine1_15.setOnClickListener(this);
        btnLine2_1.setOnTouchListener(listener);
        btnLine2_2.setOnTouchListener(listener);
        btnLine2_3.setOnTouchListener(listener);
        btnLine2_4.setOnTouchListener(listener);
        btnLine2_5.setOnTouchListener(listener);
        btnLine2_6.setOnTouchListener(listener);
        btnLine2_7.setOnTouchListener(listener);
        btnLine2_8.setOnTouchListener(listener);
        btnLine2_9.setOnTouchListener(listener);
        btnLine2_10.setOnTouchListener(listener);
        btnLine2_11.setOnTouchListener(listener);
        btnLine2_12.setOnTouchListener(listener);
        btnLine2_13.setOnTouchListener(listener);
        btnLine2_14.setOnTouchListener(listener);
        btnLine3_1.setOnTouchListener(listener);
        btnLine3_2.setOnTouchListener(listener);
        btnLine3_3.setOnTouchListener(listener);
        btnLine3_4.setOnTouchListener(listener);
        btnLine3_5.setOnTouchListener(listener);
        btnLine3_6.setOnTouchListener(listener);
        btnLine3_7.setOnTouchListener(listener);
        btnLine3_8.setOnTouchListener(listener);
        btnLine3_9.setOnTouchListener(listener);
        btnLine3_10.setOnTouchListener(listener);
        btnLine3_11.setOnTouchListener(listener);
        btnLine3_12.setOnTouchListener(listener);
        btnLine3_13.setOnTouchListener(listener);
        btnLine4_1.setOnTouchListener(listener);
        btnLine4_2.setOnTouchListener(listener);
        btnLine4_3.setOnTouchListener(listener);
        btnLine4_4.setOnTouchListener(listener);
        btnLine4_5.setOnTouchListener(listener);
        btnLine4_6.setOnTouchListener(listener);
        btnLine4_7.setOnTouchListener(listener);
        btnLine4_8.setOnTouchListener(listener);
        btnLine4_9.setOnTouchListener(listener);
        btnLine4_10.setOnTouchListener(listener);
        btnLine4_11.setOnTouchListener(listener);
        btnLine4_12.setOnTouchListener(listener);
        btnLine5_1.setOnTouchListener(listener);
        btnLine5_2.setOnTouchListener(listener);
        btnLine5_3.setOnTouchListener(listener);
        btnLine5_4.setOnTouchListener(listener);
        btnLine5_5.setOnTouchListener(listener);
        btnLine5_6.setOnTouchListener(listener);
        btnLine5_7.setOnTouchListener(listener);
        btnLine5_8.setOnTouchListener(listener);
        btnLine5_9.setOnTouchListener(listener);
        btnLine5_10.setOnTouchListener(listener);
        btnLine5_11.setOnTouchListener(listener);

    }

    @Override
    protected void initLoad() {

        radius = DensityUtil.getScreenWidth(this) * 0.4f;

        ivWindowsScreen.setOnTouchListener(new MouseOnTouchListener(this));

        String host = getIntent().getStringExtra(IntentExtraEnum.HOST.name());
        Integer port = getIntent().getIntExtra(IntentExtraEnum.PORT.name(), 0);
        initSocketTcp(host, port);
        KeyboardTouchListener listener = new KeyboardTouchListener(nettyClient);
        initKeyboardLoad(listener);

        iv_bottom_menu.setOnClickListener(this);
        iv_bottom_menu_keyboard.setOnClickListener(this);
        iv_bottom_menu_set.setOnClickListener(this);
        iv_bottom_menu_help.setOnClickListener(this);
        iv_bottom_menu_file.setOnClickListener(this);
        iv_bottom_menu_feedback.setOnClickListener(this);

        bottomMenus.add(iv_bottom_menu_keyboard);
        bottomMenus.add(iv_bottom_menu_set);
        bottomMenus.add(iv_bottom_menu_help);
        bottomMenus.add(iv_bottom_menu_file);
        bottomMenus.add(iv_bottom_menu_feedback);

        initAnim();
    }

    private void initAnim() {
        animShow = AnimationUtils.loadAnimation(this, R.anim.close);
        //设置为线性旋转
        animShow.setInterpolator(new LinearInterpolator());
        animShow.setFillAfter(!animShow.getFillAfter());

        animClose = AnimationUtils.loadAnimation(this, R.anim.show);
        animClose.setInterpolator(new LinearInterpolator());
        animClose.setFillAfter(!animClose.getFillAfter());
    }

    private void initSocketTcp(String host, Integer port) {
        nettyClient = new NettyClient(host, port);
        if (!nettyClient.getConnectStatus()) {
            nettyClient.setListener(MainActivity.this);
            nettyClient.connect();
        } else {
            nettyClient.disconnect();
        }
    }

    @Override
    public void onMessageResponse(Object msg) {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        result.release();
        final String ss = new String(result1);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                GracefullyToast.show(MainActivity.this, "接收成功" + ss);
            }
        });
    }

    @Override
    public void onServiceStatusConnectChanged(final ConnectStatusEnum status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (ConnectStatusEnum.STATUS_CONNECT_SUCCESS.equals(status)) {
                    Log.e(TAG, "STATUS_CONNECT_SUCCESS:");
                    if (nettyClient.getConnectStatus()) {
                        GracefullyToast.show(MainActivity.this, "连接成功");
                    }
                } else {
                    Log.e(TAG, "onServiceStatusConnectChanged:" + status);
                    if (!nettyClient.getConnectStatus()) {
                        GracefullyToast.show(MainActivity.this, "网路不好，正在重连");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bottom_menu:
                if (isOpenMenu) {
                    closeSectorMenu();
                    isOpenMenu = false;
                    iv_bottom_menu.startAnimation(animShow);
                } else {
                    showSectorMenu();
                    isOpenMenu = true;
                    iv_bottom_menu.startAnimation(animClose);
                }
                break;
            case R.id.iv_bottom_menu_keyboard:
                ll_keyboard.setVisibility(View.VISIBLE);

                closeSectorMenu();
                break;
            case R.id.iv_bottom_menu_set:
            case R.id.iv_bottom_menu_help:
            case R.id.iv_bottom_menu_file:
            case R.id.iv_bottom_menu_feedback:
                closeSectorMenu();
                break;
            case R.id.btn_line1_15 :
                ll_keyboard.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void showSectorMenu() {
        for (int i = 0; i < bottomMenus.size(); i++) {
            PointF point = new PointF();
            int avgAngle = (90 / (bottomMenus.size() - 1));
            int angle = avgAngle * i;
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius;
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius;

            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(bottomMenus.get(i), "translationX", 0, point.x);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(bottomMenus.get(i), "translationY", 0, point.y);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.play(objectAnimatorX).with(objectAnimatorY);
            animatorSet.addListener(new AnimatorListener(bottomMenus.get(i)));
            animatorSet.start();
        }
    }

    private void closeSectorMenu() {
        if (isOpenMenu) {
            for (int i = 0; i < bottomMenus.size(); i++) {
                PointF point = new PointF();
                int avgAngle = (90 / (bottomMenus.size() - 1));
                int angle = avgAngle * i;
                point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius;
                point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius;

                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(bottomMenus.get(i), "translationX", point.x, 0);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(bottomMenus.get(i), "translationY", point.y, 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(300);
                animatorSet.play(objectAnimatorX).with(objectAnimatorY);

                animatorSet.addListener(new AnimatorListener(bottomMenus.get(i)));
                animatorSet.start();
            }

            isOpenMenu = false;
            iv_bottom_menu.startAnimation(animShow);
        }
    }

    @Override
    public void onSingleClick(PositionDTO positionDTO) {
        Log.d(TAG, "onSingleClick， positionX: " + positionDTO.getPositionX() + ", positionY: " + positionDTO.getPositionY());
        TouchReqVO reqVO = new TouchReqVO();
        reqVO.setBeforePosition(positionDTO);
        reqVO.setCurPosition(positionDTO);
        reqVO.setType(TouchTypeEnum.SINGLE_CLICK);
        VO vo = new VO(BehaviorEnum.CONTROL_MOUSE, reqVO);
        sendToPC(vo);
    }

    @Override
    public void onLongPress(PositionDTO positionDTO) {
        Log.d(TAG, "onLongPress， positionX: " + positionDTO.getPositionX() + ", positionY: " + positionDTO.getPositionY());
        TouchReqVO reqVO = new TouchReqVO();
        reqVO.setBeforePosition(positionDTO);
        reqVO.setCurPosition(positionDTO);
        reqVO.setType(TouchTypeEnum.LONG_PRESS);
        VO vo = new VO(BehaviorEnum.CONTROL_MOUSE, reqVO);
        sendToPC(vo);
    }

    @Override
    public void onMove(PositionDTO beforePosition, PositionDTO afterPosition) {
        Log.d(TAG, "onMove， beforePositionX: " + beforePosition.getPositionX() + ", beforePositionY: " + beforePosition.getPositionY()
                + ", afterPositionX: " + afterPosition.getPositionX() + ", afterPositionY: " + afterPosition.getPositionY());
        TouchReqVO reqVO = new TouchReqVO();
        reqVO.setBeforePosition(beforePosition);
        reqVO.setCurPosition(afterPosition);
        reqVO.setType(TouchTypeEnum.MOVE);
        VO vo = new VO(BehaviorEnum.CONTROL_MOUSE, reqVO);
        sendToPC(vo);
    }

    private void sendToPC(final VO request) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                nettyClient.sendMsgToServer(JSON.toJSONString(request), new ChannelFutureListener() {
                    @Override
                    public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GracefullyToast.show(MainActivity.this, channelFuture.isSuccess() + "");
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nettyClient.disconnect();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("exit-pool-%d").daemon(true).build());

    private void exitBy2Click() {
        if (!isExit) {
            GracefullyToast.show(this, "click again to exit.");
            isExit = true;
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2, 1, TimeUnit.SECONDS);
        } else {
            ActivityUtil.finishAll();
        }
    }


    class AnimatorListener implements Animator.AnimatorListener {
        private ImageView imageView;

        AnimatorListener(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onAnimationStart(Animator animator) {
            imageView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            if (isOpenMenu) {
            } else {
                imageView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }


}

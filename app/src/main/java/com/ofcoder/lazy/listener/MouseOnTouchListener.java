package com.ofcoder.lazy.listener;

import android.view.MotionEvent;
import android.view.View;

import com.ofcoder.lazy.domain.dto.PositionDTO;
import com.ofcoder.lazy.listener.callback.TouchCallbackListener;

import java.util.Calendar;

/**
 * Created by ofcoder on 2019/2/19.
 */
public class MouseOnTouchListener implements View.OnTouchListener {
    private static final int OFFSET_DISTANCE = 5;
    private static final long CLICK_SPACING_TIME = 300;

    private long downClickTime;
    private int curPositionY;
    private int curPositionX;
    private int downPositionX;
    private int downPositionY;
    private int moveBeforeX = 0;
    private int moveBeforeY = 0;

    private TouchCallbackListener listener;

    public MouseOnTouchListener(TouchCallbackListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //当前的点
        curPositionX = (int) event.getRawX();
        curPositionY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录Down下时的坐标
                downPositionX = curPositionX;
                downPositionY = curPositionY;
                //记录当前点击的时间
                downClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                if (moveBeforeX == 0 && moveBeforeY == 0) {
                    moveBeforeX = downPositionX;
                    moveBeforeY = downPositionY;
                } else {
//                        int distance_X = curPositionX - moveBeforeX;
//                        int distance_Y = curPositionY - moveBeforeY;
                    PositionDTO before = new PositionDTO(moveBeforeX, moveBeforeY);
                    PositionDTO after = new PositionDTO(curPositionX, curPositionY);
                    listener.onMove(before, after);
                    moveBeforeX = (int) event.getRawX();
                    moveBeforeY = (int) event.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:
                moveBeforeX = 0;
                moveBeforeY = 0;

                if (!isMove()) {
                    PositionDTO after = new PositionDTO(curPositionX, curPositionY);
                    if (Calendar.getInstance().getTimeInMillis() - downClickTime <= CLICK_SPACING_TIME) {
                        listener.onSingleClick(after);
                    } else {
                        listener.onLongPress(after);
                    }
                } else {
                    //do nothing...
                }
                break;
            default:
                break;
        }
        return true;
    }

    private boolean isMove() {
        if (Math.abs(downPositionX - curPositionX) <= OFFSET_DISTANCE && Math.abs(downPositionY - curPositionY) <= OFFSET_DISTANCE) {
            return false;
        } else {
            return true;
        }
    }

}

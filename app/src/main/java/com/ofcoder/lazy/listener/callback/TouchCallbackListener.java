package com.ofcoder.lazy.listener.callback;

import com.ofcoder.lazy.domain.dto.PositionDTO;

/**
 * Created by ofcoder on 2019/2/19.
 */
public interface TouchCallbackListener {
    void onSingleClick(PositionDTO positionDTO);

    void onLongPress(PositionDTO positionDTO);

    void onMove(PositionDTO beforePosition,
                PositionDTO afterPosition);
}

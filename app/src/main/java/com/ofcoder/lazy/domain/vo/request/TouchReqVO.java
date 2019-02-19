package com.ofcoder.lazy.domain.vo.request;

import com.ofcoder.lazy.domain.dto.PositionDTO;
import com.ofcoder.lazy.domain.enums.TouchTypeEnum;

/**
 * Created by ofcoder on 2019/2/19.
 */
public class TouchReqVO {
    private PositionDTO beforePosition;
    private PositionDTO curPosition;
    private TouchTypeEnum type;

    public PositionDTO getBeforePosition() {
        return beforePosition;
    }

    public void setBeforePosition(PositionDTO beforePosition) {
        this.beforePosition = beforePosition;
    }

    public PositionDTO getCurPosition() {
        return curPosition;
    }

    public void setCurPosition(PositionDTO curPosition) {
        this.curPosition = curPosition;
    }

    public TouchTypeEnum getType() {
        return type;
    }

    public void setType(TouchTypeEnum type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TouchReqVO reqVO = (TouchReqVO) o;

        if (beforePosition != null ? !beforePosition.equals(reqVO.beforePosition) : reqVO.beforePosition != null)
            return false;
        if (curPosition != null ? !curPosition.equals(reqVO.curPosition) : reqVO.curPosition != null)
            return false;
        return type == reqVO.type;
    }

    @Override
    public int hashCode() {
        int result = beforePosition != null ? beforePosition.hashCode() : 0;
        result = 31 * result + (curPosition != null ? curPosition.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TouchReqVO{" +
                "beforePosition=" + beforePosition +
                ", curPosition=" + curPosition +
                ", type=" + type +
                '}';
    }
}

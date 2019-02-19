package com.ofcoder.lazy.domain.dto;

/**
 * Created by ofcoder on 2019/2/19.
 */
public class PositionDTO {
    private Integer positionX;
    private Integer positionY;

    public PositionDTO(Integer positionX, Integer positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PositionDTO that = (PositionDTO) o;

        if (positionX != null ? !positionX.equals(that.positionX) : that.positionX != null){
            return false;
        }
        return positionY != null ? positionY.equals(that.positionY) : that.positionY == null;
    }

    @Override
    public int hashCode() {
        int result = positionX != null ? positionX.hashCode() : 0;
        result = 31 * result + (positionY != null ? positionY.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PositionDTO{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}

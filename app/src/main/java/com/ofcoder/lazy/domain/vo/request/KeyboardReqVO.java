package com.ofcoder.lazy.domain.vo.request;

/**
 * Created by ofcoder on 2019/2/19.
 */
public class KeyboardReqVO {
    private String action;
    private String key;

    public KeyboardReqVO(String action, String key) {
        this.action = action;
        this.key = key;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyboardReqVO that = (KeyboardReqVO) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KeyboardReqVO{" +
                "action='" + action + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

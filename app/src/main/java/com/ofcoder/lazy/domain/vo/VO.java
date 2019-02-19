package com.ofcoder.lazy.domain.vo;

import com.ofcoder.lazy.domain.enums.BehaviorEnum;
import com.ofcoder.lazy.domain.enums.ConveyStatusEnum;

import java.io.Serializable;

/**
 * Created by ofcoder on 2019/2/18.
 */
public class VO implements Serializable {
    private static final long serialVersionUID = 5704957411985783570L;

    private String code;
    private String msg;
    private BehaviorEnum behavior;
    private Object data;


    public VO(BehaviorEnum behavior, Object data) {
        this.code = ConveyStatusEnum.SUCCESS.getCode();
        this.msg = ConveyStatusEnum.SUCCESS.getMsg();
        this.behavior = behavior;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BehaviorEnum getBehavior() {
        return behavior;
    }

    public void setBehavior(BehaviorEnum behavior) {
        this.behavior = behavior;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VO vo = (VO) o;

        if (code != null ? !code.equals(vo.code) : vo.code != null) return false;
        if (msg != null ? !msg.equals(vo.msg) : vo.msg != null) return false;
        if (behavior != vo.behavior) return false;
        return data != null ? data.equals(vo.data) : vo.data == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (behavior != null ? behavior.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", behavior=" + behavior +
                ", data=" + data +
                '}';
    }
}

package com.ofcoder.lazy.domain.enums;

/**
 * Created by ofcoder on 2019/2/19.
 */
public enum ConveyStatusEnum {
    SUCCESS("00", "success");

    private String code;
    private String msg;

    ConveyStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }
}

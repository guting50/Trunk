package com.zhiluo.android.yunpu.entity.eventbus;

/**
 * EventBus
 */
public class MessageEvent {
    private String msg;
    private String type;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

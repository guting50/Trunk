package com.zhiluo.android.yunpu.member.jsonbean;

/**
 * 上传会员头像响应结果javaBean
 * 作者：罗咏哲 on 2017/6/20 17:49.
 * 邮箱：137615198@qq.com
 */

public class MemberPhotoBean {

    /**
     * success : true
     * code : null
     * msg : null
     * data : /FileUpload/131424250572488843.jpg
     */

    private boolean success;
    private Object code;
    private Object msg;
    private String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MemberPhotoBean{" +
                "success=" + success +
                ", code=" + code +
                ", msg=" + msg +
                ", data='" + data + '\'' +
                '}';
    }
}

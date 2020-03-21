package com.zhiluo.android.yunpu.member.jsonbean;

/**
 * Created by Administrator on 2016/9/23.
 */
public class Adduserbean
{
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"5358dd0c-7c97-48ad-bebc-41e11c83970d","VG_GID":"默认等级","VT_Code":"IntegerFulfil","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":null,"VIP_Email":"",
     * "VIP_ICCard":"","VIP_Creator":"yinshangyong93@163.com","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"",
     * "VIP_UpdateTime":"2016-09-23 09:55:34","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":"0b6c2615-c5c8-458b-99dc-8643cf602453","VIP_HeadPicture":null,
     * "SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"2345","VCH_Fee":0,"VCH_Pwd":null,"VIP_Addr":null,"MA_AggregateAmount":0,
     * "MA_AvailableIntegral":0,"VCH_CreateTime":null,"VT_Name":null}
     */
    private boolean success;
    private Object code;
    private String msg;

    /**
     * GID : 5358dd0c-7c97-48ad-bebc-41e11c83970d
     * VG_GID : 默认等级
     * VT_Code : IntegerFulfil
     * VIP_Name : null
     * VIP_CellPhone : null
     * VIP_Birthday : null
     * VIP_Email :
     * VIP_ICCard :
     * VIP_Creator : yinshangyong93@163.com
     * EM_ID :
     * VIP_Overdue : null
     * VIP_IsForver : 1
     * VIP_State : 0
     * VIP_Sex : 0
     * VIP_FixedPhone :
     * VIP_UpdateTime : 2016-09-23 09:55:34
     * VIP_Referee :
     * VIP_FaceNumber :
     * VIP_Remark :
     * CY_GID : 0b6c2615-c5c8-458b-99dc-8643cf602453
     * VIP_HeadPicture : null
     * SM_ID : a07d8272-4c2e-4590-aca6-e92abc46e1a6
     * VIP_IsDeleted : 0
     * VIP_Percent : 0
     * VCH_Card : 2345
     * VCH_Fee : 0
     * VCH_Pwd : null
     * VIP_Addr : null
     * MA_AggregateAmount : 0.0
     * MA_AvailableIntegral : 0.0
     * VCH_CreateTime : null
     * VT_Name : null
     */

    //  private DataBean data;
    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public Object getCode()
    {
        return code;
    }

    public void setCode(Object code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}

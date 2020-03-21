package com.zhiluo.android.yunpu.member.jsonbean;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class VipDetailList implements Collection<VipDetailList> {
    private String GID;

    private Object VG_GID;
    private Object VT_Code;
    private String VIP_Name;
    private Object VIP_CellPhone;
    private String VIP_Birthday;
    private Object VIP_Email;
    private String EM_ID;
    private Object VIP_Overdue;
    private int VIP_IsForver;
    private String VIP_State;
    private String VIP_Sex;
    private Object VIP_FixedPhone;
    private String VIP_UpdateTime;
    private Object VIP_Referee;
    private Object VIP_FaceNumber;
    private Object VIP_Remark;
    private Object CY_GID;
    private Object VIP_HeadPicture;
    private Object SM_ID;
    private int VIP_IsDeleted;
    private int VIP_Percent;
    private String VCH_Card;
    private int VCH_Fee;
    private Object VG_Name;
    private Object VGC_Code;
    private Object US_Value;
    private Object VT_Name;
    private int VCH_Type;
    private String VCH_UpdateTime;
    private String MA_AccountBalance;
    private String MA_AggregateAmount;
    private String MA_AvailableBalance;
    private String MA_AvailableIntegral;
    private String MA_FreezeBalance;
    private Object VIP_Addr;
    private String SM_Name;
    private String VCH_CreateTime;
    private String VIP_Creator;
    private Object VCH_Pwd;
    private Object VIP_ICCard;
    private Object US_Name;
    private boolean IS_CHECK;
    public VipDetailList() {
    }

    public VipDetailList(String GID, Object VG_GID, Object VT_Code, String VIP_Name, Object VIP_CellPhone, String VIP_Birthday, Object VIP_Email, String EM_ID, Object VIP_Overdue, int VIP_IsForver, String VIP_State, String VIP_Sex, Object VIP_FixedPhone, String VIP_UpdateTime, Object VIP_Referee, Object VIP_FaceNumber, Object VIP_Remark, Object CY_GID, Object VIP_HeadPicture, Object SM_ID, int VIP_IsDeleted, int VIP_Percent, String VCH_Card, int VCH_Fee, Object VG_Name, Object VGC_Code, Object US_Value, Object VT_Name, int VCH_Type, String VCH_UpdateTime, String MA_AccountBalance, String MA_AggregateAmount, String MA_AvailableBalance, String MA_AvailableIntegral, String MA_FreezeBalance, Object VIP_Addr, String SM_Name, String VCH_CreateTime, String VIP_Creator, Object VCH_Pwd, Object VIP_ICCard, Object US_Name) {
        this.GID = GID;
        this.VG_GID = VG_GID;
        this.VT_Code = VT_Code;
        this.VIP_Name = VIP_Name;
        this.VIP_CellPhone = VIP_CellPhone;
        this.VIP_Birthday = VIP_Birthday;
        this.VIP_Email = VIP_Email;
        this.EM_ID = EM_ID;
        this.VIP_Overdue = VIP_Overdue;
        this.VIP_IsForver = VIP_IsForver;
        this.VIP_State = VIP_State;
        this.VIP_Sex = VIP_Sex;
        this.VIP_FixedPhone = VIP_FixedPhone;
        this.VIP_UpdateTime = VIP_UpdateTime;
        this.VIP_Referee = VIP_Referee;
        this.VIP_FaceNumber = VIP_FaceNumber;
        this.VIP_Remark = VIP_Remark;
        this.CY_GID = CY_GID;
        this.VIP_HeadPicture = VIP_HeadPicture;
        this.SM_ID = SM_ID;
        this.VIP_IsDeleted = VIP_IsDeleted;
        this.VIP_Percent = VIP_Percent;
        this.VCH_Card = VCH_Card;
        this.VCH_Fee = VCH_Fee;
        this.VG_Name = VG_Name;
        this.VGC_Code = VGC_Code;
        this.US_Value = US_Value;
        this.VT_Name = VT_Name;
        this.VCH_Type = VCH_Type;
        this.VCH_UpdateTime = VCH_UpdateTime;
        this.MA_AccountBalance = MA_AccountBalance;
        this.MA_AggregateAmount = MA_AggregateAmount;
        this.MA_AvailableBalance = MA_AvailableBalance;
        this.MA_AvailableIntegral = MA_AvailableIntegral;
        this.MA_FreezeBalance = MA_FreezeBalance;
        this.VIP_Addr = VIP_Addr;
        this.SM_Name = SM_Name;
        this.VCH_CreateTime = VCH_CreateTime;
        this.VIP_Creator = VIP_Creator;
        this.VCH_Pwd = VCH_Pwd;
        this.VIP_ICCard = VIP_ICCard;
        this.US_Name = US_Name;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public Object getVG_GID() {
        return VG_GID;
    }

    public void setVG_GID(Object VG_GID) {
        this.VG_GID = VG_GID;
    }

    public Object getVT_Code() {
        return VT_Code;
    }

    public void setVT_Code(Object VT_Code) {
        this.VT_Code = VT_Code;
    }

    public String getVIP_Name() {
        return VIP_Name;
    }

    public void setVIP_Name(String VIP_Name) {
        this.VIP_Name = VIP_Name;
    }

    public Object getVIP_CellPhone() {
        return VIP_CellPhone;
    }

    public void setVIP_CellPhone(Object VIP_CellPhone) {
        this.VIP_CellPhone = VIP_CellPhone;
    }

    public String getVIP_Birthday() {
        return VIP_Birthday;
    }

    public void setVIP_Birthday(String VIP_Birthday) {
        this.VIP_Birthday = VIP_Birthday;
    }

    public Object getVIP_Email() {
        return VIP_Email;
    }

    public void setVIP_Email(Object VIP_Email) {
        this.VIP_Email = VIP_Email;
    }

    public String getEM_ID() {
        return EM_ID;
    }

    public void setEM_ID(String EM_ID) {
        this.EM_ID = EM_ID;
    }

    public Object getVIP_Overdue() {
        return VIP_Overdue;
    }

    public void setVIP_Overdue(Object VIP_Overdue) {
        this.VIP_Overdue = VIP_Overdue;
    }

    public int getVIP_IsForver() {
        return VIP_IsForver;
    }

    public void setVIP_IsForver(int VIP_IsForver) {
        this.VIP_IsForver = VIP_IsForver;
    }

    public String getVIP_State() {
        return VIP_State;
    }

    public void setVIP_State(String VIP_State) {
        this.VIP_State = VIP_State;
    }

    public String getVIP_Sex() {
        return VIP_Sex;
    }

    public void setVIP_Sex(String VIP_Sex) {
        this.VIP_Sex = VIP_Sex;
    }

    public Object getVIP_FixedPhone() {
        return VIP_FixedPhone;
    }

    public void setVIP_FixedPhone(Object VIP_FixedPhone) {
        this.VIP_FixedPhone = VIP_FixedPhone;
    }

    public String getVIP_UpdateTime() {
        return VIP_UpdateTime;
    }

    public void setVIP_UpdateTime(String VIP_UpdateTime) {
        this.VIP_UpdateTime = VIP_UpdateTime;
    }

    public Object getVIP_Referee() {
        return VIP_Referee;
    }

    public void setVIP_Referee(Object VIP_Referee) {
        this.VIP_Referee = VIP_Referee;
    }

    public Object getVIP_FaceNumber() {
        return VIP_FaceNumber;
    }

    public void setVIP_FaceNumber(Object VIP_FaceNumber) {
        this.VIP_FaceNumber = VIP_FaceNumber;
    }

    public Object getVIP_Remark() {
        return VIP_Remark;
    }

    public void setVIP_Remark(Object VIP_Remark) {
        this.VIP_Remark = VIP_Remark;
    }

    public Object getCY_GID() {
        return CY_GID;
    }

    public void setCY_GID(Object CY_GID) {
        this.CY_GID = CY_GID;
    }

    public Object getVIP_HeadPicture() {
        return VIP_HeadPicture;
    }

    public void setVIP_HeadPicture(Object VIP_HeadPicture) {
        this.VIP_HeadPicture = VIP_HeadPicture;
    }

    public Object getSM_ID() {
        return SM_ID;
    }

    public void setSM_ID(Object SM_ID) {
        this.SM_ID = SM_ID;
    }

    public int getVIP_IsDeleted() {
        return VIP_IsDeleted;
    }

    public void setVIP_IsDeleted(int VIP_IsDeleted) {
        this.VIP_IsDeleted = VIP_IsDeleted;
    }

    public int getVIP_Percent() {
        return VIP_Percent;
    }

    public void setVIP_Percent(int VIP_Percent) {
        this.VIP_Percent = VIP_Percent;
    }

    public String getVCH_Card() {
        return VCH_Card;
    }

    public void setVCH_Card(String VCH_Card) {
        this.VCH_Card = VCH_Card;
    }

    public int getVCH_Fee() {
        return VCH_Fee;
    }

    public void setVCH_Fee(int VCH_Fee) {
        this.VCH_Fee = VCH_Fee;
    }

    public Object getVG_Name() {
        return VG_Name;
    }

    public void setVG_Name(Object VG_Name) {
        this.VG_Name = VG_Name;
    }

    public Object getVGC_Code() {
        return VGC_Code;
    }

    public void setVGC_Code(Object VGC_Code) {
        this.VGC_Code = VGC_Code;
    }

    public Object getUS_Value() {
        return US_Value;
    }

    public void setUS_Value(Object US_Value) {
        this.US_Value = US_Value;
    }

    public Object getVT_Name() {
        return VT_Name;
    }

    public void setVT_Name(Object VT_Name) {
        this.VT_Name = VT_Name;
    }

    public int getVCH_Type() {
        return VCH_Type;
    }

    public void setVCH_Type(int VCH_Type) {
        this.VCH_Type = VCH_Type;
    }

    public String getVCH_UpdateTime() {
        return VCH_UpdateTime;
    }

    public void setVCH_UpdateTime(String VCH_UpdateTime) {
        this.VCH_UpdateTime = VCH_UpdateTime;
    }

    public String getMA_AccountBalance() {
        return MA_AccountBalance;
    }

    public void setMA_AccountBalance(String MA_AccountBalance) {
        this.MA_AccountBalance = MA_AccountBalance;
    }

    public String getMA_AggregateAmount() {
        return MA_AggregateAmount;
    }

    public void setMA_AggregateAmount(String MA_AggregateAmount) {
        this.MA_AggregateAmount = MA_AggregateAmount;
    }

    public String getMA_AvailableBalance() {
        return MA_AvailableBalance;
    }

    public void setMA_AvailableBalance(String MA_AvailableBalance) {
        this.MA_AvailableBalance = MA_AvailableBalance;
    }

    public String getMA_AvailableIntegral() {
        return MA_AvailableIntegral;
    }

    public void setMA_AvailableIntegral(String MA_AvailableIntegral) {
        this.MA_AvailableIntegral = MA_AvailableIntegral;
    }

    public String getMA_FreezeBalance() {
        return MA_FreezeBalance;
    }

    public void setMA_FreezeBalance(String MA_FreezeBalance) {
        this.MA_FreezeBalance = MA_FreezeBalance;
    }

    public Object getVIP_Addr() {
        return VIP_Addr;
    }

    public void setVIP_Addr(Object VIP_Addr) {
        this.VIP_Addr = VIP_Addr;
    }

    public String getSM_Name() {
        return SM_Name;
    }

    public void setSM_Name(String SM_Name) {
        this.SM_Name = SM_Name;
    }

    public String getVCH_CreateTime() {
        return VCH_CreateTime;
    }

    public void setVCH_CreateTime(String VCH_CreateTime) {
        this.VCH_CreateTime = VCH_CreateTime;
    }

    public String getVIP_Creator() {
        return VIP_Creator;
    }

    public void setVIP_Creator(String VIP_Creator) {
        this.VIP_Creator = VIP_Creator;
    }

    public Object getVCH_Pwd() {
        return VCH_Pwd;
    }

    public void setVCH_Pwd(Object VCH_Pwd) {
        this.VCH_Pwd = VCH_Pwd;
    }

    public Object getVIP_ICCard() {
        return VIP_ICCard;
    }

    public void setVIP_ICCard(Object VIP_ICCard) {
        this.VIP_ICCard = VIP_ICCard;
    }

    public Object getUS_Name() {
        return US_Name;
    }

    public void setUS_Name(Object US_Name) {
        this.US_Name = US_Name;
    }

    public boolean IS_CHECK() {
        return IS_CHECK;
    }

    public void setIS_CHECK(boolean IS_CHECK) {
        this.IS_CHECK = IS_CHECK;
    }

    @Override
    public boolean add(VipDetailList object) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends VipDetailList> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NonNull
    @Override
    public Iterator<VipDetailList> iterator() {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return null;
    }
}

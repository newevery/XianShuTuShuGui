package cn.com.sino_device.xianshutushugui.bean.user;

import java.util.Date;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/14
 */
public class UserDeposit {

    /**
     * 用户账号
     */
    String userId;

    /**
     * 手机
     */
    String mobile;

    /**
     * 押金实际金额
     */
    Double deposit;

    /**
     * 交易方式
     */
    String dealType;

    /**
     * 最后充值时间
     */
    Date lastTime;

    /**
     * 手续费用
     */
    Double charge;

    /**
     * 操作人员Id(本人充值就是userId)
     */
    String operatorId;

    /**
     * 操作时间
     */
    String operatorTime;

    /**
     * 操作IP
     */
    String operatorIP;

    /**
     * 备注
     */
    String remarks;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorIP() {
        return operatorIP;
    }

    public void setOperatorIP(String operatorIP) {
        this.operatorIP = operatorIP;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", deposit:" + deposit +
                ", dealType:'" + dealType + '\'' +
                ", lastTime:" + lastTime +
                ", charge:" + charge +
                ", operatorId:'" + operatorId + '\'' +
                ", operatorTime:'" + operatorTime + '\'' +
                ", operatorIP:'" + operatorIP + '\'' +
                ", remarks:'" + remarks + '\'' +
                '}';
    }
}

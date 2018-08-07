package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link Result}
 *
 * @author affe
 * @date 2018/6/26
 */
public class UserPay {

//    String usermobilenum;
//    String imei;
//    String type;
//    String areacode;

    /**
     * 用户账号
     */
    String userId;

    /**
     * 交易方式
     */
    String dealType;

    /**
     * 交易时间
     */
    String dealTime;

    /**
     * 交易金额
     */
    double dealAmount;

    /**
     * 备注
     */
    String remarks;

    /**
     * 手续费用
     */
    double charge;

    /**
     * 操作人员
     */
    String IdoperatorId;

    /**
     * 操作时间
     */
    String operatorTime;

    /**
     * 操作
     */
    String IPoperatorIP;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public String getIdoperatorId() {
        return IdoperatorId;
    }

    public void setIdoperatorId(String idoperatorId) {
        IdoperatorId = idoperatorId;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getIPoperatorIP() {
        return IPoperatorIP;
    }

    public void setIPoperatorIP(String IPoperatorIP) {
        this.IPoperatorIP = IPoperatorIP;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", dealType:'" + dealType + '\'' +
                ", dealTime:'" + dealTime + '\'' +
                ", dealAmount:" + dealAmount +
                ", remarks:'" + remarks + '\'' +
                ", charge:" + charge +
                ", IdoperatorId:'" + IdoperatorId + '\'' +
                ", operatorTime:'" + operatorTime + '\'' +
                ", IPoperatorIP:'" + IPoperatorIP + '\'' +
                '}';
    }
}

package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 买票订单表
* @TableName train_order
*/
public class TrainOrder implements Serializable {

    /**
    * 订单ID
    */
    private Integer orderid;
    /**
    * 账号
    */
    private String userid;
    /**
    * 车次名
    */
    private String number;
    /**
    * 出发站点名称
    */
    private String startstationid;
    /**
    * 终点站点名称
    */
    private String endstationid;
    /**
    * 余票
    */
    private Integer num;
    /**
    * 距离出发站点的票价
    */
    private Integer money;
    /**
    * 买票时间
    */
    private Date createtime;
    /**
    * 状态
    */
    private String state;
    /**
    * 备注
    */
    private String info;

    /**
    * 订单ID
    */
    public void setOrderid(Integer orderid){
    this.orderid = orderid;
    }

    /**
    * 账号
    */
    public void setUserid(String userid){
    this.userid = userid;
    }

    /**
    * 车次名
    */
    public void setNumber(String number){
    this.number = number;
    }

    /**
    * 出发站点名称
    */
    public void setStartstationid(String startstationid){
    this.startstationid = startstationid;
    }

    /**
    * 终点站点名称
    */
    public void setEndstationid(String endstationid){
    this.endstationid = endstationid;
    }

    /**
    * 余票
    */
    public void setNum(Integer num){
    this.num = num;
    }

    /**
    * 距离出发站点的票价
    */
    public void setMoney(Integer money){
    this.money = money;
    }

    /**
    * 买票时间
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }

    /**
    * 状态
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 备注
    */
    public void setInfo(String info){
    this.info = info;
    }


    /**
    * 订单ID
    */
    public Integer getOrderid(){
    return this.orderid;
    }

    /**
    * 账号
    */
    public String getUserid(){
    return this.userid;
    }

    /**
    * 车次名
    */
    public String getNumber(){
    return this.number;
    }

    /**
    * 出发站点名称
    */
    public String getStartstationid(){
    return this.startstationid;
    }

    /**
    * 终点站点名称
    */
    public String getEndstationid(){
    return this.endstationid;
    }

    /**
    * 余票
    */
    public Integer getNum(){
    return this.num;
    }

    /**
    * 距离出发站点的票价
    */
    public Integer getMoney(){
    return this.money;
    }

    /**
    * 买票时间
    */
    public Date getCreatetime(){
    return this.createtime;
    }

    /**
    * 状态
    */
    public String getState(){
    return this.state;
    }

    /**
    * 备注
    */
    public String getInfo(){
    return this.info;
    }

}

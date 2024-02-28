package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 车次表
* @TableName train_number
*/
public class TrainNumber implements Serializable {

    /**
    * 车次名
    */
    private String number;
    /**
    * 开车时间
    */
    private Date starttime;
    /**
    * 终点时间
    */
    private Date endtime;
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
    * 票价
    */
    private Integer money;
    /**
    * 车次类型
    */
    private Integer ntid;
    /**
    * 备注
    */
    private String info;

    /**
    * 车次名
    */
    public void setNumber(String number){
    this.number = number;
    }

    /**
    * 开车时间
    */
    public void setStarttime(Date starttime){
    this.starttime = starttime;
    }

    /**
    * 终点时间
    */
    public void setEndtime(Date endtime){
    this.endtime = endtime;
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
    * 票价
    */
    public void setMoney(Integer money){
    this.money = money;
    }

    /**
    * 车次类型
    */
    public void setNtid(Integer ntid){
    this.ntid = ntid;
    }

    /**
    * 备注
    */
    public void setInfo(String info){
    this.info = info;
    }


    /**
    * 车次名
    */
    public String getNumber(){
    return this.number;
    }

    /**
    * 开车时间
    */
    public Date getStarttime(){
    return this.starttime;
    }

    /**
    * 终点时间
    */
    public Date getEndtime(){
    return this.endtime;
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
    * 票价
    */
    public Integer getMoney(){
    return this.money;
    }

    /**
    * 车次类型
    */
    public Integer getNtid(){
    return this.ntid;
    }

    /**
    * 备注
    */
    public String getInfo(){
    return this.info;
    }

}

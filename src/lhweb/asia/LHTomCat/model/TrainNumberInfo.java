package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 车次详情表
* @TableName train_number_info
*/
public class TrainNumberInfo implements Serializable {

    /**
    * 车次详情ID
    */
    private Integer infoid;
    /**
    * 车次名
    */
    private String number;
    /**
    * 车站顺序
    */
    private Integer stationorder;
    /**
    * 站点名称
    */
    private String stationid;
    /**
    * 到站时间
    */
    private Date arrivetime;
    /**
    * 离站时间
    */
    private Date leavetime;
    /**
    * 余票
    */
    private Integer num;
    /**
    * 距离出发站点的票价
    */
    private Integer money;
    /**
    * 备注
    */
    private String info;

    /**
    * 车次详情ID
    */
    public void setInfoid(Integer infoid){
    this.infoid = infoid;
    }

    /**
    * 车次名
    */
    public void setNumber(String number){
    this.number = number;
    }

    /**
    * 车站顺序
    */
    public void setStationorder(Integer stationorder){
    this.stationorder = stationorder;
    }

    /**
    * 站点名称
    */
    public void setStationid(String stationid){
    this.stationid = stationid;
    }

    /**
    * 到站时间
    */
    public void setArrivetime(Date arrivetime){
    this.arrivetime = arrivetime;
    }

    /**
    * 离站时间
    */
    public void setLeavetime(Date leavetime){
    this.leavetime = leavetime;
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
    * 备注
    */
    public void setInfo(String info){
    this.info = info;
    }


    /**
    * 车次详情ID
    */
    public Integer getInfoid(){
    return this.infoid;
    }

    /**
    * 车次名
    */
    public String getNumber(){
    return this.number;
    }

    /**
    * 车站顺序
    */
    public Integer getStationorder(){
    return this.stationorder;
    }

    /**
    * 站点名称
    */
    public String getStationid(){
    return this.stationid;
    }

    /**
    * 到站时间
    */
    public Date getArrivetime(){
    return this.arrivetime;
    }

    /**
    * 离站时间
    */
    public Date getLeavetime(){
    return this.leavetime;
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
    * 备注
    */
    public String getInfo(){
    return this.info;
    }

}

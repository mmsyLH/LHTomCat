package lhweb.asia.LHTomCat.model;

import java.io.Serializable;

/**
* 车站
* @TableName train_station
*/
public class TrainStation implements Serializable {

    /**
    * 站点名
    */
    private String stationid;
    /**
    * 车站名称的拼音
    */
    private String stationpy;
    /**
    * 备注
    */
    private String stationinfo;

    /**
    * 站点名
    */
    public void setStationid(String stationid){
    this.stationid = stationid;
    }

    /**
    * 车站名称的拼音
    */
    public void setStationpy(String stationpy){
    this.stationpy = stationpy;
    }

    /**
    * 备注
    */
    public void setStationinfo(String stationinfo){
    this.stationinfo = stationinfo;
    }


    /**
    * 站点名
    */
    public String getStationid(){
    return this.stationid;
    }

    /**
    * 车站名称的拼音
    */
    public String getStationpy(){
    return this.stationpy;
    }

    /**
    * 备注
    */
    public String getStationinfo(){
    return this.stationinfo;
    }

}

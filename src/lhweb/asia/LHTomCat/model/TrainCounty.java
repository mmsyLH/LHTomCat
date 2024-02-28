package lhweb.asia.LHTomCat.model;

import java.io.Serializable;

/**
* 区县表
* @TableName train_county
*/
public class TrainCounty implements Serializable {

    /**
    * 区县ID
    */
    private Integer countyid;
    /**
    * 区县名
    */
    private String countyname;
    /**
    * 备注
    */
    private String countyinfo;
    /**
    * 城市ID
    */
    public Integer cityid;

    /**
    * 区县ID
    */
    public void setCountyid(Integer countyid){
    this.countyid = countyid;
    }

    /**
    * 区县名
    */
    public void setCountyname(String countyname){
    this.countyname = countyname;
    }

    /**
    * 备注
    */
    public void setCountyinfo(String countyinfo){
    this.countyinfo = countyinfo;
    }

    /**
    * 城市ID
    */
    public void setCityid(Integer cityid){
    this.cityid = cityid;
    }


    /**
    * 区县ID
    */
    public Integer getCountyid(){
    return this.countyid;
    }

    /**
    * 区县名
    */
    public String getCountyname(){
    return this.countyname;
    }

    /**
    * 备注
    */
    public String getCountyinfo(){
    return this.countyinfo;
    }

    /**
    * 城市ID
    */
    public Integer getCityid(){
    return this.cityid;
    }

}

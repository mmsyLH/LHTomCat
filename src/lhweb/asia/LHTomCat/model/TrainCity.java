package lhweb.asia.LHTomCat.model;


import java.io.Serializable;

/**
* 城市表
* @TableName train_city
*/
public class TrainCity implements Serializable {

    /**
    * 城市ID
    */

    private Integer cityid;
    /**
    * 城市名
    */

    private String cityname;
    /**
    * 备注
    */

    private String cityinfo;
    /**
    * 省份ID
    */

    private Integer provinceid;

    /**
    * 城市ID
    */
    public void setCityid(Integer cityid){
    this.cityid = cityid;
    }

    /**
    * 城市名
    */
    public void setCityname(String cityname){
    this.cityname = cityname;
    }

    /**
    * 备注
    */
    public void setCityinfo(String cityinfo){
    this.cityinfo = cityinfo;
    }

    /**
    * 省份ID
    */
    public void setProvinceid(Integer provinceid){
    this.provinceid = provinceid;
    }


    /**
    * 城市ID
    */
    public Integer getCityid(){
    return this.cityid;
    }

    /**
    * 城市名
    */
    public String getCityname(){
    return this.cityname;
    }

    /**
    * 备注
    */
    public String getCityinfo(){
    return this.cityinfo;
    }

    /**
    * 省份ID
    */
    public Integer getProvinceid(){
    return this.provinceid;
    }

}

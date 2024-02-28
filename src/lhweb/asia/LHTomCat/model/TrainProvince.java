package lhweb.asia.LHTomCat.model;

import java.io.Serializable;

/**
* 省份表
* @TableName train_province
*/
public class TrainProvince implements Serializable {

    /**
    * 省份ID
    */
    private Integer provinceid;
    /**
    * 省份名
    */
    private String provincename;
    /**
    * 备注
    */
    private String provinceinfo;

    /**
    * 省份ID
    */
    public void setProvinceid(Integer provinceid){
    this.provinceid = provinceid;
    }

    /**
    * 省份名
    */
    public void setProvincename(String provincename){
    this.provincename = provincename;
    }

    /**
    * 备注
    */
    public void setProvinceinfo(String provinceinfo){
    this.provinceinfo = provinceinfo;
    }


    /**
    * 省份ID
    */
    public Integer getProvinceid(){
    return this.provinceid;
    }

    /**
    * 省份名
    */
    public String getProvincename(){
    return this.provincename;
    }

    /**
    * 备注
    */
    public String getProvinceinfo(){
    return this.provinceinfo;
    }

}

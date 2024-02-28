package lhweb.asia.LHTomCat.model;

import java.io.Serializable;



/**
* 乡镇表
* @TableName train_town
*/
public class TrainTown implements Serializable {

    /**
    * 乡镇ID
    */
    private Integer townid;
    /**
    * 乡镇名
    */
    private String townname;
    /**
    * 备注
    */
    private String towninfo;
    /**
    * 区县ID
    */
    private Integer countyid;

    /**
    * 乡镇ID
    */
    public void setTownid(Integer townid){
    this.townid = townid;
    }

    /**
    * 乡镇名
    */
    public void setTownname(String townname){
    this.townname = townname;
    }

    /**
    * 备注
    */
    public void setTowninfo(String towninfo){
    this.towninfo = towninfo;
    }

    /**
    * 区县ID
    */
    public void setCountyid(Integer countyid){
    this.countyid = countyid;
    }


    /**
    * 乡镇ID
    */
    public Integer getTownid(){
    return this.townid;
    }

    /**
    * 乡镇名
    */
    public String getTownname(){
    return this.townname;
    }

    /**
    * 备注
    */
    public String getTowninfo(){
    return this.towninfo;
    }

    /**
    * 区县ID
    */
    public Integer getCountyid(){
    return this.countyid;
    }

}

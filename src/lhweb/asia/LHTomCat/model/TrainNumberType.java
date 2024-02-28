package lhweb.asia.LHTomCat.model;

import java.io.Serializable;

/**
* 车次类型
* @TableName train_number_type
*/
public class TrainNumberType implements Serializable {

    /**
    * 车次类型ID
    */
    private Integer ntid;
    /**
    * 车次类型名称
    */
    private String ntname;
    /**
    * 备注
    */
    private String ntinfo;

    /**
    * 车次类型ID
    */
    public void setNtid(Integer ntid){
    this.ntid = ntid;
    }

    /**
    * 车次类型名称
    */
    public void setNtname(String ntname){
    this.ntname = ntname;
    }

    /**
    * 备注
    */
    public void setNtinfo(String ntinfo){
    this.ntinfo = ntinfo;
    }


    /**
    * 车次类型ID
    */
    public Integer getNtid(){
    return this.ntid;
    }

    /**
    * 车次类型名称
    */
    public String getNtname(){
    return this.ntname;
    }

    /**
    * 备注
    */
    public String getNtinfo(){
    return this.ntinfo;
    }

}

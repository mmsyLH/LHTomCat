package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 客服表
* @TableName train_admin
*/
public class TrainAdmin implements Serializable {

    /**
    * 客户ID
    */
    private String adminid;
    /**
    * 密码
    */

    private String psw;
    /**
    * 头像
    */
    private String photo;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 备注
    */
    private String info;

    /**
    * 客户ID
    */
    public void setAdminid(String adminid){
    this.adminid = adminid;
    }

    /**
    * 密码
    */
    public void setPsw(String psw){
    this.psw = psw;
    }

    /**
    * 头像
    */
    public void setPhoto(String photo){
    this.photo = photo;
    }

    /**
    * 创建时间
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }

    /**
    * 备注
    */
    public void setInfo(String info){
    this.info = info;
    }


    /**
    * 客户ID
    */
    public String getAdminid(){
    return this.adminid;
    }

    /**
    * 密码
    */
    public String getPsw(){
    return this.psw;
    }

    /**
    * 头像
    */
    public String getPhoto(){
    return this.photo;
    }

    /**
    * 创建时间
    */
    public Date getCreatetime(){
    return this.createtime;
    }

    /**
    * 备注
    */
    public String getInfo(){
    return this.info;
    }

}

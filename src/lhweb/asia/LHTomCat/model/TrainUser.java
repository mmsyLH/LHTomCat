package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 用户表
* @TableName train_user
*/
public class TrainUser implements Serializable {

    /**
    * 账号
    */
    private String userid;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 性别
    */
    private String sex;
    /**
    * 余额
    */
    private Integer money;
    /**
    * 身份证号
    */
    private String id;
    /**
    * 手机
    */
    private String phone;
    /**
    * 头像
    */
    private String photo;
    /**
    * 注册时间
    */
    private Date createtime;

    /**
    * 账号
    */
    public void setUserid(String userid){
    this.userid = userid;
    }

    /**
    * 用户名
    */
    public void setUsername(String username){
    this.username = username;
    }

    /**
    * 密码
    */
    public void setPassword(String password){
    this.password = password;
    }

    /**
    * 性别
    */
    public void setSex(String sex){
    this.sex = sex;
    }

    /**
    * 余额
    */
    public void setMoney(Integer money){
    this.money = money;
    }

    /**
    * 身份证号
    */
    public void setId(String id){
    this.id = id;
    }

    /**
    * 手机
    */
    public void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 头像
    */
    public void setPhoto(String photo){
    this.photo = photo;
    }

    /**
    * 注册时间
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }


    /**
    * 账号
    */
    public String getUserid(){
    return this.userid;
    }

    /**
    * 用户名
    */
    public String getUsername(){
    return this.username;
    }

    /**
    * 密码
    */
    public String getPassword(){
    return this.password;
    }

    /**
    * 性别
    */
    public String getSex(){
    return this.sex;
    }

    /**
    * 余额
    */
    public Integer getMoney(){
    return this.money;
    }

    /**
    * 身份证号
    */
    public String getId(){
    return this.id;
    }

    /**
    * 手机
    */
    public String getPhone(){
    return this.phone;
    }

    /**
    * 头像
    */
    public String getPhoto(){
    return this.photo;
    }

    /**
    * 注册时间
    */
    public Date getCreatetime(){
    return this.createtime;
    }

}

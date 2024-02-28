package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 聊天记录表
* @TableName train_chat
*/
public class TrainChat implements Serializable {

    /**
    * 聊天ID
    */
    private Integer chatid;
    /**
    * 发送者账号
    */
    private String sendid;
    /**
    * 接收者账号
    */
    private String revid;
    /**
    * 聊天内容
    */
    private String content;
    /**
    * 聊天时间
    */
    private Date createtime;
    /**
    * 状态
    */
    private String state;
    /**
    * 备注
    */
    private String info;

    /**
    * 聊天ID
    */
    public void setChatid(Integer chatid){
    this.chatid = chatid;
    }

    /**
    * 发送者账号
    */
    public void setSendid(String sendid){
    this.sendid = sendid;
    }

    /**
    * 接收者账号
    */
    public void setRevid(String revid){
    this.revid = revid;
    }

    /**
    * 聊天内容
    */
    public void setContent(String content){
    this.content = content;
    }

    /**
    * 聊天时间
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }

    /**
    * 状态
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 备注
    */
    public void setInfo(String info){
    this.info = info;
    }


    /**
    * 聊天ID
    */
    public Integer getChatid(){
    return this.chatid;
    }

    /**
    * 发送者账号
    */
    public String getSendid(){
    return this.sendid;
    }

    /**
    * 接收者账号
    */
    public String getRevid(){
    return this.revid;
    }

    /**
    * 聊天内容
    */
    public String getContent(){
    return this.content;
    }

    /**
    * 聊天时间
    */
    public Date getCreatetime(){
    return this.createtime;
    }

    /**
    * 状态
    */
    public String getState(){
    return this.state;
    }

    /**
    * 备注
    */
    public String getInfo(){
    return this.info;
    }

}

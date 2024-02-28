package lhweb.asia.LHTomCat.model;

import java.io.Serializable;
import java.util.Date;

/**
* 买票订单评论表
* @TableName train_order_comment
*/
public class TrainOrderComment implements Serializable {

    /**
    * 评论ID
    */
    private Integer commentid;
    /**
    * 订单ID
    */
    private Integer orderid;
    /**
    * 账号
    */
    private String userid;
    /**
    * 评论内容
    */
    private String content;
    /**
    * 评论时间
    */
    private Date createtime;
    /**
    * 备注
    */
    private String info;

    /**
    * 评论ID
    */
    public void setCommentid(Integer commentid){
    this.commentid = commentid;
    }

    /**
    * 订单ID
    */
    public void setOrderid(Integer orderid){
    this.orderid = orderid;
    }

    /**
    * 账号
    */
    public void setUserid(String userid){
    this.userid = userid;
    }

    /**
    * 评论内容
    */
    public void setContent(String content){
    this.content = content;
    }

    /**
    * 评论时间
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
    * 评论ID
    */
    public Integer getCommentid(){
    return this.commentid;
    }

    /**
    * 订单ID
    */
    public Integer getOrderid(){
    return this.orderid;
    }

    /**
    * 账号
    */
    public String getUserid(){
    return this.userid;
    }

    /**
    * 评论内容
    */
    public String getContent(){
    return this.content;
    }

    /**
    * 评论时间
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

package com.gac.nioapp.test.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.syy.modulebase.http.bean.UserIconModel;
import com.syy.modulebase.http.constants.HttpKeyConstant;

import java.util.List;

/**
 * @Description：评论
 * @Author：Sai
 * @Date：2019/3/13 18:48
 */
public class CommentBean implements Parcelable {


    private List<CommentBean> records;
    /**
     * id : 1
     * parentCommentId : null
     * level : 1
     * praiseCount : 16
     * praiseLogo : 0
     * content : 我是一级评论
     * contentId : 1
     * createTime : 2019-03-11T10:23:45.000+0000
     * createId : 1
     * updateId : 1
     * byReplyName : null
     * createName : admin111
     * createAvatar : http://123123
     * article : null
     * childCommentCount : 67
     * records : [{"id":3,"parentCommentId":1,"level":2,"praiseCount":2,"praiseLogo":0,"content":"我是二级评论1","contentId":1,"createTime":"2019-03-11T10:25:30.000+0000","createId":1,"updateId":1,"byReplyName":null,"createName":"admin111","createAvatar":"http://123123","article":null,"childCommentCount":0,"records":null},{"id":4,"parentCommentId":1,"level":2,"praiseCount":3,"praiseLogo":0,"content":"我是三级级评论，也是回复2","contentId":1,"createTime":"2019-03-11T10:26:38.000+0000","createId":1,"updateId":1,"byReplyName":"用户1","createName":"admin111","createAvatar":"http://123123","article":null,"childCommentCount":0,"records":null},{"id":19,"parentCommentId":1,"level":2,"praiseCount":32,"praiseLogo":0,"content":"我是二级评论3","contentId":1,"createTime":"2019-03-18T12:17:21.000+0000","createId":1,"updateId":1,"byReplyName":null,"createName":"admin111","createAvatar":"http://123123","article":null,"childCommentCount":0,"records":null},{"id":26,"parentCommentId":1,"level":2,"praiseCount":0,"praiseLogo":0,"content":"我是二级评论4","contentId":1,"createTime":"2019-03-19T03:20:20.000+0000","createId":31,"updateId":31,"byReplyName":null,"createName":"admin111","createAvatar":"http://123123","article":null,"childCommentCount":0,"records":null},{"id":27,"parentCommentId":1,"level":2,"praiseCount":0,"praiseLogo":0,"content":"我是二级评论5","contentId":1,"createTime":"2019-03-19T03:22:22.000+0000","createId":31,"updateId":31,"byReplyName":null,"createName":"admin111","createAvatar":"http://123123","article":null,"childCommentCount":0,"records":null}]
     */

    private String id;//文章id
    private String parentCommentId;
    private int level;
    private String praiseCount;
    private int praiseLogo;//0没赞过1已赞
    private String content;
    private String contentId;//评论id
    private String timeStamp;
    private String byReplyName;
    private String createName;
    private String createAvatar;
    private int contentType;
    private int userType;
    private int carType;
    protected int carOwnerType; //车主类型--用于前端加V: 0为普通用户,1为预定车主,2为车主(Z03盲定和Z03大定都是预定车主)
    private int childCommentCount;
    private String createId;
    private String userActionId;
    private String msg;
    private String byReplyId;
    private int topType;
    private int pgcType;
    private String commentRichText;
    private UserIconModel userIconModel;

    private int ugcType;

    public UserIconModel getUserIconModel() {
        return userIconModel;
    }

    public void setUserIconModel(UserIconModel userIconModel) {
        this.userIconModel = userIconModel;
    }

    public String getCommentRichText() {
        return commentRichText;
    }

    public void setCommentRichText(String commentRichText) {
        this.commentRichText = commentRichText;
    }

    public int getPgcType() {
        return pgcType;
    }

    public void setPgcType(int pgcType) {
        this.pgcType = pgcType;
    }

    public void setByReplyId(String byReplyId) {
        this.byReplyId = byReplyId;
    }

    public String getByReplyId() {
        return byReplyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPraiseCount() {
        int pcount = 0;
        try {
            if (TextUtils.isEmpty(praiseCount)) {
                pcount = 0;
            } else
                pcount = Integer.valueOf(praiseCount);
            if (praiseLogo == HttpKeyConstant.ACTIONTYPE_DEFAULT && pcount == 0) {
                return "1";
            }

            if (pcount > 999)
                return "999+";
        } catch (Exception e) {
            return praiseCount;
        }
        if (pcount < 0) pcount = 0;

        return String.valueOf(pcount);
    }

    public int getCarOwnerType() {
        return carOwnerType;
    }

    public void setCarOwnerType(int carOwnerType) {
        this.carOwnerType = carOwnerType;
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getPraiseLogo() {
        return praiseLogo;
    }

    public void setPraiseLogo(int praiseLogo) {
        this.praiseLogo = praiseLogo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getByReplyName() {
        return byReplyName;
    }

    public void setByReplyName(String byReplyName) {
        this.byReplyName = byReplyName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateAvatar() {
        return createAvatar;
    }

    public void setCreateAvatar(String createAvatar) {
        this.createAvatar = createAvatar;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getChildCommentCount() {
        return childCommentCount;
    }

    public void setChildCommentCount(int childCommentCount) {
        this.childCommentCount = childCommentCount;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public boolean isEmployee() {
        return getUserType() == 1;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public List<CommentBean> getRecords() {
        return records;
    }

    public void setRecords(List<CommentBean> records) {
        this.records = records;
    }

    public String getUserActionId() {
        return userActionId;
    }

    public void setUserActionId(String userActionId) {
        this.userActionId = userActionId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public int getCarType() {
        //优先显示深V，2：深V，1：浅V
        if (this.carType == 2 || this.carOwnerType == 2) {
            return 2;
        } else if (this.carType == 1 || this.carOwnerType == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isCarOwner() {
        return getCarType() > 0 || getCarOwnerType() > 0;
    }

    public void setTopType(int topType) {
        this.topType = topType;
    }

    public int getTopType() {
        return topType;
    }

    public boolean isSetTop() {
        return getTopType() == 1;
    }

    public int getUgcType() {
        return ugcType;
    }

    public void setUgcType(int ugcType) {
        this.ugcType = ugcType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.parentCommentId);
        dest.writeInt(this.level);
        dest.writeInt(this.userType);
        dest.writeInt(this.carType);
        dest.writeString(this.praiseCount);
        dest.writeInt(this.praiseLogo);
        dest.writeString(this.content);
        dest.writeString(this.contentId);
        dest.writeString(this.timeStamp);
        dest.writeString(this.byReplyName);
        dest.writeString(this.createName);
        dest.writeString(this.createAvatar);
        dest.writeInt(this.contentType);
        dest.writeInt(this.pgcType);
        dest.writeInt(this.childCommentCount);
        dest.writeInt(this.topType);
        dest.writeString(this.createId);
        dest.writeString(this.userActionId);
        dest.writeString(this.commentRichText);
        dest.writeParcelable(userIconModel, flags);
        dest.writeInt(this.ugcType);
    }

    public CommentBean() {
    }

    protected CommentBean(Parcel in) {
        this.id = in.readString();
        this.parentCommentId = in.readString();
        this.level = in.readInt();
        this.userType = in.readInt();
        this.carType = in.readInt();
        this.praiseCount = in.readString();
        this.praiseLogo = in.readInt();
        this.content = in.readString();
        this.contentId = in.readString();
        this.timeStamp = in.readString();
        this.byReplyName = in.readString();
        this.createName = in.readString();
        this.createAvatar = in.readString();
        this.contentType = in.readInt();
        this.pgcType = in.readInt();
        this.childCommentCount = in.readInt();
        this.topType = in.readInt();
        this.createId = in.readString();
        this.userActionId = in.readString();
        this.commentRichText = in.readString();
        this.userIconModel = in.readParcelable(Thread.currentThread().getContextClassLoader());
        this.ugcType = in.readInt();
    }

    public static final Creator<CommentBean> CREATOR = new Creator<CommentBean>() {
        @Override
        public CommentBean createFromParcel(Parcel source) {
            return new CommentBean(source);
        }

        @Override
        public CommentBean[] newArray(int size) {
            return new CommentBean[size];
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        CommentBean o = (CommentBean) obj;
        return id.equals(o.id);
    }
}

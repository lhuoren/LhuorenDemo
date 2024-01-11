package com.gac.nioapp.test.bean;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-07-31 14:39
 ** @describe
 *******************************/
public class SpecialArticleDetailBean {
    /**
     * id : 专题id
     * articleName : 文章专题列表名称
     * articleId : 关联文章列表（列表id)
     * articleReadNum : 文章总阅读量
     * articleNum : 文章篇数
     * titleImageUrl : 专题头图
     * remark : 专题描述
     * articleType : 0
     */

    private String id;
    private String articleName;
    private String articleId;
    private String articleReadNum;
    private String articleNum;
    private String titleImageUrl;
    private String remark;
    private int articleType;
    private String miniProgramShareUrl;
    private String articleTopicDetailShareUrl;
    private String htmlShareUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleReadNum() {
        return articleReadNum;
    }

    public void setArticleReadNum(String articleReadNum) {
        this.articleReadNum = articleReadNum;
    }

    public String getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(String articleNum) {
        this.articleNum = articleNum;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public String getHtmlShareUrl() {
        return htmlShareUrl;
    }

    public void setHtmlShareUrl(String htmlShareUrl) {
        this.htmlShareUrl = htmlShareUrl;
    }

    public String getMiniProgramShareUrl() {
        return miniProgramShareUrl == null ? articleTopicDetailShareUrl : miniProgramShareUrl;
    }

    public void setMiniProgramShareUrl(String miniProgramShareUrl) {
        this.miniProgramShareUrl = miniProgramShareUrl;
    }

    public void setArticleTopicDetailShareUrl(String articleTopicDetailShareUrl) {
        this.articleTopicDetailShareUrl = articleTopicDetailShareUrl;
    }

    public String getArticleTopicDetailShareUrl() {
        return articleTopicDetailShareUrl;
    }
}

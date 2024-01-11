package com.gac.nioapp.test.bean;


import com.syy.modulebase.http.bean.UserBean;

import java.util.List;

public class SpecialArticleBean {


    /**
     * articleList : [{"type":"文章类型 1 PGC 2 PUGC","targetTableId":1,"title":"文章标题","coverPath":"文章头图路径","articleId":"文章详情id"}]
     * articleName : 专题名字
     */

    int id;//专题id
    private String articleName;
    private String articleTitle;
    private List<ArticleListBean> details;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public List<ArticleListBean> getArticleList() {
        return details;
    }

    public void setArticleList(List<ArticleListBean> articleList) {
        this.details = articleList;
    }

    public static class ArticleListBean {
        /**
         * type : 文章类型 1 PGC 2 PUGC
         * targetTableId : 1
         * title : 文章标题
         * coverPath : 文章头图路径
         * articleId : 文章详情id
         */

        private String type;
        private int targetTableId;
        private String title;
        private String coverPath;
        private String articleId;
        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTargetTableId() {
            return targetTableId;
        }

        public void setTargetTableId(int targetTableId) {
            this.targetTableId = targetTableId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverPath() {
            return coverPath;
        }

        public void setCoverPath(String coverPath) {
            this.coverPath = coverPath;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }
    }
}

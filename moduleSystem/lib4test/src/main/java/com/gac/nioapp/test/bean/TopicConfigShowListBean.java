package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: evan
 * Date: 2020/4/23
 * Time: 12:09 PM
 */
public class TopicConfigShowListBean {

    /**
     * total : 8
     * size : 10
     * pages : 1
     * current : 1
     * records : [{"id":12,"topicName":"专题十一","articleTopicId":11,"articleList":[{"articleId":1,"commentCount":1,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/65f92f262c014bdf9912d4d758b4c063.png","createId":1,"id":18,"praise":true,"praiseCount":1,"title":"ddd","type":1},{"articleId":1,"commentCount":0,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/2658681f39e44a0e8c4d5113d6503c88.jpg","createId":4,"id":21,"praise":false,"praiseCount":0,"title":"asewq","type":1},{"articleId":1,"commentCount":1,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111441_5b690ced4e994d3d8b6b92e25afb2eb7_Desert.jpg","createId":1,"id":43,"praise":true,"praiseCount":1,"title":"这是长图PGC","type":1}]},{"id":11,"topicName":"专题十","articleTopicId":10,"articleList":[{"articleId":2,"commentCount":0,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111746_5816fa670d62414cab29f57801ae1774_Jellyfish.jpg","createId":1,"id":44,"praise":true,"praiseCount":1,"title":"新闻","type":1}]},{"id":10,"topicName":"专题九","articleTopicId":9,"articleList":[{"articleId":1,"commentCount":1,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/65f92f262c014bdf9912d4d758b4c063.png","createId":1,"id":18,"praise":true,"praiseCount":1,"title":"ddd","type":1},{"articleId":1,"commentCount":0,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/2658681f39e44a0e8c4d5113d6503c88.jpg","createId":4,"id":21,"praise":false,"praiseCount":0,"title":"asewq","type":1},{"articleId":1,"commentCount":1,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111441_5b690ced4e994d3d8b6b92e25afb2eb7_Desert.jpg","createId":1,"id":43,"praise":true,"praiseCount":1,"title":"这是长图PGC","type":1}]},{"id":9,"topicName":"专题八","articleTopicId":8,"articleList":[{"articleId":2,"commentCount":0,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111746_5816fa670d62414cab29f57801ae1774_Jellyfish.jpg","createId":1,"id":44,"praise":true,"praiseCount":1,"title":"新闻","type":1}]},{"id":8,"topicName":"专题七","articleTopicId":7,"articleList":[]},{"id":4,"topicName":"专题三","articleTopicId":3,"articleList":[]},{"id":3,"topicName":"专题二","articleTopicId":2,"articleList":[{"articleId":2,"commentCount":0,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111746_5816fa670d62414cab29f57801ae1774_Jellyfish.jpg","createId":1,"id":44,"praise":true,"praiseCount":1,"title":"新闻","type":1}]},{"id":2,"topicName":"测试专题3","articleTopicId":1,"articleList":[{"articleId":1,"commentCount":1,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/65f92f262c014bdf9912d4d758b4c063.png","createId":1,"id":18,"praise":true,"praiseCount":1,"title":"ddd","type":1},{"articleId":1,"commentCount":0,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/2658681f39e44a0e8c4d5113d6503c88.jpg","createId":4,"id":21,"praise":false,"praiseCount":0,"title":"asewq","type":1},{"articleId":1,"commentCount":1,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111441_5b690ced4e994d3d8b6b92e25afb2eb7_Desert.jpg","createId":1,"id":43,"praise":true,"praiseCount":1,"title":"这是长图PGC","type":1}]}]
     */

    private int total;
    private int size;
    private int pages;
    private int current;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 12
         * topicName : 专题十一
         * articleTopicId : 11
         * articleList : [{"articleId":1,"commentCount":1,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/65f92f262c014bdf9912d4d758b4c063.png","createId":1,"id":18,"praise":true,"praiseCount":1,"title":"ddd","type":1},{"articleId":1,"commentCount":0,"coverPath":"http://10.10.8.114/upload/images/community/2019-04-04/2658681f39e44a0e8c4d5113d6503c88.jpg","createId":4,"id":21,"praise":false,"praiseCount":0,"title":"asewq","type":1},{"articleId":1,"commentCount":1,"coverPath":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20190417111441_5b690ced4e994d3d8b6b92e25afb2eb7_Desert.jpg","createId":1,"id":43,"praise":true,"praiseCount":1,"title":"这是长图PGC","type":1}]
         */

        private int id;
        private String topicName;
        private int articleTopicId;
        private List<ArticleListBean> articleList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public int getArticleTopicId() {
            return articleTopicId;
        }

        public void setArticleTopicId(int articleTopicId) {
            this.articleTopicId = articleTopicId;
        }

        public List<ArticleListBean> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<ArticleListBean> articleList) {
            this.articleList = articleList;
        }

        public static class ArticleListBean {
            /**
             * articleId : 1
             * commentCount : 1
             * coverPath : http://10.10.8.114/upload/images/community/2019-04-04/65f92f262c014bdf9912d4d758b4c063.png
             * createId : 1
             * id : 18
             * praise : true
             * praiseCount : 1
             * title : ddd
             * type : 1
             */

            private int articleId;
            private int commentCount;
            private String coverPath;
            private int createId;
            private int id;
            private boolean praise;
            private int praiseCount;
            private String title;
            private int type;

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getCoverPath() {
                return coverPath;
            }

            public void setCoverPath(String coverPath) {
                this.coverPath = coverPath;
            }

            public int getCreateId() {
                return createId;
            }

            public void setCreateId(int createId) {
                this.createId = createId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isPraise() {
                return praise;
            }

            public void setPraise(boolean praise) {
                this.praise = praise;
            }

            public int getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(int praiseCount) {
                this.praiseCount = praiseCount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}

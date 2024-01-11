package com.gac.nioapp.test.bean;

import com.syy.modulebase.http.bean.UserBean;

import java.util.List;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-07-26 11:53
 ** @describe
 *******************************/
public class HotUgcBean {
    /**
     * name : 24小时最热
     * ugcs : [{"id":1,"user":{"userId":1,"nickName":"","avatar":"","userType":1},"content":"","headImage":"","praise":false,"praiseCount":1,"commentCount":1}]
     */

    private String name;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private List<UgcsBean> ugcs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UgcsBean> getUgcs() {
        return ugcs;
    }

    public void setUgcs(List<UgcsBean> ugcs) {
        this.ugcs = ugcs;
    }

    public static class UgcsBean {
        /**
         * id : 1
         * user : {"userId":1,"nickName":"","avatar":"","userType":1}
         * content :
         * headImage :
         * praise : false
         * praiseCount : 1
         * commentCount : 1
         */

        private int id;
        private UserBean user;
        private String content;
        private String title;
        private String coverImage;
        private List<String> images;
        private String timeName;
        private boolean praise;
        private int praiseCount;
        private int commentCount;
        private int type;
        private String itemType;
        private String revision;
        private List<TopicBean> topicList;

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemType() {
            return itemType;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public boolean isPraise() {
            return praise;
        }

        public void setPraise(boolean praise) {
            this.praise = praise;
        }


        public void setPraiseCount(int praiseCount) {
            this.praiseCount = praiseCount;
        }


        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public void setTimeName(String timeName) {
            this.timeName = timeName;
        }

        public String getTimeName() {
            return timeName;
        }

        public void setRevision(String revision) {
            this.revision = revision;
        }

        public String getRevision() {
            return revision;
        }

        public void setTopicList(List<TopicBean> topicList) {
            this.topicList = topicList;
        }

        public List<TopicBean> getTopicList() {
            return topicList;
        }

        public String getPraiseCount() {
            if (praiseCount > 999) {
                return "999+";
            }
            return praiseCount + "";
        }

        public String getCommentCount() {
            if (commentCount > 999) {
                return "999+";
            }
            return commentCount + "";
        }

        public String getTopicListString() {
            StringBuffer buf = new StringBuffer();
            if (null != topicList && topicList.size() > 0) {
                for (TopicBean topic : topicList) {
                    buf.append("#").append(topic.getName()).append("#");
                    break;
                }
            }
            return buf.toString();
        }
    }

}

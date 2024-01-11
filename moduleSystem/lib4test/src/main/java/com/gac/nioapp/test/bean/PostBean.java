package com.gac.nioapp.test.bean;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.syy.modulebase.http.bean.UserBean;

import java.util.List;

/**
 * @Description：帖子模型
 * @Author：Sai
 * @Date：2019/3/8 9:58
 */
public class PostBean {


    /**
     * id : 1
     * title :
     * coverPath : http://a.jpg
     * images : []
     * contentType : 1
     * contentId : 1
     * praiseCount : 20
     * commentCount : 1
     * time : 2天前
     * createTime : 2019-02-12 14:01:01
     * userDto : {"userId":1,"username":"","avatar":""}
     */
    private String id;
    private String title;
    private String coverPath;
    private String newCoverPath;

    private String coverImage;
    private int contentType;//内容类型： 1--ugc 2--pgc 3--活动 4 文章专题 5--调查问卷 6--ugc热文 7--直播 8 pugc 【3.2.2】
    private String contentId;
    private String praiseCount;
    private String commentCount;
    private String time;
    private String createTime;
    private String updateTime;
    private String content;
    private String revision;//区分新旧发布
    private UserBean userDto;//TODO 接口待优化，接口推荐和社区分别叫userDto和user 现在需要判断
    private UserBean user;
    private List<String> images;
    //目前只有一个topic
    private List<TopicBean> topicList;
    private boolean praise;
    private String userActionId;
    private String miniProgramShareUrl;
    private String htmlShareUrl;
    //1-发布(上架) 2-下架 3-重新上架 4-不显示 5-待审核 6-违规
    private int offlineType;
    private String activityBannerUrl;
    private String activityTitle;
    private int activityId;
    //用户状态 1--关注 2--已关注 3--相互关注 4--该用户是自己
    private int status;
    private boolean recommend;
    private String brift;
    /////调查问卷begin/////
    private QuestionNaireBean questionnaire;
    /////调查问卷end/////
    private HotUgcBean ugcHot;
    private SpecialArticleBean article;
    private CampaignBean activity;
    private String articleId;
    private int recommendListSize;
    //商品评论
    private UgcProductBean shopObj;

    //////直播begin/////////
    private String startTime;
    private String endTime;

    private String streamAddr;
    private String returnViemAddr;
    private int liveStatus;//直播状态 1--未开始 2--直播中 3--已结束
    private String timeScale; //时间范围
    private LiveBroadcastBean liveBroadcast; //房间id
    private String pgvId;
    private String videoUrl;
    private String videoTime;
    private String shareBrift;
    private String shareUrl;
    private int type;//0 ugc 1 pugc【3.2.2】 2 视频
    private int collectStatus;// 0否 1收藏

    private UgcVideo ugcVideo;

    @SerializedName("isTop")
    private Boolean top;

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getPgvId() {
        return pgvId;
    }

    public void setPgvId(String pgvId) {
        this.pgvId = pgvId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getShareBrift() {
        return shareBrift;
    }

    public void setShareBrift(String shareBrift) {
        this.shareBrift = shareBrift;
    }

    //////直播end/////////
    public UgcProductBean getShopObj() {
        return shopObj;
    }

    public void setShopObj(UgcProductBean shopObj) {
        this.shopObj = shopObj;
    }

    public String getNewCoverPath() {
        return newCoverPath;
    }

    public void setNewCoverPath(String newCoverPath) {
        this.newCoverPath = newCoverPath;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public QuestionNaireBean getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionNaireBean questionnaire) {
        this.questionnaire = questionnaire;
    }

    public void setBrift(String brift) {
        this.brift = brift;
    }

    public String getBrift() {
        return brift;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityBannerUrl() {
        return activityBannerUrl;
    }

    public void setActivityBannerUrl(String activityBannerUrl) {
        this.activityBannerUrl = activityBannerUrl;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPath() {
        if (!TextUtils.isEmpty(newCoverPath))
            return newCoverPath;
        else if (!TextUtils.isEmpty(coverPath))
            return coverPath;
        else
            return coverImage;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContentId() {
        //contentId只在推荐里面使用，如果其他地方用到则判断为空返回id
        if (TextUtils.isEmpty(contentId)) return id;
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getPraiseCount() {
        int pcount = 0;
        try {
            if (TextUtils.isEmpty(praiseCount)) {
                pcount = 0;
            } else
                pcount = Integer.valueOf(praiseCount);
            if (praise && pcount == 0) {
                return "1";
            }
            if (pcount > 999)
                return "999+";
        } catch (Exception e) {
            return praiseCount;
        }
        return String.valueOf(pcount);
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getCommentCount() {
        try {
            int pcount;
            if (TextUtils.isEmpty(commentCount)) {
                pcount = 0;
            } else
                pcount = Integer.valueOf(commentCount);
            if (pcount > 999)
                return "999+";
        } catch (Exception e) {
        }
        return commentCount;
    }

    public void plusCommentCount() {
        try {
            int pcount;
            if (TextUtils.isEmpty(commentCount)) {
                pcount = 0;
            } else
                pcount = Integer.valueOf(commentCount);
            pcount++;
            commentCount = String.valueOf(pcount);
        } catch (Exception e) {
        }
    }

    public void plusCommentCount(int number) {
        try {
            int pcount;
            if (TextUtils.isEmpty(commentCount)) {
                pcount = 0;
            } else
                pcount = Integer.valueOf(commentCount);
            pcount = pcount + number;
            commentCount = String.valueOf(pcount);
        } catch (Exception e) {
        }
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public UserBean getUserDto() {
        return userDto != null ? userDto : user;
    }

    public void setUserDto(UserBean userDto) {
        this.userDto = userDto;
    }

    public UserBean getUser() {
        UserBean u = user != null ? user : userDto;
        if (u == null && questionnaire != null && questionnaire.getUser() != null) {
            return questionnaire.getUser();
        }

        return u;
    }

    public String getUserId() {
        UserBean userBean = user != null ? user : userDto;
        if (userBean != null) {
            return userBean.getUserId();
        } else if (questionnaire != null && questionnaire.getUser() != null) {
            return questionnaire.getUser().getUserId();
        }
        return "";
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public List<TopicBean> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicBean> topicList) {
        this.topicList = topicList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getUserActionId() {
        return userActionId;
    }

    public void setUserActionId(String userActionId) {
        this.userActionId = userActionId;
    }

    public String getMiniProgramShareUrl() {
        return miniProgramShareUrl;
    }

    public void setMiniProgramShareUrl(String miniProgramShareUrl) {
        this.miniProgramShareUrl = miniProgramShareUrl;
    }

    public String getHtmlShareUrl() {
        return htmlShareUrl;
    }

    public void setHtmlShareUrl(String htmlShareUrl) {
        this.htmlShareUrl = htmlShareUrl;
    }

    public int getOfflineType() {
        return offlineType;
    }

    public void setOfflineType(int offlineType) {
        this.offlineType = offlineType;
    }

    public boolean equals(@Nullable PostBean obj) {
        return id.equals(obj.id);
    }

    /**
     * 目前只有一个topic
     *
     * @return
     */
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

    /**
     * 获取第一个话题信息
     *
     * @return
     */
    public String getFirstTopicString() {
        String firstTopic = null;
        if (null != topicList && topicList.size() > 0) {
            firstTopic = topicList.get(0).getName();
        }
        return firstTopic;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        PostBean o = (PostBean) obj;
        //推荐列表使用contentId作为id的
        if (!TextUtils.isEmpty(o.contentId))
            return id.equals(o.contentId);
        else
            return id.equals(o.id);
    }


    public void setUgcHot(HotUgcBean ugcHot) {
        this.ugcHot = ugcHot;
    }

    public HotUgcBean getUgcHot() {
        return ugcHot;
    }


    public CampaignBean getActivity() {
        return activity;
    }

    public void setActivity(CampaignBean activity) {
        this.activity = activity;
    }


    public SpecialArticleBean getArticle() {
        return article;
    }

    public void setArticle(SpecialArticleBean article) {
        this.article = article;
    }

    public void setRecommendListSize(int recommendListSize) {
        this.recommendListSize = recommendListSize;
    }

    public int getRecommendListSize() {
        return recommendListSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStreamAddr() {
        return streamAddr;
    }

    public void setStreamAddr(String streamAddr) {
        this.streamAddr = streamAddr;
    }

    public String getReturnViemAddr() {
        return returnViemAddr;
    }

    public void setReturnViemAddr(String returnViemAddr) {
        this.returnViemAddr = returnViemAddr;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(String timeScale) {
        this.timeScale = timeScale;
    }

    public void setLiveBroadcast(LiveBroadcastBean liveBroadcast) {
        this.liveBroadcast = liveBroadcast;
    }

    public LiveBroadcastBean getLiveBroadcast() {
        return liveBroadcast;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }


    public boolean isFavorite() {
        return getCollectStatus() == 1;
    }

    public int getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(int collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Boolean isTop() {
        return top != null && top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public UgcVideo getUgcVideo() {
        return ugcVideo;
    }

    public void setUgcVideo(UgcVideo ugcVideo) {
        this.ugcVideo = ugcVideo;
    }
}

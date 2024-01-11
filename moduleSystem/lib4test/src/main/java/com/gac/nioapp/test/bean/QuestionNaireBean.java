package com.gac.nioapp.test.bean;

import com.syy.modulebase.http.bean.UserBean;

/**
 * @package： com.hycan.community.bean
 * @describe：
 * @author： liming
 * @time： 2019-08-01 11:21
 * @e-mail： liming@gac-nio.com
 */
public class QuestionNaireBean {
    private int id;
    private String title;
    private String coverPath;
    private String timeName;
    private String reward;
    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}

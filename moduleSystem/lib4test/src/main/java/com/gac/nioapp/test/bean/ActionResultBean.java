package com.gac.nioapp.test.bean;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/4/18 11:41
 */
public class ActionResultBean extends IntegralTipsResultBean{
    private String userActionId;

    public void setUserActionId(String userActionId) {
        this.userActionId = userActionId;
    }

    public String getUserActionId() {
        return userActionId;
    }
}

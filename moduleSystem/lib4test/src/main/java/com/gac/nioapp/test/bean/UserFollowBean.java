package com.gac.nioapp.test.bean;

import com.syy.modulebase.http.bean.UserBean;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019-06-18 10:14
 */
public class UserFollowBean {

    /**
     * id : 1
     * status : 1
     * user : {"userId":1,"username":"","avatar":"http://abc.jpg"}
     */

    private int id;
    private int status;
    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }
    public String getUserId() {
        return user==null?"":user.getUserId();
    }

    public void setUser(UserBean user) {
        this.user = user;
    }


}

package com.gac.nioapp.test.bean;

import java.util.List;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-11-14 16:51
 ** @describe
 *******************************/
public class JumpUrlContentBean {
    /**
     * url : http://pages/presale-index/presale-index?s=2&ty=5
     * type : 1
     * jumpUrlParam : [{"name":"id","param":"1"}]
     */

    private String url;
    private int type;
    private List<JumpUrlParamBean> jumpUrlParam;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<JumpUrlParamBean> getJumpUrlParam() {
        return jumpUrlParam;
    }

    public void setJumpUrlParam(List<JumpUrlParamBean> jumpUrlParam) {
        this.jumpUrlParam = jumpUrlParam;
    }


}
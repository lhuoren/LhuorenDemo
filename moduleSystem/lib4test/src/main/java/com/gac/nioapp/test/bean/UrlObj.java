package com.gac.nioapp.test.bean;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-08-13 15:06
 ** @describe
 *******************************/
public class UrlObj {

    private String id;
    private String url;
    private int type;
    private Object content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

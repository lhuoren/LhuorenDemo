package com.gac.nioapp.test.bean;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/4/16 11:22
 */
public class UgcADBean {

    /**
     * id : 1
     * title : banner标题
     * imagePath : /a/b/c.jpg
     * contentType : 1
     * contentId : 1
     */

    private String id;
    private String title;
    private String imagePath;
    private int contentType;
    private String contentId;
    private UrlObj urlObj;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public void setUrlObj(UrlObj urlObj) {
        this.urlObj = urlObj;
    }

    public UrlObj getUrlObj() {
        return urlObj;
    }
}

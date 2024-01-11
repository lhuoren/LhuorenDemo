package com.gac.nioapp.test.bean;

import java.util.List;

public class InformationBannerBean {

    /**
     * openStatus : 1
     * records : [{"id":1,"bannerTheme":"劳动节","bannerImage":"https://test-azure-community.obs.cn-south-1.myhuaweicloud.com:443/20190513161114%2Fa8b269c9f762416aa13967bbb5297e81.jpg","jumpUrlContent":{"jumpUrlParam":[{"param":"1","name":"id"}],"type":8,"url":"/pages/topic-detail/topic-detail?id=1"}}]
     */

    private int openStatus;
    private List<RecordsBean> records;

    public int getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 1
         * bannerTheme : 劳动节
         * bannerImage : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com:443/20190513161114%2Fa8b269c9f762416aa13967bbb5297e81.jpg
         * jumpUrlContent : {"jumpUrlParam":[{"param":"1","name":"id"}],"type":8,"url":"/pages/topic-detail/topic-detail?id=1"}
         */

        private int id;
        private String bannerTheme;
        private String bannerImage;
        private JumpUrlContentBean jumpUrlContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBannerTheme() {
            return bannerTheme;
        }

        public void setBannerTheme(String bannerTheme) {
            this.bannerTheme = bannerTheme;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public JumpUrlContentBean getJumpUrlContent() {
            return jumpUrlContent;
        }

        public void setJumpUrlContent(JumpUrlContentBean jumpUrlContent) {
            this.jumpUrlContent = jumpUrlContent;
        }
    }
}

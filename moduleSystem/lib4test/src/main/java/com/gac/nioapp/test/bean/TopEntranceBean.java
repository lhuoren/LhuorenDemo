package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * @package： com.hycan.community.bean
 * @describe：
 * @author： liming
 * @time： 2020/4/26 2:34 PM
 * @e-mail： liming@gac-nio.com
 */
public class TopEntranceBean {
    private String name;
    private String pic;
    private JumpUrlContentBean jumpUrlContent;
    private String entryType; //快捷入口类型：1=直播 2=常规
    private List<String> top3PlannerList; //直播类在线3名规划师头像列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public JumpUrlContentBean getJumpUrlContent() {
        return jumpUrlContent;
    }

    public void setJumpUrlContent(JumpUrlContentBean jumpUrlContent) {
        this.jumpUrlContent = jumpUrlContent;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public List<String> getTop3PlannerList() {
        return top3PlannerList;
    }

    public void setTop3PlannerList(List<String> top3PlannerList) {
        this.top3PlannerList = top3PlannerList;
    }
}

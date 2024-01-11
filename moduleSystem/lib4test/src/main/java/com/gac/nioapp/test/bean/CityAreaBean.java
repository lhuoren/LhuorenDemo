package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * @Description：城市区域选择
 * @Author：Sai
 * @Date：2019/4/1 15:54
 */
public class CityAreaBean {

    /**
     * nodeCode : 110101
     * nodeName : 东城区
     * id : 368100109725868000
     * pid : 368100109679730700
     * children : null
     */
    /**
     * 本地字段,用于区分是否从网络获取children数据
     */
    private boolean getChildrenFromNetwork;
    private long nodeCode;
    private String nodeName;
    private long pid;
    private List<CityAreaBean> children;

    public boolean isGetChildrenFromNetwork() {
        return getChildrenFromNetwork;
    }

    public void setGetChildrenFromNetwork(boolean getChildrenFromNetwork) {
        this.getChildrenFromNetwork = getChildrenFromNetwork;
    }

    public long getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(long nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public List<CityAreaBean> getChildren() {
        return children;
    }

    public void setChildren(List<CityAreaBean> children) {
        this.children = children;
    }

    public boolean hasChildren() {
        return getChildren() != null || getChildrenFromNetwork;
    }
}

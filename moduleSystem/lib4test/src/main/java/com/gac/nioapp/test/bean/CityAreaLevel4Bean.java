package com.gac.nioapp.test.bean;


/**
 * @Description：城市区域选择
 * @Author：Sai
 * @Date：2019/4/1 15:54
 */
public class CityAreaLevel4Bean {

    private long areaId;
    private String areaName;
    private long parentId;

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public CityAreaBean toCityAreaBean() {
        CityAreaBean cityAreaBean = new CityAreaBean();
        //标记为需要从网络获取数据
        cityAreaBean.setGetChildrenFromNetwork(true);
        cityAreaBean.setNodeCode(areaId);
        cityAreaBean.setNodeName(areaName);
        cityAreaBean.setPid(parentId);
        return cityAreaBean;
    }
}

package com.syy.moduledbbase.db.entity.moduleuav.plantask;

import org.greenrobot.greendao.annotation.Convert;

import java.util.List;

public class TasklinesBean {
    /**
     * id : 9b4cc42d5c3ec313406a6a8933ce50af
     * createDate : null
     * updateDate : null
     * planid : 83f6082d88c84ea7af46d0e5c8053245
     * patrollineid : 03051520832396
     * patrollinename : 110kV濠岗线
     * plansegid : 43C4C8798454B211A9B906972952C33E
     * siteid : 03051520832396
     * towers : [{"id":"f73c0b04435ce78afb85675b19a131f4","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":null,"plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","sitename":null,"towerid":"03051520834752","towername":"N1杆塔单元","sortno":830552,"airlinetower":null},{"id":"1c2d2f7cf52a135439eb702710bea5de","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":null,"plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","sitename":null,"towerid":"03051520833581","towername":"N2杆塔单元","sortno":830802,"airlinetower":null},{"id":"33f3090a6ff2775504b8bc1cdd737bea","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":null,"plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","sitename":null,"towerid":"03051520834119","towername":"N3杆塔单元","sortno":830904,"airlinetower":null}]
     * airline : null
     */

    private String id;
    private Object createDate;
    private Object updateDate;
    private String planid;
    private String patrollineid;
    private String patrollinename;
    private String plansegid;
    private String siteid;
    private Object airline;
    @Convert(columnType = String.class, converter = TowersBeanConverter.class)
    private List<TowersBean> towers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public String getPatrollineid() {
        return patrollineid;
    }

    public void setPatrollineid(String patrollineid) {
        this.patrollineid = patrollineid;
    }

    public String getPatrollinename() {
        return patrollinename;
    }

    public void setPatrollinename(String patrollinename) {
        this.patrollinename = patrollinename;
    }

    public String getPlansegid() {
        return plansegid;
    }

    public void setPlansegid(String plansegid) {
        this.plansegid = plansegid;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public Object getAirline() {
        return airline;
    }

    public void setAirline(Object airline) {
        this.airline = airline;
    }

    public List<TowersBean> getTowers() {
        return towers;
    }

    public void setTowers(List<TowersBean> towers) {
        this.towers = towers;
    }

}

package com.syy.moduledbbase.db.entity.moduleuav.plantask;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PlanTask implements Serializable {

    private static final long serialVersionUID = 156748885229545179L;

    /**
     * id : 83f6082d88c84ea7af46d0e5c8053245
     * createDate : 1637916066741
     * updateDate : 1637916066741
     * planid : 83f6082d88c84ea7af46d0e5c8053245
     * plantype : 1
     * workmasteruaccount : chenbaoping@st.gd.csg.cn
     * workmasteruname : 陈宝平
     * plancreatetime : 1637916022000
     * planstarttime : 1637973000000
     * planendtime : 1638437400000
     * powergridflag : 1
     * worktype : null
     * jobcontent : 110kV濠岗线@N1-N34@通道巡视
     * workresult : null
     * gmptaskstate : null
     * workmemberunames : 曾睿,陈敬亮,陈为东,陈喜波,陈亿,陈梓荣,杜伟强,李楚文,林溢欣,佘维爱,佘伟春,宋俊光,谢楚文,张泽翼,陈宝平
     * airlinest : 1
     * tasklines : [{"id":"9b4cc42d5c3ec313406a6a8933ce50af","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":"03051520832396","patrollinename":"110kV濠岗线","plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","towers":[{"id":"f73c0b04435ce78afb85675b19a131f4","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":null,"plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","sitename":null,"towerid":"03051520834752","towername":"N1杆塔单元","sortno":830552,"airlinetower":null},{"id":"1c2d2f7cf52a135439eb702710bea5de","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":null,"plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","sitename":null,"towerid":"03051520833581","towername":"N2杆塔单元","sortno":830802,"airlinetower":null},{"id":"33f3090a6ff2775504b8bc1cdd737bea","createDate":null,"updateDate":null,"planid":"83f6082d88c84ea7af46d0e5c8053245","patrollineid":null,"plansegid":"43C4C8798454B211A9B906972952C33E","siteid":"03051520832396","sitename":null,"towerid":"03051520834119","towername":"N3杆塔单元","sortno":830904,"airlinetower":null}],"airline":null}]
     * airlines : [{"airlineid":"c7c4b9f7905e9dd81d0bb89a6e80076c","name":"深圳精细化航线"}]
     */

    @Id(autoincrement = true)
    private Long nId;
    private String id;
    private long createDate;
    private long updateDate;
    private String planid;
    private int plantype;
    private String workmasteruaccount;
    private String workmasteruname;
    private long plancreatetime;
    private long planstarttime;
    private long planendtime;
    private int powergridflag;
    private int worktype;
    private String jobcontent;
    private String workresult;
    private int gmptaskstate;
    private String workmemberunames;
    private int taskstate;//0:未完成 1:完成
    private int airlinest;
    @Convert(columnType = String.class, converter = TasklinesBeanConverter.class)
    private List<TasklinesBean> tasklines;
    @Convert(columnType = String.class, converter = AirlinesBeanConverter.class)
    private List<AirlinesBean> airlines;
    @Generated(hash = 499875329)
    public PlanTask(Long nId, String id, long createDate, long updateDate, String planid, int plantype, String workmasteruaccount, String workmasteruname, long plancreatetime, long planstarttime, long planendtime, int powergridflag, int worktype, String jobcontent, String workresult, int gmptaskstate, String workmemberunames, int taskstate, int airlinest, List<TasklinesBean> tasklines, List<AirlinesBean> airlines) {
        this.nId = nId;
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.planid = planid;
        this.plantype = plantype;
        this.workmasteruaccount = workmasteruaccount;
        this.workmasteruname = workmasteruname;
        this.plancreatetime = plancreatetime;
        this.planstarttime = planstarttime;
        this.planendtime = planendtime;
        this.powergridflag = powergridflag;
        this.worktype = worktype;
        this.jobcontent = jobcontent;
        this.workresult = workresult;
        this.gmptaskstate = gmptaskstate;
        this.workmemberunames = workmemberunames;
        this.taskstate = taskstate;
        this.airlinest = airlinest;
        this.tasklines = tasklines;
        this.airlines = airlines;
    }
    @Generated(hash = 815233186)
    public PlanTask() {
    }
    public Long getNId() {
        return this.nId;
    }
    public void setNId(Long nId) {
        this.nId = nId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
    public long getUpdateDate() {
        return this.updateDate;
    }
    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
    public String getPlanid() {
        return this.planid;
    }
    public void setPlanid(String planid) {
        this.planid = planid;
    }
    public int getPlantype() {
        return this.plantype;
    }
    public void setPlantype(int plantype) {
        this.plantype = plantype;
    }
    public String getWorkmasteruaccount() {
        return this.workmasteruaccount;
    }
    public void setWorkmasteruaccount(String workmasteruaccount) {
        this.workmasteruaccount = workmasteruaccount;
    }
    public String getWorkmasteruname() {
        return this.workmasteruname;
    }
    public void setWorkmasteruname(String workmasteruname) {
        this.workmasteruname = workmasteruname;
    }
    public long getPlancreatetime() {
        return this.plancreatetime;
    }
    public void setPlancreatetime(long plancreatetime) {
        this.plancreatetime = plancreatetime;
    }
    public long getPlanstarttime() {
        return this.planstarttime;
    }
    public void setPlanstarttime(long planstarttime) {
        this.planstarttime = planstarttime;
    }
    public long getPlanendtime() {
        return this.planendtime;
    }
    public void setPlanendtime(long planendtime) {
        this.planendtime = planendtime;
    }
    public int getPowergridflag() {
        return this.powergridflag;
    }
    public void setPowergridflag(int powergridflag) {
        this.powergridflag = powergridflag;
    }
    public int getWorktype() {
        return this.worktype;
    }
    public void setWorktype(int worktype) {
        this.worktype = worktype;
    }
    public String getJobcontent() {
        return this.jobcontent;
    }
    public void setJobcontent(String jobcontent) {
        this.jobcontent = jobcontent;
    }
    public String getWorkresult() {
        return this.workresult;
    }
    public void setWorkresult(String workresult) {
        this.workresult = workresult;
    }
    public int getGmptaskstate() {
        return this.gmptaskstate;
    }
    public void setGmptaskstate(int gmptaskstate) {
        this.gmptaskstate = gmptaskstate;
    }
    public String getWorkmemberunames() {
        return this.workmemberunames;
    }
    public void setWorkmemberunames(String workmemberunames) {
        this.workmemberunames = workmemberunames;
    }
    public int getTaskstate() {
        return this.taskstate;
    }
    public void setTaskstate(int taskstate) {
        this.taskstate = taskstate;
    }
    public int getAirlinest() {
        return this.airlinest;
    }
    public void setAirlinest(int airlinest) {
        this.airlinest = airlinest;
    }
    public List<TasklinesBean> getTasklines() {
        return this.tasklines;
    }
    public void setTasklines(List<TasklinesBean> tasklines) {
        this.tasklines = tasklines;
    }
    public List<AirlinesBean> getAirlines() {
        return this.airlines;
    }
    public void setAirlines(List<AirlinesBean> airlines) {
        this.airlines = airlines;
    }


}

package com.syy.moduledbbase.db.entity.moduleuav.plantask.line;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChildRoutes implements Serializable {

    private static final long serialVersionUID = 156749995229541546L;

    /**
     * id : 58d4239a53caaa750e7b5cce7ca6c7cb
     * name : 航线33
     * airlinetype : DETAILEDAIRLINE
     */

    @Id(autoincrement = true)
    private Long nId;
    private String id;
    private String name;
    private String airlinetype;
    private int fromtype;//0:SpliceLineAndTowerUtil 拼接航线 1:SelectLineAndTowerUtil 选择航线
    @Generated(hash = 745845745)
    public ChildRoutes(Long nId, String id, String name, String airlinetype, int fromtype) {
        this.nId = nId;
        this.id = id;
        this.name = name;
        this.airlinetype = airlinetype;
        this.fromtype = fromtype;
    }
    @Generated(hash = 1845262946)
    public ChildRoutes() {
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAirlinetype() {
        return this.airlinetype;
    }
    public void setAirlinetype(String airlinetype) {
        this.airlinetype = airlinetype;
    }
    public int getFromtype() {
        return this.fromtype;
    }
    public void setFromtype(int fromtype) {
        this.fromtype = fromtype;
    }

}

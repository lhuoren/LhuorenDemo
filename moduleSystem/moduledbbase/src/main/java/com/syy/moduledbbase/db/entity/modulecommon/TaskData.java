package com.syy.moduledbbase.db.entity.modulecommon;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class TaskData implements Serializable {

    private static final long serialVersionUID = 8748285151220109124L;

    @Id(autoincrement = true)
    private Long id;

    private Long recordId;

    private String flyTaskWorkId;

    private String flyTaskWorkCruiseId;

    private String flyTaskWorkCruiseName;

    private int slantingType;//倾斜摄影类型

    private Date createDate;

    private int idx;

    private String path;

    private String bigPhotoPath;

    private String dataType;

    private String targetId;//目标物id

    private String remark;//备注

    private String towerName;

    private Double towerLatitude;

    private Double towerLongitude;

    private String signPhotoDefect;

    @Generated(hash = 1885084365)
    public TaskData(Long id, Long recordId, String flyTaskWorkId, String flyTaskWorkCruiseId,
            String flyTaskWorkCruiseName, int slantingType, Date createDate, int idx,
            String path, String bigPhotoPath, String dataType, String targetId, String remark,
            String towerName, Double towerLatitude, Double towerLongitude,
            String signPhotoDefect) {
        this.id = id;
        this.recordId = recordId;
        this.flyTaskWorkId = flyTaskWorkId;
        this.flyTaskWorkCruiseId = flyTaskWorkCruiseId;
        this.flyTaskWorkCruiseName = flyTaskWorkCruiseName;
        this.slantingType = slantingType;
        this.createDate = createDate;
        this.idx = idx;
        this.path = path;
        this.bigPhotoPath = bigPhotoPath;
        this.dataType = dataType;
        this.targetId = targetId;
        this.remark = remark;
        this.towerName = towerName;
        this.towerLatitude = towerLatitude;
        this.towerLongitude = towerLongitude;
        this.signPhotoDefect = signPhotoDefect;
    }

    @Generated(hash = 427500158)
    public TaskData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getFlyTaskWorkId() {
        return this.flyTaskWorkId;
    }

    public void setFlyTaskWorkId(String flyTaskWorkId) {
        this.flyTaskWorkId = flyTaskWorkId;
    }

    public String getFlyTaskWorkCruiseId() {
        return this.flyTaskWorkCruiseId;
    }

    public void setFlyTaskWorkCruiseId(String flyTaskWorkCruiseId) {
        this.flyTaskWorkCruiseId = flyTaskWorkCruiseId;
    }

    public String getFlyTaskWorkCruiseName() {
        return this.flyTaskWorkCruiseName;
    }

    public void setFlyTaskWorkCruiseName(String flyTaskWorkCruiseName) {
        this.flyTaskWorkCruiseName = flyTaskWorkCruiseName;
    }

    public int getSlantingType() {
        return this.slantingType;
    }

    public void setSlantingType(int slantingType) {
        this.slantingType = slantingType;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getIdx() {
        return this.idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBigPhotoPath() {
        return this.bigPhotoPath;
    }

    public void setBigPhotoPath(String bigPhotoPath) {
        this.bigPhotoPath = bigPhotoPath;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTowerName() {
        return this.towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    public Double getTowerLatitude() {
        return this.towerLatitude;
    }

    public void setTowerLatitude(Double towerLatitude) {
        this.towerLatitude = towerLatitude;
    }

    public Double getTowerLongitude() {
        return this.towerLongitude;
    }

    public void setTowerLongitude(Double towerLongitude) {
        this.towerLongitude = towerLongitude;
    }

    public String getSignPhotoDefect() {
        return this.signPhotoDefect;
    }

    public void setSignPhotoDefect(String signPhotoDefect) {
        this.signPhotoDefect = signPhotoDefect;
    }

}

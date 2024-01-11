package com.syy.moduledbbase.db.entity.moduleuav;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ImageInfo implements Serializable {
    private static final long serialVersionUID = 156749382129631463L;

    @Id(autoincrement = true)
    private Long id;
    private String airLineTaskId;
    private String airlineVersion;
    private Date startTime;
    private Date endTime;
    private long takePhotoTime;
    private String isUpload;
    private String imageName;
    private int flyType;
    private String upLoadImageState;
    private long FileSize;
    private String savePhotoPath;
    private long photoNum;
    private String flyRecordCreateTime;
    private String uploadType;
    @Generated(hash = 1136593152)
    public ImageInfo(Long id, String airLineTaskId, String airlineVersion,
            Date startTime, Date endTime, long takePhotoTime, String isUpload,
            String imageName, int flyType, String upLoadImageState, long FileSize,
            String savePhotoPath, long photoNum, String flyRecordCreateTime,
            String uploadType) {
        this.id = id;
        this.airLineTaskId = airLineTaskId;
        this.airlineVersion = airlineVersion;
        this.startTime = startTime;
        this.endTime = endTime;
        this.takePhotoTime = takePhotoTime;
        this.isUpload = isUpload;
        this.imageName = imageName;
        this.flyType = flyType;
        this.upLoadImageState = upLoadImageState;
        this.FileSize = FileSize;
        this.savePhotoPath = savePhotoPath;
        this.photoNum = photoNum;
        this.flyRecordCreateTime = flyRecordCreateTime;
        this.uploadType = uploadType;
    }
    @Generated(hash = 2139894022)
    public ImageInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAirLineTaskId() {
        return this.airLineTaskId;
    }
    public void setAirLineTaskId(String airLineTaskId) {
        this.airLineTaskId = airLineTaskId;
    }
    public String getAirlineVersion() {
        return this.airlineVersion;
    }
    public void setAirlineVersion(String airlineVersion) {
        this.airlineVersion = airlineVersion;
    }
    public Date getStartTime() {
        return this.startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public long getTakePhotoTime() {
        return this.takePhotoTime;
    }
    public void setTakePhotoTime(long takePhotoTime) {
        this.takePhotoTime = takePhotoTime;
    }
    public String getIsUpload() {
        return this.isUpload;
    }
    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }
    public String getImageName() {
        return this.imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public int getFlyType() {
        return this.flyType;
    }
    public void setFlyType(int flyType) {
        this.flyType = flyType;
    }
    public String getUpLoadImageState() {
        return this.upLoadImageState;
    }
    public void setUpLoadImageState(String upLoadImageState) {
        this.upLoadImageState = upLoadImageState;
    }
    public long getFileSize() {
        return this.FileSize;
    }
    public void setFileSize(long FileSize) {
        this.FileSize = FileSize;
    }
    public String getSavePhotoPath() {
        return this.savePhotoPath;
    }
    public void setSavePhotoPath(String savePhotoPath) {
        this.savePhotoPath = savePhotoPath;
    }
    public long getPhotoNum() {
        return this.photoNum;
    }
    public void setPhotoNum(long photoNum) {
        this.photoNum = photoNum;
    }
    public String getFlyRecordCreateTime() {
        return this.flyRecordCreateTime;
    }
    public void setFlyRecordCreateTime(String flyRecordCreateTime) {
        this.flyRecordCreateTime = flyRecordCreateTime;
    }
    public String getUploadType() {
        return this.uploadType;
    }
    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }




}

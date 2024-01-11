package com.syy.moduledbbase.db.entity.modulecommon;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by smilelu on 2018/4/15.
 */
@Entity
public class TaskParams implements Serializable {

    private static final long serialVersionUID = 6955195619501016546L;

    @Id(autoincrement = true)
    private Long id;

    private double sideOverlap;//旁向重叠度

    private double courseOverlap;//航向重叠度

    private double flyHeight;//飞行高度

    private double takeOffHeight;//起降高度

    private double datumHeight;//基准面高度

    private double buffer;//缓冲区

    private double slantingAngle;//云台角度

    private int taskMode;//任务模式 0：视频拍摄，1：定时拍照

    private int backMode;//返航模式 0：直线返航，1：原路返航

    private double flySpeed;//飞行速度

    private double flyYaw;//机头朝向

    private int slantingType;//倾斜类型

    private double rotationAngle; //图形旋转角度

    private double surroundDistance;

    private double density;

    private int surroundMode;

    private double offsetHeight;

    private double topAngle;

    //是否飞塔顶
    private boolean enableTop;

    private int pathMode;

    private int towerMode;

    private boolean isManifold;
    //飞行类型
    private int flyType;
    //可变航高
    private boolean enableChangeHeight;
    //精细化模式
    private int fineOrder; //0：学习模式，1：巡检模式
    //航线类型
    private int airlineType; //0：学习，1：点云
    //作业速度
    private double workSpeed;

    private double baseStationAltitude;

    private double resolution;

    private double backSpeed;

    @Generated(hash = 1901790808)
    public TaskParams(Long id, double sideOverlap, double courseOverlap, double flyHeight, double takeOffHeight,
            double datumHeight, double buffer, double slantingAngle, int taskMode, int backMode, double flySpeed,
            double flyYaw, int slantingType, double rotationAngle, double surroundDistance, double density,
            int surroundMode, double offsetHeight, double topAngle, boolean enableTop, int pathMode,
            int towerMode, boolean isManifold, int flyType, boolean enableChangeHeight, int fineOrder,
            int airlineType, double workSpeed, double baseStationAltitude, double resolution, double backSpeed) {
        this.id = id;
        this.sideOverlap = sideOverlap;
        this.courseOverlap = courseOverlap;
        this.flyHeight = flyHeight;
        this.takeOffHeight = takeOffHeight;
        this.datumHeight = datumHeight;
        this.buffer = buffer;
        this.slantingAngle = slantingAngle;
        this.taskMode = taskMode;
        this.backMode = backMode;
        this.flySpeed = flySpeed;
        this.flyYaw = flyYaw;
        this.slantingType = slantingType;
        this.rotationAngle = rotationAngle;
        this.surroundDistance = surroundDistance;
        this.density = density;
        this.surroundMode = surroundMode;
        this.offsetHeight = offsetHeight;
        this.topAngle = topAngle;
        this.enableTop = enableTop;
        this.pathMode = pathMode;
        this.towerMode = towerMode;
        this.isManifold = isManifold;
        this.flyType = flyType;
        this.enableChangeHeight = enableChangeHeight;
        this.fineOrder = fineOrder;
        this.airlineType = airlineType;
        this.workSpeed = workSpeed;
        this.baseStationAltitude = baseStationAltitude;
        this.resolution = resolution;
        this.backSpeed = backSpeed;
    }

    @Generated(hash = 1163900101)
    public TaskParams() {
    }

    @Override
    public String toString() {
        return "TaskParams{" +
                "id=" + id +
                ", sideOverlap=" + sideOverlap +
                ", courseOverlap=" + courseOverlap +
                ", flyHeight=" + flyHeight +
                ", takeOffHeight=" + takeOffHeight +
                ", datumHeight=" + datumHeight +
                ", buffer=" + buffer +
                ", slantingAngle=" + slantingAngle +
                ", taskMode=" + taskMode +
                ", backMode=" + backMode +
                ", flySpeed=" + flySpeed +
                ", flyYaw=" + flyYaw +
                ", slantingType=" + slantingType +
                ", rotationAngle=" + rotationAngle +
                '}';
    }

    public TaskParams getParamsByFlySetting(FlySetting flySetting) {
        this.sideOverlap = flySetting.getSideOverlap();
        this.courseOverlap = flySetting.getCourseOverlap();
        this.flyHeight = flySetting.getFlyHeight();
        this.takeOffHeight = flySetting.getTakeOffHeight();
        this.datumHeight = flySetting.getDatumHeight();
        this.buffer = flySetting.getBuffer();
        this.slantingAngle = flySetting.getSlantingAngle();
        this.taskMode = flySetting.getTaskMode();
        this.backMode = flySetting.getBackMode();
        this.flySpeed = flySetting.getFlySpeed();
        this.flyYaw = flySetting.getFlyYaw();
        this.slantingType = flySetting.getSlantingType();
        this.surroundDistance = flySetting.getSurroundDistance();
        this.density = flySetting.getDensity();
        this.surroundMode = flySetting.getSurroundMode();
        this.offsetHeight = flySetting.getOffsetHeight();
        this.topAngle = flySetting.getTopAngle();
        this.enableTop = flySetting.isEnableTop();
        this.pathMode = flySetting.getPathMode();
        this.towerMode = flySetting.getTowerMode();
        this.isManifold = flySetting.isManifold();
        this.flyType = flySetting.getFlyType();
        this.enableChangeHeight = flySetting.isEnableChangeHeight();
        this.fineOrder = flySetting.getFineOrder();
        this.airlineType = flySetting.getAirlineType();
        this.workSpeed = flySetting.getWorkSpeed();
        this.baseStationAltitude = flySetting.getBaseStationAltitude();
        this.resolution = flySetting.getResolution();
        this.backSpeed=flySetting.getBackSpeed();
        return this;
    }

    public FlySetting getFlySetting(int patrolType) {
        FlySetting flySetting = new FlySetting(patrolType);
        flySetting.setSideOverlap(sideOverlap);
        flySetting.setCourseOverlap(courseOverlap);
        flySetting.setFlyHeight(flyHeight);
        flySetting.setTakeOffHeight(takeOffHeight);
        flySetting.setDatumHeight(datumHeight);
        flySetting.setBuffer(buffer);
        flySetting.setSlantingAngle(slantingAngle);
        flySetting.setTaskMode(taskMode);
        flySetting.setBackMode(backMode);
        flySetting.setFlySpeed(flySpeed);
        flySetting.setFlyYaw(flyYaw);
        flySetting.setSlantingType(slantingType);
        flySetting.setSurroundDistance(surroundDistance);
        flySetting.setDensity(density);
        flySetting.setSurroundMode(surroundMode);
        flySetting.setOffsetHeight(offsetHeight);
        flySetting.setTopAngle(topAngle);
        flySetting.setEnableTop(enableTop);
        flySetting.setPathMode(pathMode);
        flySetting.setTowerMode(towerMode);
        flySetting.setManifold(isManifold);
        flySetting.setFlyType(flyType);
        flySetting.setEnableChangeHeight(enableChangeHeight);
        flySetting.setFineOrder(fineOrder);
        flySetting.setAirlineType(airlineType);
        flySetting.setWorkSpeed(workSpeed);
        flySetting.setBaseStationAltitude(baseStationAltitude);
        flySetting.setResolution(resolution);
        flySetting.setBackSpeed(backSpeed);
        return flySetting;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSideOverlap() {
        return this.sideOverlap;
    }

    public void setSideOverlap(double sideOverlap) {
        this.sideOverlap = sideOverlap;
    }

    public double getCourseOverlap() {
        return this.courseOverlap;
    }

    public void setCourseOverlap(double courseOverlap) {
        this.courseOverlap = courseOverlap;
    }

    public double getFlyHeight() {
        return this.flyHeight;
    }

    public void setFlyHeight(double flyHeight) {
        this.flyHeight = flyHeight;
    }

    public double getTakeOffHeight() {
        return this.takeOffHeight;
    }

    public void setTakeOffHeight(double takeOffHeight) {
        this.takeOffHeight = takeOffHeight;
    }

    public double getDatumHeight() {
        return this.datumHeight;
    }

    public void setDatumHeight(double datumHeight) {
        this.datumHeight = datumHeight;
    }

    public double getBuffer() {
        return this.buffer;
    }

    public void setBuffer(double buffer) {
        this.buffer = buffer;
    }

    public double getSlantingAngle() {
        return this.slantingAngle;
    }

    public void setSlantingAngle(double slantingAngle) {
        this.slantingAngle = slantingAngle;
    }

    public int getTaskMode() {
        return this.taskMode;
    }

    public void setTaskMode(int taskMode) {
        this.taskMode = taskMode;
    }

    public int getBackMode() {
        return this.backMode;
    }

    public void setBackMode(int backMode) {
        this.backMode = backMode;
    }

    public double getFlySpeed() {
        return this.flySpeed;
    }

    public void setFlySpeed(double flySpeed) {
        this.flySpeed = flySpeed;
    }

    public double getFlyYaw() {
        return this.flyYaw;
    }

    public void setFlyYaw(double flyYaw) {
        this.flyYaw = flyYaw;
    }

    public int getSlantingType() {
        return this.slantingType;
    }

    public void setSlantingType(int slantingType) {
        this.slantingType = slantingType;
    }

    public double getRotationAngle() {
        return this.rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public double getSurroundDistance() {
        return this.surroundDistance;
    }

    public void setSurroundDistance(double surroundDistance) {
        this.surroundDistance = surroundDistance;
    }

    public double getDensity() {
        return this.density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public int getSurroundMode() {
        return this.surroundMode;
    }

    public void setSurroundMode(int surroundMode) {
        this.surroundMode = surroundMode;
    }

    public double getOffsetHeight() {
        return this.offsetHeight;
    }

    public void setOffsetHeight(double offsetHeight) {
        this.offsetHeight = offsetHeight;
    }

    public double getTopAngle() {
        return this.topAngle;
    }

    public void setTopAngle(double topAngle) {
        this.topAngle = topAngle;
    }

    public boolean getEnableTop() {
        return this.enableTop;
    }

    public void setEnableTop(boolean enableTop) {
        this.enableTop = enableTop;
    }

    public int getPathMode() {
        return this.pathMode;
    }

    public void setPathMode(int pathMode) {
        this.pathMode = pathMode;
    }

    public int getTowerMode() {
        return this.towerMode;
    }

    public void setTowerMode(int towerMode) {
        this.towerMode = towerMode;
    }

    public boolean getIsManifold() {
        return this.isManifold;
    }

    public void setIsManifold(boolean isManifold) {
        this.isManifold = isManifold;
    }

    public int getFlyType() {
        return this.flyType;
    }

    public void setFlyType(int flyType) {
        this.flyType = flyType;
    }

    public boolean getEnableChangeHeight() {
        return this.enableChangeHeight;
    }

    public void setEnableChangeHeight(boolean enableChangeHeight) {
        this.enableChangeHeight = enableChangeHeight;
    }

    public int getFineOrder() {
        return this.fineOrder;
    }

    public void setFineOrder(int fineOrder) {
        this.fineOrder = fineOrder;
    }

    public int getAirlineType() {
        return this.airlineType;
    }

    public void setAirlineType(int airlineType) {
        this.airlineType = airlineType;
    }

    public double getWorkSpeed() {
        return this.workSpeed;
    }

    public void setWorkSpeed(double workSpeed) {
        this.workSpeed = workSpeed;
    }

    public double getBaseStationAltitude() {
        return this.baseStationAltitude;
    }

    public void setBaseStationAltitude(double baseStationAltitude) {
        this.baseStationAltitude = baseStationAltitude;
    }

    public double getResolution() {
        return this.resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    public double getBackSpeed() {
        return this.backSpeed;
    }

    public void setBackSpeed(double backSpeed) {
        this.backSpeed = backSpeed;
    }
}

package com.syy.moduledbbase.db.entity.modulecommon;

public class FlySetting {
    public static final int TASK_MODE_RECORD = 0;
    public static final int TASK_MODE_SHOOT = 1;
    public static final int TASK_MODE_SHOOT_THEN_RECORD = 2;
    public static final int TASK_MODE_RECORD_THEN_SHOOT = 3;
    public static final int TAKE_PHOTO_MODE_COMMON = 0;
    public static final int TAKE_PHOTO_MODE_FAST = 1;
    public static final int BACK_MODE_STRAIGHT = 0;
    public static final int BACK_MODE_ORIGINAL = 1;
    public static final int BACK_MODE_NEAR = 2;
    public static final int FINE_ORDER_ASC = 0;
    public static final int FINE_ORDER_DESC = 1;
    public static final int FINE_ZOOM = 1;
    public static final int AIRLINE_TYPE_LEARN = 0;
    public static final int AIRLINE_TYPE_CLOUD_POINT = 1;
    public static final int SURROUND_MODE_AUTO = 0;
    public static final int SURROUND_MODE_NOW = 1;
    public static final int PATH_MODE_ALL = 0;
    public static final int PATH_MODE_LEFT = 1;
    public static final int PATH_MODE_RIGHT = 2;
    public static final int FLY_MODE_NORMAL = 0;
    public static final int FLY_MODE_CURVED = 1;
    public static final int TOWER_MODE_TRANSMISSION = 0;
    public static final int TOWER_MODE_DISTRIBUTION = 2;
    public static final int PATH_TYPE_NORMAL = 0;
    public static final int PATH_TYPE_SNAKE = 1;
    public static final int FINE_FLY_MODE_COMMON = 0;
    public static final int FINE_FLY_MODE_EFFICIENT = 1;
    public static final int CHANNEL_INSPECTION_LEFT = 0;
    public static final int CHANNEL_INSPECTION_RIGHT = 1;
    public static final int CHANNEL_INSPECTION_ALL = 2;
    public int flyType;
    public double sideOverlap;
    public double courseOverlap;
    public double flyHeight;
    public double takeOffHeight;
    public double takePhotoInterval;
    public double takePhotoTime;
    public double landingHeight;
    public double datumHeight;
    public double buffer;
    public double slantingAngle;
    public int taskMode;
    public int backMode;
    public double flySpeed;
    public double flyYaw;
    public int slantingType;
    public boolean enableChangeHeight;
    public int fineOrder;
    public int airlineType;
    public double workSpeed;
    public double baseStationAltitude;
    public double surroundDistance;
    public double density;
    public double resolution;
    public int surroundMode;
    public double offsetHeight;
    public double topAngle;
    public boolean enableTop;
    public int pathMode;
    public int towerMode;
    public boolean isManifold;
    public double backSpeed;
    public int flyMode;
    public int fineZoom;
    public int workMode;
    public int inspectionPosition = 0;

    public FlySetting(int var1) {
        this.flyType = var1;
        this.sideOverlap = 0.6D;
        this.courseOverlap = 0.7D;
        this.flyHeight = 115.0D;
        this.takeOffHeight = 115.0D;
        this.takePhotoInterval = 2.0D;
        this.takePhotoTime = 0.0D;
        this.landingHeight = 115.0D;
        this.datumHeight = 0.0D;
        this.buffer = 70.0D;
        double var2;
        if (var1 == 5) {
            var2 = 0.0D;
        } else {
            var2 = 40.0D;
        }

        this.slantingAngle = var2;
        this.taskMode = 1;
        this.backMode = 0;
        this.flySpeed = 10.0D;
        this.flyYaw = 0.0D;
        this.slantingType = 0;
        this.enableChangeHeight = false;
        this.fineOrder = 0;
        this.airlineType = 1;
        this.workSpeed = 2.0D;
        this.baseStationAltitude = 0.0D;
        this.surroundDistance = 5.0D;
        this.density = 8.0D;
        this.surroundMode = 0;
        this.offsetHeight = 0.0D;
        this.topAngle = 90.0D;
        this.enableTop = false;
        this.pathMode = 0;
        this.towerMode = 0;
        this.isManifold = false;
        this.backSpeed = 10.0D;
        this.flyMode = 0;
        this.fineZoom = 1;
        this.workMode = 1;
        this.inspectionPosition = 0;
    }

    public int getFlyType() {
        return this.flyType;
    }

    public void setFlyType(int var1) {
        this.flyType = var1;
    }

    public double getSideOverlap() {
        return this.sideOverlap;
    }

    public void setSideOverlap(double var1) {
        this.sideOverlap = var1;
    }

    public double getCourseOverlap() {
        return this.courseOverlap;
    }

    public void setCourseOverlap(double var1) {
        this.courseOverlap = var1;
    }

    public double getFlyHeight() {
        return this.flyHeight;
    }

    public void setFlyHeight(double var1) {
        this.flyHeight = var1;
    }

    public double getTakeOffHeight() {
        return this.takeOffHeight;
    }

    public void setTakeOffHeight(double var1) {
        this.takeOffHeight = var1;
    }

    public double getTakePhotoInterval() {
        return takePhotoInterval;
    }

    public void setTakePhotoInterval(double takePhotoInterval) {
        this.takePhotoInterval = takePhotoInterval;
    }

    public double getTakePhotoTime() {
        return takePhotoTime;
    }

    public void setTakePhotoTime(double takePhotoTime) {
        this.takePhotoTime = takePhotoTime;
    }

    public double getLandingHeight() {
        return this.landingHeight;
    }

    public void setLandingHeight(double var1) {
        this.landingHeight = var1;
    }

    public double getDatumHeight() {
        return this.datumHeight;
    }

    public void setDatumHeight(double var1) {
        this.datumHeight = var1;
    }

    public double getBuffer() {
        return this.buffer;
    }

    public void setBuffer(double var1) {
        this.buffer = var1;
    }

    public double getSlantingAngle() {
        return this.slantingAngle;
    }

    public void setSlantingAngle(double var1) {
        this.slantingAngle = var1;
    }

    public int getTaskMode() {
        return this.taskMode;
    }

    public void setTaskMode(int var1) {
        this.taskMode = var1;
    }

    public int getBackMode() {
        return this.backMode;
    }

    public void setBackMode(int var1) {
        this.backMode = var1;
    }

    public double getFlySpeed() {
        return this.flySpeed;
    }

    public void setFlySpeed(double var1) {
        this.flySpeed = var1;
    }

    public double getFlyYaw() {
        return this.flyYaw;
    }

    public void setFlyYaw(double var1) {
        this.flyYaw = var1;
    }

    public int getSlantingType() {
        return this.slantingType;
    }

    public void setSlantingType(int var1) {
        this.slantingType = var1;
    }

    public boolean isEnableChangeHeight() {
        return this.enableChangeHeight;
    }

    public void setEnableChangeHeight(boolean var1) {
        this.enableChangeHeight = var1;
    }

    public int getFineOrder() {
        return this.fineOrder;
    }

    public void setFineOrder(int var1) {
        this.fineOrder = var1;
    }

    public int getAirlineType() {
        return this.airlineType;
    }

    public void setAirlineType(int var1) {
        this.airlineType = var1;
    }

    public double getWorkSpeed() {
        return this.workSpeed;
    }

    public void setWorkSpeed(double var1) {
        this.workSpeed = var1;
    }

    public double getBaseStationAltitude() {
        return this.baseStationAltitude;
    }

    public void setBaseStationAltitude(double var1) {
        this.baseStationAltitude = var1;
    }

    public double getSurroundDistance() {
        return this.surroundDistance;
    }

    public void setSurroundDistance(double var1) {
        this.surroundDistance = var1;
    }

    public double getDensity() {
        return this.density;
    }

    public void setDensity(double var1) {
        this.density = var1;
    }

    public double getResolution() {
        return this.resolution;
    }

    public void setResolution(double var1) {
        this.resolution = var1;
    }

    public int getSurroundMode() {
        return this.surroundMode;
    }

    public void setSurroundMode(int var1) {
        this.surroundMode = var1;
    }

    public double getOffsetHeight() {
        return this.offsetHeight;
    }

    public void setOffsetHeight(double var1) {
        this.offsetHeight = var1;
    }

    public double getTopAngle() {
        return this.topAngle;
    }

    public void setTopAngle(double var1) {
        this.topAngle = var1;
    }

    public boolean isEnableTop() {
        return this.enableTop;
    }

    public void setEnableTop(boolean var1) {
        this.enableTop = var1;
    }

    public int getPathMode() {
        return this.pathMode;
    }

    public void setPathMode(int var1) {
        this.pathMode = var1;
    }

    public int getTowerMode() {
        return this.towerMode;
    }

    public void setTowerMode(int var1) {
        this.towerMode = var1;
    }

    public boolean isManifold() {
        return this.isManifold;
    }

    public void setManifold(boolean var1) {
        this.isManifold = var1;
    }

    public double getBackSpeed() {
        return this.backSpeed;
    }

    public void setBackSpeed(double var1) {
        this.backSpeed = var1;
    }

    public int getFlyMode() {
        return flyMode;
    }

    public void setFlyMode(int flyMode) {
        this.flyMode = flyMode;
    }

    public int getFineZoom() {
        return fineZoom;
    }

    public void setFineZoom(int fineZoom) {
        this.fineZoom = fineZoom;
    }

    public int getWorkMode() {
        return workMode;
    }

    public void setWorkMode(int workMode) {
        this.workMode = workMode;
    }

    public int getInspectionPosition() {
        return inspectionPosition;
    }

    public void setInspectionPosition(int inspectionPosition) {
        this.inspectionPosition = inspectionPosition;
    }

    public String toString() {
        return "FlySetting{flyType=" + this.flyType + ", sideOverlap=" + this.sideOverlap + ", courseOverlap=" + this.courseOverlap + ", flyHeight=" + this.flyHeight + ", takeOffHeight=" + this.takeOffHeight + ", landingHeight=" + this.landingHeight + ", datumHeight=" + this.datumHeight + ", buffer=" + this.buffer + ", slantingAngle=" + this.slantingAngle + ", taskMode=" + this.taskMode + ", backMode=" + this.backMode + ", flySpeed=" + this.flySpeed + ", flyYaw=" + this.flyYaw + ", slantingType=" + this.slantingType + ", enableChangeHeight=" + this.enableChangeHeight + ", fineOrder=" + this.fineOrder + ", airlineType=" + this.airlineType + ", workSpeed=" + this.workSpeed + ", baseStationAltitude=" + this.baseStationAltitude + ", surroundDistance=" + this.surroundDistance + ", density=" + this.density + ", resolution=" + this.resolution + ", surroundMode=" + this.surroundMode + ", offsetHeight=" + this.offsetHeight + ", topAngle=" + this.topAngle + ", enableTop=" + this.enableTop + ", pathMode=" + this.pathMode + ", towerMode=" + this.towerMode + ", isManifold=" + this.isManifold + ", backSpeed=" + this.backSpeed + '}';
    }

    public FlySetting clone() {
        FlySetting var10000 = this;
        FlySetting var3 = null;

        try {
            var10000 = (FlySetting) var10000.clone();
        } catch (Exception var2) {
            var2.printStackTrace();
            return var3;
        }

        var3 = var10000;
        return var3;
    }
}

package com.syy.moduledbbase.db.entity.modulecommon;

import com.syy.moduledbbase.db.greedao.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.syy.moduledbbase.db.greedao.TaskDataDao;
import com.syy.moduledbbase.db.greedao.FlyRecordDetailDao;
import com.syy.moduledbbase.db.greedao.PatrolTaskDao;
import com.syy.moduledbbase.db.greedao.FlyRecordDao;

@Entity
public class FlyRecord implements Serializable {

    private static final long serialVersionUID = -4125852796962658260L;

    @Id(autoincrement = true)
    private Long id;

    private String airLineTaskId;

    private double latOffset;

    private double lngOffset;

    private Long taskId;

    private Date createDate;

    private Date startTime;

    private Date endTime;

    private String productName;//设备名称

    private String productSerialNumber;//设备序列号

    private String batterySerialNumber; //电池序列号

    private String cameraSerialNumber; //相机序列号

    private String uavTrackerSerialNumber; //盒子序列号

    private boolean isUpload;//是否上传

    private boolean isImagesUpload;//图片是否上传

    private double distance; //飞行路程

    private double maxHeight;//最大高度

    private double longitude;

    private double latitude;

    private int slantingType;

    private boolean isSimulation = false;

    private int dataCount = 0;

    private int batteryDischargeNumber;//电池充放电次数

    private int batteryLife; //电池寿命

    private String airlineVersion;

    private String rtkAccount;

    @ToOne(joinProperty = "taskId")
    private PatrolTask patrolTask;

    @ToMany(referencedJoinProperty = "recordId")
    private volatile List<FlyRecordDetail> flyRecordDetailList;

    @ToMany(referencedJoinProperty = "recordId")
    private List<TaskData> taskDataList;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 180894066)
    private transient FlyRecordDao myDao;

    @Generated(hash = 2034449399)
    private transient Long patrolTask__resolvedKey;

    @Generated(hash = 2076042867)
    public FlyRecord() {
    }

    @Generated(hash = 670240288)
    public FlyRecord(Long id, String airLineTaskId, double latOffset, double lngOffset, Long taskId, Date createDate, Date startTime, Date endTime, String productName,
                     String productSerialNumber, String batterySerialNumber, String cameraSerialNumber, String uavTrackerSerialNumber, boolean isUpload, boolean isImagesUpload,
                     double distance, double maxHeight, double longitude, double latitude, int slantingType, boolean isSimulation, int dataCount, int batteryDischargeNumber,
                     int batteryLife, String airlineVersion, String rtkAccount) {
        this.id = id;
        this.airLineTaskId = airLineTaskId;
        this.latOffset = latOffset;
        this.lngOffset = lngOffset;
        this.taskId = taskId;
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.productName = productName;
        this.productSerialNumber = productSerialNumber;
        this.batterySerialNumber = batterySerialNumber;
        this.cameraSerialNumber = cameraSerialNumber;
        this.uavTrackerSerialNumber = uavTrackerSerialNumber;
        this.isUpload = isUpload;
        this.isImagesUpload = isImagesUpload;
        this.distance = distance;
        this.maxHeight = maxHeight;
        this.longitude = longitude;
        this.latitude = latitude;
        this.slantingType = slantingType;
        this.isSimulation = isSimulation;
        this.dataCount = dataCount;
        this.batteryDischargeNumber = batteryDischargeNumber;
        this.batteryLife = batteryLife;
        this.airlineVersion = airlineVersion;
        this.rtkAccount = rtkAccount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSerialNumber() {
        return this.productSerialNumber;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    public String getBatterySerialNumber() {
        return this.batterySerialNumber;
    }

    public void setBatterySerialNumber(String batterySerialNumber) {
        this.batterySerialNumber = batterySerialNumber;
    }

    public boolean getIsUpload() {
        return this.isUpload;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }

    public boolean isImagesUpload() {
        return isImagesUpload;
    }

    public void setImagesUpload(boolean imagesUpload) {
        isImagesUpload = imagesUpload;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1193077256)
    public List<FlyRecordDetail> getFlyRecordDetailList() {
        if (flyRecordDetailList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FlyRecordDetailDao targetDao = daoSession.getFlyRecordDetailDao();
            List<FlyRecordDetail> flyRecordDetailListNew = targetDao._queryFlyRecord_FlyRecordDetailList(id);
            synchronized (this) {
                if (flyRecordDetailList == null) {
                    flyRecordDetailList = flyRecordDetailListNew;
                }
            }
        }
        return flyRecordDetailList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1194046618)
    public synchronized void resetFlyRecordDetailList() {
        flyRecordDetailList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public int getSlantingType() {
        return this.slantingType;
    }

    public void setSlantingType(int slantingType) {
        this.slantingType = slantingType;
    }

    public boolean getIsSimulation() {
        return this.isSimulation;
    }

    public void setIsSimulation(boolean isSimulation) {
        this.isSimulation = isSimulation;
    }

    public int getDataCount() {
        return this.dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public double getMaxHeight() {
        return this.maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 754060846)
    public List<TaskData> getTaskDataList() {
        if (taskDataList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskDataDao targetDao = daoSession.getTaskDataDao();
            List<TaskData> taskDataListNew = targetDao._queryFlyRecord_TaskDataList(id);
            synchronized (this) {
                if (taskDataList == null) {
                    taskDataList = taskDataListNew;
                }
            }
        }
        return taskDataList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 941477347)
    public synchronized void resetTaskDataList() {
        taskDataList = null;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 561387121)
    public PatrolTask getPatrolTask() {
        Long __key = this.taskId;
        if (patrolTask__resolvedKey == null || !patrolTask__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PatrolTaskDao targetDao = daoSession.getPatrolTaskDao();
            PatrolTask patrolTaskNew = targetDao.load(__key);
            synchronized (this) {
                patrolTask = patrolTaskNew;
                patrolTask__resolvedKey = __key;
            }
        }
        return patrolTask;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1382146527)
    public void setPatrolTask(PatrolTask patrolTask) {
        synchronized (this) {
            this.patrolTask = patrolTask;
            taskId = patrolTask == null ? null : patrolTask.getId();
            patrolTask__resolvedKey = taskId;
        }
    }

    public String getCameraSerialNumber() {
        return this.cameraSerialNumber;
    }

    public void setCameraSerialNumber(String cameraSerialNumber) {
        this.cameraSerialNumber = cameraSerialNumber;
    }

    public int getBatteryDischargeNumber() {
        return this.batteryDischargeNumber;
    }

    public void setBatteryDischargeNumber(int batteryDischargeNumber) {
        this.batteryDischargeNumber = batteryDischargeNumber;
    }

    public int getBatteryLife() {
        return this.batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public String getAirlineVersion() {
        return this.airlineVersion;
    }

    public void setAirlineVersion(String airlineVersion) {
        this.airlineVersion = airlineVersion;
    }

    public String getUavTrackerSerialNumber() {
        return this.uavTrackerSerialNumber;
    }

    public void setUavTrackerSerialNumber(String uavTrackerSerialNumber) {
        this.uavTrackerSerialNumber = uavTrackerSerialNumber;
    }

    public String getRtkAccount() {
        return this.rtkAccount;
    }

    public void setRtkAccount(String rtkAccount) {
        this.rtkAccount = rtkAccount;
    }

    public String getAirLineTaskId() {
        return this.airLineTaskId;
    }

    public void setAirLineTaskId(String airLineTaskId) {
        this.airLineTaskId = airLineTaskId;
    }

    public double getLatOffset() {
        return latOffset;
    }

    public void setLatOffset(double latOffset) {
        this.latOffset = latOffset;
    }

    public double getLngOffset() {
        return lngOffset;
    }

    public void setLngOffset(double lngOffset) {
        this.lngOffset = lngOffset;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1661770960)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFlyRecordDao() : null;
    }

    public boolean getIsImagesUpload() {
        return this.isImagesUpload;
    }

    public void setIsImagesUpload(boolean isImagesUpload) {
        this.isImagesUpload = isImagesUpload;
    }

}

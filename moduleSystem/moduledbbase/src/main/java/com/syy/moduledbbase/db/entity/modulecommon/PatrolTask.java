package com.syy.moduledbbase.db.entity.modulecommon;


import com.syy.moduledbbase.db.greedao.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.syy.moduledbbase.db.greedao.FlyRecordDao;
import com.syy.moduledbbase.db.greedao.TaskMarkerDao;
import com.syy.moduledbbase.db.greedao.TaskParamsDao;
import com.syy.moduledbbase.db.greedao.PatrolTaskDao;

/**
 * Created by smilelu on 2018/4/14.
 */
@Entity
public class PatrolTask implements Serializable {

    private static final long serialVersionUID = 3401096050474431422L;

    @Id(autoincrement = true)
    private Long id;

    private String taskName;

    private int taskStatus; //0:未完成，1:已完成

    private int subTaskStatus = 0; //倾斜飞行子任务状态（按位）

    private int taskType;//任务类型 参看SpConstrants.PatrolType

    private int shapeType; //区域类型 见CustomShape

    private Date createDate;

    private Date startDate;//任务开始时间

    private Date endDate;//任务结束时间

    private Double flyHeight;//飞行高度

    private Double resolution; //分辨率

    private String screenshotPath;//快照路径

    private Long taskParamsId;

    @ToOne(joinProperty = "taskParamsId")
    private TaskParams taskParams;

    @ToMany(referencedJoinProperty = "taskId")
    private List<TaskMarker> taskMarkers;

    @ToMany(referencedJoinProperty = "taskId")
    @OrderBy(value = "createDate DESC")
    private volatile List<FlyRecord> flyRecord;

    @Transient
    private Boolean isChecked = false; //载入任务页是否选中

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 2113976851)
    private transient PatrolTaskDao myDao;

    @Generated(hash = 1715482690)
    private transient Long taskParams__resolvedKey;


    @Generated(hash = 1346356933)
    public PatrolTask() {
    }

    @Generated(hash = 300366406)
    public PatrolTask(Long id, String taskName, int taskStatus, int subTaskStatus, int taskType,
                      int shapeType, Date createDate, Date startDate, Date endDate, Double flyHeight,
                      Double resolution, String screenshotPath, Long taskParamsId) {
        this.id = id;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.subTaskStatus = subTaskStatus;
        this.taskType = taskType;
        this.shapeType = shapeType;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.flyHeight = flyHeight;
        this.resolution = resolution;
        this.screenshotPath = screenshotPath;
        this.taskParamsId = taskParamsId;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "PatrolTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskStatus=" + taskStatus +
                ", subTaskStatus=" + subTaskStatus +
                ", taskType=" + taskType +
                ", shapeType=" + shapeType +
                ", createDate=" + createDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", flyHeight=" + flyHeight +
                ", resolution=" + resolution +
                ", screenshotPath='" + screenshotPath + '\'' +
                ", taskParamsId=" + taskParamsId +
                ", taskParams=" + taskParams +
                ", taskMarkers=" + taskMarkers +
                ", isChecked=" + isChecked +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", taskParams__resolvedKey=" + taskParams__resolvedKey +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskType() {
        return this.taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getShapeType() {
        return this.shapeType;
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getFlyHeight() {
        return this.flyHeight;
    }

    public void setFlyHeight(Double flyHeight) {
        this.flyHeight = flyHeight;
    }

    public Double getResolution() {
        return this.resolution;
    }

    public void setResolution(Double resolution) {
        this.resolution = resolution;
    }

    public String getScreenshotPath() {
        return this.screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

    public int getSubTaskStatus() {
        return this.subTaskStatus;
    }

    public void setSubTaskStatus(int subTaskStatus) {
        this.subTaskStatus = subTaskStatus;
    }

    public Long getTaskParamsId() {
        return this.taskParamsId;
    }

    public void setTaskParamsId(Long taskParamsId) {
        this.taskParamsId = taskParamsId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 327370704)
    public TaskParams getTaskParams() {
        Long __key = this.taskParamsId;
        if (taskParams__resolvedKey == null || !taskParams__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskParamsDao targetDao = daoSession.getTaskParamsDao();
            TaskParams taskParamsNew = targetDao.load(__key);
            synchronized (this) {
                taskParams = taskParamsNew;
                taskParams__resolvedKey = __key;
            }
        }
        return taskParams;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 638903731)
    public void setTaskParams(TaskParams taskParams) {
        synchronized (this) {
            this.taskParams = taskParams;
            taskParamsId = taskParams == null ? null : taskParams.getId();
            taskParams__resolvedKey = taskParamsId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 57596454)
    public List<TaskMarker> getTaskMarkers() {
        if (taskMarkers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskMarkerDao targetDao = daoSession.getTaskMarkerDao();
            List<TaskMarker> taskMarkersNew = targetDao._queryPatrolTask_TaskMarkers(id);
            synchronized (this) {
                if (taskMarkers == null) {
                    taskMarkers = taskMarkersNew;
                }
            }
        }
        return taskMarkers;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1532999646)
    public synchronized void resetTaskMarkers() {
        taskMarkers = null;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 648903274)
    public List<FlyRecord> getFlyRecord() {
        if (flyRecord == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FlyRecordDao targetDao = daoSession.getFlyRecordDao();
            List<FlyRecord> flyRecordNew = targetDao._queryPatrolTask_FlyRecord(id);
            synchronized (this) {
                if (flyRecord == null) {
                    flyRecord = flyRecordNew;
                }
            }
        }
        return flyRecord;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1614068868)
    public synchronized void resetFlyRecord() {
        flyRecord = null;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1767424397)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPatrolTaskDao() : null;
    }

}

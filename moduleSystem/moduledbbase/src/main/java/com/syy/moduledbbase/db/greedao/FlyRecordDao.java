package com.syy.moduledbbase.db.greedao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.syy.moduledbbase.db.entity.modulecommon.PatrolTask;

import com.syy.moduledbbase.db.entity.modulecommon.FlyRecord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FLY_RECORD".
*/
public class FlyRecordDao extends AbstractDao<FlyRecord, Long> {

    public static final String TABLENAME = "FLY_RECORD";

    /**
     * Properties of entity FlyRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AirLineTaskId = new Property(1, String.class, "airLineTaskId", false, "AIR_LINE_TASK_ID");
        public final static Property LatOffset = new Property(2, double.class, "latOffset", false, "LAT_OFFSET");
        public final static Property LngOffset = new Property(3, double.class, "lngOffset", false, "LNG_OFFSET");
        public final static Property TaskId = new Property(4, Long.class, "taskId", false, "TASK_ID");
        public final static Property CreateDate = new Property(5, java.util.Date.class, "createDate", false, "CREATE_DATE");
        public final static Property StartTime = new Property(6, java.util.Date.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(7, java.util.Date.class, "endTime", false, "END_TIME");
        public final static Property ProductName = new Property(8, String.class, "productName", false, "PRODUCT_NAME");
        public final static Property ProductSerialNumber = new Property(9, String.class, "productSerialNumber", false, "PRODUCT_SERIAL_NUMBER");
        public final static Property BatterySerialNumber = new Property(10, String.class, "batterySerialNumber", false, "BATTERY_SERIAL_NUMBER");
        public final static Property CameraSerialNumber = new Property(11, String.class, "cameraSerialNumber", false, "CAMERA_SERIAL_NUMBER");
        public final static Property UavTrackerSerialNumber = new Property(12, String.class, "uavTrackerSerialNumber", false, "UAV_TRACKER_SERIAL_NUMBER");
        public final static Property IsUpload = new Property(13, boolean.class, "isUpload", false, "IS_UPLOAD");
        public final static Property IsImagesUpload = new Property(14, boolean.class, "isImagesUpload", false, "IS_IMAGES_UPLOAD");
        public final static Property Distance = new Property(15, double.class, "distance", false, "DISTANCE");
        public final static Property MaxHeight = new Property(16, double.class, "maxHeight", false, "MAX_HEIGHT");
        public final static Property Longitude = new Property(17, double.class, "longitude", false, "LONGITUDE");
        public final static Property Latitude = new Property(18, double.class, "latitude", false, "LATITUDE");
        public final static Property SlantingType = new Property(19, int.class, "slantingType", false, "SLANTING_TYPE");
        public final static Property IsSimulation = new Property(20, boolean.class, "isSimulation", false, "IS_SIMULATION");
        public final static Property DataCount = new Property(21, int.class, "dataCount", false, "DATA_COUNT");
        public final static Property BatteryDischargeNumber = new Property(22, int.class, "batteryDischargeNumber", false, "BATTERY_DISCHARGE_NUMBER");
        public final static Property BatteryLife = new Property(23, int.class, "batteryLife", false, "BATTERY_LIFE");
        public final static Property AirlineVersion = new Property(24, String.class, "airlineVersion", false, "AIRLINE_VERSION");
        public final static Property RtkAccount = new Property(25, String.class, "rtkAccount", false, "RTK_ACCOUNT");
    }

    private DaoSession daoSession;

    private Query<FlyRecord> patrolTask_FlyRecordQuery;

    public FlyRecordDao(DaoConfig config) {
        super(config);
    }
    
    public FlyRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FLY_RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"AIR_LINE_TASK_ID\" TEXT," + // 1: airLineTaskId
                "\"LAT_OFFSET\" REAL NOT NULL ," + // 2: latOffset
                "\"LNG_OFFSET\" REAL NOT NULL ," + // 3: lngOffset
                "\"TASK_ID\" INTEGER," + // 4: taskId
                "\"CREATE_DATE\" INTEGER," + // 5: createDate
                "\"START_TIME\" INTEGER," + // 6: startTime
                "\"END_TIME\" INTEGER," + // 7: endTime
                "\"PRODUCT_NAME\" TEXT," + // 8: productName
                "\"PRODUCT_SERIAL_NUMBER\" TEXT," + // 9: productSerialNumber
                "\"BATTERY_SERIAL_NUMBER\" TEXT," + // 10: batterySerialNumber
                "\"CAMERA_SERIAL_NUMBER\" TEXT," + // 11: cameraSerialNumber
                "\"UAV_TRACKER_SERIAL_NUMBER\" TEXT," + // 12: uavTrackerSerialNumber
                "\"IS_UPLOAD\" INTEGER NOT NULL ," + // 13: isUpload
                "\"IS_IMAGES_UPLOAD\" INTEGER NOT NULL ," + // 14: isImagesUpload
                "\"DISTANCE\" REAL NOT NULL ," + // 15: distance
                "\"MAX_HEIGHT\" REAL NOT NULL ," + // 16: maxHeight
                "\"LONGITUDE\" REAL NOT NULL ," + // 17: longitude
                "\"LATITUDE\" REAL NOT NULL ," + // 18: latitude
                "\"SLANTING_TYPE\" INTEGER NOT NULL ," + // 19: slantingType
                "\"IS_SIMULATION\" INTEGER NOT NULL ," + // 20: isSimulation
                "\"DATA_COUNT\" INTEGER NOT NULL ," + // 21: dataCount
                "\"BATTERY_DISCHARGE_NUMBER\" INTEGER NOT NULL ," + // 22: batteryDischargeNumber
                "\"BATTERY_LIFE\" INTEGER NOT NULL ," + // 23: batteryLife
                "\"AIRLINE_VERSION\" TEXT," + // 24: airlineVersion
                "\"RTK_ACCOUNT\" TEXT);"); // 25: rtkAccount
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FLY_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FlyRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String airLineTaskId = entity.getAirLineTaskId();
        if (airLineTaskId != null) {
            stmt.bindString(2, airLineTaskId);
        }
        stmt.bindDouble(3, entity.getLatOffset());
        stmt.bindDouble(4, entity.getLngOffset());
 
        Long taskId = entity.getTaskId();
        if (taskId != null) {
            stmt.bindLong(5, taskId);
        }
 
        java.util.Date createDate = entity.getCreateDate();
        if (createDate != null) {
            stmt.bindLong(6, createDate.getTime());
        }
 
        java.util.Date startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindLong(7, startTime.getTime());
        }
 
        java.util.Date endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindLong(8, endTime.getTime());
        }
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(9, productName);
        }
 
        String productSerialNumber = entity.getProductSerialNumber();
        if (productSerialNumber != null) {
            stmt.bindString(10, productSerialNumber);
        }
 
        String batterySerialNumber = entity.getBatterySerialNumber();
        if (batterySerialNumber != null) {
            stmt.bindString(11, batterySerialNumber);
        }
 
        String cameraSerialNumber = entity.getCameraSerialNumber();
        if (cameraSerialNumber != null) {
            stmt.bindString(12, cameraSerialNumber);
        }
 
        String uavTrackerSerialNumber = entity.getUavTrackerSerialNumber();
        if (uavTrackerSerialNumber != null) {
            stmt.bindString(13, uavTrackerSerialNumber);
        }
        stmt.bindLong(14, entity.getIsUpload() ? 1L: 0L);
        stmt.bindLong(15, entity.getIsImagesUpload() ? 1L: 0L);
        stmt.bindDouble(16, entity.getDistance());
        stmt.bindDouble(17, entity.getMaxHeight());
        stmt.bindDouble(18, entity.getLongitude());
        stmt.bindDouble(19, entity.getLatitude());
        stmt.bindLong(20, entity.getSlantingType());
        stmt.bindLong(21, entity.getIsSimulation() ? 1L: 0L);
        stmt.bindLong(22, entity.getDataCount());
        stmt.bindLong(23, entity.getBatteryDischargeNumber());
        stmt.bindLong(24, entity.getBatteryLife());
 
        String airlineVersion = entity.getAirlineVersion();
        if (airlineVersion != null) {
            stmt.bindString(25, airlineVersion);
        }
 
        String rtkAccount = entity.getRtkAccount();
        if (rtkAccount != null) {
            stmt.bindString(26, rtkAccount);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FlyRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String airLineTaskId = entity.getAirLineTaskId();
        if (airLineTaskId != null) {
            stmt.bindString(2, airLineTaskId);
        }
        stmt.bindDouble(3, entity.getLatOffset());
        stmt.bindDouble(4, entity.getLngOffset());
 
        Long taskId = entity.getTaskId();
        if (taskId != null) {
            stmt.bindLong(5, taskId);
        }
 
        java.util.Date createDate = entity.getCreateDate();
        if (createDate != null) {
            stmt.bindLong(6, createDate.getTime());
        }
 
        java.util.Date startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindLong(7, startTime.getTime());
        }
 
        java.util.Date endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindLong(8, endTime.getTime());
        }
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(9, productName);
        }
 
        String productSerialNumber = entity.getProductSerialNumber();
        if (productSerialNumber != null) {
            stmt.bindString(10, productSerialNumber);
        }
 
        String batterySerialNumber = entity.getBatterySerialNumber();
        if (batterySerialNumber != null) {
            stmt.bindString(11, batterySerialNumber);
        }
 
        String cameraSerialNumber = entity.getCameraSerialNumber();
        if (cameraSerialNumber != null) {
            stmt.bindString(12, cameraSerialNumber);
        }
 
        String uavTrackerSerialNumber = entity.getUavTrackerSerialNumber();
        if (uavTrackerSerialNumber != null) {
            stmt.bindString(13, uavTrackerSerialNumber);
        }
        stmt.bindLong(14, entity.getIsUpload() ? 1L: 0L);
        stmt.bindLong(15, entity.getIsImagesUpload() ? 1L: 0L);
        stmt.bindDouble(16, entity.getDistance());
        stmt.bindDouble(17, entity.getMaxHeight());
        stmt.bindDouble(18, entity.getLongitude());
        stmt.bindDouble(19, entity.getLatitude());
        stmt.bindLong(20, entity.getSlantingType());
        stmt.bindLong(21, entity.getIsSimulation() ? 1L: 0L);
        stmt.bindLong(22, entity.getDataCount());
        stmt.bindLong(23, entity.getBatteryDischargeNumber());
        stmt.bindLong(24, entity.getBatteryLife());
 
        String airlineVersion = entity.getAirlineVersion();
        if (airlineVersion != null) {
            stmt.bindString(25, airlineVersion);
        }
 
        String rtkAccount = entity.getRtkAccount();
        if (rtkAccount != null) {
            stmt.bindString(26, rtkAccount);
        }
    }

    @Override
    protected final void attachEntity(FlyRecord entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FlyRecord readEntity(Cursor cursor, int offset) {
        FlyRecord entity = new FlyRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // airLineTaskId
            cursor.getDouble(offset + 2), // latOffset
            cursor.getDouble(offset + 3), // lngOffset
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // taskId
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // createDate
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // startTime
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // endTime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // productName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // productSerialNumber
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // batterySerialNumber
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // cameraSerialNumber
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // uavTrackerSerialNumber
            cursor.getShort(offset + 13) != 0, // isUpload
            cursor.getShort(offset + 14) != 0, // isImagesUpload
            cursor.getDouble(offset + 15), // distance
            cursor.getDouble(offset + 16), // maxHeight
            cursor.getDouble(offset + 17), // longitude
            cursor.getDouble(offset + 18), // latitude
            cursor.getInt(offset + 19), // slantingType
            cursor.getShort(offset + 20) != 0, // isSimulation
            cursor.getInt(offset + 21), // dataCount
            cursor.getInt(offset + 22), // batteryDischargeNumber
            cursor.getInt(offset + 23), // batteryLife
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // airlineVersion
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25) // rtkAccount
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FlyRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAirLineTaskId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLatOffset(cursor.getDouble(offset + 2));
        entity.setLngOffset(cursor.getDouble(offset + 3));
        entity.setTaskId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setCreateDate(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setStartTime(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setEndTime(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setProductName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProductSerialNumber(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBatterySerialNumber(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCameraSerialNumber(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setUavTrackerSerialNumber(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setIsUpload(cursor.getShort(offset + 13) != 0);
        entity.setIsImagesUpload(cursor.getShort(offset + 14) != 0);
        entity.setDistance(cursor.getDouble(offset + 15));
        entity.setMaxHeight(cursor.getDouble(offset + 16));
        entity.setLongitude(cursor.getDouble(offset + 17));
        entity.setLatitude(cursor.getDouble(offset + 18));
        entity.setSlantingType(cursor.getInt(offset + 19));
        entity.setIsSimulation(cursor.getShort(offset + 20) != 0);
        entity.setDataCount(cursor.getInt(offset + 21));
        entity.setBatteryDischargeNumber(cursor.getInt(offset + 22));
        entity.setBatteryLife(cursor.getInt(offset + 23));
        entity.setAirlineVersion(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setRtkAccount(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FlyRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FlyRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FlyRecord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "flyRecord" to-many relationship of PatrolTask. */
    public List<FlyRecord> _queryPatrolTask_FlyRecord(Long taskId) {
        synchronized (this) {
            if (patrolTask_FlyRecordQuery == null) {
                QueryBuilder<FlyRecord> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TaskId.eq(null));
                queryBuilder.orderRaw("T.'CREATE_DATE' DESC");
                patrolTask_FlyRecordQuery = queryBuilder.build();
            }
        }
        Query<FlyRecord> query = patrolTask_FlyRecordQuery.forCurrentThread();
        query.setParameter(0, taskId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPatrolTaskDao().getAllColumns());
            builder.append(" FROM FLY_RECORD T");
            builder.append(" LEFT JOIN PATROL_TASK T0 ON T.\"TASK_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected FlyRecord loadCurrentDeep(Cursor cursor, boolean lock) {
        FlyRecord entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        PatrolTask patrolTask = loadCurrentOther(daoSession.getPatrolTaskDao(), cursor, offset);
        entity.setPatrolTask(patrolTask);

        return entity;    
    }

    public FlyRecord loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<FlyRecord> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<FlyRecord> list = new ArrayList<FlyRecord>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<FlyRecord> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<FlyRecord> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}

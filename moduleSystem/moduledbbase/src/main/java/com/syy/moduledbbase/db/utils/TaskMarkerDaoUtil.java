package com.syy.moduledbbase.db.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.Projection;
import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.modulecommon.PatrolTask;
import com.syy.moduledbbase.db.entity.modulecommon.TaskMarker;
import com.syy.moduledbbase.db.entity.moduleuav.CustomMarker;
import com.syy.moduledbbase.db.greedao.TaskMarkerDao;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by smilelu on 2018/4/16.
 */

public class TaskMarkerDaoUtil {

    private TaskMarkerDao taskMarkerDao;

    public TaskMarkerDaoUtil() throws Exception {
        taskMarkerDao =  DBManager.getInstance().getDaoSession().getTaskMarkerDao();
    }

    public Long insert(TaskMarker taskMarker) throws Exception {
        return taskMarkerDao.insert(taskMarker);
    }

    public void insertInTx(List<TaskMarker> taskMarkers) throws Exception {
        taskMarkerDao.insertInTx(taskMarkers);
    }


    public void deleteInTx(List<TaskMarker> taskMarkers) throws Exception {
        taskMarkerDao.deleteInTx(taskMarkers);
    }

    public List<TaskMarker> getTaskMarkers(Long taskId) throws Exception {
        return taskMarkerDao.queryBuilder().where(TaskMarkerDao.Properties.TaskId.eq(taskId)).list();
    }

    public List<CustomMarker> getCustomMarkersByTask(PatrolTask task, Projection projection) throws Exception {
        List<TaskMarker> taskMarkers = getTaskMarkers(task.getId());

        List<CustomMarker> markers = new ArrayList<>();
        for (TaskMarker taskMarker : taskMarkers) {
            markers.add(new CustomMarker(new LatLng(taskMarker.getLatitude(), taskMarker.getLongitude(), taskMarker.getFlightHeight()), taskMarker.getMarkerType(), projection));
        }
        return markers;
    }
}

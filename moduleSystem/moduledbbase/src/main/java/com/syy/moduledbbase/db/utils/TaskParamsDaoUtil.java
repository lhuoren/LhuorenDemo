package com.syy.moduledbbase.db.utils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.modulecommon.TaskParams;
import com.syy.moduledbbase.db.greedao.TaskParamsDao;

import java.util.List;


public class TaskParamsDaoUtil {

    private TaskParamsDao taskParamsDao;

    public TaskParamsDaoUtil() throws Exception {
        taskParamsDao =  DBManager.getInstance().getDaoSession().getTaskParamsDao();
    }

    public Long insert(TaskParams taskParams) throws Exception {
        return taskParamsDao.insert(taskParams);
    }

    public Long insertOrReplace(TaskParams taskParams) throws Exception {
        return taskParamsDao.insertOrReplace(taskParams);
    }


    public TaskParams getTaskParams(Long taskId) throws Exception {
        List<TaskParams> taskParamsList = taskParamsDao.queryBuilder().where(TaskParamsDao.Properties.Id.eq(taskId)).list();
        if (taskParamsList.size() > 0) {
            return taskParamsList.get(0);
        }
        return null;
    }
}

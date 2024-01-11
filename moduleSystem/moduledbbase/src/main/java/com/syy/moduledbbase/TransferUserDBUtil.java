package com.syy.moduledbbase;

import com.syy.moduledbbase.db.entity.modulecommon.FlyRecord;
import com.syy.moduledbbase.db.entity.modulecommon.FlyRecordDetail;
import com.syy.moduledbbase.db.entity.modulecommon.PatrolTask;
import com.syy.moduledbbase.db.entity.modulecommon.TaskData;
import com.syy.moduledbbase.db.entity.modulecommon.TaskMarker;
import com.syy.moduledbbase.db.entity.modulecommon.TaskParams;
import com.syy.moduledbbase.db.entity.moduleuav.FineStep;
import com.syy.moduledbbase.db.entity.moduleuav.ImageInfo;
import com.syy.moduledbbase.db.entity.moduleuav.channel.ChannelAirTaskBean;
import com.syy.moduledbbase.db.entity.moduleuav.fine.FineAirTaskBean;
import com.syy.moduledbbase.db.entity.moduleuav.panorama.PanoramaAirTaskBean;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.PlanTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.channel.PlanChannelAirTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.fine.PlanFineAirTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.line.ChildRoutes;
import com.syy.moduledbbase.db.entity.moduleuav.pointcloud.PointCloudAirTaskBean;
import com.syy.moduledbbase.db.utils.LogUtils;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

public class TransferUserDBUtil {

    Transferlistener transferlistener;
    long count;
    long current;

    public interface Transferlistener {
        void onSucceed();

        void onStart();

        void onProgress(long current, long count);

        void onFaild(String message);

        void onFinish();
    }

    public TransferUserDBUtil(Transferlistener transferlistener) {
        this.transferlistener = transferlistener;
        this.transferlistener.onStart();
        try {
//                count = getAllDataCount();
            LogUtils.e("TransferUserDBUtil", "开始了");
            transferFineStep();
            transferFlyRecord();
            transferFlyRecordDetail();
            transferPatrolTask();
            transferTaskData();
            transferTaskMarker();
            transferTaskParams();
            transferImageInfo();
            transferChannelAirTask();
            transferFineAirTask();
            transferPanoramaAirTask();
            transferPointCloudAirTask();
            transferPlanTask();
            transferChildRoutes();
            transferPlanChannelAirTask();
            transferPlanFineAirTask();
            LogUtils.e("TransferUserDBUtil", "迁移成功");
            this.transferlistener.onSucceed();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("TransferUserDBUtil", "迁移失败");
            this.transferlistener.onFaild("迁移失败");
        } finally {
            LogUtils.e("TransferUserDBUtil", "迁移完成");
            this.transferlistener.onFinish();
        }

    }

//    private long getFineStepCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getFineStepDao());
//    }
//
//    private long getFlyRecordCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getFlyRecordDao());
//    }
//
//    private long getFlyRecordDetailCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getFlyRecordDetailDao());
//    }
//
//    private long getPatrolTaskCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getPatrolTaskDao());
//    }
//
//    private long getTaskDataCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getTaskDataDao());
//    }
//
//    private long getTaskMarkerCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getTaskMarkerDao());
//    }
//
//    private long getTaskParamsCount() {
//        return readCount(DBManagerOld.getInstance().getDaoSession().getTaskParamsDao());
//    }

    private void transferFineStep() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFineStepDao());
//        transferDB(DBManager.getInstance().getDaoSession().getFineStepDao(), list);

        List<FineStep> list = (List<FineStep>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFineStepDao());
        DBManager.getInstance().getDaoSession().getFineStepDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferFineStep:" + list.size());
    }

    private void transferFlyRecord() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFlyRecordDao());
//        transferDB(DBManager.getInstance().getDaoSession().getFlyRecordDao(), list);

        List<FlyRecord> list = (List<FlyRecord>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFlyRecordDao());
        DBManager.getInstance().getDaoSession().getFlyRecordDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferFlyRecord:" + list.size());
    }

    private void transferFlyRecordDetail() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFlyRecordDetailDao());
//        transferDB(DBManager.getInstance().getDaoSession().getFlyRecordDetailDao(), list);

        List<FlyRecordDetail> list = (List<FlyRecordDetail>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFlyRecordDetailDao());
        DBManager.getInstance().getDaoSession().getFlyRecordDetailDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferFlyRecordDetail:" + list.size());
    }

    private void transferPatrolTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPatrolTaskDao());
//        transferDB(DBManager.getInstance().getDaoSession().getPatrolTaskDao(), list);

        List<PatrolTask> list = (List<PatrolTask>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPatrolTaskDao());
        DBManager.getInstance().getDaoSession().getPatrolTaskDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferPatrolTask:" + list.size());
    }

    private void transferTaskData() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getTaskDataDao());
//        transferDB(DBManager.getInstance().getDaoSession().getTaskDataDao(), list);

        List<TaskData> list = (List<TaskData>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getTaskDataDao());
        DBManager.getInstance().getDaoSession().getTaskDataDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferTaskData:" + list.size());
    }

    private void transferTaskMarker() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getTaskMarkerDao());
//        transferDB(DBManager.getInstance().getDaoSession().getTaskMarkerDao(), list);

        List<TaskMarker> list = (List<TaskMarker>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getTaskMarkerDao());
        DBManager.getInstance().getDaoSession().getTaskMarkerDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferTaskMarker:" + list.size());
    }

    private void transferTaskParams() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getTaskParamsDao());
//        transferDB(DBManager.getInstance().getDaoSession().getTaskParamsDao(), list);

        List<TaskParams> list = (List<TaskParams>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getTaskParamsDao());
        DBManager.getInstance().getDaoSession().getTaskParamsDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferTaskParams:" + list.size());
    }

    private void transferImageInfo() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getImageInfoDao());
//        transferDB(DBManager.getInstance().getDaoSession().getImageInfoDao(), list);

        List<ImageInfo> list = (List<ImageInfo>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getImageInfoDao());
        DBManager.getInstance().getDaoSession().getImageInfoDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferImageInfo:" + list.size());
    }

    private void transferChannelAirTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getChannelAirTaskBeanDao());
//        transferDB(DBManager.getInstance().getDaoSession().getChannelAirTaskBeanDao(), list);

        List<ChannelAirTaskBean> list = (List<ChannelAirTaskBean>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getChannelAirTaskBeanDao());
        DBManager.getInstance().getDaoSession().getChannelAirTaskBeanDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferChannelAirTask:" + list.size());
    }

    private void transferFineAirTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFineAirTaskBeanDao());
//        transferDB(DBManager.getInstance().getDaoSession().getFineAirTaskBeanDao(), list);

        List<FineAirTaskBean> list = (List<FineAirTaskBean>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getFineAirTaskBeanDao());
        DBManager.getInstance().getDaoSession().getFineAirTaskBeanDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferFineAirTask:" + list.size());
    }

    private void transferPanoramaAirTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPanoramaAirTaskBeanDao());
//        transferDB(DBManager.getInstance().getDaoSession().getPanoramaAirTaskBeanDao(), list);

        List<PanoramaAirTaskBean> list = (List<PanoramaAirTaskBean>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPanoramaAirTaskBeanDao());
        DBManager.getInstance().getDaoSession().getPanoramaAirTaskBeanDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferPanoramaAirTask:" + list.size());
    }

    private void transferPointCloudAirTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPointCloudAirTaskBeanDao());
//        transferDB(DBManager.getInstance().getDaoSession().getPointCloudAirTaskBeanDao(), list);

        List<PointCloudAirTaskBean> list = (List<PointCloudAirTaskBean>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPointCloudAirTaskBeanDao());
        DBManager.getInstance().getDaoSession().getPointCloudAirTaskBeanDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferPointCloudAirTask:" + list.size());
    }

    private void transferPlanTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPlanTaskDao());
//        transferDB(DBManager.getInstance().getDaoSession().getPlanTaskDao(), list);

        List<PlanTask> list = (List<PlanTask>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPlanTaskDao());
        DBManager.getInstance().getDaoSession().getPlanTaskDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferPlanTask:" + list.size());
    }

    private void transferChildRoutes() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getChildRoutesDao());
//        transferDB(DBManager.getInstance().getDaoSession().getChildRoutesDao(), list);

        List<ChildRoutes> list = (List<ChildRoutes>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getChildRoutesDao());
        DBManager.getInstance().getDaoSession().getChildRoutesDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferChildRoutes:" + list.size());
    }

    private void transferPlanChannelAirTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPlanChannelAirTaskDao());
//        transferDB(DBManager.getInstance().getDaoSession().getPlanChannelAirTaskDao(), list);

        List<PlanChannelAirTask> list = (List<PlanChannelAirTask>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPlanChannelAirTaskDao());
        DBManager.getInstance().getDaoSession().getPlanChannelAirTaskDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferPlanChannelAirTask:" + list.size());
    }

    private void transferPlanFineAirTask() {
//        List<?> list = readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPlanFineAirTaskDao());
//        transferDB(DBManager.getInstance().getDaoSession().getPlanFineAirTaskDao(), list);

        List<PlanFineAirTask> list = (List<PlanFineAirTask>) readDB(DBManagerDownLoadDB.getInstance().getDaoSession().getPlanFineAirTaskDao());
        DBManager.getInstance().getDaoSession().getPlanFineAirTaskDao().insertOrReplaceInTx(list);
        LogUtils.e("TransferUserDBUtil", "transferPlanFineAirTask:" + list.size());
    }

    private List<?> readDB(AbstractDao abstractDao) {
        List<?> list = abstractDao.loadAll();
        return list;
    }

//    private void transferDB(AbstractDao abstractDao, List<?> list) {
//        for (int i = 0; i < list.size(); i++) {
//            abstractDao.insertOrReplaceInTx(list.get(i));
////            sendProgress(++current);
//        }
//    }

    private long readCount(AbstractDao abstractDao) {
        long count = abstractDao.count();
        return count;
    }

//    private long getAllDataCount() {
//        return getFineStepCount() + getFlyRecordCount() + getFlyRecordDetailCount() + getPatrolTaskCount() + getTaskDataCount() + getTaskMarkerCount() + getTaskParamsCount();
//    }

//    private void sendProgress(long current) {
//        AppConfig.mHandler.post(() -> this.transferlistener.onProgress(current, count));
//    }

}

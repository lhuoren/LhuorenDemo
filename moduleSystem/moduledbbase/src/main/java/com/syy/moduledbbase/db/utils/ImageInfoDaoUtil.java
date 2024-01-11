package com.syy.moduledbbase.db.utils;

import android.text.TextUtils;

import com.syy.moduledbbase.DBManager;
import com.syy.moduledbbase.db.entity.moduleuav.ImageInfo;
import com.syy.moduledbbase.db.greedao.ImageInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class ImageInfoDaoUtil {

    private final ImageInfoDao imageInfoDao;

    public ImageInfoDaoUtil() throws Exception {
        imageInfoDao = DBManager.getInstance().getDaoSession().getImageInfoDao();
    }

    public Long insert(ImageInfo imageInfo) throws Exception {
        return imageInfoDao.insert(imageInfo);
    }

    public void update(ImageInfo imageInfo) throws Exception {
        imageInfoDao.update(imageInfo);
    }

    public Long insertOrReplace(ImageInfo imageInfo) throws Exception {
        return imageInfoDao.insertOrReplace(imageInfo);
    }

    public ImageInfo getImageInfoWithTakePhotoTime(long takePhotoTime) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.TakePhotoTime.eq(takePhotoTime)).unique();
    }

    public ImageInfo getImageInfoWithFileName(String fileName) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.ImageName.eq(fileName)).unique();
    }

    public List<ImageInfo> getImageInfoList() throws Exception {
        return imageInfoDao.queryBuilder().orderAsc(ImageInfoDao.Properties.TakePhotoTime).list();
    }

    public List<ImageInfo> getImageInfoNotUpList(String isUpload) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.IsUpload.eq(isUpload)).orderAsc(ImageInfoDao.Properties.TakePhotoTime).list();
    }

    public List<ImageInfo> getImageInfoWithIsUploadAndUploadTypeAndAirLineTaskIdList(String isUpload, String uploadType, String airLineTaskId) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.IsUpload.eq(isUpload), ImageInfoDao.Properties.UploadType.eq(uploadType), ImageInfoDao.Properties.AirLineTaskId.eq(airLineTaskId)).orderAsc(ImageInfoDao.Properties.TakePhotoTime).list();
    }

    public List<ImageInfo> getImageInfoWithIsUploadAndUploadTypeAndAirlineVersionList(String isUpload, String uploadType, String airlineVersion) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.IsUpload.eq(isUpload), ImageInfoDao.Properties.UploadType.eq(uploadType), ImageInfoDao.Properties.AirlineVersion.eq(airlineVersion)).orderAsc(ImageInfoDao.Properties.TakePhotoTime).list();
    }

    public List<ImageInfo> getImageInfoWithIsUploadAndUploadTypeList(String isUpload, String uploadType) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.IsUpload.eq(isUpload), ImageInfoDao.Properties.UploadType.eq(uploadType)).orderAsc(ImageInfoDao.Properties.TakePhotoTime).list();
    }

    public List<ImageInfo> getImageInfoWithFlyType(String flyType) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.FlyType.eq(flyType)).list();
    }

    public List<ImageInfo> getImageInfoWithUpLoadImageState(String state) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.UpLoadImageState.eq(state)).list();
    }

    public List<ImageInfo> getImageInfoWithTaskID(String taskId) throws Exception {
        return imageInfoDao.queryBuilder().where(ImageInfoDao.Properties.AirLineTaskId.eq(taskId)).list();
    }

    public void delete(ImageInfo imageInfo) throws Exception {
        imageInfoDao.delete(imageInfo);
    }

    public void deleteAll() throws Exception {
        imageInfoDao.deleteAll();
    }

}

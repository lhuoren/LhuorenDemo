package com.gac.nioapp.test.bean;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-10-24 15:41
 ** @describe TODO
 *******************************/
public class UpdateBean {
    /**
     * systemId : 1
     * system : 安卓
     * appId : 1
     * appName : gacnio
     * versionCode : 201907181447
     * versionName : 1.7
     * minAppVersionCode : 201904181447
     * apkUrl : hhtp
     * updateContent : 1、我是耿此心了；
     * 2、我是第二行更新；
     * 3、我是第三行更新；
     * releaseTime : 2019-07-10 14:47:24
     * isUpdate : false
     * isForceUpdate : false
     */

    private int systemId;
    private String system;
    private int appId;
    private String appName;
    private long versionCode;
    private String versionName;
    private long minAppVersionCode;
    private String apkUrl;
    private String updateContent;
    private String releaseTime;
    private boolean isUpdate;
    private boolean isForceUpdate;
    //热修复专用 beggin
    private int isFix;
    private String packageUrl;
    private int pathVersionCode;
    //热修复专用 end


    public int getPathVersionCode() {
        return pathVersionCode;
    }

    public void setPathVersionCode(int pathVersionCode) {
        this.pathVersionCode = pathVersionCode;
    }

    public boolean isFix() {
        return isFix == 1;
    }

    public void setIsFix(int isFix) {
        this.isFix = isFix;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getMinAppVersionCode() {
        return minAppVersionCode;
    }

    public void setMinAppVersionCode(long minAppVersionCode) {
        this.minAppVersionCode = minAppVersionCode;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(boolean isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }
}

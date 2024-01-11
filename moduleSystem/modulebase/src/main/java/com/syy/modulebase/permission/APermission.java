package com.syy.modulebase.permission;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.syy.modulebase.utils.DecimalUtil;
import com.syy.modulebase.utils.LogUtils;
import com.syy.modulebase.utils.ToastUtil;
import java.util.Arrays;


/**
 * Created by lhr on 2017/1/25.
 */

public class APermission {

    private Builder builder;

    public static Builder builder(FragmentActivity mActivity) {
        return new Builder(mActivity);
    }

    public static Builder builder(Activity mActivity) {
        return new Builder(mActivity);
    }

    public static class Builder {
        private String[] permissions;
        private final Activity mActivity;
        private PermissionListener permissionListener;
        private String tipContent = null;
        private int code = Permissions.PERMISSION_REQUEST_CODE;

        private Builder(FragmentActivity activity) {
            this.mActivity = activity;
        }

        private Builder(Activity activity) {
            this.mActivity = activity;
        }

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setTipContent(String tipContent) {
            this.tipContent = tipContent;
            return this;
        }

        public Builder callBack(PermissionListener permissionListener) {

            this.permissionListener = permissionListener;
            return this;
        }

        public Builder setPermissions(String[] permissions) {
            LogUtils.d("setPermissions", "permissionListener--" + "setPermissions" + Arrays.toString(permissions));
            this.permissions = permissions;
            return this;
        }

        public APermission request() {
            APermission axdPermission = new APermission();
            axdPermission.builder = this;
            if (!DecimalUtil.isEmpty(permissions)) {
                axdPermission.requestPermission();
            }
            if (mActivity instanceof PermissionActivity) {
                ((PermissionActivity) mActivity).setAxdPermission(axdPermission);
            }
            return axdPermission;
        }
    }

    PermissionListener getPermissionListener() {
        if (builder == null) {
            return null;
        }
        return builder.permissionListener;
    }

    private void requestPermission() {
        if (PermissionUtil.hasSelfPermissions(builder.mActivity, builder.permissions) || !DeviceHelper.isOverMarshmallow()) {
            builder.permissionListener.onPassed();
        } else {
            String[] unPassPermissions = PermissionUtil.cutPassPermissions(builder.mActivity, builder.permissions);
            if (unPassPermissions.length == 0) {
                builder.permissionListener.onPassed();
                return;
            }
            ActivityCompat.requestPermissions(builder.mActivity, unPassPermissions, builder.code);
        }
    }

    void onRequestPermissionsResult(Context context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if ((requestCode == builder.code && PermissionUtil.verifyPermissions(grantResults))
                || PermissionUtil.hasSelfPermissions(builder.mActivity, builder.permissions)) {
            builder.permissionListener.onPassed();
        } else {
            boolean neverAsk = !PermissionUtil.shouldShowRequestPermissionRationale(builder.mActivity, permissions);
            if (!builder.permissionListener.onDenied(neverAsk)) {
                if (neverAsk) {
                    ToastUtil.toastShortMessage("权限缺失");
                } else {
                    ToastUtil.toastShortMessage("权限缺失");
                }
            }

        }
    }

    /**
     * 从设置页面返回后
     */
    public void onSettingResult(@NonNull APermission axdPermission) {
        axdPermission.requestPermission();
    }

    public void destroy() {
        builder = null;
    }
}

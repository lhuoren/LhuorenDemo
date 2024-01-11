package com.gac.nioapp.test.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.gac.nioapp.test.DebugRouterConstant;
import com.gac.nioapp.test.R;
import com.gac.nioapp.test.mvppresenter.QrScanPresenter;
import com.gac.nioapp.test.mvpview.QrScanModel;
import com.gac.nioapp.test.mvpview.QrScanResultView;
import com.gac.nioapp.test.qrcode.core.QRCodeView;
import com.gac.nioapp.test.qrcode.zxing.ZXingView;
import com.gac.nioapp.test.router.ARouterManager;
import com.gac.nioapp.test.router.RouterConstant;
import com.gac.nioapp.test.screen.DpPxUtil;
import com.gac.nioapp.test.utils.DialogUtil;
import com.gac.nioapp.test.utils.MatisseUtil;
import com.gac.nioapp.test.view.topbar.TopBarView;
import com.syy.modulebase.http.constants.HttpKeyConstant;
import com.syy.modulebase.utils.StringUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mars.xlog.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2020-02-14 10:20
 ** @describe
 *******************************/
@Route(path = DebugRouterConstant.QR_SCAN_TEST_ACTIVITY)
public class QrScanTestActivity extends BaseDetailActivity<QrScanPresenter> implements QRCodeView.Delegate, QrScanResultView {
    private static final String TAG = "xyz" + QrScanTestActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    private static final int REQUEST_CODE_ACTIVE_CHARGING = 667;
    private boolean isFlashLightOn = false;
    private ZXingView mZXingView;
    private ImageView flashLight, fromAlbum;
    private TextView tvReload, tvError;
    private ConstraintLayout conError;
    private String qrResultInfo;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_qr_scan_test;
    }

    @Override
    protected void initView() {
        mZXingView = findViewById(R.id.zxingview);
        flashLight = findViewById(R.id.flashLight);
        fromAlbum = findViewById(R.id.fromAlbum);
        TopBarView topBarView = findViewById(R.id.topBarView);
        topBarView.getTopBarTitleView().setTextColor(getResources().getColor(R.color.white));
        mZXingView.setDelegate(this);
        mZXingView.setTipBackgroundRadius(DpPxUtil.dp2px(14));
        tvReload = findViewById(R.id.tvReload);
        conError = findViewById(R.id.con_error);
        tvError = findViewById(R.id.tvError);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        //申请权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
                        //mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

                        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                    } else {
                        showToastLong("扫描二维码需要打开相机和散光灯的权限");
                    }
                });

    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        qrResultInfo = result;
        vibrate();
        DialogUtil.dismissLoadingDialog(this);
        if (TextUtils.isEmpty(result)) {
            tvError.setText(R.string.qr_scan_result_null);
            conError.setVisibility(View.VISIBLE);
        } else {

            Intent intent = new Intent();
            intent.putExtra(RouterConstant.URL, result);
            intent.putExtra(RouterConstant.TITLE, "调试页面");
            ARouterManager.getInstance()
                    .startARActivityPble(RouterConstant.PATH_TO_WEBVIEW_ACTIVITY, intent);
        }
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText != null && tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }


    private void openAlbum() {
        MatisseUtil.openAlbum(currActivity, false, new ArrayList<>(), 1, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "返回结果");
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//            DialogUtil.showLoadingDialog(this, R.string.loading_str);
//            List<Uri> newUriList = Matisse.obtainResult(data);
//            final String picturePath = PhotoUtil.getRealFilePath(AppInfoManager.getInstance().getContext(), newUriList.get(0));
//            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
//            mZXingView.decodeQRCode(picturePath);
//            //没有用到 QRCodeView 时可以调用 QRCodeDecoder 的 syncDecodeQRCode 方法
//        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_ACTIVE_CHARGING) {
//            this.finish();
//        } else if (resultCode == Activity.RESULT_CANCELED && requestCode == REQUEST_CODE_ACTIVE_CHARGING) {
//            String msg = data.getStringExtra(HttpKeyConstant.DATA);
//            DialogUtil.show2LineAlertDialog(this, msg, R.drawable.act_windows_red_warn, "", "", "知道了", (bundle, positon) -> {
//
//            });
//        }
    }

    @Override
    public void qrScanResult(QrScanModel resultData) {
        //-1--未知 1--特来电 2--车机 3--加群二维码 4--新版车机扫码登陆或激活
        if (resultData.type == 2) {
            if (HttpKeyConstant.VEHICLE.equals(StringUtils.getUrlParameterReg(resultData.content, HttpKeyConstant.QR_YTPE))) {
                //车辆激活
                ARouterManager.getInstance().startARActivityWithString(RouterConstant.PATH_TO_CAR_ACTIVSTION,
                        HttpKeyConstant.DATA, resultData.content);
                this.finish();
            }
        } else if (resultData.type == 1) {
            //ActiveChargeLaunchActivity.startForResult(this, resultData.content, REQUEST_CODE_ACTIVE_CHARGING);
        } else if (resultData.type == 3) {
//            Uri uri = Uri.parse(resultData.content);
//            String groupId = uri.getQueryParameter("groupId");
//            String groupTitle = uri.getQueryParameter("groupTitle");
//            ChatUtils.startChat(this, groupId,
//                    groupTitle, TIMConversationType.Group);
//            this.finish();
        } else if (resultData.type == 4) {
//            CarMaintenanceWrapService.getInstance().qrActive(resultData.content,0);
//            this.finish();
        } else if (resultData.type == 5) {
            //车机登陆
//            CarMaintenanceWrapService.getInstance().qrLogin(resultData.content,0);
//            this.finish();
        } else {
//            tvError.setText(R.string.qr_scan_result_fail);
//            conError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void qrScanResultFailed(int code, String msg) {
        if (code == 8006) {
            //已经在群里了
            if (!TextUtils.isEmpty(qrResultInfo)) {
                QrScanModel model = new QrScanModel();
                model.type = 3;
                model.content = qrResultInfo;
                qrScanResult(model);
            }
        } else {
            tvError.setText(msg);
            conError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void writeOffResult(int type) {

    }

    @Override
    public void onRefreshing(boolean refreshing) {

    }

    @Override
    public void onHttpEmptySuccess(String msg) {

    }

    @Override
    public void onStatusLoading() {

    }

    @Override
    public void onHttpError(int code, String msg) {

    }

    @Override
    public void onHttpNetworkError(String msg) {

    }

    @Override
    public void onHttpDataGet(Object data) {

    }

    @Override
    public void onHttpCompleted() {

    }

    @Override
    protected void initListener() {
        //从相册
        fromAlbum.setOnClickListener(v -> {
            openAlbum();
        });
        //开灯
        flashLight.setOnClickListener(v -> {
            isFlashLightOn = !isFlashLightOn;
            if (isFlashLightOn) {
                mZXingView.openFlashlight(); // 打开闪光灯
            } else {
                mZXingView.closeFlashlight(); // 关闭闪光灯
            }
            flashLight.setActivated(isFlashLightOn);
        });

        flashLight.setActivated(isFlashLightOn);
        //重新扫描
        tvReload.setOnClickListener(v -> {
            presenter.onCallObservableDelay(2, new Observer() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(Object o) {
                    mZXingView.startSpotAndShowRect();
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
            conError.setVisibility(View.GONE);
        });
    }
}

package com.gac.nioapp.test.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.didichuxing.doraemonkit.util.ToastUtils;
import com.gac.nioapp.test.R;
import com.gac.nioapp.test.activity.BaseActivity;
import com.gac.nioapp.test.bean.CaptureStrategy;
import com.gac.nioapp.test.bean.ImageEngine;
import com.gac.nioapp.test.bean.SelectionSpec;
import com.gac.nioapp.test.fragment.BaseFragment;
import com.gac.nioapp.test.impl.GlideEngine;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @package： com.gac.matisse
 * @describe：
 * @author： liming
 * @time： 2019/4/3 4:19 PM
 * @e-mail： liming@gac-nio.com
 */
public class MatisseUtil {


    @SuppressLint("CheckResult")
    public static void openVideo(final Activity context, int requestCode) {
        try {
            //申请权限
            RxPermissions permissions = new RxPermissions(context);
            permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {
                            //打开图片选择
                            Matisse.from(context)
                                    .choose(MimeType.ofVideo())
                                    .maxVideoFileSize(500 * 1024 * 1024)
                                    .minVideoDuration(5_000)
                                    .maxVideoDuration(60_999)
                                    .showSingleMediaType(true)
                                    .countable(true)
                                    .showCheckView(false)
                                    .videoAutoPlay(true)
                                    .maxSelectable(1)
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                    .thumbnailScale(0.85f)
                                    .imageEngine((ImageEngine) new GlideEngine())
                                    .forResult(requestCode);

                        } else {
                            ToastUtils.showShort(R.string.alert_no_album_permission);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("CheckResult")
    public static void openVideo(final Activity context, Intent nextIntent) {
        //申请权限
        RxPermissions permissions = new RxPermissions(context);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(granted -> {
                    if (granted) {
                        //打开图片选择
                        Matisse.from(context)
                                .choose(MimeType.ofVideo())
                                .maxVideoFileSize(500 * 1024 * 1024)
                                .minVideoDuration(5_000)
                                .maxVideoDuration(60_999)
                                .showSingleMediaType(true)
                                .showCheckView(false)
                                .videoAutoPlay(true)
                                .countable(true)
                                .maxSelectable(1)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine((ImageEngine) new GlideEngine())
                                .start(nextIntent);
                    } else {
                        ToastUtils.showShort(R.string.alert_no_album_permission);
                    }
                });
    }

    /**
     * 负责打开相册选择图片，方法里面做了动态权限申请，外部无须再申请，选择的结果在activity的onActivityResult监听
     * 传入进来的requireCode，通过Matisse.obtainResult(Intent data) 直接获取列表
     *
     * @param context            当前页面activity
     * @param captureable        是否需要拍照
     * @param mimeType           需要选择的文件类型（MimeType.ofImage()/ofVideo()/ofAll()）
     * @param selectedList       已经选择了的图片列表，用于在选择页面标记
     * @param maxSelectable      最大选择个数（包括图片视频）
     * @param maxPhotoSelectable 最大图片选择数
     * @param maxVideoSelectable 最大视频选择数
     * @param maxVideoFileSize   视频大小限制（单位：MB）
     * @param requireCode        onActivityResult监听的code
     */
    @SuppressLint("CheckResult")
    public static void openAlbum(final BaseActivity context,
                                 final boolean captureable,
                                 final Set<MimeType> mimeType,
                                 final List<Uri> selectedList,
                                 final int maxSelectable,
                                 final int maxPhotoSelectable,
                                 final int maxVideoSelectable,
                                 final long maxVideoFileSize,
                                 final boolean videoAutoPlay,
                                 final int requireCode) {

        //申请权限
        RxPermissions permissions = new RxPermissions(context);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        //打开图片选择
                        SelectionCreator creator = Matisse.from(context)
                                .choose(mimeType, true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .selectedList(selectedList)
                                .capture(captureable)
                                .videoAutoPlay(videoAutoPlay)
                                .captureStrategy(
                                        new CaptureStrategy(true, context.getPackageName() + ".fileprovider"))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine((ImageEngine) new GlideEngine());
                        if (maxPhotoSelectable > 0 && maxVideoSelectable > 0) {
                            creator = creator.maxSelectablePerMediaType(maxPhotoSelectable, maxVideoSelectable);
                        } else {
                            creator = creator.maxSelectable(maxSelectable);
                        }
                        if (maxVideoFileSize > 0) {
                            creator = creator.maxVideoFileSize(maxVideoFileSize * 1024 * 1024);
                        }
                        creator.selectionListener((eventType, bundle, context1) -> {
                            if (eventType == SelectionSpec.EventType.TYPE_CONFLICT) {
                                DialogUtil.showAlertDialog((FragmentActivity) context1, "无法同时选择图片和视频。", "", "我知道了", null);
                            }
                        });
                        creator.forResult(requireCode);
                    } else {
                        context.showToastLong(R.string.alert_no_album_permission);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void openAlbum(final BaseActivity context, final boolean captureable, final List<Uri> selectedList,
                                 final int maxSelectable, final int requireCode) {
        openAlbum(context, captureable, MimeType.ofImage(), selectedList, maxSelectable, 0, 0, 0, false, requireCode);
    }

    /**
     * 负责打开相册选择图片，方法里面做了动态权限申请，外部无须再申请，选择的结果在activity的onActivityResult监听
     * 传入进来的requireCode，通过Matisse.obtainResult(Intent data) 直接获取列表
     *
     * @param context      当前页面activity
     * @param captureable  是否需要拍照
     * @param selectedList 已经选择了的图片列表，用于在选择页面标记
     * @param maxPhotoSize 最大选择图片数目
     * @param requireCode  onActivityResult监听的code
     */
    @SuppressLint("CheckResult")
    public static void openAlbum(final AppCompatActivity context, final boolean captureable, final List<Uri> selectedList,
                                 final int maxPhotoSize, final int requireCode) {

        //申请权限
        RxPermissions permissions = new RxPermissions(context);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        //打开图片选择
                        Matisse.from(context)
                                .choose(MimeType.ofImage(), true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .selectedList(selectedList)
                                .capture(captureable)
                                .captureStrategy(
                                        new CaptureStrategy(true, context.getPackageName() + ".fileprovider"))
                                .maxSelectable(maxPhotoSize)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine((ImageEngine) new GlideEngine())
                                .forResult(requireCode);

                    } else {
                        Toast.makeText(context, R.string.alert_no_album_permission, Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * 负责打开相册选择图片，方法里面做了动态权限申请，外部无须再申请，选择的结果在fragment的onActivityResult监听
     * 传入进来的requireCode，通过Matisse.obtainResult(Intent data) 直接获取列表
     *
     * @param fragment     当前页面 fragment
     * @param captureable  是否需要拍照
     * @param selectedList 已经选择了的图片列表，用于在选择页面标记
     * @param maxPhotoSize 最大选择图片数目
     * @param requireCode  onActivityResult监听的code
     */
    @SuppressLint("CheckResult")
    public static void openAlbum(final BaseFragment fragment, final boolean captureable, final List<Uri> selectedList,
                                 final int maxPhotoSize, final int requireCode) {

        //申请权限
        RxPermissions permissions = new RxPermissions(fragment.getActivity());
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        //打开图片选择
                        Matisse.from(fragment)
                                .choose(MimeType.ofImage(), true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .selectedList(selectedList)
                                .capture(captureable)
                                .captureStrategy(
                                        new CaptureStrategy(true, fragment.getActivity().getPackageName() + ".fileprovider"))
                                .maxSelectable(maxPhotoSize)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine((ImageEngine) new GlideEngine())
                                .forResult(requireCode);

                    } else {
                        fragment.showToastLong(R.string.alert_no_album_permission);
                    }
                });
    }

    /**
     * 调用相机方法，方法里面做了动态权限申请，外部无须再申请，选择的结果在activity的onActivityResult监听
     * 传入进来的requireCode，通过Matisse.obtainResult(Intent data) 直接获取列表
     *
     * @param context
     * @param requireCode
     */
    @SuppressLint("CheckResult")
    public static void openCamera(final BaseActivity context, final int requireCode) {
        //申请权限
        RxPermissions permissions = new RxPermissions(context);
        permissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        //打开图片选择
                        Matisse.from(context)
                                .choose(MimeType.ofImage(), true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .selectedList(new ArrayList<Uri>())
                                .capture(true)
                                .onlyTakePicture(true)
                                .captureStrategy(
                                        new CaptureStrategy(true, context.getPackageName() + ".fileprovider"))
                                .maxSelectable(1)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine((ImageEngine) new GlideEngine())
                                .forResult(requireCode);

                    } else {
                        context.showToastLong(R.string.alert_no_camera_permission);
                    }
                });
    }


    @SuppressLint("CheckResult")
    public static void openCamera(final AppCompatActivity context, final int requireCode) {
        //申请权限
        RxPermissions permissions = new RxPermissions(context);
        permissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        //打开图片选择
                        Matisse.from(context)
                                .choose(MimeType.ofImage(), true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .selectedList(new ArrayList<Uri>())
                                .capture(true)
                                .onlyTakePicture(true)
                                .captureStrategy(
                                        new CaptureStrategy(true, context.getPackageName() + ".fileprovider"))
                                .maxSelectable(1)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine((ImageEngine) new GlideEngine())
                                .forResult(requireCode);

                    } else {
                        ToastUtils.showLong(R.string.alert_no_camera_permission);
                    }
                });
    }
}

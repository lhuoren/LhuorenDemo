package com.gac.nioapp.test.utils;

import android.app.Activity;
import android.graphics.Bitmap;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.gac.nioapp.test.bean.MutibleSelectorBean;
import com.gac.nioapp.test.dialog.AlertDialogFragment;
import com.gac.nioapp.test.dialog.AlertDialogWithTitleFragment;
import com.gac.nioapp.test.dialog.BaseAlertDialogFragment;
import com.gac.nioapp.test.dialog.BaseDialogFragment;
import com.gac.nioapp.test.dialog.BottomAirTimeListDialog;
import com.gac.nioapp.test.dialog.BottomButtonDialog;
import com.gac.nioapp.test.dialog.BottomListDialog;
import com.gac.nioapp.test.dialog.BottomMutibleSelectorDialog;
import com.gac.nioapp.test.dialog.BottomPrePayDialog;
import com.gac.nioapp.test.dialog.SignUpAlertDialogFragment;

import java.util.List;

/**
 * Created by Sai on 2018/4/12.
 * Dialog通用类，不同的dialog用不同的Tag，在此类里面拓展不同类型
 */

public class DialogUtil {
    private static final String TAG_IDEA_SUCCESS = "idea_commit_success";

    public static final String TAG_BOTTOM = "BottomListDialog";
    public static String TAG_ALERT = "alert";
    public static String TAG_UPDATE = "app_update";
    public static final String TAG_2L_ALERT = "2l_alert";
    public static String TAG_PAY_DIALOG = "pay_dialog";
    public static String TAG_BTN_DIALOG = "btn_dialog";
    public static String TAG_WHISTLELIGH_DIALOG = "whistleligh_dialog";
    public static String TAG_PINCODE_DIALOG = "btn_pincode_dialog";
    public static String TAG_PICKER_DIALOG = "picker_dialog";
    private static final String TAG_GENERATE_POSTER_DIALOG = "generate_poster";
    private static final String TAG_QRCODE_DIALOG = "qr_code_dialog";
    private static final String TAG_ALERT_WITH_BUTTON = "alertWithButton";

    public static void showDialog(FragmentActivity context, BaseDialogFragment dialogFragment, String tag) {
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), tag);
    }

    public static void dismissDialog(FragmentActivity context, String tag) {
        FragmentManager manager = context.getSupportFragmentManager();
        if (manager != null) {
            Fragment fragment = manager.findFragmentByTag(tag);
            if (fragment != null) {
                if (fragment instanceof BaseDialogFragment) {
                    BaseDialogFragment dialogFragment = (BaseDialogFragment) fragment;
                    if (dialogFragment.isShowing())
                        dialogFragment.dismiss();
                } else if (fragment instanceof BaseAlertDialogFragment) {
                    BaseAlertDialogFragment dialogFragment = (BaseAlertDialogFragment) fragment;
                    if (dialogFragment.isShowing())
                        dialogFragment.dismiss();
                }
            }
        }
    }

    public static void dismissDialog(Fragment root, String tag) {
        FragmentManager manager = root.getChildFragmentManager();
        if (manager != null) {
            Fragment fragment = manager.findFragmentByTag(tag);
            if (fragment != null) {
                if (fragment instanceof BaseDialogFragment) {
                    BaseDialogFragment dialogFragment = (BaseDialogFragment) fragment;
                    if (dialogFragment.isShowing())
                        dialogFragment.dismiss();
                } else if (fragment instanceof BaseAlertDialogFragment) {
                    BaseAlertDialogFragment dialogFragment = (BaseAlertDialogFragment) fragment;
                    if (dialogFragment.isShowing())
                        dialogFragment.dismiss();
                }
            }
        }
    }

    public static void dismissAllDialog(FragmentActivity context) {
        dismissDialog(context.getSupportFragmentManager());
    }

    private static void dismissDialog(FragmentManager manager) {
        if (manager != null) {
            List<Fragment> fragments = manager.getFragments();
            if (fragments.size() > 0) {
                for (int i = 0; i < fragments.size(); i++) {
                    Fragment fragment = fragments.get(i);

                    if (fragment != null && fragment.getHost() != null) {
                        if (fragment instanceof DialogFragment) {
                            DialogFragment dialogFragment = (DialogFragment) fragment;
                            dialogFragment.dismiss();
                        } else if (fragment instanceof BaseAlertDialogFragment) {
                            BaseAlertDialogFragment dialogFragment = (BaseAlertDialogFragment) fragment;
                            if (dialogFragment.isShowing())
                                dialogFragment.dismiss();
                        } else {
                            dismissDialog(fragment.getChildFragmentManager());
                        }
                    }
                }
            }
        }
    }

    public static void showDialog(FragmentActivity context, BaseAlertDialogFragment dialogFragment, String tag) {
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), tag);
    }

    //loading----------------------------------------------------------

    public static void showLoadingDialog(FragmentActivity context) {
        if (null != context) {
            LoadingDialogUtil.showLoadingDialog(context, null);
        }
    }

    public static void showLoadingDialog(FragmentActivity context, final String msg) {
        if (null != context) {
            LoadingDialogUtil.showLoadingDialog(context, msg);
        }
    }

    public static void showLoadingDialog(FragmentActivity context, final int msg) {
        LoadingDialogUtil.showLoadingDialog(context, context.getApplicationContext().getString(msg));
    }


    /**
     * 关闭loading
     *
     * @param context
     */
    public static void dismissLoadingDialog(FragmentActivity context) {
        if (null != context) {
            LoadingDialogUtil.dismissDialog(context, LoadingDialogUtil.TAG_LOADING);
        }
    }
    //loading----------------------------------------------------------

    //alert----------------------------------------------------------

    public static void showAlertDialog(FragmentActivity context, final int msg, int middleText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        showAlertDialog(context, context.getApplicationContext().getString(msg), context.getApplicationContext().getString(middleText), "", onDialogHandleListener);
    }

    public static void showAlertDialog(FragmentActivity context, final String msg, int leftText, int rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        showAlertDialog(context, msg, context.getApplicationContext().getString(leftText), context.getApplicationContext().getString(rightText), onDialogHandleListener);
    }

    public static void showAlertDialog(FragmentActivity context, final int msg, int leftText, int rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        showAlertDialog(context, context.getApplicationContext().getString(msg), context.getApplicationContext().getString(leftText), context.getApplicationContext().getString(rightText), onDialogHandleListener);
    }


    public static void showAlertDialog(FragmentActivity context, String msg, String leftText, String rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_ALERT);
        BaseAlertDialogFragment dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = AlertDialogFragment.newInstance(msg, leftText, rightText, onDialogHandleListener, false);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_ALERT);
    }

    public static void showAlertDialog(Fragment context, String msg, String leftText, String rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        Fragment fragment = context.getChildFragmentManager().findFragmentByTag(TAG_ALERT);
        BaseAlertDialogFragment dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = AlertDialogFragment.newInstance(msg, leftText, rightText, onDialogHandleListener, false);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getChildFragmentManager(), TAG_ALERT);
    }

    public static void showAlertDialog(FragmentActivity context, @LayoutRes int layoutRes,
                                       String msg, String leftText, String rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_ALERT);
        BaseAlertDialogFragment dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = AlertDialogFragment.newInstance(layoutRes, msg, leftText, rightText, onDialogHandleListener, false);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_ALERT);
    }


    /**
     * 关闭AlertDialog
     *
     * @param context
     */
    public static void dismissAlertDialog(Activity context) {

        if (null != context) {
            if (!(context instanceof FragmentActivity)) {
                return;
            }
            FragmentActivity act = (FragmentActivity) context;
            dismissDialog(act, TAG_ALERT);
        }
    }


    /**
     * 单个确认按钮弹窗，带标题
     *
     * @param context
     * @param title
     * @param msg
     * @param rightText
     * @param onDialogHandleListener 0--左边按钮 1---右边按钮
     */
    public static void showAlertDialogWithTitle(FragmentActivity context, String title, String msg, String rightText, String leftText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_ALERT_WITH_BUTTON);
        BaseAlertDialogFragment dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = AlertDialogWithTitleFragment.newInstance(msg, title, rightText, leftText, onDialogHandleListener, false);

        }
        if (!dialogFragment.isShowing()) {
            dialogFragment.show(context.getSupportFragmentManager(), TAG_ALERT_WITH_BUTTON);
        }

    }

    /**
     * 关闭AlertDialog
     *
     * @param context
     */
    public static void dismissAlertDialogWithTitle(Activity context) {

        if (null != context) {
            if (!(context instanceof FragmentActivity)) {
                return;
            }
            FragmentActivity act = (FragmentActivity) context;
            dismissDialog(act, TAG_ALERT_WITH_BUTTON);
        }
    }


    //alert----------------------------------------------------------


    //app_pay_dialog----------------------------------------------------------

    public static void showPayDialog(FragmentActivity context, String data,
                                     BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        showPayDialog(context, data, BottomPrePayDialog.ACTION_FROM_NORMAL, onDialogHandleListener, canClose, null);
    }

    public static void showPayDialog(FragmentActivity context, String data,
                                     BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose, List<String> payTypes) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_PAY_DIALOG);
        BottomPrePayDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomPrePayDialog) fragment;
        } else {
            dialogFragment = BottomPrePayDialog.newInstance(data, BottomPrePayDialog.ACTION_FROM_NORMAL, onDialogHandleListener, canClose, payTypes);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_PAY_DIALOG);
    }

    public static void showPayDialog(FragmentActivity context, String data, int actionFrom,
                                     BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_PAY_DIALOG);
        BottomPrePayDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomPrePayDialog) fragment;
        } else {
            dialogFragment = BottomPrePayDialog.newInstance(data, actionFrom, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_PAY_DIALOG);
    }

    public static void showPayDialog(FragmentActivity context, String data, int actionFrom,
                                     BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose, List<String> payTypes) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_PAY_DIALOG);
        BottomPrePayDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomPrePayDialog) fragment;
        } else {
            dialogFragment = BottomPrePayDialog.newInstance(data, actionFrom, onDialogHandleListener, canClose, payTypes);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_PAY_DIALOG);
    }

    /**
     * 关闭UpdateDialog
     *
     * @param context
     */
    public static void dismissPayDialog(FragmentActivity context) {
        if (null != context) {
            dismissDialog(context, TAG_PAY_DIALOG);
        }
    }


    public static void showBottomBtnDialog(FragmentActivity context, String title, String tvBtn,
                                           BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BTN_DIALOG + title);
        BottomButtonDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomButtonDialog) fragment;
        } else {
            dialogFragment = BottomButtonDialog.newInstance(title, tvBtn, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BTN_DIALOG + title);
    }

    public static void showBottomBtnDialog(FragmentActivity context, String title, String tvBtn, String msg,
                                           BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BTN_DIALOG + title);
        BottomButtonDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomButtonDialog) fragment;
        } else {
            dialogFragment = BottomButtonDialog.newInstance(title, tvBtn, msg, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BTN_DIALOG + title);
    }

    /**
     * 关闭UpdateDialog
     *
     * @param context
     */
    public static void dismissBtnDialog(FragmentActivity context) {
        if (null != context) {
            dismissDialog(context, TAG_BTN_DIALOG);
        }
    }

    //app_bottom_dialog----------------------------------------------------------

    public static void showBottomDialog(FragmentActivity context, String[] data,
                                        BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BOTTOM);
        BottomListDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomListDialog) fragment;
        } else {
            dialogFragment = BottomListDialog.newInstance(data, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BOTTOM);
    }

    public static void showBottomDialog(FragmentActivity context, int[] icons, String[] data,
                                        BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BOTTOM);
        BottomListDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomListDialog) fragment;
        } else {
            dialogFragment = BottomListDialog.newInstance(icons, data, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BOTTOM);
    }

    public static void showBottomAirTimeDialog(FragmentActivity context, int selectTime, int[] icons, int[] data,
                                               BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BOTTOM);
        BottomAirTimeListDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomAirTimeListDialog) fragment;
        } else {
            dialogFragment = BottomAirTimeListDialog.newInstance(selectTime, icons, data, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BOTTOM);
    }

    public static void showBottomAirTimeDialog(FragmentActivity context, String title, int selectTime, int[] icons, int[] data,
                                               BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BOTTOM);
        BottomAirTimeListDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomAirTimeListDialog) fragment;
        } else {
            dialogFragment = BottomAirTimeListDialog.newInstance(title, selectTime, icons, data, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BOTTOM);
    }

    public static void showBottomMutibleSelectorDialog(FragmentActivity context, String title, List<MutibleSelectorBean> data,
                                                       BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_BOTTOM);
        BottomMutibleSelectorDialog dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BottomMutibleSelectorDialog) fragment;
        } else {
            dialogFragment = BottomMutibleSelectorDialog.newInstance(title, data, onDialogHandleListener, canClose);
        }
        if (!dialogFragment.isShowing()) {
            dialogFragment.show(context.getSupportFragmentManager(), TAG_BOTTOM);
        }
    }

    /**
     * 关闭UpdateDialog
     *
     * @param context
     */
    public static void dismissBottomDialog(FragmentActivity context) {
        if (null != context) {
            dismissDialog(context, TAG_BOTTOM);
        }
    }


    //2 Line alert----------------------------------------------------------

    public static void show2LineAlertDialog(@Nullable Activity context, CharSequence title, int imgResId, CharSequence msg, CharSequence leftText, CharSequence rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        if (context == null) {
            return;
        }
        if (!(context instanceof FragmentActivity)) {
            return;
        }
        BaseAlertDialogFragment dialogFragment = get2LineAlertDialog(context, title, imgResId, msg, leftText, rightText, onDialogHandleListener);
        if (!dialogFragment.isShowing())
            dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), TAG_2L_ALERT);
    }

    public static BaseAlertDialogFragment get2LineAlertDialog(@Nullable Activity context, CharSequence title, int imgResId, CharSequence msg, CharSequence leftText, CharSequence rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        FragmentActivity act = (FragmentActivity) context;

        Fragment fragment = act.getSupportFragmentManager().findFragmentByTag(TAG_2L_ALERT);
        BaseAlertDialogFragment dialogFragment;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = SignUpAlertDialogFragment.newInstance(title, imgResId, msg, leftText, rightText, onDialogHandleListener, false);
        }
        return dialogFragment;
    }


    public static void show2LineAlertDialog(@Nullable Fragment root, CharSequence title, int imgResId, CharSequence msg, CharSequence leftText, CharSequence rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener) {
        if (root == null) {
            return;
        }


        Fragment fragment = root.getChildFragmentManager().findFragmentByTag(TAG_2L_ALERT);
        BaseAlertDialogFragment dialogFragment = null;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = SignUpAlertDialogFragment.newInstance(title, imgResId, msg, leftText, rightText, onDialogHandleListener, false);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(root.getChildFragmentManager(), TAG_2L_ALERT);
    }

    /**
     * 关闭AlertDialog
     *
     * @param context
     */
    public static void dismiss2LineAlertDialog(FragmentActivity context) {
        if (null != context) {
            dismissDialog(context, TAG_2L_ALERT);
        }
    }


//    /**
//     * @param context
//     * @param canClose
//     */
//    public static void showNormalPosterDialog(FragmentActivity context, boolean canClose, Bitmap posterPath) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_GENERATE_POSTER_DIALOG);
//        NormalPosterDialog dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (NormalPosterDialog) fragment;
//        } else {
//            dialogFragment = NormalPosterDialog.newInstance(posterPath, canClose);
//        }
//        if (!dialogFragment.isShowing()) {
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_GENERATE_POSTER_DIALOG);
//        }
//    }

//    public static void showGeneratePosterDialog(FragmentActivity context, boolean canClose, String posterPath) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_GENERATE_POSTER_DIALOG);
//        PosterDialogFragment dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (PosterDialogFragment) fragment;
//        } else {
//            dialogFragment = PosterDialogFragment.newInstance(canClose, posterPath);
//        }
//        if (!dialogFragment.isShowing()) {
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_GENERATE_POSTER_DIALOG);
//        }
//    }
//
//    public static void showGeneratePosterDialog(FragmentActivity context, boolean canClose, Bitmap bitmap) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_GENERATE_POSTER_DIALOG);
//        PosterDialogFragment dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (PosterDialogFragment) fragment;
//        } else {
//            dialogFragment = PosterDialogFragment.newInstance(canClose, bitmap);
//        }
//        if (!dialogFragment.isShowing()) {
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_GENERATE_POSTER_DIALOG);
//        }
//    }
//
//    /**
//     * 关闭UpdateDialog
//     *
//     * @param context
//     */
//    public static void dismissGeneratePosterDialog(FragmentActivity context) {
//        if (null != context) {
//            DialogUtil.dismissDialog(context, TAG_GENERATE_POSTER_DIALOG);
//        }
//    }
//
//
//    //app_pay_dialog----------------------------------------------------------
//
//    public static void showPickerDialog(FragmentActivity context, List<PickerBean> optionsItems,
//                                        BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean canClose) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_PICKER_DIALOG);
//        BottomPickerDialog dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (BottomPickerDialog) fragment;
//        } else {
//            dialogFragment = BottomPickerDialog.newInstance(optionsItems, onDialogHandleListener, canClose);
//        }
//        if (!dialogFragment.isShowing())
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_PICKER_DIALOG);
//    }
//
//    /**
//     * 关闭UpdateDialog
//     *
//     * @param context
//     */
//    public static void dismissPickerDialog(FragmentActivity context) {
//        if (null != context) {
//            dismissDialog(context, TAG_PICKER_DIALOG);
//        }
//    }
//
//    //app_bottom_dialog----------------------------------------------------------
//
//    /**
//     * @param context
//     * @param
//     */
//    public static void showQrCodeDialog(FragmentActivity context, String url) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_QRCODE_DIALOG);
//        ShowQrcodeDialogFragment dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (ShowQrcodeDialogFragment) fragment;
//        } else {
//            dialogFragment = ShowQrcodeDialogFragment.newInstance(url);
//        }
//        if (!dialogFragment.isShowing()) {
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_QRCODE_DIALOG);
//        }
//    }
//
//    /**
//     * @param context
//     */
//    public static void dismissQrCodeDialog(FragmentActivity context) {
//        if (null != context) {
//            DialogUtil.dismissDialog(context, TAG_QRCODE_DIALOG);
//        }
//    }
//
//    ///idea提交dialog
//    public static void showSuccessDialog(FragmentActivity context, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, String topMsg, String msg) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_IDEA_SUCCESS);
//        BaseAlertDialogFragment dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (BaseAlertDialogFragment) fragment;
//        } else {
//            dialogFragment = IdeaSuccessDialogFragment.newInstance(onDialogHandleListener, false, topMsg, msg);
//        }
//        if (!dialogFragment.isShowing())
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_IDEA_SUCCESS);
//    }
//
//    ///idea提交dialog
//    public static void showSuccessBtnDialog(FragmentActivity context, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, String topMsg, String msg, String btn) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_IDEA_SUCCESS);
//        BaseAlertDialogFragment dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (BaseAlertDialogFragment) fragment;
//        } else {
//            dialogFragment = IdeaSuccessDialogFragment.newInstance(onDialogHandleListener, false, topMsg, msg, btn);
//        }
//        if (!dialogFragment.isShowing())
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_IDEA_SUCCESS);
//    }
//
//    public static void dismissSuccessDialog(FragmentActivity context) {
//        DialogUtil.dismissDialog(context, TAG_IDEA_SUCCESS);
//    }
//    ///idea提交dialog
//
//
//    public static void showInputPinCodeDialog(FragmentActivity context, GetPinCodeCallback callback) {
//        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_PINCODE_DIALOG);
//        InputPinCodeDialog dialogFragment = null;
//        if (fragment != null) {
//            dialogFragment = (InputPinCodeDialog) fragment;
//        } else {
//            dialogFragment = InputPinCodeDialog.newInstance(callback);
//        }
//        if (!dialogFragment.isShowing())
//            dialogFragment.show(context.getSupportFragmentManager(), TAG_PINCODE_DIALOG);
//    }

    public static void dismissPinCodeDialog(FragmentActivity context) {
        if (null != context) {
            dismissDialog(context, TAG_PINCODE_DIALOG);
        }
    }
}


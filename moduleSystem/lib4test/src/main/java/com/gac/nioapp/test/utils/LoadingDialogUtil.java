package com.gac.nioapp.test.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.gac.nioapp.test.dialog.BaseAlertDialogFragment;
import com.gac.nioapp.test.dialog.BaseDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by Sai on 2018/4/12.
 * Dialog通用类，不同的dialog用不同的Tag，在此类里面拓展不同类型
 */

public class LoadingDialogUtil {
    public static String TAG_LOADING = "loading";
    public static void showDialog(FragmentActivity context, BaseDialogFragment dialogFragment, String tag) {
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), tag);
    }

    public static void dismissDialog(FragmentActivity context, String tag) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            if (fragment instanceof BaseDialogFragment) {
                BaseDialogFragment dialogFragment = (BaseDialogFragment) fragment;
                if (dialogFragment.isShowing())
                    dialogFragment.dismiss();
            } else if (fragment instanceof BaseAlertDialogFragment) {
                BaseAlertDialogFragment dialogFragment = (BaseAlertDialogFragment) fragment;
                if (dialogFragment.isShowing())
                    dialogFragment.dismiss();
            }else if(fragment instanceof BottomSheetDialogFragment){
                BottomSheetDialogFragment dialogFragment = (BottomSheetDialogFragment) fragment;
                if (dialogFragment.isVisible())
                    dialogFragment.dismiss();
            }
        }
    }

    public static void dismissDialog(Fragment host, String tag) {
        Fragment fragment = host.getChildFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            if (fragment instanceof BaseDialogFragment) {
                BaseDialogFragment dialogFragment = (BaseDialogFragment) fragment;
                if (dialogFragment.isShowing())
                    dialogFragment.dismiss();
            } else if (fragment instanceof BaseAlertDialogFragment) {
                BaseAlertDialogFragment dialogFragment = (BaseAlertDialogFragment) fragment;
                if (dialogFragment.isShowing())
                    dialogFragment.dismiss();
            }else if(fragment instanceof BottomSheetDialogFragment){
                BottomSheetDialogFragment dialogFragment = (BottomSheetDialogFragment) fragment;
                if (dialogFragment.isVisible())
                    dialogFragment.dismiss();
            }
        }
    }

    /**
     * 在Fragment中调用showLoading时候无法找到Fragment，使用此方法dismiss
     * @param context
     * @param tag
     */
    public static void dismissDialogWhenCantFindFrag(FragmentActivity context, String tag) {
        context.getSupportFragmentManager().executePendingTransactions();
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(tag);
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

    public static void showDialog(AppCompatActivity context, BaseAlertDialogFragment dialogFragment, String tag) {
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), tag);
    }

    //loading----------------------------------------------------------

    public static void showLoadingDialog(FragmentActivity context) {
        if (null != context) {
            showLoadingDialog(context, null);
        }
    }

    public static void showLoadingDialog(FragmentActivity context, final int msg) {
        showLoadingDialog(context, context.getApplicationContext().getString(msg));
    }

    public static void showLoadingDialog(FragmentActivity context, final String msg) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentByTag(TAG_LOADING);
        BaseAlertDialogFragment dialogFragment;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = LoadingDialogFragment.newInstance(msg, false);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(context.getSupportFragmentManager(), TAG_LOADING);
    }

    public static void showLoadingDialog(Fragment host, final String msg) {
        Fragment fragment = host.getChildFragmentManager().findFragmentByTag(TAG_LOADING);
        BaseAlertDialogFragment dialogFragment;
        if (fragment != null) {
            dialogFragment = (BaseAlertDialogFragment) fragment;
        } else {
            dialogFragment = LoadingDialogFragment.newInstance(msg, false);
        }
        if (!dialogFragment.isShowing())
            dialogFragment.show(host.getChildFragmentManager(), TAG_LOADING);
    }


    public static void dismissLoadingDialog(Fragment host) {
        if (null != host) {
            dismissDialog(host, TAG_LOADING);
        }
    }



    /**
     * 关闭loading
     *
     * @param context
     */
    public static void dismissLoadingDialog(FragmentActivity context) {
        if (null != context) {
            dismissDialog(context, TAG_LOADING);
        }
    }

}


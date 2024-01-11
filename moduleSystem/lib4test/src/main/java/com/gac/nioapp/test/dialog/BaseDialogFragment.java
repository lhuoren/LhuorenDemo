package com.gac.nioapp.test.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gac.nioapp.test.BaseApplication;

/**
 * Created by Sai on 2018/4/12.
 * 通用的Dialog
 */

public class BaseDialogFragment extends AppCompatDialogFragment {
    public static final int DISMISS = -1;
    public static final int OK = 0;
    protected String TAG = "xyz" + this.getClass().getSimpleName();
    /**
     * 监听弹出窗是否被取消
     */
    protected OnDialogHandleListener onDialogHandleListener;

    /**
     * 回调获得需要显示的dialog
     */
    protected OnCallDialog onCallDialog;

    public interface OnDialogHandleListener {
        void onDialogHandle(Bundle bundle, int handle);
    }

    public interface OnCallDialog {
        Dialog getDialog(Context context);

        int getLayoutResID();
    }

    public static BaseDialogFragment newInstance(OnCallDialog call, boolean cancelable) {
        return newInstance(call, cancelable, null);
    }

    public static BaseDialogFragment newInstance(OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener) {
        BaseDialogFragment instance = new BaseDialogFragment();
        instance.setCancelable(cancelable);
        instance.onDialogHandleListener = onDialogHandleListener;
        instance.onCallDialog = call;
        return instance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (null == onCallDialog) {
            super.onCreateDialog(savedInstanceState);
        }
        Dialog dialog = null;
        try {
            dialog = onCallDialog.getDialog(requireActivity());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            setWindowParams();
        }
    }

    protected void setWindowParams() {
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;
        window.setAttributes(windowParams);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (onDialogHandleListener != null) {
            onDialogHandleListener.onDialogHandle(null, DISMISS);
        }
    }

    public boolean isShowing() {
        boolean isShowing = false;
        if (getDialog() != null)
            isShowing = getDialog().isShowing();
        return isShowing;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return getDialog().findViewById(id);
    }

    public void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(Context context, int msgId) {
        Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String msg) {
        Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int msgId) {
        Toast.makeText(BaseApplication.getInstance(), msgId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        // 这里吧原来的commit()方法换成了commitAllowingStateLoss()
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
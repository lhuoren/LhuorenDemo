package com.gac.nioapp.test.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gac.nioapp.test.R;
import com.syy.modulebase.utils.EventConstant;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/4/12 10:11
 */
public class AlertDialogFragment extends BaseAlertDialogFragment implements View.OnClickListener {
    public static final String MSG = "msg";
    public static final String LEFTTEXT = "leftText";
    public static final String RIGHTTEXT = "rightText";
    public static final String CANCELABLE = "cancelable";
    public static final String LAYOUT = "layout";
    private TextView tvMsg;
    private TextView tvLeft;
    private TextView tvRight;
    private View loBackgound,vDivider;
    private int layoutRes;
    private boolean cancelable = false;

    public static AlertDialogFragment newInstance(
            String msg,
            String leftText,
            String rightText,
            BaseDialogFragment.OnDialogHandleListener onDialogHandleListener,
            boolean cancelable) {
        return newInstance(0, msg
                , leftText, rightText, onDialogHandleListener, cancelable);
    }


    public static AlertDialogFragment newInstance(
            int layoutRes,
            String msg,
            String leftText,
            String rightText,
            BaseDialogFragment.OnDialogHandleListener onDialogHandleListener,
            boolean cancelable) {
        Bundle args = new Bundle();
        args.putString(MSG, msg);
        args.putString(LEFTTEXT, leftText);
        args.putString(RIGHTTEXT, rightText);
        args.putBoolean(CANCELABLE, cancelable);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.layoutRes = layoutRes;
        fragment.setArguments(args);
        fragment.onDialogHandleListener = onDialogHandleListener;
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loBackgound) {
            if (cancelable)
                dismiss();
            return;
        }
        int position = v.getId() == R.id.tvLeft ? EventConstant.ALERT_BUTTON_LEFT : EventConstant.ALERT_BUTTON_RIGHT;
        if (onDialogHandleListener != null) {
            onDialogHandleListener.onDialogHandle(null, position);
        }
        dismiss();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        cancelable = bundle.getBoolean(CANCELABLE);
        tvMsg.setText(bundle.getString(MSG));
        tvLeft.setText(bundle.getString(LEFTTEXT));
        tvRight.setText(bundle.getString(RIGHTTEXT));
        if (TextUtils.isEmpty(tvRight.getText())) {
            tvRight.setVisibility(View.GONE);
            vDivider.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(tvLeft.getText())) {
            tvLeft.setVisibility(View.GONE);
            vDivider.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        loBackgound = findViewById(R.id.loBackgound);
        tvMsg = findViewById(R.id.tvMsg);
        tvLeft = findViewById(R.id.tvLeft);
        tvRight = findViewById(R.id.tvRight);
        vDivider = findViewById(R.id.vDivider);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        if (cancelable)
            loBackgound.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResID() {
        return layoutRes > 0 ? layoutRes : R.layout.dialog_alert;
    }
}

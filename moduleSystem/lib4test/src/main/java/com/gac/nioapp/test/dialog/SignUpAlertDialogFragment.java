package com.gac.nioapp.test.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gac.nioapp.test.R;
import com.syy.modulebase.utils.EventConstant;

public class SignUpAlertDialogFragment extends BaseAlertDialogFragment implements View.OnClickListener {
    public static final String TYTLE_NAME = "titleName";
    public static final String TYTLE_IMG = "titleImg";
    public static final String MSG = "msg";
    public static final String LEFTTEXT = "leftText";
    public static final String RIGHTTEXT = "rightText";
    public static final String CANCELABLE = "cancelable";
    private TextView tvTitle;
    private ImageView imageView;
    private TextView tvMsg;
    private TextView tvLeft;
    private TextView tvRight;
    private View loBackgound;
    private boolean cancelable = false;
    private View breakLine;

    public static SignUpAlertDialogFragment newInstance(CharSequence name, int resId, CharSequence msg, CharSequence leftText, CharSequence rightText, BaseDialogFragment.OnDialogHandleListener onDialogHandleListener, boolean cancelable) {
        Bundle args = new Bundle();
        args.putCharSequence(TYTLE_NAME, name);
        args.putInt(TYTLE_IMG, resId);
        args.putCharSequence(MSG, msg);
        args.putCharSequence(LEFTTEXT, leftText);
        args.putCharSequence(RIGHTTEXT, rightText);
        args.putBoolean(CANCELABLE, cancelable);
        SignUpAlertDialogFragment fragment = new SignUpAlertDialogFragment();
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
        CharSequence title = bundle.getCharSequence(TYTLE_NAME);

        int imgResId = bundle.getInt(TYTLE_IMG, 0);

        if (imgResId > 0) {
            imageView.setImageResource(imgResId);
            imageView.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        if (imgResId == R.drawable.act_windows_red_warn_small) {
            tvTitle.setTextColor(Color.parseColor("#EC5042"));
        } else if (imgResId == R.drawable.ic_resultstate_success) {
            tvTitle.setTextColor(Color.parseColor("#44BD2B"));
        }

        CharSequence msg = bundle.getCharSequence(MSG);
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msg);
            if (imgResId > 0 && TextUtils.isEmpty(title)) {
                tvMsg.setCompoundDrawablesWithIntrinsicBounds(0, imgResId, 0, 0);
            }
        } else {
            tvMsg.setVisibility(View.GONE);
        }

        tvLeft.setText(bundle.getCharSequence(LEFTTEXT));
        tvRight.setText(bundle.getCharSequence(RIGHTTEXT));
        if (TextUtils.isEmpty(tvRight.getText())) {
            tvRight.setVisibility(View.GONE);
            breakLine.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(tvLeft.getText())) {
            tvLeft.setVisibility(View.GONE);
            breakLine.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        loBackgound = findViewById(R.id.loBackgound);
        tvTitle = findViewById(R.id.tvTitle);
        imageView = findViewById(R.id.ivIcon);
        tvMsg = findViewById(R.id.tvMsg);
        tvLeft = findViewById(R.id.tvLeft);
        tvRight = findViewById(R.id.tvRight);
        breakLine = findViewById(R.id.view_line);
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
        return R.layout.signup_dialog_alert;
    }
}

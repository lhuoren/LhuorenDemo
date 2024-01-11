package com.gac.nioapp.test.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.gac.nioapp.test.R;

public class BottomButtonDialog extends BaseBottomDialogFragment implements View.OnClickListener {
    private ImageView closeView;
    private TextView tvTitle;
    private TextView tvButton;
    private TextView tvNotices;
    private Bundle bundle;
    private String title;
    private String tvBtn;
    private String msg;
    public static BottomButtonDialog newInstance(String title,String tvBtn, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomButtonDialog dialogFragment = newInstance(title, tvBtn,"", new OnCallDialogImpl(){
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_button;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }

    public static BottomButtonDialog newInstance(String title,String tvBtn,String msg, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomButtonDialog dialogFragment = newInstance(title, tvBtn,msg, new OnCallDialogImpl(){
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_button;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }
    private static BottomButtonDialog newInstance(String title,String tvBtn,String msg, OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener) {
        BottomButtonDialog instance = new BottomButtonDialog();
        instance.title = title;
        instance.tvBtn = tvBtn;
        instance.msg = msg;
        instance.setCancelable(cancelable);
        instance.onDialogHandleListener = onDialogHandleListener;
        instance.onCallDialog = call;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    public void init() {
        bundle = new Bundle();
        initView();
        initData();
        initListener();
    }

    private void initData() {
        tvButton.setText(tvBtn);
        tvTitle.setText(title);

        if (!TextUtils.isEmpty(msg)) {
            tvNotices.setText(msg);
            tvNotices.setVisibility(View.VISIBLE);
        } else {
            tvNotices.setVisibility(View.GONE);
        }
    }

    private void initView() {
        closeView = findViewById(R.id.tvClose);
        tvButton = findViewById(R.id.tvButton);
        tvTitle = findViewById(R.id.tvTitle);
        tvNotices = findViewById(R.id.tvNotices);

    }

    private void initListener() {
        tvButton.setOnClickListener(this);
        closeView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvButton) {
            if (onDialogHandleListener != null) {
                onDialogHandleListener.onDialogHandle(bundle, v.getId());
            }
        }else if (v.getId() == R.id.tvClose){
            if (onDialogHandleListener != null) {
                onDialogHandleListener.onDialogHandle(bundle, BottomButtonDialog.DISMISS);
            }
        }
        dismiss();
    }
}

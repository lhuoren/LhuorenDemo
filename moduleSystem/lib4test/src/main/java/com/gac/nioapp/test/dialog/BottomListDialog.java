package com.gac.nioapp.test.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gac.nioapp.test.R;


/**
 * @Description：在底部弹出的dialog，显示字符串列表，最多显示4个
 * @Author：Sai
 * @Date：2019/4/10 18:30
 */
public class BottomListDialog extends BaseBottomDialogFragment implements View.OnClickListener {
    private String[] list;
    private int[] icons;
    private TextView itemView0;
    private TextView itemView1, itemView2, itemView3, itemView4;
    public static String BTN_TEXT = "btn_text";

    public static BottomListDialog newInstance(String[] list, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomListDialog dialogFragment = newInstance(null, list, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_list;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }

    public static BottomListDialog newInstance(int[] icons, String[] list, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomListDialog dialogFragment = newInstance(icons, list, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_list;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }

    private static BottomListDialog newInstance(int[] icons, String[] list, OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener) {
        BottomListDialog instance = new BottomListDialog();
        instance.list = list;
        instance.icons = icons;
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
        initView();
        initData();
        initListener();
    }

    private void initData() {
        if (list.length >= 2) {
            itemView0.setText(list[0]);
            itemView1.setText(list[1]);
            if (icons != null && icons.length >= 2 && icons[1] > 0) {
                itemView1.setGravity(Gravity.CENTER_VERTICAL);
                itemView1.setCompoundDrawablesWithIntrinsicBounds(icons[1], 0, 0, 0);
            } else {
                itemView1.setGravity(Gravity.CENTER);
            }
        }
        if (list.length >= 3) {
            itemView2.setText(list[2]);
            itemView2.setVisibility(View.VISIBLE);
            if (icons != null && icons.length >= 3 && icons[2] > 0) {
                itemView1.setGravity(Gravity.CENTER_VERTICAL);
                itemView1.setCompoundDrawablesWithIntrinsicBounds(icons[2], 0, 0, 0);
            } else {
                itemView1.setGravity(Gravity.CENTER);
            }
        }
        if (list.length >= 4) {
            itemView3.setText(list[3]);
            itemView3.setVisibility(View.VISIBLE);
            if (icons != null && icons.length >= 4 && icons[3] > 0) {
                itemView1.setGravity(Gravity.CENTER_VERTICAL);
                itemView1.setCompoundDrawablesWithIntrinsicBounds(icons[3], 0, 0, 0);
            } else {
                itemView1.setGravity(Gravity.CENTER);
            }
        }

        if (list.length >= 5) {
            itemView4.setText(list[4]);
            itemView4.setVisibility(View.VISIBLE);
            if (icons != null && icons.length >= 5 && icons[4] > 0) {
                itemView1.setGravity(Gravity.CENTER_VERTICAL);
                itemView1.setCompoundDrawablesWithIntrinsicBounds(icons[4], 0, 0, 0);
            } else {
                itemView1.setGravity(Gravity.CENTER);
            }
        }
    }

    private void initView() {
        itemView0 = findViewById(R.id.tv_bottom_item0);
        itemView1 = findViewById(R.id.tv_bottom_item1);
        itemView2 = findViewById(R.id.tv_bottom_item2);
        itemView3 = findViewById(R.id.tv_bottom_item3);
        itemView4 = findViewById(R.id.tv_bottom_item4);
    }

    private void initListener() {
        itemView0.setOnClickListener(this);
        itemView1.setOnClickListener(this);
        itemView2.setOnClickListener(this);
        itemView3.setOnClickListener(this);
        itemView4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String text = (String) ((TextView) v).getText();
        Bundle bundle = new Bundle();
        bundle.putString(BTN_TEXT, text);
        onDialogHandleListener.onDialogHandle(bundle, v.getId());
        dismiss();
    }
}

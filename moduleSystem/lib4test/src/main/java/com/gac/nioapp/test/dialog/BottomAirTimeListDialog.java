package com.gac.nioapp.test.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gac.nioapp.test.R;

/**
 * @Description：在底部弹出的dialog，显示字符串列表，最多显示4个
 * @Author：Sai
 * @Date：2019/4/10 18:30
 */
public class BottomAirTimeListDialog extends BaseBottomDialogFragment implements View.OnClickListener {
    private int[] list;
    private int[] icons;
    private LinearLayout itemView0;
    private LinearLayout itemView1;
    private LinearLayout itemView2;
    private LinearLayout itemView3;
    private ImageView ivItemView0, ivItemView1, ivItemView2, ivItemView3,ivClose;
    private TextView tvItemView0, tvItemView1, tvItemView2, tvItemView3;
    private TextView tvTitle;
    private int selectTime;
    public static String BTN_TEXT = "btn_text";
    public static String TITLE_TEXT = "空调开启时长";

    public static BottomAirTimeListDialog newInstance(int select, int[] icons, int[] list, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomAirTimeListDialog dialogFragment = newInstance(select, icons, list, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_air_time_list;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }

    public static BottomAirTimeListDialog newInstance(String title,int select, int[] icons, int[] list, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomAirTimeListDialog dialogFragment = newInstance(title,select, icons, list, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_air_time_list;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }

    private static BottomAirTimeListDialog newInstance(int selectTime, int[] icons, int[] list, OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener) {
        BottomAirTimeListDialog instance = new BottomAirTimeListDialog();
        instance.list = list;
        instance.selectTime = selectTime;
        instance.icons = icons;
        instance.setCancelable(cancelable);
        instance.onDialogHandleListener = onDialogHandleListener;
        instance.onCallDialog = call;
        return instance;
    }

    private static BottomAirTimeListDialog newInstance(String title,int selectTime, int[] icons, int[] list, OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener) {
        BottomAirTimeListDialog instance = new BottomAirTimeListDialog();
        instance.TITLE_TEXT = title;
        instance.list = list;
        instance.selectTime = selectTime;
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
        tvTitle.setText(TITLE_TEXT);
        tvItemView0.setText(list[0] + "分钟");
        tvItemView1.setText(list[1] + "分钟");
        tvItemView2.setText(list[2] + "分钟");
        tvItemView3.setText(list[3] + "分钟");
        switch (selectTime) {
            case 5:
                ivItemView0.setVisibility(View.VISIBLE);
                break;
            case 10:
                ivItemView1.setVisibility(View.VISIBLE);
                break;
            case 20:
                ivItemView2.setVisibility(View.VISIBLE);
                break;
            case 30:
                ivItemView3.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initView() {
        itemView0 = findViewById(R.id.ll_bottom_item0);
        itemView1 = findViewById(R.id.ll_bottom_item1);
        itemView2 = findViewById(R.id.ll_bottom_item2);
        itemView3 = findViewById(R.id.ll_bottom_item3);
        ivItemView0 = findViewById(R.id.iv_bottom_item0);
        ivItemView1 = findViewById(R.id.iv_bottom_item1);
        ivItemView2 = findViewById(R.id.iv_bottom_item2);
        ivItemView3 = findViewById(R.id.iv_bottom_item3);
        ivClose = findViewById(R.id.ivClose);
        tvTitle = findViewById(R.id.tvTitle);
        tvItemView0 = findViewById(R.id.tv_bottom_item0);
        tvItemView1 = findViewById(R.id.tv_bottom_item1);
        tvItemView2 = findViewById(R.id.tv_bottom_item2);
        tvItemView3 = findViewById(R.id.tv_bottom_item3);
    }

    private void initListener() {
        itemView0.setOnClickListener(this);
        itemView1.setOnClickListener(this);
        itemView2.setOnClickListener(this);
        itemView3.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        String text = "";
        if (id == R.id.ll_bottom_item0) {
            text = String.valueOf(list[0]);
        } else if (id == R.id.ll_bottom_item1) {
            text = String.valueOf(list[1]);
        } else if (id == R.id.ll_bottom_item2) {
            text = String.valueOf(list[2]);
        } else if (id == R.id.ll_bottom_item3) {
            text = String.valueOf(list[3]);
        }else if (id == R.id.ivClose){
            dismiss();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(BTN_TEXT, text);
        onDialogHandleListener.onDialogHandle(bundle, v.getId());
        dismiss();
    }
}

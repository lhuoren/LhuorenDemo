package com.gac.nioapp.test.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gac.nioapp.test.R;
import com.gac.nioapp.test.adapter.MutibleSelectorAdapter;
import com.gac.nioapp.test.bean.MutibleSelectorBean;

import java.util.List;

/**
 * @Description：在底部弹出的dialog，显示字符串列表，最多显示4个
 * @Author：Sai
 * @Date：2019/4/10 18:30
 */
public class BottomMutibleSelectorDialog extends BaseBottomDialogFragment {
    private List<MutibleSelectorBean> list;
    private RecyclerView ry_mutible_selector;
    private TextView tvTitle;
    private ImageView ivClose;
    public static String TITLE_TEXT = "空调开启时长";
    public static String BTN_TEXT = "btn_text";
    public static String INDEX = "select_index";
    private MutibleSelectorAdapter adapter;

    public static BottomMutibleSelectorDialog newInstance(String title, List<MutibleSelectorBean> list, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomMutibleSelectorDialog dialogFragment = newInstance(title, list, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_mutible_selector_list;
            }
        }, canClose, onDialogHandleListener);
        return dialogFragment;
    }


    private static BottomMutibleSelectorDialog newInstance(String title, List<MutibleSelectorBean> list, OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener) {
        BottomMutibleSelectorDialog instance = new BottomMutibleSelectorDialog();
        instance.TITLE_TEXT = title;
        instance.list = list;
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
        ry_mutible_selector.setAdapter(adapter);
    }

    private void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ry_mutible_selector = findViewById(R.id.ry_mutible_selector);
        ivClose = findViewById(R.id.ivClose);
        ry_mutible_selector.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MutibleSelectorAdapter(list);
    }

    private void initListener() {
        ivClose.setOnClickListener(v -> {
            onDialogHandleListener.onDialogHandle(null, DISMISS);
            dismiss();
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                MutibleSelectorBean mutibleSelectorBean = null;
//                for (int i = 0; i < list.size(); i++) {
//                    mutibleSelectorBean = list.get(i);
//                    if (i == position) {
//                        mutibleSelectorBean.setSelect(true);
//                    } else {
//                        mutibleSelectorBean.setSelect(false);
//                    }
//                    list.set(position, mutibleSelectorBean);
//                }
                Bundle bundle = new Bundle();
                bundle.putString(BTN_TEXT, list.get(position).getText());
                bundle.putInt(INDEX,position);
                onDialogHandleListener.onDialogHandle(bundle, view.getId());
                dismiss();
            }
        });
    }

}

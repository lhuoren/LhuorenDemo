package com.gac.nioapp.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gac.nioapp.test.BaseApplication;
import com.gac.nioapp.test.R;
import com.gac.nioapp.test.utils.AntiClickUtils;
import com.gac.nioapp.test.view.GlobalNativeToast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sai on 2018/3/18.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected Context currContext;

    protected String TAG = "xyz" + this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        currContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResID(), container, false);

            if (with2022Bg()) {
                //wrap bg
                View bg = inflater.inflate(R.layout.bg_home_2022, container, false);
                FrameLayout bgContent = bg.findViewById(R.id.bg_content);
                bgContent.addView(rootView);
                rootView = bg;
            }
            initViewOnCreateView();//临时解决地址选择问题
        }

        return rootView;
    }

    protected void initViewOnCreateView() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    public <T extends View> T findViewById(int id) {
        return rootView.findViewById(id);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected void initListener() {
    }

    /**
     * 添加2022年新版的背景,渐变图
     *
     * @return
     */
    protected boolean with2022Bg() {
        return false;
    }

    protected abstract int getLayoutResID();

    public void showToast(String msg, int duration) {
        /**
         * HYCAN wifi经常访问生产环境会出现异常，老板总是在此wifi下使用，未知异常是后台返回的一个异常之外的异常，前端不要显示出来。
         */
        if (TextUtils.isEmpty(msg) || msg.equals("未知异常") || msg.startsWith("HTTP 504")) {
            return;
        }
        View view = Toast.makeText(getContext(), "", duration).getView();
        Toast toast = new Toast(getContext());
        toast.setView(view);
        toast.setText(msg);
        toast.setDuration(duration);
        toast.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        currContext = null;
    }


    public void showToast(String msg) {
        GlobalNativeToast.show(BaseApplication.getInstance(), msg, Toast.LENGTH_SHORT);
    }

    public void showToast(int msgId) {
        GlobalNativeToast.show(BaseApplication.getInstance(), BaseApplication.getInstance().getString(msgId), Toast.LENGTH_SHORT);
    }

    public void showToastLong(int msgId) {
        GlobalNativeToast.show(BaseApplication.getInstance(), BaseApplication.getInstance().getString(msgId), Toast.LENGTH_LONG);

    }

    public void showToastLong(String msg) {
        GlobalNativeToast.show(BaseApplication.getInstance(), msg, Toast.LENGTH_LONG);
    }

    /**
     * Find出来的View，自带防抖功能,使用此方法，在onClick禁止使用AntiClickUtils.isInvalidClick
     * 推荐kotlin的setOnRxClick
     */
    @Deprecated
    public <T extends View> T findClickView(int id) {

        T view = findViewById(id);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AntiClickUtils.isInvalidClick(view)) {
                    BaseFragment.this.onClick(view);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        //子类重写，替换 EventListener() ,反射耗费性能
    }

    private Map<Integer, View> viewMaps = new HashMap<>();

    //google 早已废弃了 kotlin-android-extensions,建议后续使用ViewBinding方式,
    protected <T extends View> T fv(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (T) viewMaps.get(id);
    }

    protected TextView fvT(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (TextView) viewMaps.get(id);
    }

    protected Button fvB(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (Button) viewMaps.get(id);
    }

    protected ImageView fvI(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (ImageView) viewMaps.get(id);
    }

    protected EditText fvE(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (EditText) viewMaps.get(id);
    }

    protected RecyclerView fvRv(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (RecyclerView) viewMaps.get(id);
    }

    protected View fvV(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return viewMaps.get(id);
    }
}
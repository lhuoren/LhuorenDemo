package com.gac.nioapp.test.activity;

import android.os.Bundle;
import com.gac.nioapp.test.mvppresenter.BaseDataPresenter;
import com.gac.nioapp.test.utils.TUtil;

/**
 * Created by Sai on 2018/3/20.
 */
public abstract class BaseDataActivity<P extends BaseDataPresenter> extends BaseActivity {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected void initPresenter() {
        presenter = TUtil.getT(this, 0);
        if (presenter != null) {
            presenter.setVM(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }

    protected abstract void initData();
}

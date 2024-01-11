package com.gac.nioapp.test.dialog;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gac.nioapp.test.model.DismissDialogEvent;
import com.gac.nioapp.test.model.ShowDialogEvent;
import com.gac.nioapp.test.rx.RxBus;
import com.gac.nioapp.test.fragment.BaseLazyFragment;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/4/11 18:54
 */
public abstract class BaseAlertDialogFragment extends BaseLazyFragment {
    private boolean isShowing;
    public BaseDialogFragment.OnDialogHandleListener onDialogHandleListener;

    public void show(FragmentManager manager, String tag) {
        isShowing = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(android.R.id.content, this, tag);
        // 这里吧原来的commit()方法换成了commitAllowingStateLoss()
        ft.commitAllowingStateLoss();
//        ft.commit();
        RxBus.getInstance().post(new ShowDialogEvent(tag));
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void dismiss() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.commitAllowingStateLoss();
        isShowing = false;
        onDialogHandleListener = null;
        RxBus.getInstance().post(new DismissDialogEvent(getTag() == null ? "" : getTag()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDialogHandleListener = null;
    }
}

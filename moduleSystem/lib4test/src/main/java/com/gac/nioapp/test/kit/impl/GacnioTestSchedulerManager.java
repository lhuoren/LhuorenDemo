package com.gac.nioapp.test.kit.impl;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gac.nioapp.test.router.RouterConstant;

/**
 * FileName: GacnioTestSchedulers
 * Author: heyufei
 * Date: 2021/4/10 10:39
 * Description:
 *
 * @Version: 1.0
 */
public final class GacnioTestSchedulerManager {

    @Autowired(name = RouterConstant.AROUTE_TEST_PATH)
    public GacnioTestScheduler mScheduler = null;


    private GacnioTestSchedulerManager() {
        ARouter.getInstance().inject(this);
    }

    private static class GacnioTestSchedulerManagerInstance {
        private static final GacnioTestSchedulerManager INSTANCE = new GacnioTestSchedulerManager();
    }

    public static GacnioTestSchedulerManager getInstance() {
        return GacnioTestSchedulerManagerInstance.INSTANCE;
    }

    public GacnioTestScheduler getTestScheduler() {
        if (mScheduler != null) {
            return mScheduler;
        }
        mScheduler = createScheduler();
        return mScheduler;
    }

    private GacnioTestScheduler createScheduler() {
//        return new DefaultTestScheduler(BuildConfig.ENGINEER_NAME);
        return new DefaultTestScheduler("lhuoren");
    }

}

package com.syy.modulebase.rxbus.register;

import android.annotation.SuppressLint;

import com.syy.modulebase.rxbus.RxBus;
import com.syy.modulebase.rxbus.RxBusMessageType;
import com.syy.modulebase.rxbus.message.LoginDJIAcFormDownAppProgressRxBusMessage;
import com.syy.modulebase.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginDJIAcRegister extends baseRegister {
    @SuppressLint("StaticFieldLeak")
    private static class Holder {
        private static final LoginDJIAcRegister loginDJIRegister = new LoginDJIAcRegister();
    }

    public static LoginDJIAcRegister getInstanceBus() {
        return Holder.loginDJIRegister;
    }

    public LoginDJIAcRegister() {
    }

    private OnSendLoginDJIAcRxBusMessageListener mOnSendLoginDJIAcRxBusMessageListener;

    public void setOnSendLoginDJIAcRxBusMessageListener(OnSendLoginDJIAcRxBusMessageListener onSendLoginDJIAcRxBusMessageListener) {
        mOnSendLoginDJIAcRxBusMessageListener = onSendLoginDJIAcRxBusMessageListener;
    }

    public void removeOnSendLoginDJIAcRxBusMessageListener() {
        if (mOnSendLoginDJIAcRxBusMessageListener != null) {
            mOnSendLoginDJIAcRxBusMessageListener = null;
        }
    }

    public void acceptLoginDJIAcRegister(String classType) {

        LogUtils.e("mSubject", "mSubject:acceptLoginDJIAcRegister" + "," + classType);

        switch (classType) {
            case RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(LoginDJIAcFormDownAppProgressRxBusMessage.class, loginDJIAcFormDownAppProgressRxBusMessageConsumer);
                break;
            default:
                break;
        }

    }

    private final Consumer<LoginDJIAcFormDownAppProgressRxBusMessage> loginDJIAcFormDownAppProgressRxBusMessageConsumer = loginDJIAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "loginDJIAcFormDownAppProgressRxBusMessageConsumer:" + loginDJIAcRxBusMessage.getData());

                        if (mOnSendLoginDJIAcRxBusMessageListener != null) {
                            mOnSendLoginDJIAcRxBusMessageListener.sendLoginDJIAcFormDownAppProgressRxBusMessage(loginDJIAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(LoginDJIAcFormDownAppProgressRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(LoginDJIAcFormDownAppProgressRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(LoginDJIAcFormDownAppProgressRxBusMessage.class);
                    }
                });
    };

    public interface OnSendLoginDJIAcRxBusMessageListener {
        void sendLoginDJIAcFormDownAppProgressRxBusMessage(LoginDJIAcFormDownAppProgressRxBusMessage loginDJIAcFormDownAppProgressRxBusMessage);

    }
}

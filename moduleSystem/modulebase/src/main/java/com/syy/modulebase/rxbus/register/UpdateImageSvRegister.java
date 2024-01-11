package com.syy.modulebase.rxbus.register;

import android.annotation.SuppressLint;

import com.syy.modulebase.rxbus.RxBus;
import com.syy.modulebase.rxbus.RxBusMessageType;
import com.syy.modulebase.rxbus.message.LoginDJIAcFormDownAppProgressRxBusMessage;
import com.syy.modulebase.rxbus.message.UpdateImageSvFormDownAirPhotoThenUpRxBusMessage;
import com.syy.modulebase.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpdateImageSvRegister extends baseRegister {
    @SuppressLint("StaticFieldLeak")
    private static class Holder {
        private static final UpdateImageSvRegister upDateImageSvRegister = new UpdateImageSvRegister();
    }

    public static UpdateImageSvRegister getInstanceBus() {
        return Holder.upDateImageSvRegister;
    }

    public UpdateImageSvRegister() {
    }

    private OnSendUpDateImageSvRxBusMessageListener mOnSendUpDateImageSvRxBusMessageListener;

    public void setOnSendUpDateImageSvRxBusMessageListener(OnSendUpDateImageSvRxBusMessageListener onSendUpDateImageSvRxBusMessageListener) {
        mOnSendUpDateImageSvRxBusMessageListener = onSendUpDateImageSvRxBusMessageListener;
    }

    public void removeOnSendUpDateImageSvRxBusMessageListener() {
        if (mOnSendUpDateImageSvRxBusMessageListener != null) {
            mOnSendUpDateImageSvRxBusMessageListener = null;
        }
    }

    public void acceptUpDateImageSvRegister(String classType) {

        LogUtils.e("mSubject", "mSubject:accept UpDateImageSvRegister"+ "," + classType);

        switch (classType) {
            case RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(UpdateImageSvFormDownAirPhotoThenUpRxBusMessage.class, updateImageSvFormDownAirPhotoThenUpRxBusMessageConsumer);
                break;
            default:
                break;
        }

    }

    private final Consumer<UpdateImageSvFormDownAirPhotoThenUpRxBusMessage> updateImageSvFormDownAirPhotoThenUpRxBusMessageConsumer = loginDJIAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

            LogUtils.e("mSubject", "updateImageSvFormDownAirPhotoThenUpRxBusMessageConsumer:" + loginDJIAcRxBusMessage.getData());

            if (mOnSendUpDateImageSvRxBusMessageListener != null) {
                mOnSendUpDateImageSvRxBusMessageListener.sendUpdateImageSvFormDownAirPhotoThenUpRxBusMessage(loginDJIAcRxBusMessage);
            }

            emitter.onNext(RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(UpdateImageSvFormDownAirPhotoThenUpRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    };

    public interface OnSendUpDateImageSvRxBusMessageListener {
        void sendUpdateImageSvFormDownAirPhotoThenUpRxBusMessage(UpdateImageSvFormDownAirPhotoThenUpRxBusMessage updateImageSvFormDownAirPhotoThenUpRxBusMessage);

    }
}

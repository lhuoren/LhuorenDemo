package com.syy.modulebase.rxbus.register;

import android.annotation.SuppressLint;

import com.syy.modulebase.rxbus.RxBus;
import com.syy.modulebase.rxbus.RxBusMessageType;
import com.syy.modulebase.rxbus.message.AirLineTaskFgFormChangMapTypeRxBusMessage;
import com.syy.modulebase.rxbus.message.AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage;
import com.syy.modulebase.rxbus.message.AirLineTaskFgFormPanoramaMissionFinishRxBusMessage;
import com.syy.modulebase.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AirLineTaskFgRegister extends baseRegister {

    @SuppressLint("StaticFieldLeak")
    private static class Holder {
        private static final AirLineTaskFgRegister airLineTaskFgRegister = new AirLineTaskFgRegister();
    }

    public static AirLineTaskFgRegister getInstanceBus() {
        return Holder.airLineTaskFgRegister;
    }

    public AirLineTaskFgRegister() {
    }

    private OnSendAirLineTaskFgRxBusMessageListener mOnSendAirLineTaskFgRxBusMessageListener = null;

    public void setOnSendAirLineTaskRxBusMessageListener(OnSendAirLineTaskFgRxBusMessageListener onSendAirLineTaskFgRxBusMessageListener) {
        removeOnSendAirLineTaskRxBusMessageListener();
        mOnSendAirLineTaskFgRxBusMessageListener = onSendAirLineTaskFgRxBusMessageListener;
        LogUtils.e("setOnSendAirLineTaskRxBusMessageListener", "onSendAirLineTaskFgRxBusMessageListener：" + onSendAirLineTaskFgRxBusMessageListener);
    }

    public void removeOnSendAirLineTaskRxBusMessageListener() {
        if (mOnSendAirLineTaskFgRxBusMessageListener != null) {
            mOnSendAirLineTaskFgRxBusMessageListener = null;
        }
    }

    public void acceptAirLineTaskRegister(String classType) {

        LogUtils.e("mSubject", "mSubject:acceptAirLineTaskRegister" + "," + classType);

        switch (classType) {
            case RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(AirLineTaskFgFormChangMapTypeRxBusMessage.class, airLineTaskFgFormChangMapTypeRxBusMessageConsumer);
                break;
            case RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage.class, airLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessageConsumer);
                break;
            case RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(AirLineTaskFgFormPanoramaMissionFinishRxBusMessage.class, airLineTaskFgFormPanoramaMissionFinishRxBusMessageConsumer);
                break;

            default:
                break;
        }

    }

    private final Consumer<AirLineTaskFgFormChangMapTypeRxBusMessage> airLineTaskFgFormChangMapTypeRxBusMessageConsumer = AirLineTaskRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "airLineTaskFgFormChangMapTypeRxBusMessageConsumer:" + AirLineTaskRxBusMessage.getData());

                        if (mOnSendAirLineTaskFgRxBusMessageListener != null) {
                            mOnSendAirLineTaskFgRxBusMessageListener.sendAirLineTaskFgFormChangMapTypeRxBusMessage(AirLineTaskRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormChangMapTypeRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormChangMapTypeRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormChangMapTypeRxBusMessage.class);
                    }
                });
    };

    private final Consumer<AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage> airLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessageConsumer = AirLineTaskRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "airLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessageConsumer:" + AirLineTaskRxBusMessage.getData());
                        if (mOnSendAirLineTaskFgRxBusMessageListener != null) {
                            mOnSendAirLineTaskFgRxBusMessageListener.sendAirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage(AirLineTaskRxBusMessage);
                        }
                        emitter.onNext(RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }

                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage.class);
                    }
                });
    };

    private final Consumer<AirLineTaskFgFormPanoramaMissionFinishRxBusMessage> airLineTaskFgFormPanoramaMissionFinishRxBusMessageConsumer = AirLineTaskRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                LogUtils.e("mSubject", "airLineTaskFgFormPanoramaMissionFinishRxBusMessageConsumer:" + AirLineTaskRxBusMessage.getData());

                if (mOnSendAirLineTaskFgRxBusMessageListener != null) {
                    mOnSendAirLineTaskFgRxBusMessageListener.sendAirLineTaskFgFormPanoramaMissionFinishRxBusMessage(AirLineTaskRxBusMessage);
                }

                emitter.onNext(RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE);
                emitter.onComplete();
            }catch (Exception e){
                e.printStackTrace();
                emitter.onError(e);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE, d);
            }

            @Override
            public void onNext(@NotNull String aLString) {
                unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE);
                RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormPanoramaMissionFinishRxBusMessage.class);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE);
                RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormPanoramaMissionFinishRxBusMessage.class);
            }

            @Override
            public void onComplete() {
                unDisposableWithType(RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE);
                RxBus.getInstanceBus().unregisterRxBus(AirLineTaskFgFormPanoramaMissionFinishRxBusMessage.class);
            }
        });
    };

    public interface OnSendAirLineTaskFgRxBusMessageListener {

        void sendAirLineTaskFgFormChangMapTypeRxBusMessage(AirLineTaskFgFormChangMapTypeRxBusMessage airLineTaskFgFormChangMapTypeRxBusMessage);

        void sendAirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage(AirLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage airLineTaskFgFormDismissSettingParamPuPoWindowRxBusMessage);

        void sendAirLineTaskFgFormPanoramaMissionFinishRxBusMessage(AirLineTaskFgFormPanoramaMissionFinishRxBusMessage airLineTaskFgFormPanoramaMissionFinishRxBusMessage);
    }

}

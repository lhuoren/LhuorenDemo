package com.syy.modulebase.rxbus.register;

import android.annotation.SuppressLint;

import com.syy.modulebase.rxbus.LoopEventsRxBus;
import com.syy.modulebase.rxbus.RxBus;
import com.syy.modulebase.rxbus.RxBusMessageType;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfBatterStateRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfCameraMediaRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfCameraStateRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfConnectRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfFlightStateRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfFlyStateRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormAirStateOfGimbalStateRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormChannelFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormCollectionFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormFaultFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormFineFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormKmlTaskOfCollectionRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormKmlTaskOfPanoramaRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormKmlTaskOfPointCloudRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormPanoramaFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormPlanChannelRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormPlanFineRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormPointCloudFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormRemoteControllerStateRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormRemoteFgRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormSettingParamPopuWindowRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormSettingRemoteFlyRxBusMessage;
import com.syy.modulebase.rxbus.message.MainAcFormUavTaskRxBusMessage;
import com.syy.modulebase.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainAcRegister extends baseRegister {

    @SuppressLint("StaticFieldLeak")
    private static class Holder {
        private static final MainAcRegister mainAcRegister = new MainAcRegister();
    }

    public static MainAcRegister getInstanceBus() {
        return Holder.mainAcRegister;
    }

    public MainAcRegister() {
    }

    private OnSendMainAcRxBusMessageListener mOnSendMainAcRxBusMessageListener;

    public void setOnSendMainAcRxBusMessageListener(OnSendMainAcRxBusMessageListener onSendMainAcRxBusMessageListener) {
        this.mOnSendMainAcRxBusMessageListener = onSendMainAcRxBusMessageListener;
    }

    public void removeOnSendMainAcRxBusMessageListener() {
        if (this.mOnSendMainAcRxBusMessageListener != null) {
            this.mOnSendMainAcRxBusMessageListener = null;
        }
    }

    public void acceptMainAcRegister(String classType) {

        LogUtils.e("mSubject", "mSubject:acceptMainAcRegister" + "," + classType);

        switch (classType) {
            case RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormChannelFgRxBusMessage.class, mainAcFormChannelFgRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE:
                LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-COLLECTIONR");
                RxBus.getInstanceBus().registerRxBus(MainAcFormCollectionFgRxBusMessage.class, mainAcFormCollectionFgRxBusMessageConsumer);
//                RxBus.getInstanceBus().registerRxBus(MainAcFormCollectionFgRxBusMessage.class, new Consumer<MainAcFormCollectionFgRxBusMessage>() {
//                    @Override
//                    public void accept(MainAcFormCollectionFgRxBusMessage mainAcFormCollectionFgRxBusMessage) throws Exception {
//                        LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-COLLECTIONR-1-1-1");
//                    }
//                });
                break;
            case RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormFaultFgRxBusMessage.class, mainAcFormFaultFgRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMFINERXBUSMESSAGE:
//                LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-FINE");
                RxBus.getInstanceBus().registerRxBus(MainAcFormFineFgRxBusMessage.class, mainAcFormFineFgRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormPanoramaFgRxBusMessage.class, mainAcFormPanoramaFgRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE:
//                LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-POINTCLOUD");
                RxBus.getInstanceBus().registerRxBus(MainAcFormPointCloudFgRxBusMessage.class, mainAcFormPointCloudFgRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormRemoteFgRxBusMessage.class, mainAcFormRemoteFgRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormUavTaskRxBusMessage.class, mainAcFormUavTaskRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormSettingRemoteFlyRxBusMessage.class, mainAcFormSettingRemoteFlyRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMPLANFINERXBUSMESSAGE:
//                LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-PLANFINE");
                RxBus.getInstanceBus().registerRxBus(MainAcFormPlanFineRxBusMessage.class, mainAcFormPlanFineRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormPlanChannelRxBusMessage.class, mainAcFormPlanChannelRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormKmlTaskOfPanoramaRxBusMessage.class, mainAcFormKmlTaskOfPanoramaRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormKmlTaskOfCollectionRxBusMessage.class, mainAcFormKmlTaskOfCollectionRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormKmlTaskOfPointCloudRxBusMessage.class, mainAcFormKmlTaskOfPointCloudRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormSettingParamPopuWindowRxBusMessage.class, mainAcFormSettingParamPopuWindowRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormAirStateOfConnectRxBusMessage.class, mainAcFormAirStateOfConnectRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormAirStateOfFlyStateRxBusMessage.class, mainAcFormAirStateOfFlyStateRxBusMessageConsumer);
                break;
            case RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(MainAcFormRemoteControllerStateRxBusMessage.class, mainAcFormRemoteControllerStateRxBusMessageConsumer);
                break;

            default:
                break;
        }

    }

    private final Consumer<MainAcFormChannelFgRxBusMessage> mainAcFormChannelFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormChannelFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormChannelFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormChannelFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormChannelFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormChannelFgRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormCollectionFgRxBusMessage> mainAcFormCollectionFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-COLLECTIONR-2");

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "mainAcFormCollectionFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());
                        LogUtils.e("mSubject", "mSubject:acceptMainAcRegister-COLLECTIONR-2-1");
                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormCollectionFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormCollectionFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormCollectionFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormCollectionFgRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormFaultFgRxBusMessage> mainAcFormFaultFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormFaultFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormFaultFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormFaultFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormFaultFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormFaultFgRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormFineFgRxBusMessage> mainAcFormFineFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "mainAcFormFineFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormFineFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMFINERXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMFINERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMFINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormFineFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMFINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormFineFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMFINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormFineFgRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormPanoramaFgRxBusMessage> mainAcFormPanoramaFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "mainAcFormPanoramaFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormPanoramaFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPanoramaFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPanoramaFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPanoramaFgRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormPointCloudFgRxBusMessage> mainAcFormPointCloudFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "mainAcFormPointCloudFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormPointCloudFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }

                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPointCloudFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPointCloudFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPointCloudFgRxBusMessage.class);
                        unDisposableWithType(RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE);
                    }
                });
    };

    private final Consumer<MainAcFormRemoteFgRxBusMessage> mainAcFormRemoteFgRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {

                    try {
                        LogUtils.e("mSubject", "mainAcFormRemoteFgRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormRemoteFgRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }

                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormRemoteFgRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormRemoteFgRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormRemoteFgRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormUavTaskRxBusMessage> mainAcFormUavTaskRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormUavTaskRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormUavTaskRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormUavTaskRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormUavTaskRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormUavTaskRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormSettingRemoteFlyRxBusMessage> mainAcFormSettingRemoteFlyRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormSettingRemoteFlyRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormSettingRemoteFlyRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormSettingRemoteFlyRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormSettingRemoteFlyRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormSettingRemoteFlyRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormPlanFineRxBusMessage> mainAcFormPlanFineRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormPlanFineRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormPlanFineRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMPLANFINERXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMPLANFINERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPLANFINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPlanFineRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPLANFINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPlanFineRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPLANFINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPlanFineRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormPlanChannelRxBusMessage> mainAcFormPlanChannelRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormPlanChannelRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormPlanChannelRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPlanChannelRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPlanChannelRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormPlanChannelRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormKmlTaskOfPanoramaRxBusMessage> mainAcFormKmlTaskOfPanoramaRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormKmlTaskOfPanoramaRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormKmlTaskOfPanoramaRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfPanoramaRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfPanoramaRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfPanoramaRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormKmlTaskOfCollectionRxBusMessage> mainAcFormKmlTaskOfCollectionRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormKmlTaskOfCollectionRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormKmlTaskOfCollectionRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfCollectionRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfCollectionRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfCollectionRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormKmlTaskOfPointCloudRxBusMessage> mainAcFormKmlTaskOfPointCloudRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormKmlTaskOfPointCloudRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormKmlTaskOfPointCloudRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfPointCloudRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfPointCloudRxBusMessage.class);

                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormKmlTaskOfPointCloudRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormSettingParamPopuWindowRxBusMessage> mainAcFormSettingParamPopuWindowRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormSettingParamPopuWindowRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormSettingParamPopuWindowRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormSettingParamPopuWindowRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormSettingParamPopuWindowRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormSettingParamPopuWindowRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormAirStateOfConnectRxBusMessage> mainAcFormAirStateOfConnectRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormAirStateOfConnectRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormAirStateOfConnectRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }

                })
                .serialize()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormAirStateOfConnectRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormAirStateOfConnectRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormAirStateOfConnectRxBusMessage.class);
                    }
                });
    };


    private final Consumer<MainAcFormAirStateOfFlyStateRxBusMessage> mainAcFormAirStateOfFlyStateRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormAirStateOfFlyStateRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormAirStateOfFlyStateRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormAirStateOfFlyStateRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormAirStateOfFlyStateRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormAirStateOfFlyStateRxBusMessage.class);
                    }
                });
    };

    private final Consumer<MainAcFormRemoteControllerStateRxBusMessage> mainAcFormRemoteControllerStateRxBusMessageConsumer = mainAcRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "mainAcFormRemoteControllerStateRxBusMessageConsumer:" + mainAcRxBusMessage.getData());

                        if (this.mOnSendMainAcRxBusMessageListener != null) {
                            this.mOnSendMainAcRxBusMessageListener.sendMainAcFormRemoteControllerStateRxBusMessage(mainAcRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormRemoteControllerStateRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormRemoteControllerStateRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(MainAcFormRemoteControllerStateRxBusMessage.class);
                    }
                });
    };

    public interface OnSendMainAcRxBusMessageListener {
        void sendMainAcFormChannelFgRxBusMessage(MainAcFormChannelFgRxBusMessage mainAcFormChannelFgRxBusMessage);

        void sendMainAcFormCollectionFgRxBusMessage(MainAcFormCollectionFgRxBusMessage mainAcFormCollectionFgRxBusMessage);

        void sendMainAcFormFaultFgRxBusMessage(MainAcFormFaultFgRxBusMessage mainAcFormFaultFgRxBusMessage);

        void sendMainAcFormFineFgRxBusMessage(MainAcFormFineFgRxBusMessage mainAcFormFineFgRxBusMessage);

        void sendMainAcFormPanoramaFgRxBusMessage(MainAcFormPanoramaFgRxBusMessage mainAcFormPanoramaFgRxBusMessage);

        void sendMainAcFormPointCloudFgRxBusMessage(MainAcFormPointCloudFgRxBusMessage mainAcFormPointCloudFgRxBusMessage);

        void sendMainAcFormRemoteFgRxBusMessage(MainAcFormRemoteFgRxBusMessage mainAcFormRemoteFgRxBusMessage);

        void sendMainAcFormUavTaskRxBusMessage(MainAcFormUavTaskRxBusMessage mainAcFormUavTaskRxBusMessage);

        void sendMainAcFormSettingRemoteFlyRxBusMessage(MainAcFormSettingRemoteFlyRxBusMessage mainAcFormSettingRemoteFlyRxBusMessage);

        void sendMainAcFormPlanFineRxBusMessage(MainAcFormPlanFineRxBusMessage mainAcFormPlanFineRxBusMessage);

        void sendMainAcFormPlanChannelRxBusMessage(MainAcFormPlanChannelRxBusMessage mainAcFormPlanChannelRxBusMessage);

        void sendMainAcFormKmlTaskOfPanoramaRxBusMessage(MainAcFormKmlTaskOfPanoramaRxBusMessage mainAcFormKmlTaskFgRxBusMessage);

        void sendMainAcFormKmlTaskOfCollectionRxBusMessage(MainAcFormKmlTaskOfCollectionRxBusMessage mainAcFormKmlTaskOfCollectionRxBusMessage);

        void sendMainAcFormKmlTaskOfPointCloudRxBusMessage(MainAcFormKmlTaskOfPointCloudRxBusMessage mainAcFormKmlTaskOfPointCloudRxBusMessage);

        void sendMainAcFormSettingParamPopuWindowRxBusMessage(MainAcFormSettingParamPopuWindowRxBusMessage mainAcFormSettingParamPopuWindowRxBusMessage);

        void sendMainAcFormAirStateOfConnectRxBusMessage(MainAcFormAirStateOfConnectRxBusMessage mainAcFormAirStateOfConnectRxBusMessage);

        void sendMainAcFormAirStateOfFlyStateRxBusMessage(MainAcFormAirStateOfFlyStateRxBusMessage mainAcFormAirStateOfFlyStateRxBusMessage);

        void sendMainAcFormRemoteControllerStateRxBusMessage(MainAcFormRemoteControllerStateRxBusMessage mainAcFormRemoteControllerStateRxBusMessage);

    }

}

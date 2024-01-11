package com.syy.modulebase.rxbus.register;

import android.annotation.SuppressLint;

import com.syy.modulebase.rxbus.RxBus;
import com.syy.modulebase.rxbus.RxBusMessageType;
import com.syy.modulebase.rxbus.message.RemoteFgFormChangeTransMissionModeRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormDjiLiveVideoInfoRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormDownAirPhotoThenUpRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormGetChannelLineRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormGetFineLineRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderAirStatusWaitBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderDoBackHomeRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderDoCheckStateRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderDownTaskHomeRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderPauseTaskRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderResumeTaskRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormOrderTakeOffRxBusMessage;
import com.syy.modulebase.rxbus.message.RemoteFgFormRestartStreamRxBusMessage;
import com.syy.modulebase.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RemoteFgRegister extends baseRegister {
    @SuppressLint("StaticFieldLeak")
    private static class Holder {
        private static final RemoteFgRegister remoteFgRegister = new RemoteFgRegister();
    }

    public static RemoteFgRegister getInstanceBus() {
        return Holder.remoteFgRegister;
    }

    public RemoteFgRegister() {
    }

    private OnSendRemoteFgRxBusMessageListener mOnSendRemoteFgRxBusMessageListener;

    public void setOnSendRemoteFgRxBusMessageListener(OnSendRemoteFgRxBusMessageListener onSendRemoteFgRxBusMessageListener) {
        mOnSendRemoteFgRxBusMessageListener = onSendRemoteFgRxBusMessageListener;
    }

    public void removeOnSendRemoteFgRxBusMessageListener() {
        if (mOnSendRemoteFgRxBusMessageListener != null) {
            mOnSendRemoteFgRxBusMessageListener = null;
        }

    }

    public void acceptRemoteFgRegister(String classType) {

        LogUtils.e("mSubject", "mSubject:acceptRemoteFgRegister" + "," + classType);

        switch (classType) {
            case RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormGetFineLineRxBusMessage.class, remoteFgFormGetFineLineRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormGetChannelLineRxBusMessage.class, remoteFgFormGetChannelLineRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERTAKEOFFRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderTakeOffRxBusMessage.class, remoteFgFormOrderTakeOffRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderDoCheckStateRxBusMessage.class, remoteFgFormOrderDoCheckStateRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERPAUSETASKRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderPauseTaskRxBusMessage.class, remoteFgFormOrderPauseTaskRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderResumeTaskRxBusMessage.class, remoteFgFormOrderResumeTaskRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderDoBackHomeRxBusMessage.class, remoteFgFormOrderDoBackHomeRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class, remoteFgFormOrderDownTaskHomeRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormChangeTransMissionModeRxBusMessage.class, remoteFgFormChangeTransMissionModeRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormDownAirPhotoThenUpRxBusMessage.class, remoteFgFormDownAirPhotoThenUpRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormDjiLiveVideoInfoRxBusMessage.class, remoteFgFormDjiLiveVideoInfoRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormRestartStreamRxBusMessage.class, remoteFgFormRestartStreamRxBusMessageConsumer);
                break;
            case RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE:
                RxBus.getInstanceBus().registerRxBus(RemoteFgFormOrderAirStatusWaitBusMessage.class, remoteFgFormOrderAirStatusWaitBusMessageConsumer);
                break;

            default:
                break;
        }

    }

    private final Consumer<RemoteFgFormGetFineLineRxBusMessage> remoteFgFormGetFineLineRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormGetFineLineRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormGetFineLineRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE);
                        emitter.onComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormGetFineLineRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormGetFineLineRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormGetFineLineRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormGetChannelLineRxBusMessage> remoteFgFormGetChannelLineRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormGetChannelLineRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormGetChannelLineRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormGetChannelLineRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormGetChannelLineRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormGetChannelLineRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderTakeOffRxBusMessage> remoteFgFormOrderTakeOffRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderTakeOffRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormOrderTakeOffRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderTakeOffRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderTakeOffRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderTakeOffRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderDoCheckStateRxBusMessage> remoteFgFormOrderDoCheckStateRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderDoCheckStateRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRRemoteFgFormOrderDoCheckStateRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDoCheckStateRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDoCheckStateRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDoCheckStateRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderPauseTaskRxBusMessage> remoteFgFormOrderPauseTaskRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderPauseTaskRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormOrderPauseTaskRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderPauseTaskRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderPauseTaskRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderPauseTaskRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderResumeTaskRxBusMessage> remoteFgFormOrderResumeTaskRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderResumeTaskRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormOrderResumeTaskRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderResumeTaskRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderResumeTaskRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderResumeTaskRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderDoBackHomeRxBusMessage> remoteFgFormOrderDoBackHomeRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderDoBackHomeRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormOrderDoBackHomeRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDoBackHomeRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDoBackHomeRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDoBackHomeRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderDownTaskHomeRxBusMessage> remoteFgFormOrderDownTaskHomeRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderDownTaskHomeRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormOrderDownTaskHomeRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormChangeTransMissionModeRxBusMessage> remoteFgFormChangeTransMissionModeRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormChangeTransMissionModeRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormChangeTransMissionModeRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderDownTaskHomeRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormDownAirPhotoThenUpRxBusMessage> remoteFgFormDownAirPhotoThenUpRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormDownAirPhotoThenUpRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormDownAirPhotoThenUpRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormDownAirPhotoThenUpRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormDownAirPhotoThenUpRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormDownAirPhotoThenUpRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormDjiLiveVideoInfoRxBusMessage> remoteFgFormDjiLiveVideoInfoRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormDjiLiveVideoInfoRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormDjiLiveVideoInfoRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormDjiLiveVideoInfoRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormDjiLiveVideoInfoRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    };

    private final Consumer<RemoteFgFormRestartStreamRxBusMessage> remoteFgFormRestartStreamRxBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormRestartStreamRxBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormRestartStreamRxBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormRestartStreamRxBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormRestartStreamRxBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormRestartStreamRxBusMessage.class);
                    }
                });
    };

    private final Consumer<RemoteFgFormOrderAirStatusWaitBusMessage> remoteFgFormOrderAirStatusWaitBusMessageConsumer = remoteFgRxBusMessage -> {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    try {
                        LogUtils.e("mSubject", "remoteFgFormOrderAirStatusWaitBusMessageConsumer:" + remoteFgRxBusMessage.getData());

                        if (mOnSendRemoteFgRxBusMessageListener != null) {
                            mOnSendRemoteFgRxBusMessageListener.sendRemoteFgFormOrderAirStatusWaitBusMessage(remoteFgRxBusMessage);
                        }

                        emitter.onNext(RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE);
                        emitter.onComplete();
                    }catch (Exception e){
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE, d);
                    }

                    @Override
                    public void onNext(@NotNull String aLString) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderAirStatusWaitBusMessage.class);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderAirStatusWaitBusMessage.class);
                    }

                    @Override
                    public void onComplete() {
                        unDisposableWithType(RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE);
                        RxBus.getInstanceBus().unregisterRxBus(RemoteFgFormOrderAirStatusWaitBusMessage.class);
                    }
                });
    };

    public interface OnSendRemoteFgRxBusMessageListener {
        void sendRemoteFgFormGetFineLineRxBusMessage(RemoteFgFormGetFineLineRxBusMessage remoteFgFormGetFineLineRxBusMessage);

        void sendRemoteFgFormGetChannelLineRxBusMessage(RemoteFgFormGetChannelLineRxBusMessage remoteFgFormGetChannelLineRxBusMessage);

        void sendRemoteFgFormOrderTakeOffRxBusMessage(RemoteFgFormOrderTakeOffRxBusMessage remoteFgFormOrderTakeOffRxBusMessage);

        void sendRRemoteFgFormOrderDoCheckStateRxBusMessage(RemoteFgFormOrderDoCheckStateRxBusMessage remoteFgFormOrderDoCheckStateRxBusMessage);

        void sendRemoteFgFormOrderPauseTaskRxBusMessage(RemoteFgFormOrderPauseTaskRxBusMessage remoteFgFormOrderPauseTaskRxBusMessage);

        void sendRemoteFgFormOrderResumeTaskRxBusMessage(RemoteFgFormOrderResumeTaskRxBusMessage remoteFgFormOrderResumeTaskRxBusMessage);

        void sendRemoteFgFormOrderDoBackHomeRxBusMessage(RemoteFgFormOrderDoBackHomeRxBusMessage remoteFgFormOrderDoBackHomeRxBusMessage);

        void sendRemoteFgFormOrderDownTaskHomeRxBusMessage(RemoteFgFormOrderDownTaskHomeRxBusMessage remoteFgFormOrderDownTaskHomeRxBusMessage);

        void sendRemoteFgFormChangeTransMissionModeRxBusMessage(RemoteFgFormChangeTransMissionModeRxBusMessage remoteFgFormChangeTransMissionModeRxBusMessage);

        void sendRemoteFgFormDownAirPhotoThenUpRxBusMessage(RemoteFgFormDownAirPhotoThenUpRxBusMessage remoteFgFormDownAirPhotoThenUpRxBusMessage);

        void sendRemoteFgFormDjiLiveVideoInfoRxBusMessage(RemoteFgFormDjiLiveVideoInfoRxBusMessage remoteFgFormDjiLiveVideoInfoRxBusMessage);

        void sendRemoteFgFormRestartStreamRxBusMessage(RemoteFgFormRestartStreamRxBusMessage remoteFgFormRestartStreamRxBusMessage);

        void sendRemoteFgFormOrderAirStatusWaitBusMessage(RemoteFgFormOrderAirStatusWaitBusMessage remoteFgFormOrderAirStatusWaitBusMessage);
    }
}

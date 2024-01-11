package com.syy.modulebase.rxbus;

import com.syy.modulebase.rxbus.register.AirLineTaskFgRegister;
import com.syy.modulebase.rxbus.register.LoginDJIAcRegister;
import com.syy.modulebase.rxbus.register.MainAcRegister;
import com.syy.modulebase.rxbus.register.UpdateImageSvRegister;
import com.syy.modulebase.utils.LogUtils;
import com.syy.modulebase.utils.SpConstants;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostRxBusMessageFactory {

//    @SuppressLint("StaticFieldLeak")
//    private static class Holder {
//        private static final PostRxBusMessageFactory postRxBusMessage = new PostRxBusMessageFactory();
//    }
//
//    public static PostRxBusMessageFactory getInstanceBus() {
//        return Holder.postRxBusMessage;
//    }

    private static volatile PostRxBusMessageFactory mPostRxBusMessageFactory;

    //Single column mode
    public static PostRxBusMessageFactory getInstanceBus() {
        if (mPostRxBusMessageFactory == null) {
            synchronized (PostRxBusMessageFactory.class) {
                if (mPostRxBusMessageFactory == null) {
                    mPostRxBusMessageFactory = new PostRxBusMessageFactory();
                }
            }
        }
        return mPostRxBusMessageFactory;
    }

    public PostRxBusMessageFactory() {

    }

    public <T> void postRxBusMessage(String fromType, String classType, T rxBusMessage) {
        synchronized (this) {
            LogUtils.e("postRxBusMessage", "fromType:" + fromType + ",classType:" + classType + ",rxBusMessage:" + rxBusMessage);

            switch (classType) {
                case RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMCHANNELFGRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMCOLLECTIONRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMFAULTRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMFINERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMFINERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMPANORAMARXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMPOINTCLOUDRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMREMOTEFGRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMUAVTASKRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMSETTINGREMOTEFLYRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMPLANCHANNELRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMKMLTASKOFPANORAMARXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMKMLTASKOFCOLLECTIONRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMKMLTASKOFPOITCLOUDRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMSETTINGPARAMPOPUUWINDOWRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMAIRSTATEOFCONNECTRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMAIRSTATEOFFLYSTATERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMMAINACREGISTER + "_" + RxBusMessageType.MAINACFORMREMOTECONTROLLERSTATERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;

                case RxBusMessageType.AIRLINETASKFGFROMCHANGEWIDGETSIZERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMAIRLINETASKFGREGISTER + "_" + RxBusMessageType.AIRLINETASKFGFROMCHANGEWIDGETSIZERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMAIRLINETASKFGREGISTER + "_" + RxBusMessageType.AIRLINETASKFGFROMCHANGEMAPTYPERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMAIRLINETASKFGREGISTER + "_" + RxBusMessageType.AIRLINETASKFGFROMDISMISSSETTINGPARAMPUPOWINDOWRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMAIRLINETASKFGREGISTER + "_" + RxBusMessageType.AIRLINETASKFGFROMPANORAMAMISSIONFINISHRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;

                case RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMLOGINDJIACREGISTER + "_" + RxBusMessageType.LOGINDJIACFROMDOWNAPPPROGRESSRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;

                case RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMGETFINELINERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMGETCHANNELLINERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERTAKEOFFRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERTAKEOFFRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERDOCHECKSTATERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERPAUSETASKRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERPAUSETASKRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERRESUMETASKRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERDOBACKHOMERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERDOWNTASKHOMERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMCHAGETRANSMISSIONMODERXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMDJILIVEVIDEOINFORXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMRESTARTSTREAMRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
                case RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMREMOTEFGREGISTER + "_" + RxBusMessageType.REMOTEFGFROMORDERAIRSTATUSWAITRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;

                case RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE:
                    onTouchBusMessage(RxBusMessageType.FROMUPDATEIMAGESVREGISTER + "_" + RxBusMessageType.UPDATEIMAGESVFROMDOWNAIRPHOTOTHENUPRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;

                default:
                    onTouchBusMessage(SpConstants.RxJavaType.POSTRXBUSMESSAGEFACTORY_CHILDCONVERTSMAINTHREAD_POSTMAINACRXBUSMESSAGE, fromType, classType, rxBusMessage);
                    break;
            }
        }

    }

    private <T> void onTouchBusMessage(String onNextToDoType, String fromType, String classType, T rxBusMessage) {

        synchronized (this) {

            final Observer<String> observer = new Observer<String>() {
                @Override
                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                    addDisposable(onNextToDoType, d);
                }

                @Override
                public void onNext(@NotNull String aLString) {
                    unDisposableWithType(onNextToDoType);
                    RxBus.getInstanceBus().post(rxBusMessage);
                }

                @Override
                public void onError(@NotNull Throwable e) {
                    unDisposableWithType(onNextToDoType);
                }

                @Override
                public void onComplete() {
                }
            };

            Observable.create((ObservableOnSubscribe<String>) emitter -> {

                switch (fromType) {
                    case RxBusMessageType.FROMAIRLINETASKFGREGISTER:
                        AirLineTaskFgRegister.getInstanceBus().acceptAirLineTaskRegister(classType);
                        break;
                    case RxBusMessageType.FROMLOGINDJIACREGISTER:
                        LoginDJIAcRegister.getInstanceBus().acceptLoginDJIAcRegister(classType);
                        break;
                    case RxBusMessageType.FROMMAINACREGISTER:
                        MainAcRegister.getInstanceBus().acceptMainAcRegister(classType);
                        break;
                    case RxBusMessageType.FROMUPDATEIMAGESVREGISTER:
                        UpdateImageSvRegister.getInstanceBus().acceptUpDateImageSvRegister(classType);
                        break;
                    default:
                        break;
                }

                emitter.onNext(onNextToDoType);
            })
                    .subscribeOn(Schedulers.io())
                    .delay(200, TimeUnit.MILLISECONDS)
                    .subscribe(observer);

        }
    }

    private final ConcurrentHashMap<String, Disposable> concurrentHashMap = new ConcurrentHashMap<>();
    private CompositeDisposable mCompositeDisposable;

    protected void addDisposable(String type, Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);

        concurrentHashMap.put(type, d);

    }

    protected void unDisposableWithType(String type) {
        try {
            if (!concurrentHashMap.containsKey(type)) {
                return;
            }
            if (concurrentHashMap.get(type) != null && !Objects.requireNonNull(concurrentHashMap.get(type)).isDisposed()) {
                Objects.requireNonNull(concurrentHashMap.get(type)).dispose();
                mCompositeDisposable.remove(Objects.requireNonNull(concurrentHashMap.get(type)));
                concurrentHashMap.remove(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("PostRxBusMessageFactory-unDisposableWithType", e.getMessage());
        }

    }

    public void unDisposable() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }

}

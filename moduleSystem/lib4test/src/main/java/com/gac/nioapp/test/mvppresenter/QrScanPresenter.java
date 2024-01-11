package com.gac.nioapp.test.mvppresenter;

import android.net.Uri;

import com.gac.nioapp.test.mvpview.QrScanModel;
import com.gac.nioapp.test.mvpview.QrScanResultView;
import com.gac.nioapp.test.observer.HttpResultObserver;
import com.gac.nioapp.test.service.HttpAuthService;
import com.gac.nioapp.test.service.HttpCommunityService;
import com.syy.modulebase.http.HttpServiceGenerator;
import com.syy.modulebase.http.bean.HttpResult;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by xiaojiang on 2020/4/18.
 */
public class QrScanPresenter extends BaseDetailPresenter<QrScanResultView> {
    @Override
    public <Data> Observable<HttpResult<Data>> onLoadDataHttpRequest() {
        return null;
    }

    public void qrScan(String qrScan) {

        if (qrScan.startsWith("http://www.carbit.com.cn")) {
            QrScanModel model = new QrScanModel();
            model.content = qrScan;
            model.type = 6600;
            queryQrScan.onHttpSuccess(model, "拦截");
        } else {
            HashMap<String, String> params = new HashMap<>();
            params.put("qrContent", qrScan);
            onCallHttpRequest(HttpServiceGenerator.create(HttpAuthService.class).queryQrScanType(params), queryQrScan);
        }
    }

    /**
     * 扫码完成任务
     *
     * @param content
     */
    public void scanFinishTask(String content) {
        Uri uri = Uri.parse(content);
        HashMap<String, String> params = new HashMap<>();
        params.put("toolTaskId", uri.getQueryParameter("toolTaskId"));
        params.put("toolActId", uri.getQueryParameter("toolActId"));
        params.put("userId", uri.getQueryParameter("userId"));
        onCallHttpRequest(HttpServiceGenerator.create(HttpCommunityService.class).scanFinishTask(params), createWriteOffCommonObserver(11));
    }

    /**
     * 核销任务
     *
     * @param content
     */
    public void writeOffTask(String content) {
        Uri uri = Uri.parse(content);
        HashMap<String, String> params = new HashMap<>();
        params.put("toolTaskId", uri.getQueryParameter("toolTaskId"));
        params.put("toolActId", uri.getQueryParameter("toolActId"));
        params.put("userId", uri.getQueryParameter("userId"));
        onCallHttpRequest(HttpServiceGenerator.create(HttpCommunityService.class).writeOffTask(params), createWriteOffCommonObserver(10));
    }

    /**
     * 核销奖品
     *
     * @param content
     */
    public void writeOffPrize(String content) {
        Uri uri = Uri.parse(content);
        HashMap<String, String> params = new HashMap<>();
        params.put("id", uri.getQueryParameter("id"));
        onCallHttpRequest(HttpServiceGenerator.create(HttpCommunityService.class).writeOffPrize(params), createWriteOffCommonObserver(9));
    }

    private HttpResultObserver queryQrScan = new HttpResultObserver<QrScanModel>() {
        @Override
        public void onHttpSuccess(QrScanModel resultData, String msg) {
            view.qrScanResult(resultData);
        }

        @Override
        public void onHttpFail(int code, String msg) {
            view.qrScanResultFailed(code, msg);
        }

        @Override
        public void onNetWorkError(String msg) {
            view.qrScanResultFailed(-1, msg);
        }
    };

    private HttpResultObserver writeOffCommonObserver;

    private HttpResultObserver createWriteOffCommonObserver(int type) {
        if (writeOffCommonObserver == null) {
            writeOffCommonObserver = new HttpResultObserver<Boolean>() {
                @Override
                public void onHttpSuccess(Boolean result, String msg) {
                    view.writeOffResult(type);
                }

                @Override
                public void onHttpFail(int code, String msg) {
                    view.qrScanResultFailed(code, msg);
                }

                @Override
                public void onNetWorkError(String msg) {
                    view.qrScanResultFailed(-1, msg);
                }
            };
        }
        return writeOffCommonObserver;
    }
}
//public class ProductListPresenter extends BaseListRAMPresenter<ProductListView> {
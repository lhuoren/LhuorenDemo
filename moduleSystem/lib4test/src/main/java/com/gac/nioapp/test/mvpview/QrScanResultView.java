package com.gac.nioapp.test.mvpview;


/**
 * Created by xiaojiang on 2020/4/18.
 */
public interface QrScanResultView extends BaseDetailView {
    void qrScanResult(QrScanModel resultData);

    void qrScanResultFailed(int code, String msg);

    void writeOffResult(int type);
}

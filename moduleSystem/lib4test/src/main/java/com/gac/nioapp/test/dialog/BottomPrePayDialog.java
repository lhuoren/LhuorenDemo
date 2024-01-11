package com.gac.nioapp.test.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gac.nioapp.test.R;
import com.syy.modulebase.http.constants.HttpKeyConstant;

import java.util.List;


/**
 * @Description：收银台
 * @Author：Sai
 * @Date：2019/4/10 18:30
 */
public class BottomPrePayDialog extends BaseBottomDialogFragment implements View.OnClickListener {

    public static final int ACTION_FROM_FREE = 0;
    public static final int ACTION_FROM_NORMAL = 1;
    private ImageView closeView;
    private TextView tvPreMoney, freeMsgView;
    private TextView tvPayNow;
    private ViewGroup wxPay, aliPay, bankPay;
    private RadioButton wxRadio, aliRadio, bankRadio;
    private int actionFrom;
    private String money;
    private ImageView imgIntegral;
    private Bundle bundle;
    private List<String> payTypes;


    public static BottomPrePayDialog newInstance(String money, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomPrePayDialog dialogFragment = newInstance(money, ACTION_FROM_NORMAL, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_pre_pay;
            }
        }, canClose, onDialogHandleListener, null);
        return dialogFragment;
    }


    public static BottomPrePayDialog newInstance(String money, int actionFrom, OnDialogHandleListener onDialogHandleListener, boolean canClose) {
        BottomPrePayDialog dialogFragment = newInstance(money, actionFrom, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_pre_pay;
            }
        }, canClose, onDialogHandleListener, null);
        return dialogFragment;
    }

    public static BottomPrePayDialog newInstance(String money, int actionFrom, OnDialogHandleListener onDialogHandleListener, boolean canClose, List<String> showBankPay) {
        BottomPrePayDialog dialogFragment = newInstance(money, actionFrom, new OnCallDialogImpl() {
            @Override
            public int getLayoutResID() {
                return R.layout.dialog_bottom_pre_pay;
            }
        }, canClose, onDialogHandleListener, showBankPay);
        return dialogFragment;
    }

    private static BottomPrePayDialog newInstance(String money, int actionFrom, OnCallDialog call, boolean cancelable, OnDialogHandleListener onDialogHandleListener, List<String> showBankPay) {
        BottomPrePayDialog instance = new BottomPrePayDialog();
        instance.money = money;
        instance.actionFrom = actionFrom;
        instance.setCancelable(cancelable);
        instance.onDialogHandleListener = onDialogHandleListener;
        instance.onCallDialog = call;
        instance.payTypes = showBankPay;
//        instance.showBankPay = false;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    public void init() {
        bundle = new Bundle();
        initView();
        initData();
        initListener();
    }

    private void initData() {
        tvPreMoney.setText(money);
    }

    private void initView() {
        closeView = findViewById(R.id.tv_close);
        tvPayNow = findViewById(R.id.tvPay);
        tvPreMoney = findViewById(R.id.tv_pre_money);
        imgIntegral = findViewById(R.id.img_integral);
        if (money.contains("+")) {
            imgIntegral.setVisibility(View.VISIBLE);
        } else {
            imgIntegral.setVisibility(View.GONE);
        }
        freeMsgView = findViewById(R.id.tv_free_msg);
        wxPay = findViewById(R.id.wx_pay_layout);
        aliPay = findViewById(R.id.ali_pay_layout);
        wxRadio = findViewById(R.id.wx_pay);
        bankRadio = findViewById(R.id.bank_pay);
        aliRadio = findViewById(R.id.ali_pay);
        bankPay = findViewById(R.id.bank_pay_layout);
        bundle.putInt(HttpKeyConstant.PAY_TYPE, HttpKeyConstant.WX_PAY);
        if (actionFrom == ACTION_FROM_FREE) {
            freeMsgView.setVisibility(View.VISIBLE);
        } else {
            freeMsgView.setVisibility(View.INVISIBLE);
        }
//        Typeface fromAsset = Typeface.createFromAsset(this.getResources().getAssets(), "fonts/Saira-Medium.ttf");
//        tvPreMoney.setTypeface(fromAsset);

        bankPay.setVisibility(View.GONE);
        wxPay.setVisibility(View.GONE);
        aliPay.setVisibility(View.GONE);
        if (payTypes == null || payTypes.isEmpty()) {
            //默认支付宝微信
            wxPay.setVisibility(View.VISIBLE);
            aliPay.setVisibility(View.VISIBLE);
        } else {
            if (payTypes.contains("WXPAY")) {
                wxPay.setVisibility(View.VISIBLE);
            }
            if (payTypes.contains("ALIPAY")) {
                aliPay.setVisibility(View.VISIBLE);
            }
            if (payTypes.contains("TRANSFER")) {
                bankPay.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initListener() {
        tvPayNow.setOnClickListener(this);
        closeView.setOnClickListener(this);
        wxPay.setOnClickListener(this);
        aliPay.setOnClickListener(this);
        wxRadio.setOnClickListener(this);
        aliRadio.setOnClickListener(this);
        bankRadio.setOnClickListener(this);
        bankPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.wx_pay_layout == v.getId() || R.id.wx_pay == v.getId()) {
            wxRadio.setChecked(true);
            aliRadio.setChecked(false);
            bankRadio.setChecked(false);
            bundle.putInt(HttpKeyConstant.PAY_TYPE, HttpKeyConstant.WX_PAY);
        } else if (R.id.ali_pay_layout == v.getId() || R.id.ali_pay == v.getId()) {
            wxRadio.setChecked(false);
            aliRadio.setChecked(true);
            bankRadio.setChecked(false);
            bundle.putInt(HttpKeyConstant.PAY_TYPE, HttpKeyConstant.ALI_PAY);
        } else if (R.id.bank_pay_layout == v.getId() || R.id.bank_pay == v.getId()) {
            wxRadio.setChecked(false);
            aliRadio.setChecked(false);
            bankRadio.setChecked(true);
            bundle.putInt(HttpKeyConstant.PAY_TYPE, HttpKeyConstant.BANK_PAY);
        } else {
//            if (R.id.tvPay == v.getId() && bundle.getInt(HttpKeyConstant.PAY_TYPE) == HttpKeyConstant.WX_PAY) {
//                if (!ShareSDKManager.getInstance().getWxAPI().isAppInstalled()) {
//                    showToast(R.string.wechatIsNotInstall);
//                    return;
//                }
//            }
//            if (R.id.tvPay == v.getId() && bundle.getInt(HttpKeyConstant.PAY_TYPE) == HttpKeyConstant.ALI_PAY) {
//                if (!isAliPayInstalled(getContext())) {
//                    showToast(R.string.aliPayIsNotInstall);
//                    return;
//                }
//            }
            onDialogHandleListener.onDialogHandle(bundle, v.getId());
            dismiss();
        }
    }

    /**
     * 检测是否安装支付宝
     *
     * @param context
     * @return
     */
    private boolean isAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }
}

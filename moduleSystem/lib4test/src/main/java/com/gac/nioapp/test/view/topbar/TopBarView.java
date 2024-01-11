package com.gac.nioapp.test.view.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.gac.nioapp.test.R;


/**
 * Created by Sai on 2018/3/27.
 * 顶部导航栏
 * colorMode 有 Light 和 Dark模式
 * titleText 设置标题
 * rightText 设置右侧文字按钮
 * rightBackground 设置右侧文字按钮背景
 * showBack 控制左侧图标显示和隐藏
 * TopBarViewShowDivider 控制底部分割线显示和隐藏
 * rightIcon 左侧返回键更换图标
 * rightIcon 右侧图标
 * 在 Activity 中 写 onTopBarBack(View view) 函数响应左侧按钮
 * 在 Activity 中 写 onTopBarRightClick(View view) 函数响应右侧按钮,含文字和图标，通过id判断
 * 其他 请看get set方法
 */
public class TopBarView extends Toolbar {
    public static final int TOPBARMODE_LIGHT = 0;
    public static final int TOPBARMODE_DARK = 1;
    private int mode = TOPBARMODE_LIGHT;
    private String titleText;
    private String rightText;
    private String leftText;
    protected TextView tvTopBarTitle;
    private TextView tvTopBarRight;
    private TextView tvTopBarLeft;
    private ImageView ivTopBarBack;
    private ImageView ivTopBarRight;
    private View dividerView;
    private int topbarBackground;
    private int leftTextColor;
    private int rightBackground;
    private boolean showBack;
    private boolean showDivider;
    private int leftIcon;
    private int rightIcon;
    private int rightSecondIcon;
    private View loTopBar;
    private View loTopBarBackgound;
    public static final int NULLRES = -1;//不设置资源
    private ImageView ivSecondTopBarRight;
    private int statusBarHeight = 0;

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarView);
        mode = typedArray.getColor(R.styleable.TopBarView_colorMode, mode);
        titleText = typedArray.getString(R.styleable.TopBarView_titleText);
        rightText = typedArray.getString(R.styleable.TopBarView_rightText);
        leftText = typedArray.getString(R.styleable.TopBarView_leftText);
        topbarBackground = typedArray.getColor(R.styleable.TopBarView_topbarBackground, getResources().getColor(R.color.topbar_color_topbarBackground));
        leftTextColor = typedArray.getColor(R.styleable.TopBarView_leftTextColor, getResources().getColor(R.color.topbar_color_topbarBackground));
        rightBackground = typedArray.getColor(R.styleable.TopBarView_right_Background, getResources().getColor(R.color.topbar_color_topbarBackground));
        showBack = typedArray.getBoolean(R.styleable.TopBarView_showBack, true);
        leftIcon = typedArray.getResourceId(R.styleable.TopBarView_leftIcon, NULLRES);
        rightIcon = typedArray.getResourceId(R.styleable.TopBarView_rightIcon, NULLRES);
        rightSecondIcon = typedArray.getResourceId(R.styleable.TopBarView_rightSecondIcon, NULLRES);
        showDivider = typedArray.getBoolean(R.styleable.TopBarView_TopBarViewShowDivider, true);

        typedArray.recycle();
        initView(context);
        initData();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.topbarview, this);
        loTopBarBackgound = findViewById(R.id.loTopBarBackgound);
        loTopBar = findViewById(R.id.loTopBar);
        tvTopBarTitle = findViewById(R.id.tvTopBarTitle);
        tvTopBarRight = findViewById(R.id.tvTopBarRight);
        tvTopBarLeft = findViewById(R.id.tvTopBarLeft);
        ivTopBarBack = findViewById(R.id.ivTopBarBack);
        ivTopBarRight = findViewById(R.id.ivTopBarRight);
        dividerView = findViewById(R.id.dividerView);
        ivSecondTopBarRight = findViewById(R.id.ivSecondTopBarRight);

        ivTopBarBack.setVisibility(showBack ? VISIBLE : INVISIBLE);
        ivTopBarRight.setVisibility(rightIcon != NULLRES ? VISIBLE : GONE);
        ivSecondTopBarRight.setVisibility(rightSecondIcon != NULLRES ? VISIBLE : GONE);
        dividerView.setVisibility(showDivider ? VISIBLE : INVISIBLE);
        tvTopBarRight.setVisibility(!TextUtils.isEmpty(rightText) ? VISIBLE : INVISIBLE);
        tvTopBarLeft.setVisibility(!TextUtils.isEmpty(leftText) ? VISIBLE : INVISIBLE);
        switch (mode) {
            case TOPBARMODE_LIGHT:
                tvTopBarTitle.setTextColor(context.getResources().getColor(R.color.contentTitle));
                tvTopBarRight.setTextColor(context.getResources().getColor(R.color.topbar_color_righttext_light));
                tvTopBarLeft.setTextColor(context.getResources().getColor(R.color.contentTitle));
                ivTopBarBack.setImageResource(R.drawable.ic_topbar_back_black);
                break;
            case TOPBARMODE_DARK:
                tvTopBarTitle.setTextColor(context.getResources().getColor(R.color.backgroundMain));
                tvTopBarRight.setTextColor(context.getResources().getColor(R.color.topbar_color_righttext_dark_999999));
                tvTopBarLeft.setTextColor(context.getResources().getColor(R.color.topbar_color_righttext_dark_999999));
                ivTopBarBack.setImageResource(R.drawable.ic_topbar_back_white);
                break;
        }
//        if (DayNightManager.isNightMode(context)) {
//
//        } else {
//
//        }
        if (leftTextColor != R.color.topbar_color_topbarBackground) {
            tvTopBarLeft.setTextColor(leftTextColor);
        }
        loTopBarBackgound.setBackgroundColor(topbarBackground);
        //暂时去掉，统一使用波纹效果
//        tvTopBarRight.setBackgroundColor(rightBackground);
        if (leftIcon != NULLRES)
            ivTopBarBack.setImageResource(leftIcon);
        if (rightIcon != NULLRES)
            ivTopBarRight.setImageResource(rightIcon);

        if (rightSecondIcon != NULLRES) {
            ivSecondTopBarRight.setImageResource(rightSecondIcon);
        }

        //适配沉浸式，主动加padding顶部
        statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        } else {
            //低版本 直接设置0
            statusBarHeight = 0;
        }
        loTopBar.setPadding(getPaddingLeft(), statusBarHeight, getPaddingRight(), getPaddingBottom());
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    private void initData() {
        if (!TextUtils.isEmpty(titleText))
            tvTopBarTitle.setText(titleText);
        if (!TextUtils.isEmpty(rightText))
            tvTopBarRight.setText(rightText);
        if (!TextUtils.isEmpty(leftText)) {
            tvTopBarLeft.setText(leftText);
            setShowBack(false);
        }
    }

    public void setTopbarBackground(int resId) {
        loTopBarBackgound.setBackgroundColor(resId);
    }

    public String getTitleText() {
        return titleText;
    }

    /**
     * 设置中间标题
     *
     * @param titleText
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        if (!TextUtils.isEmpty(titleText)) {
            tvTopBarTitle.setText(titleText);
        } else {
            tvTopBarTitle.setText("");
        }
    }

    public String getRightText() {
        return rightText;
    }

    /**
     * 设置右侧文字
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        this.rightText = rightText;
        if (!TextUtils.isEmpty(rightText)) {
            tvTopBarRight.setText(rightText);
            tvTopBarRight.setVisibility(VISIBLE);
        } else {
            tvTopBarRight.setText("");
            tvTopBarRight.setVisibility(INVISIBLE);
        }
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        if (!TextUtils.isEmpty(leftText)) {
            tvTopBarLeft.setText(leftText);
            tvTopBarLeft.setVisibility(VISIBLE);
            setShowBack(false);
        } else {
            tvTopBarLeft.setText("");
            tvTopBarLeft.setVisibility(INVISIBLE);
        }
    }

    public void  setLeftTextColor(int color){
        if (!TextUtils.isEmpty(leftText)) {
            tvTopBarLeft.setTextColor(color);
        }
    }

    public boolean isShowBack() {
        return showBack;
    }

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
        ivTopBarBack.setVisibility(showBack ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置左侧图标
     *
     * @param resId
     */
    public void setLeftIcon(int resId) {
        ivTopBarBack.setVisibility(showBack ? VISIBLE : INVISIBLE);
        ivTopBarBack.setImageResource(resId);
    }

    /**
     * 设置右侧图标
     *
     * @param resId
     */
    public void setRightIcon(int resId) {
        ivTopBarRight.setVisibility(resId != NULLRES ? VISIBLE : INVISIBLE);
        if (resId != NULLRES) {
            ivTopBarRight.setImageResource(resId);
        }
    }

    /**
     * 设置右侧图标
     *
     * @param drawable
     */
    public void setRightIcon(@Nullable Drawable drawable) {
        ivTopBarRight.setVisibility(drawable != null ? VISIBLE : GONE);
        if (drawable != null) {
            ivTopBarRight.setImageDrawable(drawable);
        }
    }

    /**
     * 设置右侧第二个图标
     */
    public void setSecondRightIcon(int resId) {
        ivSecondTopBarRight.setVisibility(View.VISIBLE);
        ivSecondTopBarRight.setImageResource(resId);
    }

    /**
     * 设置是否隐藏右边第二个图标
     * @return
     */
    public void setSecondRightIconIsShow(boolean isShow) {
        if (isShow) {
            ivSecondTopBarRight.setVisibility(View.VISIBLE);
        }else {
            ivSecondTopBarRight.setVisibility(View.GONE);
        }
    }

     /**
     * 设置是否隐藏右边第一个图标
     * @return
     */
    public void setRightIconIsShow(boolean isShow) {
        if (isShow) {
            ivTopBarRight.setVisibility(View.VISIBLE);
        }else {
            ivTopBarRight.setVisibility(View.GONE);
        }
    }

    public boolean isShowDivider() {
        return showDivider;
    }

    /**
     * 设置分割线显示或隐藏
     *
     * @param showDivider
     */
    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
        dividerView.setVisibility(showDivider ? VISIBLE : INVISIBLE);
    }

    public TextView getTopBarTitleView() {
        return tvTopBarTitle;
    }

    public View getTopBarBackgoundView() {
        return loTopBarBackgound;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cutoutHeight = getDisplayCutoutHeight();
        if (cutoutHeight != 0) {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            height = height + cutoutHeight / 2;
            int mode = MeasureSpec.getMode(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, mode);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * Android P 刘海屏高度
     *
     * @return
     */
    public int getDisplayCutoutHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                if (Build.MANUFACTURER.toLowerCase().equals("xiaomi")) {//只要小米适配
                    DisplayCutout displayCutout = getRootWindowInsets().getDisplayCutout();
                    return displayCutout.getSafeInsetTop();
                }
            } catch (Exception e) {
            } catch (NoSuchMethodError e) {
            }
        }
        return 0;
    }

    //
    public ImageView getBackView() {
        return ivTopBarBack;
    }

    public View getDividerView() {
        return dividerView;
    }

    public ImageView getRightIconView() {
        return ivTopBarRight;
    }

    public ImageView getSecondRightIconView() {
        return ivSecondTopBarRight;
    }

    public TextView getRightTextView() {
        return tvTopBarRight;
    }

    public TextView getLeftTextView() {
        return tvTopBarLeft;
    }

}

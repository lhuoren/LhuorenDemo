package com.gac.nioapp.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

/**
 * @package： com.bigkoo.katafoundation.view
 * @describe：
 * @author： liming
 * @time： 2020-04-04 22:34
 * @e-mail： liming@gac-nio.com
 */
public class GrayFrameLayout extends FrameLayout {
    private Paint mPaint = new Paint();

    public GrayFrameLayout(@NonNull Context context) {
        super(context);
        initView();
    }

    public GrayFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView(){

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.restore();
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

}

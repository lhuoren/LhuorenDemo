package com.gac.nioapp.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.gac.nioapp.test.R;


public class MediumBoldTextView extends AppCompatTextView {
    private boolean isBold = true;
    private Typeface fromAsset;

    public MediumBoldTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediumBoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MediumBoldTextView);
        isBold = typedArray.getBoolean(R.styleable.MediumBoldTextView_bold, true);
        int customTypeFace = typedArray.getInt(R.styleable.MediumBoldTextView_custom_typeface, 1);
        typedArray.recycle();

        fromAsset = Typeface.createFromAsset(this.getResources().getAssets(), "fonts/Saira-Medium.ttf");
        resetTypeFace(customTypeFace);
    }

    private void resetTypeFace(int customTypeFace) {
        if (customTypeFace == 1) {
            setTypeface(Typeface.DEFAULT);
        } else {
            setTypeface(fromAsset);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isBold) {
            TextPaint paint = getPaint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1.5f);
        }
        super.onDraw(canvas);
    }

    public void setBold(boolean isBold) {
        this.isBold = isBold;
        invalidate();
    }

    public void setCustomTypeFace(int customTypeFace) {
        resetTypeFace(customTypeFace);
        invalidate();
    }
}
package com.example.administrator.wavedrawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by lishaojie on 2018/2/9.
 */

public class WaveDrawable extends Drawable {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mViewWidth;
    private int mViewHeight;
    private ValueAnimator mValueAnimator;
    private int mRadius;

    private int mEndColor;
    private int mStartColor;
    private float mClickX;
    private float mClickY;

    public WaveDrawable(@ColorInt int originColor, @ColorInt int targetColor, int clickX, int clickY) {
        mStartColor = originColor;
        mEndColor = targetColor;
        mClickX = clickX;
        mClickY = clickY;
    }


    public void setClickXY(float clickX, float clickY) {
        mClickX = clickX;
        mClickY = clickY;
    }
    public void setColors(int startColor, int endColor) {
        mStartColor = startColor;
        mEndColor = endColor;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawColor(mStartColor);
        mPaint.setColor(mEndColor);
        canvas.drawCircle(mClickX, mClickY, mRadius, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mViewWidth = Math.abs(bounds.width());
        mViewHeight = Math.abs(bounds.height());
        int maxRadius = (int) Math.sqrt((mViewWidth * mViewWidth + mViewHeight * mViewHeight));

        mValueAnimator = ValueAnimator.ofInt(0, maxRadius);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //改变波纹半径
                mRadius = (int) animation.getAnimatedValue();
                invalidateSelf();
            }
        });
        mValueAnimator.setDuration(500);
    }

    /**
     * 波纹扩散动画开始
     */
    public void start() {
        if (mValueAnimator != null) {
            mValueAnimator.start();
        }
    }


}
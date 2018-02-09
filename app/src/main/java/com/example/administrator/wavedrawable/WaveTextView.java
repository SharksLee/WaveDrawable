package com.example.administrator.wavedrawable;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lishaojie on 2018/2/9.
 */

public class WaveTextView extends android.support.v7.widget.AppCompatTextView {
    private int mStartColor = Color.parseColor("#FF5555");
    private int mEndColor = Color.parseColor("#23C865");
    private WaveDrawable mWaveDrawable = new WaveDrawable(mStartColor, mEndColor, 0, 0);
    private boolean mIsSwitch;

    public WaveTextView(Context context) {
        this(context, null);
    }

    public WaveTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        setBackground(mWaveDrawable);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mIsSwitch) {
                mWaveDrawable.setColors(mEndColor, mStartColor);
            } else {
                mWaveDrawable.setColors(mStartColor, mEndColor);
            }
            mWaveDrawable.setClickXY(event.getX(), event.getY());
            mWaveDrawable.start();
            mIsSwitch = !mIsSwitch;

        }
        return super.dispatchTouchEvent(event);
    }

}

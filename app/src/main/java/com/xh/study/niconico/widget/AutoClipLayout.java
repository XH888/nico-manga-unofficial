package com.xh.study.niconico.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xh.study.niconico.R;

/**
 * Created by xh on 12/17/16.
 */

public class AutoClipLayout extends FrameLayout {

    @IdRes
    int fitTargetId;

    public AutoClipLayout(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public AutoClipLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray arr = context.obtainStyledAttributes(attrs,R.styleable.AutoClipLayout,defStyleAttr,0);
        fitTargetId = arr.getResourceId(R.styleable.AutoClipLayout_fit_targetId,this.getId());
        arr.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //按照指定的ID的View来计算高度
        heightMeasureSpec = findViewById(fitTargetId).getBottom();
        widthMeasureSpec = findViewById(fitTargetId).getRight();


        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

    }
}

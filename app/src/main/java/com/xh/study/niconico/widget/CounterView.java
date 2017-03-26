package com.xh.study.niconico.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xh.study.niconico.R;

/**
 * Created by xh on 1/23/17.
 */

public class CounterView extends LinearLayout {

    private TextView valueTxt;

    public CounterView(Context context, AttributeSet attrs) {
        this(context, attrs,0 , 0);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(VERTICAL);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CounterView, defStyleAttr, defStyleRes);
        Drawable iconImageDraw = context.getDrawable( a.getResourceId(R.styleable.CounterView_iconImageResourceId,0) );
        String text = a.getString(R.styleable.CounterView_text);

        View parentView = LayoutInflater.from(context).inflate(R.layout.widget_count,this,true);
        valueTxt = (TextView) parentView.findViewById(R.id.txt_value);  //数据需要有外部提供
        ExtraTextView textTxt = (ExtraTextView) parentView.findViewById(R.id.txt_text);

        textTxt.setText(text);
        textTxt.setDrawable(iconImageDraw);
        textTxt.setDraw();

        a.recycle();
    }


    public void setTextValue(String value){
        valueTxt.setText(value);
    }
}

package com.xh.study.niconico.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xh.study.niconico.R;
import com.xh.study.niconico.util.WidgetUtil;

/**
 * Created by xh on 2/3/17.
 *
 *
 <declare-styleable name="ToggleButton">
 <attr name="tbv_checked" format="boolean"/>
 <attr name="tbv_textOn" format="string" />
 <attr name="tbv_textOff" format="string" />
 <attr name="tbv_textColor" format="color" />
 <attr name="tbv_textSize" format="dimension" />
 <attr name="tbv_txt_drawableOn" format="reference" />
 <attr name="tbv_txt_drawableOff" format="reference" />
 <attr name="tbv_txt_drawableSize" format="dimension" />
 <attr name="tbv_txt_drawablePadding" format="dimension" />
 <attr name="tbv_txt_drawablePosition">
 <enum name="bottom" value="3" />
 <enum name="left" value="0" />
 <enum name="right" value="2" />
 <enum name="top" value="1" />
 </attr>
 </declare-styleable>
 */

public class ToggleButton extends RelativeLayout {

    private boolean checked;
    private String textOn ;
    private String textOff ;
    private int textColor;
    private int textSize = 10;

    private Drawable drawableOn;
    private Drawable drawableOff;

    private int drawableSize =0;
    private int drawablePadding =0;

    private int drawablePosition;

    private ExtraTextView extraTextView ;
    public ToggleButton(Context context) {
        this(context, null);
    }

    public ToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ToggleButton, defStyleAttr, defStyleAttr);
        checked = attributes.getBoolean(R.styleable.ToggleButton_tbv_checked,false);
        textOn = attributes.getString(R.styleable.ToggleButton_tbv_textOn);
        textOff = attributes.getString(R.styleable.ToggleButton_tbv_textOff);
        textColor = attributes.getColor(R.styleable.ToggleButton_tbv_textColor, Color.WHITE);
        textSize = (int) attributes.getDimension(R.styleable.ToggleButton_tbv_textSize,textSize);
        drawableOn = attributes.getDrawable(R.styleable.ToggleButton_tbv_txt_drawableOn );
        drawableOff = attributes.getDrawable(R.styleable.ToggleButton_tbv_txt_drawableOff );
        drawableSize = (int) attributes.getDimension(R.styleable.ToggleButton_tbv_txt_drawableSize, drawableSize);
        drawablePadding = (int) attributes.getDimension(R.styleable.ToggleButton_tbv_txt_drawableSize, drawablePadding);
        drawablePosition = attributes.getInteger(R.styleable.ToggleButton_tbv_txt_drawablePosition,3);
        attributes.recycle();

        extraTextView = new ExtraTextView(context);
        extraTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        extraTextView.setDrawablePosition(drawablePosition);
        extraTextView.setTextColor(textColor);
        extraTextView.setDrawableSize(drawableSize);

        isChecked(checked);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=!checked;
                isChecked(checked);
            }
        });
        addView(extraTextView);
    }

    public void isChecked(boolean checked) {
        if(checked){
            extraTextView.setDrawable(drawableOn);
            extraTextView.toDrawToggle(textOn);
        }else {
            extraTextView.setDrawable(drawableOff);
            extraTextView.toDrawToggle(textOff);
        }
    }
}

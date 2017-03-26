package com.xh.study.niconico.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.util.DateUtil;
import com.xh.study.niconico.util.Utils;

/**
 * Created by xh on 12/26/16.
 */

public class ExtraTextView extends TextView {


//    <attr name="ext_txt_drawable" format="reference" />
//    <attr name="ext_txt_drawableSize" format="dimension" />
//    <attr name="ext_txt_drawableWidthSize" format="dimension" />
//    <attr name="ext_txt_drawableHeightSize" format="dimension" />
//    <attr name="ext_txt_drawablePosition">
//    <enum name="bottom" value="3" />
//    <enum name="left" value="0" />
//    <enum name="right" value="2" />
//    <enum name="top" value="1" />
//    </attr>
//    <attr name="ext_txt_roundedCornerRadius" format="dimension" />
//    <attr name="ext_txt_roundedCornerBorderSize" format="dimension" />
//    <attr name="ext_txt_roundedCornerBorderColor" format="color" />
//    <attr name="ext_txt_roundedCornerBackgroundColor" format="color" />

    //圆角角度
    @Dimension private float radiusSize;

    //边框大小
    @Dimension private float borderSize;

    //背景颜色
    @ColorInt private int backgroundColor;

    //边框颜色 默认和边框颜色一样
    @ColorInt private int borderColor;

    private Drawable drawable;

    //矩形长宽
    @Dimension private int drawableSize;
    @Dimension private int drawableWidthSize;
    @Dimension private int drawableHeightSize;

    private int drawablePosition;
    private Context context;

    public void setDrawablePosition(int drawablePosition){
        this.drawablePosition = drawablePosition;
    }

    public void setDrawableSize(int drawableSize) {
        this.drawableSize = drawableSize;
        this.drawableWidthSize = drawableSize;
        this.drawableHeightSize = drawableSize;
    }

    public ExtraTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ExtraTextView,defStyleAttr,defStyleAttr);
        drawable = a.getDrawable(R.styleable.ExtraTextView_ext_txt_drawable);
        radiusSize = a.getDimension(R.styleable.ExtraTextView_ext_txt_roundedCornerRadius,0f);
        borderSize = a.getDimension(R.styleable.ExtraTextView_ext_txt_roundedCornerBorderSize,0f);
        backgroundColor = a.getColor(R.styleable.ExtraTextView_ext_txt_roundedCornerBackgroundColor,Color.TRANSPARENT);
        borderColor = a.getColor(R.styleable.ExtraTextView_ext_txt_roundedCornerBorderColor, getCurrentTextColor());

        drawableSize = (int) a.getDimension(R.styleable.ExtraTextView_ext_txt_drawableSize,0);
        drawableWidthSize = (int) a.getDimension(R.styleable.ExtraTextView_ext_txt_drawableWidthSize,drawableSize);
        drawableHeightSize = (int) a.getDimension(R.styleable.ExtraTextView_ext_txt_drawableHeightSize,drawableSize);

        //默认是left
        drawablePosition = a.getInt(R.styleable.ExtraTextView_ext_txt_drawablePosition,0);
        a.recycle();

        setDraw();
    }

    public ExtraTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExtraTextView(Context context) {
        this(context,null);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        setDraw();
    }



    //普通设置
    public void setDraw(){
        if (drawable!=null){
            drawable.setBounds(0,0,drawableWidthSize,drawableHeightSize);
            switch (drawablePosition){
                case 0:
                    setCompoundDrawables(drawable,null,null,null);
                    break;
                case 1:
                    setCompoundDrawables(null,drawable,null,null);
                    break;
                case 2:
                    setCompoundDrawables(null,null,drawable,null);
                    break;
                case 3:
                    setCompoundDrawables(null,null,null,drawable);
                    break;
            }
        }
    }


    //日期格式
    public void toDrawData(long date){
        //设置更新时间的TextView
        if(System.currentTimeMillis() - date < (7 * 24 * 60 * 60 * 1000)){
            setBackgroundColor( ContextCompat.getColor(context,R.color.recent_date_text_bg));
            setTextColor(ContextCompat.getColor(context,R.color.recent_date_text_color));
        }else {
            setBackgroundColor(ContextCompat.getColor(context,R.color.date_text_bg));
            setTextColor(ContextCompat.getColor(context,R.color.date_text_color));
        }
        setText(DateUtil.getDescriptionTimeFromTimestamp(date));
        toDrawGradient();
    }

    public void toDrawToggle(String title) {
        setText(title);
        setDraw();

        Log.d("ExtraTextView","drawablePosition:"+drawablePosition
                +",drawableWidthSize:"+drawableWidthSize
                +",drawableHeightSize:"+drawableHeightSize
                +",textSize:"+getTextSize()
        );
    }

    //圆柱字形
    public void toDrawGradient() {
        drawable = new GradientDrawable();
        //矩形圆角边框
        ((GradientDrawable) drawable).setCornerRadius(radiusSize);
        ((GradientDrawable) drawable).setColor(backgroundColor);
        ((GradientDrawable) drawable).setStroke((int) borderSize, getCurrentTextColor());
        setBackground(drawable);
    }
}

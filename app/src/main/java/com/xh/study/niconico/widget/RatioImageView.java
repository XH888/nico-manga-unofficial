package com.xh.study.niconico.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.IntegerRes;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xh.study.niconico.R;

import jp.wasabeef.picasso.transformations.CropTransformation;

/**
 * Created by xh on 1/1/17.
 */

public class RatioImageView extends ImageView {

    CropTransformation transformation;
    Context context;
    int ariv_widthRatio, ariv_heightRatio;

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView, defStyleAttr, 0);

        ariv_widthRatio = a.getInteger(R.styleable.RatioImageView_ariv_widthRatio, 1);
        ariv_heightRatio = a.getInteger(R.styleable.RatioImageView_ariv_heightRatio, 1);
        transformation = new CropTransformation((float) ariv_widthRatio / (float) ariv_heightRatio, CropTransformation.GravityHorizontal.CENTER, CropTransformation.GravityVertical.CENTER);
        a.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if(getTag() instanceof String){
            //url地址
            Picasso.with(context)
                    .load(getTag().toString())
                    .config(Bitmap.Config.RGB_565)
                    .tag(context)
                    .fit().centerCrop()
                    .transform(transformation)
                    .into(this);

        }else if(getTag() instanceof Integer){

            //DrawID地址
            Picasso.with(context)
                    .load(Integer.parseInt( getTag().toString() ) )
                    .config(Bitmap.Config.RGB_565)
                    .tag(context)
                    .fit().centerCrop()
                    .transform(transformation)
                    .into(this);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //根据比例设置一个默认占位(不知道官方是不是这样做的)
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth() * ariv_heightRatio / ariv_widthRatio);
    }

}

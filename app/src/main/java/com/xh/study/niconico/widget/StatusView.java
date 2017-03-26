package com.xh.study.niconico.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xh.study.niconico.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xh on 12/26/16.
 */

public class StatusView extends FrameLayout {

    private static final int TYPE_CONNECT_ERROR = -1;
    private static final int TYPE_DATA_EMPTY = 0;

    @IntDef({TYPE_CONNECT_ERROR,TYPE_DATA_EMPTY})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type{}

    private RelativeLayout loadingLayout;
    private ErrorStatusView errorStatusLayout;
    private ImageView loadImageView;

    private int errorTextColor;

    public StatusView(Context context) {
        this(context, null);
    }

    public StatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StatusView, defStyleAttr, 0);
        errorTextColor = a.getColor(R.styleable.StatusView_errorTextColor, Color.BLACK);

        View itemView = LayoutInflater.from(context).inflate(R.layout.widget_status, this, true);

        loadingLayout = (RelativeLayout) itemView.findViewById(R.id.layout_loading);
        errorStatusLayout = (ErrorStatusView) itemView.findViewById(R.id.error_status);
        loadImageView = (ImageView) loadingLayout.findViewById(R.id.image_loading);

        errorStatusLayout.getMsgTv().setTextColor(errorTextColor);
        loadImageView.setImageResource(R.drawable.loading);
        AnimationDrawable anime = (AnimationDrawable) loadImageView.getDrawable();

        anime.start();

        a.recycle();
    }

    public interface RetryClickCallBack {
        void onClick();
    }

    public void setErrorLayout(RetryClickCallBack retryClickCallBack,String msg) {
//        msg = getContext().getString(R.string.error_network_disconnected);
//        msg = getContext().getString(R.string.error_empty);
        errorStatusLayout.getMsgTv().setText(msg);
        errorStatusLayout.getMsgTv().setTextColor(errorTextColor);
        errorStatusLayout.setRetryClickCallBack(retryClickCallBack);

        this.setVisibility(VISIBLE);
        loadingLayout.setVisibility(GONE);
        errorStatusLayout.setVisibility(VISIBLE);

    }

    public void setErrorLayout(RetryClickCallBack retryClickCallBack,@Type int type) {
        String msg = null;
        if(type == TYPE_CONNECT_ERROR)
            msg = getContext().getString(R.string.error_network_disconnected);
        else if(type == TYPE_DATA_EMPTY)
            msg = getContext().getString(R.string.error_empty);
        errorStatusLayout.getMsgTv().setText(msg);
        errorStatusLayout.getMsgTv().setTextColor(errorTextColor);
        errorStatusLayout.setRetryClickCallBack(retryClickCallBack);

        this.setVisibility(VISIBLE);
        loadingLayout.setVisibility(GONE);
        errorStatusLayout.setVisibility(VISIBLE);

    }

    public void setLoadingLayout() {
        this.setVisibility(VISIBLE);
        errorStatusLayout.setVisibility(GONE);
        loadingLayout.setVisibility(VISIBLE);
    }

    public void setGone() {
        this.setVisibility(GONE);
        loadingLayout.setVisibility(GONE);
        errorStatusLayout.setVisibility(GONE);
    }
}

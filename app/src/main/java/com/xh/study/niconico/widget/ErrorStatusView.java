package com.xh.study.niconico.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.study.niconico.R;

/**
 * Created by xh on 2/4/17.
 */

public class ErrorStatusView extends RelativeLayout  {
    public ErrorStatusView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ErrorStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    ExtraTextView msgTv ;

    private StatusView.RetryClickCallBack retryClickCallBack;

    public void setRetryClickCallBack(StatusView.RetryClickCallBack retryClickCallBack) {
        this.retryClickCallBack = retryClickCallBack;
    }

    public ErrorStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View itemView = LayoutInflater.from(context).inflate(R.layout.widget_error_status,this,true);
        msgTv= (ExtraTextView) itemView.findViewById(R.id.status_message);

        LinearLayout buttonLayout = (LinearLayout) itemView.findViewById(R.id.layout_buttons);

        ExtraTextView statusBtn = (ExtraTextView) LayoutInflater.from(context).inflate(R.layout.parts_error_status_button,null);
        statusBtn.setText(getContext().getString(R.string.btn_retry));
        statusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                retryClickCallBack.onClick();
            }
        });
        buttonLayout.addView(statusBtn);
    }

    public ExtraTextView getMsgTv() {
        return msgTv;
    }
}

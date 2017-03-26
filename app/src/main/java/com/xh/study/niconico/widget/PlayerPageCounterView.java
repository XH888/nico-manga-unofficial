package com.xh.study.niconico.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xh on 2/3/17.
 */

public class PlayerPageCounterView extends TextView {
    public PlayerPageCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerPageCounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }



    public PlayerPageCounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


}

package com.xh.study.niconico.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xh.study.niconico.R;

/**
 * Created by xh on 1/21/17.
 * 底部导航Item容器
 */

public class PlayerMenuLayout extends LinearLayout {

    public PlayerMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlayerMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public PlayerMenuLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        View parentView = LayoutInflater.from(context).inflate(R.layout.widget_player_menu,this,true);

        ExtraTextView previousView = (ExtraTextView) parentView.findViewById(R.id.txt_player_menu_previous);
        ExtraTextView nextView = (ExtraTextView) parentView.findViewById(R.id.txt_player_menu_next);
        ExtraTextView commentView = (ExtraTextView) parentView.findViewById(R.id.txt_player_menu_comment);
        ExtraTextView detailView = (ExtraTextView) parentView.findViewById(R.id.txt_player_menu_detail);
        ExtraTextView shareView = (ExtraTextView) parentView.findViewById(R.id.txt_player_menu_share);
        ExtraTextView badgeView = (ExtraTextView) parentView.findViewById(R.id.comment_badge);

        //添加事件;
        
    }
}

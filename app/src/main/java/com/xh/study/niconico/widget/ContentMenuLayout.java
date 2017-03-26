package com.xh.study.niconico.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.module.home.episodes.ContentScreenFragment;
import com.xh.study.niconico.module.home.episodes.PlayerScreenFragment;
import com.xh.study.niconico.util.DBUtil;
import com.xh.study.niconico.util.Utils;

/**
 * Created by xh on 1/21/17.
 * 底部导航Item容器
 */

public class ContentMenuLayout extends LinearLayout {

    public ContentMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ContentMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    ExtraTextView firstView,continueView,favoriteView;

    public ContentMenuLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View parentView = LayoutInflater.from(context).inflate(R.layout.widget_content_menu,this,true);

        firstView = (ExtraTextView) parentView.findViewById(R.id.txt_menu_first);
        continueView = (ExtraTextView) parentView.findViewById(R.id.txt_menu_continue);
        favoriteView = (ExtraTextView) parentView.findViewById(R.id.txt_menu_favorite);

        int disableColor = ContextCompat.getColor(context,R.color.player_menu_disable_text );

        firstView.setEnabled(false);
        firstView.setDrawable(getContext().getDrawable(R.drawable.icon_read_old_dis));
        firstView.setTextColor(disableColor);
        firstView.setClickable(true);

        continueView.setEnabled(false);
        continueView.setDrawable(getContext().getDrawable(R.drawable.icon_read_new_dis));
        continueView.setTextColor(disableColor);
        continueView.setClickable(true);

        favoriteView.setClickable(true);
    }

    public void addClickListener(ContentScreenFragment fragment,int episodeId){
        ObjectBean firstEpisode =  DBUtil.findEpisode(fragment.getContext(),episodeId);
        //从开头读
        firstView.setEnabled(true);
        firstView.setDrawable(getContext().getDrawable(R.drawable.icon_read_old));
        firstView.setTextColor(Color.WHITE);
        firstView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.start(PlayerScreenFragment.newInstance( firstEpisode ));
            }
        });
        int contentId = firstEpisode.getMeta().getContent_id();
        ObjectBean readedEpisode =  DBUtil.findReadedEpisode(fragment.getContext(),contentId);
        if(readedEpisode!=null) {
            //从记录读取
            firstView.setEnabled(true);
            continueView.setDrawable(getContext().getDrawable(R.drawable.icon_read_new));
            continueView.setTextColor(Color.WHITE);
            continueView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.start(PlayerScreenFragment.newInstance(readedEpisode));
                }
            });
        }
    }







}

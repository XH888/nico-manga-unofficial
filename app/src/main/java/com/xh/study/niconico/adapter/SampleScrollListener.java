package com.xh.study.niconico.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.squareup.picasso.Picasso;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Created by xh on 1/7/17.
 */

public class SampleScrollListener extends RecyclerView.OnScrollListener {
    private final Context context;

    public SampleScrollListener(Context context) {
        this.context = context;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        final Picasso picasso = Picasso.with(context);
        if (newState == SCROLL_STATE_IDLE || newState == SCROLL_STATE_SETTLING) {
            picasso.resumeTag(context);
        } else {
            picasso.pauseTag(context);
        }
    }
}

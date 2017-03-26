package com.xh.study.niconico.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.DividerItemDecoration;
import com.xh.study.niconico.adapter.RankingItemAdapter;
import com.xh.study.niconico.entity.renderable.RankItemBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by xh on 1/8/17.
 */
public class ContentLayout extends FrameLayout {

    private RecyclerView recyclerView ;

    private List<RankItemBean> rankItemList = new ArrayList<>();

    RecyclerView.Adapter adapter ;

    public ContentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        adapter = new RankingItemAdapter(context,rankItemList,false);
        recyclerView = new RecyclerView(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayout.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        addView(recyclerView);
    }


    public void clearItemList(){
        this.rankItemList.clear();
    }

    public void addRankItemList(List<RankItemBean> rankItemList) {
        this.rankItemList.addAll(rankItemList);
        adapter.notifyDataSetChanged();
    }
}

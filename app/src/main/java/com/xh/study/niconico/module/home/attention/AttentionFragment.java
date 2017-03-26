package com.xh.study.niconico.module.home.attention;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.RenderFactory;

import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.widget.StatusView;

import java.util.ArrayList;
import java.util.List;

import me.alexrs.recyclerviewrenderers.adapter.RendererAdapter;
import me.alexrs.recyclerviewrenderers.builder.RendererBuilder;
import me.alexrs.recyclerviewrenderers.interfaces.Renderable;
import me.yokeyword.fragmentation.SupportFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xh on 12/28/16.
 */

public class AttentionFragment extends SupportFragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private StatusView statusView;

    private RendererAdapter adapter;
    private List<Renderable> results = new ArrayList<>();
    private int limit = 20;

    public static AttentionFragment newInstance() {
        return new AttentionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        statusView = (StatusView) view.findViewById(R.id.status);

        iniRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        loadData();
    }

    public void iniRefreshLayout(){
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.post(()->{
            statusView.setLoadingLayout();
        });
        swipeRefreshLayout.setOnRefreshListener(()->{
            swipeRefreshLayout.setRefreshing(true);
            loadData();
        });
    }

    public void initRecyclerView(){
        adapter = new RendererAdapter(results, new RendererBuilder(new RenderFactory()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class,NetApiService.ENDPOINT);
        netApiService.getAttentionInfo(limit)
            .map(attentionInfo -> {
                results.clear();
                results.addAll(attentionInfo.getData().getResult());
                return results;
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(result -> {
                loadFinish();
            },throwable -> {
                Log.e("AttentionFragment:",throwable.getMessage());
                initStatusView();
            });
    }

    public void loadFinish(){
        statusView.setGone();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    public void initStatusView(){
        statusView.setErrorLayout(new StatusView.RetryClickCallBack() {
            @Override
            public void onClick() {
                statusView.setLoadingLayout();
                loadData();
            }
        },null);
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}
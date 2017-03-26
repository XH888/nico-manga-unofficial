package com.xh.study.niconico.module.home.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.RankingSummaryItemAdapter;
import com.xh.study.niconico.entity.renderable.RankItemBean;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.widget.StatusView;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xh on 12/30/16.
 */

public class RankingSummaryFragment extends SupportFragment{

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private StatusView statusView;

    private SparseArray<List<RankItemBean>> list = new SparseArray<>();
    private BaseAdapter adapter;

    private final int limit = 3;
    private final String span = "hourly";

    public static RankingSummaryFragment newInstance() {
        return new RankingSummaryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_summary,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        listView = (ListView) view.findViewById(R.id.rank_sum_list);
        statusView = (StatusView) view.findViewById(R.id.status);

        initRefreshLayout();
        initListView();
    }

    public void initRefreshLayout(){
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        //在新的线程执行动作
        swipeRefreshLayout.post(() -> {
            statusView.setLoadingLayout();
        });

        //监听下拉刷新
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            loadData();
        });
    }

    public void initListView(){
        adapter = new RankingSummaryItemAdapter(getContext(),list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        loadData();
    }

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        netApiService.getRankingSummaryInfo(limit,span)
                .map(rankingSummaryInfo -> {
                        if(rankingSummaryInfo.getMeta().getStatus() == 200) {
                            list.clear();
                            list.put(R.string.rank_1_all,rankingSummaryInfo.getData().getResult().getAll());
                            list.put(R.string.rank_2_shonen,rankingSummaryInfo.getData().getResult().getShonen());
                            list.put(R.string.rank_3_seinen,rankingSummaryInfo.getData().getResult().getSeinen());
                            list.put(R.string.rank_4_shojo,rankingSummaryInfo.getData().getResult().getShojo());
                            list.put(R.string.rank_5_yonkoma,rankingSummaryInfo.getData().getResult().getYonkoma());
                            list.put(R.string.rank_6_other,rankingSummaryInfo.getData().getResult().getOther());
                            list.put(R.string.rank_7_fan,rankingSummaryInfo.getData().getResult().getFan());
                        } else {

                        }
                        return list;
                    }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(list->{
                    loadFinish();
                },throwable -> {
                    Log.e("NavRankingFragment:",throwable.getMessage());
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
        listView.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listView.setAdapter(null);
    }
}

package com.xh.study.niconico.module.home.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.DividerItemDecoration;
import com.xh.study.niconico.adapter.RankingItemAdapter;
import com.xh.study.niconico.entity.renderable.RankItemBean;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.widget.StatusView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.xh.study.niconico.util.ConstantUtil.RANK_CATEGORY_POSITION;
import static com.xh.study.niconico.util.ConstantUtil.RANK_SPAN_POSITION;

/**
 * Created by xh on 3/12/17.
 */
public class RankingFragment extends SupportFragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private StatusView statusView;

    private List<RankItemBean> list = new ArrayList<>();
    private RankingItemAdapter adapter;

    private int current_span_position = 0;  //下拉Spinner菜单位置
    private int current_category_position = 0;  //滑动PageView位置

    private int limit = 20;  //每次加载数量
    private int currentOffset = 0;  //记录当前页数位置
    private int total = 0;

    //是否清空原始数据
    protected boolean isClearData = true;

    public static RankingFragment newInstance(int current_span_position, int current_category_position) {
        Bundle args = new Bundle();
        args.putInt(RANK_SPAN_POSITION, current_span_position);
        args.putInt(RANK_CATEGORY_POSITION, current_category_position);
        RankingFragment fragment = new RankingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        current_span_position = getArguments().getInt(RANK_SPAN_POSITION,0);
        current_category_position = getArguments().getInt(RANK_CATEGORY_POSITION,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refresh_pager, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        statusView = (StatusView) view.findViewById(R.id.status);

        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        loadData();
    }

    public void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.post(() -> {
            statusView.setLoadingLayout();
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            loadData();
        });
    }

    public void initRecyclerView() {
        adapter = new RankingItemAdapter(getContext(), list, true);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore() {
                if (hasMore()) {
                    isClearData = false;
                    currentOffset += limit;
                    Log.d("RankingFragment", "currentOffset:" + currentOffset +",current_category_position:"+current_category_position);
                    loadData();
                }
            }
        });

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                statusView.setLoadingLayout();
//
//                String selectTxt = parent.getSelectedItem().toString();
//                String showTxt = getContext().getResources().getString(R.string.rank_link_in, selectTxt);
//                ((TextView) view).setText(showTxt);
//                current_span_position = position;
//
//                ViewPager viewPager = (ViewPager) activity.findViewById(R.id.view_pager);
//                viewPager.setCurrentItem(current_category_position);
//
//                isClearData=true;
//                currentOffset = 0;
//                loadData();
//
//                Log.d("NavRankingFragment", "onItemSelected : " + ConstantUtil.SPAN.values()[current_span_position].toString() + " - "+ ConstantUtil.CATEGORY.values()[current_category_position].toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        netApiService.getRankingInfo(limit, currentOffset, ConstantUtil.SPAN.values()[current_span_position].toString(), ConstantUtil.CATEGORY.values()[current_category_position].toString())
            .map(rankingInfo -> {
                if (rankingInfo.getData() != null) {
                    total = rankingInfo.getData().getExtra().getTotal();
                    adapter.setTotal(total);
                }
                return rankingInfo.getData().getResult();
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(rankItemBeans -> {
                if (isClearData) {
                    list.clear();
                    isClearData = false;
                    currentOffset = 0;
                }
                list.addAll(rankItemBeans);
                loadFinish();
            }, throwable -> {
                Log.e("RankingFragment:", throwable.getMessage());
                initStatusView();
            });
    }


    boolean hasMore() {
        return !list.isEmpty() && total >= currentOffset;
    }

    public void loadFinish() {
        statusView.setGone();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    public void initStatusView() {
        statusView.setErrorLayout(new StatusView.RetryClickCallBack() {
            @Override
            public void onClick() {
                statusView.setLoadingLayout();
                loadData();
            }
        }, null);
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

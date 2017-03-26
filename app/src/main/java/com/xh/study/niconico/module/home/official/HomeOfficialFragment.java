package com.xh.study.niconico.module.home.official;

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
import com.xh.study.niconico.adapter.OfficialItemAdapter;
import com.xh.study.niconico.entity.renderable.OfficialItemBean;
import com.xh.study.niconico.module.activity.MainActivity;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.widget.StatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xh on 12/18/16.
 */

public class HomeOfficialFragment extends SupportFragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private StatusView statusView;

    private ArrayList<OfficialItemBean> list = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    public static HomeOfficialFragment newInstance() {
        return new HomeOfficialFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        statusView = (StatusView) view.findViewById(R.id.status);

        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        loadData();
    }

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        netApiService.getOfficalData()
            .map(officialInfo -> {
                    if (officialInfo.getMeta().getStatus() == 200) {
                        list.clear();
                        list.addAll(officialInfo.getData().getResult());
                    } else {

                    }
                    return list;
                }
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(list -> {
                loadFinish();
            }, throwable -> {
                Log.e("HomeOfficialFragment:", throwable.getMessage());
                initStatusView();
            });
    }

    public void initRecyclerView() {
        adapter = new OfficialItemAdapter(getContext(), list) ;
        ((OfficialItemAdapter)adapter).setSendViewClickCallBack(params -> {
            //处理CardView 点击 启动相应的 NavOfficialFragment
            Bundle args = new Bundle();
            args.putInt(ConstantUtil.RANK_ID_POSITION,Integer.parseInt( params.toString() ));
            args.putParcelableArrayList(ConstantUtil.RANK_TITLE_LIST,list);
            //((MainActivity)getParentFragment().getActivity()).switchFragment(2,args);
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
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
        },null);
        recyclerView.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}

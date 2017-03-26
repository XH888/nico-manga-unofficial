package com.xh.study.niconico.module.home.top;

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
import com.xh.study.niconico.adapter.SampleScrollListener;
import com.xh.study.niconico.callback.ItemListener;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.entity.renderable.FeatureItemBean;
import com.xh.study.niconico.entity.renderable.CardViewImageBean;
import com.xh.study.niconico.module.home.NavHomeFragment;
import com.xh.study.niconico.module.home.episodes.ContentScreenFragment;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.util.WidgetUtil;
import com.xh.study.niconico.widget.StatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.alexrs.recyclerviewrenderers.adapter.RendererAdapter;
import me.alexrs.recyclerviewrenderers.builder.RendererBuilder;
import me.alexrs.recyclerviewrenderers.interfaces.Renderable;
import me.yokeyword.fragmentation.SupportFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/aggregate/homescreen
 * Created by xh on 12/18/16.
 */

public class TopHomeFragment extends SupportFragment implements ItemListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private StatusView statusView;

    private List<Renderable> list = new ArrayList<>();
    private RendererAdapter adapter;

    public static TopHomeFragment newInstance() {
        return new TopHomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top,container,false);
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

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        //根据模型取出list集合 将其分配给 Primary Secondary Features
        netApiService.getTopData()
            .map(topInfo -> {
                    if (topInfo.getMeta().getStatus() == 200) {
                        list.clear();
                        list.addAll(topInfo.getData().getResult().getPickup().getPrimary());
                        list.add(WidgetUtil.addHeadLine("推荐漫画"));
                        list.addAll(topInfo.getData().getResult().getPickup().getSecondary());
                        list.add(new CardViewImageBean(R.drawable.image_howto_create));
                        list.add(WidgetUtil.addHeadLine("特辑"));
                        for (FeatureItemBean itemBean : topInfo.getData().getResult().getFeatures()) {
                            list.add(itemBean);
                            for (FeatureItemBean.Content content : itemBean.getContents()) {
                                content.setItemListener(this);
                            }
                            list.addAll(itemBean.getContents());
                        }
                    } else {

                    }
                    return list;
                }
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(list -> {
                loadFinish();
            }, throwable -> {
                Log.e("TopHomeFragment:", throwable.getMessage());
                initStatusView();
            });
    }

    public void initRecyclerView() {
        adapter = new RendererAdapter(list, new RendererBuilder(new RenderFactory()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //RenderId
                int typeId = adapter.getItemViewType(position);
                if (typeId == R.layout.renderer_item_top_secondary || typeId == R.layout.renderer_item_feature || typeId == R.layout.renderer_how_to_create) {
                    return 1;
                }
                return 2;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new SampleScrollListener(this.getContext()));
    }

    public void loadFinish() {
        statusView.setGone();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    public void initStatusView() {
        swipeRefreshLayout.setRefreshing(false);
        statusView.setErrorLayout(new StatusView.RetryClickCallBack() {
            @Override
            public void onClick() {
                statusView.setLoadingLayout();
                loadData();
            }
        },null);
        statusView.setVisibility(View.VISIBLE);
        recyclerView.removeAllViews();
        recyclerView.setFocusable(true);
    }

    @Override
    public void onClick(int position,View view) {
        Log.d("TopHomeFragment","click:"+position);

        Renderable renderable = list.get(position);
        ObjectBean objectBean = (ObjectBean) renderable;

        if (renderable instanceof FeatureItemBean.Content) {
            ((NavHomeFragment)getParentFragment()).start(ContentScreenFragment.newInstance(objectBean));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}

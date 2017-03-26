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
import com.xh.study.niconico.adapter.RenderFactory;
import com.xh.study.niconico.entity.renderable.MoreButtonBean;
import com.xh.study.niconico.entity.renderable.ShareButtonBean;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.util.ConstantUtil;
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
 * Created by xh on 1/15/17.
 */

public class OfficialsFragment extends SupportFragment {

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.list)
    protected RecyclerView recyclerView;

    @BindView(R.id.status)
    protected StatusView statusView;

    private RecyclerView.Adapter adapter;

    private int currentId;

    private List<Renderable> renderableList = new ArrayList<>();

    public static OfficialsFragment newInstance(int rank_id) {
        Bundle args = new Bundle();
        args.putInt(ConstantUtil.RANK_ID, rank_id);
        OfficialsFragment fragment = new OfficialsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
            currentId = getArguments().getInt(ConstantUtil.RANK_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refresh_pager,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView= (RecyclerView) view.findViewById(R.id.list);
        statusView= (StatusView) view.findViewById(R.id.status);

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
        adapter = new RendererAdapter(renderableList, new RendererBuilder(new RenderFactory()));
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 6, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = adapter.getItemViewType(position);
                switch (type) {
                    case R.layout.renderer_official_serial_content:
                        return 3;
                    case R.layout.renderer_official_trial_content:
                        return 2;
                }
                return 6;
            }
        });
        recyclerView.setAdapter(adapter);
    }


    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        netApiService.getOfficalDataDetail(currentId)
                .map(officialDetailInfo -> {
                    if (officialDetailInfo.getMeta().getStatus() == 200) {
                        renderableList.clear();

                        //头部信息 img_url 和 backgroundColor
                        renderableList.add(officialDetailInfo.getData().getResult());

                        renderableList.addAll(WidgetUtil.addContainerColor(officialDetailInfo));

                        renderableList.add(WidgetUtil.addHeadLine("连载作品"));
                        renderableList.addAll(officialDetailInfo.getData().getResult().getContents().getSerial());
                        renderableList.add(new MoreButtonBean(null));

                        renderableList.add(WidgetUtil.addHeadLine("试读作品"));
                        renderableList.addAll(officialDetailInfo.getData().getResult().getContents().getTrial());
                        renderableList.add(new MoreButtonBean(null));

                        //renderableList.addAll(officialDetailInfo.getData().getResult().getContents().getConcluded());

                        if (!officialDetailInfo.getData().getResult().getMagazines().isEmpty())
                            renderableList.add(WidgetUtil.addHeadLine("相关信息"));

                        renderableList.addAll(officialDetailInfo.getData().getResult().getMagazines());

                        //分享
                        renderableList.add(new ShareButtonBean(null));
                    }
                    return renderableList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(list -> {
                    loadFinish();
                }, throwable -> {
                    Log.e("OfficialsFragment:", throwable.getMessage());
                    initStatusView();
                });
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
        }, null);
        recyclerView.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}

package com.xh.study.niconico.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.pager.OfficialPagerAdapter;
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
 * Created by xh on 1/15/17.
 * 侧边导航 官方漫画
 */
public class NavOfficialFragment extends SupportFragment {

    private Toolbar toolBar;
    private FrameLayout frameLayout;
    private StatusView statusView;

    private int current_position ;
    private List<OfficialItemBean> list ;
    private OfficialPagerAdapter adapter ;

    public static NavOfficialFragment newInstance() {
        return new NavOfficialFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official_screen,container,false);

        initView(view);

        return view;
    }

    @Override
    protected void onNewBundle(Bundle args) {
        super.onNewBundle(args);

        list = getArguments().getParcelableArrayList(ConstantUtil.RANK_TITLE_LIST);
        current_position = getArguments().getInt(ConstantUtil.RANK_ID_POSITION,0);
    }

    private void initView(View view) {
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);
        frameLayout = (FrameLayout) view.findViewById(R.id.container_officials);
        statusView= (StatusView) view.findViewById(R.id.status);

        initToolBar();
        initFrameLayout();
    }

    private void initToolBar() {
        toolBar.setTitle(getContext().getResources().getString(R.string.rank_toolbar));
        _mActivity.setSupportActionBar(toolBar);
        _mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(view -> {
            pop();
        });
    }

    private void initFrameLayout() {
        //显示载入状态
        statusView.setLoadingLayout();

        if(list == null)
            loadData();
        else {
            adapter = new OfficialPagerAdapter(getChildFragmentManager(), list);

            loadRootFragment(R.id.container_officials, new OfficialTitleFragment());

            //关闭载入状态
            loadFinish();
        }
    }


    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        netApiService.getOfficalData()
            .map(officialInfo -> {
                    if (officialInfo.getMeta().getStatus() == 200) {
                        list = new ArrayList<>();
                        list.addAll(officialInfo.getData().getResult());
                    }
                    return list;
                }
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(list -> {
                initFrameLayout();
            }, throwable -> {
                initStatusView();
                Log.e("NavOfficialFragment:", throwable.getMessage());
            });
    }

    public void loadFinish(){
        statusView.setGone();
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
        frameLayout.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    class OfficialTitleFragment extends SupportFragment {

        private PagerSlidingTabStrip pagerSlidingTabStrip;
        private ViewPager viewPager;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_officials, container, false);

            OfficialTitleFragment.this.initView(view);

            return view;
        }

        private void initView(View view) {
            pagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tab_strip);
            viewPager = (ViewPager) view.findViewById(R.id.view_pager);

            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(current_position);
            pagerSlidingTabStrip.setViewPager(viewPager);
        }
    }
}

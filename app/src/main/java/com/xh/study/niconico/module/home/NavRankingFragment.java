package com.xh.study.niconico.module.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.astuetz.PagerSlidingTabStrip;
import com.xh.study.niconico.R;
import com.xh.study.niconico.module.home.ranking.RankingFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 1/10/17.
 * 侧边导航 排行
 */

public class NavRankingFragment extends SupportFragment {

    private Toolbar toolBar;
    private Spinner spinner;
    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;

    private int current_span_position = 0;
    private int current_category_position = 0;
    private RankingPagerAdapter pageAdapter;

    public static NavRankingFragment newInstance() {
        return new NavRankingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_screen,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);
        spinner = (Spinner) view.findViewById(R.id.spinner_span);
        tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tab_strip);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        initToolBar();
        initViewPage();
    }

    private void initViewPage() {
        pageAdapter = new RankingPagerAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(pageAdapter);
        tabStrip.setViewPager(viewPager);
    }

    private void initToolBar() {
        _mActivity.setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolBar.setNavigationOnClickListener(view ->  pop());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.rankSpinner, R.layout.adapter_toolbar_dropdown);
        adapter.setDropDownViewResource(R.layout.adapter_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(current_span_position, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                pageAdapter.changeSpanData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class RankingPagerAdapter extends FragmentPagerAdapter {

        private String[] titles;

        private RankingFragment rankFragment ;

        public RankingPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            titles = context.getResources().getStringArray(R.array.rankingTabSections);
        }

        public void changeSpanData(int span_position){
            current_span_position = span_position;
            if(rankFragment!=null)
                rankFragment.loadData();
        }

        @Override
        public Fragment getItem(int position) {
            current_span_position = spinner.getSelectedItemPosition();
            current_category_position = position;
            rankFragment =  RankingFragment.newInstance(current_span_position,current_category_position);
            Log.d("RankingPagerAdapter","init RankingFragment: current_span_position:"+current_span_position+",current_category_position:"+current_category_position);
            return rankFragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }
}

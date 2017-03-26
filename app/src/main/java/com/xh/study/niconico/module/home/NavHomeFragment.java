package com.xh.study.niconico.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.pager.HomePagerAdapter;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 12/18/16.
 * 主页Home
 */

public class NavHomeFragment extends SupportFragment{

    Toolbar mToolBar;

    PagerSlidingTabStrip mPagerSlidingTabStrip;

    ViewPager mViewPager;

    public static NavHomeFragment newInstance(){
        return new NavHomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tab_strip);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.layout_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),drawerLayout, mToolBar,R.string.nav_open,R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mViewPager.setAdapter(new HomePagerAdapter(getChildFragmentManager(),_mActivity));
        mViewPager.setCurrentItem(1);

        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }

}

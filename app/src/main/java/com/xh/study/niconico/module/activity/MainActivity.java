package com.xh.study.niconico.module.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.xh.study.niconico.R;
import com.xh.study.niconico.module.home.NavHomeFragment;
import com.xh.study.niconico.module.home.NavOfficialFragment;
import com.xh.study.niconico.module.home.NavRankingFragment;
import com.xh.study.niconico.module.home.NavSearchFragment;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

/**
 * Created by xh on 1/11/17.
 */

public class MainActivity extends SupportActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        if(savedInstanceState == null)
            loadRootFragment(R.id.container_screen,NavHomeFragment.newInstance());

        initView();

        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks(){

        });
    }

    public void initView(){
        navigationView = (NavigationView) findViewById(R.id.navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            int id = item.getItemId();
            switch (id){
                case R.id.menu_home:
                    start(findFragment(NavHomeFragment.class), SupportFragment.SINGLETASK);
                    break;
                case R.id.menu_ranking:
                    NavRankingFragment rankingFragment = findFragment(NavRankingFragment.class);
                    if(rankingFragment== null){
                        rankingFragment = NavRankingFragment.newInstance();
                    }
                    start(rankingFragment, SupportFragment.SINGLETASK);
                    break;
                case R.id.menu_official:
                    NavOfficialFragment officialFragment = findFragment(NavOfficialFragment.class);
                    if(officialFragment== null){
                        officialFragment = NavOfficialFragment.newInstance();
                    }
                    start(officialFragment, SupportFragment.SINGLETASK);
                    break;
                case R.id.menu_search:
                    NavSearchFragment searchFragment = findFragment(NavSearchFragment.class);
                    if(searchFragment== null){
                        searchFragment =NavSearchFragment.newInstance();
                    }
                    start(searchFragment, SupportFragment.SINGLETASK);
                    break;
            }
            return true;
        });
    }


    public void showNav(boolean flag) {
        if(flag) {
            if (!drawerLayout.isDrawerVisible(navigationView))
                drawerLayout.addView(navigationView);
        }else {
            drawerLayout.removeView(navigationView);
        }
    }
}

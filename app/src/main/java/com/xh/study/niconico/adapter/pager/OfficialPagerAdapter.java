package com.xh.study.niconico.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.xh.study.niconico.entity.renderable.OfficialItemBean;
import com.xh.study.niconico.module.home.favorite.FavoriteHomeFragment;
import com.xh.study.niconico.module.home.official.OfficialsFragment;

import java.util.List;

/**
 * Created by xh on 1/15/17.
 */

public class OfficialPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    private List<OfficialItemBean> list;

    public OfficialPagerAdapter(FragmentManager fm, List<OfficialItemBean> list) {
        super(fm);
        this.list = list;
        titles = new String[list.size()];
        for(int i = 0;i<titles.length;i++){
            titles[i] = list.get(i).getMeta().getName();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return OfficialsFragment.newInstance(list.get(position).getId());
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

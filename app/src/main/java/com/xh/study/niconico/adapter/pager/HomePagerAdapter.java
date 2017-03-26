package com.xh.study.niconico.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.xh.study.niconico.R;
import com.xh.study.niconico.module.home.attention.AttentionFragment;
import com.xh.study.niconico.module.home.favorite.FavoriteHomeFragment;
import com.xh.study.niconico.module.home.official.HomeOfficialFragment;
import com.xh.study.niconico.module.home.ranking.RankingSummaryFragment;
import com.xh.study.niconico.module.home.top.TopHomeFragment;

/**
 * Created by xh on 12/18/16.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private String titles[];

    private Fragment fragments[];

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        titles = context.getResources().getStringArray(R.array.homeTabSections);
        fragments = new Fragment[titles.length];
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments!= null){
            switch (position) {
                case 0:
                    fragments[position] = FavoriteHomeFragment.newInstance();
                    break;
                case 1:
                    fragments[position] = TopHomeFragment.newInstance();
                    break;
                case 2:
                    fragments[position] = AttentionFragment.newInstance();
                    break;
                case 3:
                    fragments[position] = RankingSummaryFragment.newInstance();
                    break;
                case 4:
                    fragments[position] = HomeOfficialFragment.newInstance();
                    break;
            }
        }
        return fragments[position];
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

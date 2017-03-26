package com.xh.study.niconico.module.home.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xh.study.niconico.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 12/18/16.
 */

public class FavoriteHomeFragment extends SupportFragment {

    public static FavoriteHomeFragment newInstance(){
        return new FavoriteHomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_authentication,container,false);

        return view;
    }
}

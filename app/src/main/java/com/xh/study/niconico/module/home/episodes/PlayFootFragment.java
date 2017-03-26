package com.xh.study.niconico.module.home.episodes;

import android.os.Bundle;

import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 2/3/17.
 */

public class PlayFootFragment extends SupportFragment {

    public static PlayFootFragment newInstance() {
        Bundle args = new Bundle();
        PlayFootFragment fragment = new PlayFootFragment();
        fragment.setArguments(args);
        return fragment;
    }


}

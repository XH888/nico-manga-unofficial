package com.xh.study.niconico.module.home.episodes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.entity.renderable.FrameBean;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.widget.PlayerMenuLayout;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 2/3/17.
 */

public class PlayerFragment extends SupportFragment {

    private SlidingUpPanelLayout slidingUpPanelLayout;
    private LinearLayout playerLayout ;
    private PlayerMenuLayout playerMenuLayout;
    private FrameLayout mainContainer;

    private ArrayList<FrameBean> frames ;
    private ObjectBean episodeBean;

    public static PlayerFragment newInstance(ObjectBean episodeBean, ArrayList<FrameBean> frames) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantUtil.FRAME_BEAN_LIST,frames);
        args.putParcelable(ConstantUtil.EPISODE_BEAN,episodeBean);
        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            frames = getArguments().getParcelableArrayList(ConstantUtil.FRAME_BEAN_LIST);
            episodeBean = getArguments().getParcelable(ConstantUtil.EPISODE_BEAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_up_panel);
        playerLayout = (LinearLayout) view.findViewById(R.id.parent_player_menu);
        playerMenuLayout= (PlayerMenuLayout) view.findViewById(R.id.layout_player_menu);
        mainContainer= (FrameLayout) view.findViewById(R.id.container_main);

        replaceLoadRootFragment( R.id.container_main,PlayerContentFragment.newInstance(episodeBean,frames),false);
    }
}

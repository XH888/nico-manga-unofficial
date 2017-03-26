package com.xh.study.niconico.module.home.episodes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.entity.renderable.FrameBean;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.widget.PlayerPageCounterView;
import com.xh.study.niconico.widget.ScalableRecyclerView;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by xh on 2/3/17.
 * 漫画内容主界面
 */

public class PlayerContentFragment extends SupportFragment {

    private ScalableRecyclerView scrollRecyclerView;
    private LinearLayout pageCounterLayout;
    private PlayerPageCounterView counterView;
    private View playerInfoView;
    private Toolbar toolBar;

    private ArrayList<FrameBean> frameBeanList;
    private ObjectBean episodeBean;


    public static PlayerContentFragment newInstance(ObjectBean episodeBean, ArrayList<FrameBean> frameBeanList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantUtil.FRAME_BEAN_LIST,frameBeanList);
        args.putParcelable (ConstantUtil.EPISODE_BEAN,episodeBean);
        PlayerContentFragment fragment = new PlayerContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            frameBeanList = getArguments().getParcelableArrayList(ConstantUtil.EPISODE_ARRAY);
            episodeBean = getArguments().getParcelable(ConstantUtil.EPISODE_BEAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_player, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        scrollRecyclerView = (ScalableRecyclerView) view.findViewById(R.id.list_scroll);
        pageCounterLayout = (LinearLayout) view.findViewById(R.id.layout_page_count);
        counterView = (PlayerPageCounterView) view.findViewById(R.id.page_counter);
        playerInfoView = view.findViewById(R.id.layout_player_info);
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);

        initBaseView();
        initRecyclerView();
    }



    private void initBaseView() {
        toolBar.setTitle(episodeBean.getMeta().getTitle());
        _mActivity.setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolBar.setNavigationOnClickListener(view -> {
            _mActivity.findFragment(PlayerScreenFragment.class).pop();

            //_mActivity.showFragmentStackHierarchyView();
        });
        playerInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
            }
        });
        counterView.setText("1/"+ frameBeanList.size());
    }

    private void initRecyclerView() {
        scrollRecyclerView.setFrameBeans(frameBeanList);
    }

}

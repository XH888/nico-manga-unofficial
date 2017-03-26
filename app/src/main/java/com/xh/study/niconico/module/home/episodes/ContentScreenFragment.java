package com.xh.study.niconico.module.home.episodes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.widget.StatusView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 1/21/17.
 * 漫画章节列表信息
 */

public class ContentScreenFragment extends SupportFragment {

    private StatusView statusView;
    private FrameLayout frameLayout ;

    private ObjectBean objectBean;

    public static ContentScreenFragment newInstance(ObjectBean objectBean) {
        Bundle args = new Bundle();
        ContentScreenFragment fragment = new ContentScreenFragment();
        args.putParcelable(ConstantUtil.OBJECT_BEAN_ITEM, objectBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
            objectBean = getArguments().getParcelable(ConstantUtil.OBJECT_BEAN_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_screen,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        statusView = (StatusView) view.findViewById(R.id.status);
        frameLayout = (FrameLayout) view.findViewById(R.id.container);
        replaceLoadRootFragment(R.id.container,EpisodeFragment.newInstance(objectBean),true);
        statusView.setGone();
    }

}

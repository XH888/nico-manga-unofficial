package com.xh.study.niconico.module.home.episodes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.entity.renderable.FrameBean;
import com.xh.study.niconico.module.activity.MainActivity;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.widget.StatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xh on 1/31/17.
 * 初始化漫画内容
 */

public class PlayerScreenFragment extends SupportFragment{
    private StatusView statusView;

    private ObjectBean episodeBean;
    private ArrayList<FrameBean> frames = new ArrayList<>();

    public static PlayerScreenFragment newInstance(ObjectBean episodeBean){
        Bundle args = new Bundle();
        args.putParcelable(ConstantUtil.EPISODE_BEAN,episodeBean);
        PlayerScreenFragment fragment = new PlayerScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            episodeBean = getArguments().getParcelable(ConstantUtil.EPISODE_BEAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_screen,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        statusView= (StatusView) view.findViewById(R.id.status);
        statusView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        loadData();
    }

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);


        Observable.zip(
            //读取图片链接
            netApiService.getFramesInfo(episodeBean.getId()),

                //读取评论
            netApiService.getCommentsInfo(episodeBean.getId()),
            (framesInfo, commentInfo) -> {
                frames.clear();
                frames.addAll(framesInfo.getData().getResult());
                return frames;
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(result -> {
                statusView.setGone();
                replaceLoadRootFragment(R.id.container_player,PlayerFragment.newInstance(episodeBean,frames),false);
            }, throwable -> {
                Log.e("PlayerScreenFragment:", throwable.getMessage());
                statusView.setErrorLayout(new StatusView.RetryClickCallBack() {
                    @Override
                    public void onClick() {
                        statusView.setLoadingLayout();
                        loadData();
                    }
                },null);
            });
    }
}

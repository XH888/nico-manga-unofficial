package com.xh.study.niconico.module.home.episodes;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.EpisodeAdapter;
import com.xh.study.niconico.entity.base.MetaBean;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.module.activity.MainActivity;
import com.xh.study.niconico.module.home.NavHomeFragment;
import com.xh.study.niconico.network.NetApiService;
import com.xh.study.niconico.network.ServiceFactory;
import com.xh.study.niconico.util.ConstantUtil;
import com.xh.study.niconico.util.DBUtil;
import com.xh.study.niconico.util.NumberUtil;
import com.xh.study.niconico.widget.AuthorsView;
import com.xh.study.niconico.widget.ContentMenuLayout;
import com.xh.study.niconico.widget.CounterView;
import com.xh.study.niconico.widget.ExpandableLayout;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.StatusView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import static com.xh.study.niconico.util.ConstantUtil.OBJECT_BEAN_ITEM;

/**
 * Created by xh on 2/15/17.
 */
public class EpisodeFragment extends SupportFragment implements ObservableScrollViewCallbacks {

    private ImageView contentImage;
    private ObservableListView episodeList;
    private ContentMenuLayout contentLayout;
    private Toolbar toolBar;
    private View shadowView;
    private View paddingView ;
    private StatusView statusView;
    private ExtraTextView episodeCountTxt;

    private List<ObjectBean> list = new ArrayList<>();
    private ObjectBean objectBean;
    private BaseAdapter adapter;

    private int mainColor;

    public static EpisodeFragment newInstance(ObjectBean objectBean) {
        Bundle args = new Bundle();
        args.putParcelable(OBJECT_BEAN_ITEM,objectBean);
        EpisodeFragment fragment = new EpisodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            objectBean = getArguments().getParcelable(OBJECT_BEAN_ITEM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        contentImage = (ImageView) view.findViewById(R.id.content_main_image);
        episodeList = (ObservableListView) view.findViewById(R.id.list_episode);
        contentLayout = (ContentMenuLayout) view.findViewById(R.id.layout_content_menu);
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);
        shadowView = view.findViewById(R.id.view_toolbar_shadow);

        paddingView = LayoutInflater.from(getContext()).inflate(R.layout.template_content_info, episodeList, false);
        episodeCountTxt = (ExtraTextView) paddingView.findViewById(R.id.txt_episode_count);
        statusView = (StatusView) paddingView.findViewById(R.id.status);

        //组装
        initToolBar();
        initContentImage();
        initEpisodeList();
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        loadData();
    }

    private void initEpisodeList() {

        //占位 + 头部 bindView
        statusView.setLoadingLayout();
        bindContainerView();
        initExpandableLayout(paddingView);

        //paddingView.setClickable(true);
        episodeList.addHeaderView(paddingView);
        adapter = new EpisodeAdapter(list);
        episodeList.setScrollViewCallbacks(this);
        episodeList.setAdapter(adapter);
        episodeCountTxt.toDrawGradient();

        episodeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //contentId:26397章节  -- FrameId:221730内容
                Log.d("ContentScreenFragment","ContentScreenFragment:"+parent.getItemAtPosition(position));
                ((ContentScreenFragment)getParentFragment())
                        .start(PlayerScreenFragment.newInstance((ObjectBean) parent.getItemAtPosition(position)));
            }
        });
    }

    private void bindContainerView() {
        LinearLayout officialLayout = (LinearLayout) paddingView.findViewById(R.id.layout_official);
        TextView officialNameTxt = (TextView) paddingView.findViewById(R.id.txt_official_name);
        TextView titleTxt = (TextView) paddingView.findViewById(R.id.txt_title);
        TextView authorNameTxt = (TextView) paddingView.findViewById(R.id.txt_display_author_name);
        ExtraTextView playerTxt = (ExtraTextView) paddingView.findViewById(R.id.txt_player_type);
        ExtraTextView serialTxt = (ExtraTextView) paddingView.findViewById(R.id.txt_serial_status);
        ExtraTextView categoryTxt = (ExtraTextView) paddingView.findViewById(R.id.txt_category);
        TextView descriptionTxt = (TextView) paddingView.findViewById(R.id.txt_description);
        ImageView adultImg = (ImageView) paddingView.findViewById(R.id.img_adult_rating);
        ImageView violenceImg = (ImageView) paddingView.findViewById(R.id.img_violence_rating);
        ImageView boysloveImg = (ImageView) paddingView.findViewById(R.id.img_boyslove_expression);
        RelativeLayout expressionsLayout = (RelativeLayout) paddingView.findViewById(R.id.layout_expressions);
        CounterView counterView = (CounterView) paddingView.findViewById(R.id.view_counter);
        CounterView commentView = (CounterView) paddingView.findViewById(R.id.comment_counter);
        CounterView favoriteView = (CounterView) paddingView.findViewById(R.id.favorite_counter);
        AuthorsView authorsView = (AuthorsView) paddingView.findViewById(R.id.authors);

        //bind view value
        MetaBean.OfficialBean officialBean = objectBean.getMeta().getOfficial();
        if (officialBean != null) {
            officialNameTxt.setText(officialBean.getMeta().getShort_name());
            officialLayout.setVisibility(View.VISIBLE);
        } else {
            officialLayout.setVisibility(View.GONE);
        }
        expressionsLayout.setVisibility(View.GONE);
        adultImg.setVisibility(objectBean.getMeta().getRating().getAdult() != null ? View.VISIBLE : View.GONE);
        violenceImg.setVisibility(objectBean.getMeta().getRating().getViolence() != null ? View.VISIBLE : View.GONE);
        boysloveImg.setVisibility(objectBean.getMeta().getExpressions().isBoys_love() ? View.VISIBLE : View.GONE);
        titleTxt.setText(objectBean.getMeta().getTitle());
        authorNameTxt.setText(objectBean.getMeta().getDisplay_author_name());
        descriptionTxt.setText(Html.fromHtml(objectBean.getMeta().getDescription()));
        playerTxt.setText(setScrollType(objectBean.getMeta().getPlayer_type()));
        serialTxt.setText(getSerialStatus(objectBean.getMeta().getSerial_status()));
        categoryTxt.setText(getCategoryType(objectBean.getMeta().getCategory()));
        playerTxt.toDrawGradient();serialTxt.toDrawGradient();categoryTxt.toDrawGradient();
        counterView.setTextValue(NumberUtil.converString(objectBean.getMeta().getCounter().getView()));
        commentView.setTextValue(NumberUtil.converString(objectBean.getMeta().getCounter().getComment()));
        favoriteView.setTextValue(NumberUtil.converString(objectBean.getMeta().getCounter().getFavorite()));
        authorsView.setAuthors(objectBean.getMeta().getAuthors());
    }

    private void initExpandableLayout(View parent) {
        ToggleButton toggleButton = (ToggleButton) parent.findViewById(R.id.btn_meta_info_toggle);
        Drawable lessDrawable = ContextCompat.getDrawable(getContext(),R.drawable.icon_expand_less);
        Drawable moreDrawable = ContextCompat.getDrawable(getContext(),R.drawable.icon_expand_more);
//            lessDrawable.setBounds(24, 24, 24, 24);
//            moreDrawable.setBounds(24, 24, 24, 24);
        ExpandableLayout expandableLayout = (ExpandableLayout) parent.findViewById(R.id.layout_expandable);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout.isExpanded()) {
                    toggleButton.setCompoundDrawables(lessDrawable,null,null,null);
                    expandableLayout.collapse();
                } else {
                    toggleButton.setCompoundDrawables(moreDrawable,null,null,null);
                    expandableLayout.expand();
                }
            }
        });
    }

    public String setScrollType(String type) {
        switch (type) {
            case "scroll":
                return getString(R.string.scroll);
            default:
                return null;
        }
    }

    public String getCategoryType(String type) {
        return getString(getContext().getResources().getIdentifier("rank_" + type, "string", getContext().getPackageName()));
    }

    public String getSerialStatus(String status) {
        switch (status) {
            case "serial":
                return getString(R.string.serial);
            default:
                return null;
        }
    }

    private void initContentImage() {
        Animation animation = new AlphaAnimation(0,1);
        Picasso.with(getContext())
                .load(objectBean.getMeta().getMain_image_url())
                .config(Bitmap.Config.RGB_565)
                .into(contentImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mainColor = getImageMainColor(((BitmapDrawable) contentImage.getDrawable()).getBitmap());
                        contentImage.setAnimation(animation);
                        contentLayout.setAnimation(animation);
                        contentImage.setBackgroundColor(mainColor);
                        contentLayout.setBackgroundColor(mainColor);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initToolBar() {
        //头部toolBar
        _mActivity.setSupportActionBar(toolBar);
        toolBar.setTitle(objectBean.getMeta().getTitle());
        toolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolBar.setNavigationOnClickListener(view -> ((ContentScreenFragment)getParentFragment()).pop());
        setViewAlpha(1);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        float alpha = Math.min(1, (float) scrollY / getResources().getDimensionPixelSize(R.dimen.content_main_image_height));
        ViewHelper.setTranslationY(contentImage, -scrollY / 2);
        setViewAlpha(alpha);
    }

    //切换透明度
    void setViewAlpha(float alpha) {
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, mainColor));
        toolBar.setTitleTextColor(ScrollUtils.getColorWithAlpha(alpha, Color.WHITE));
        shadowView.getBackground().setAlpha((int) (alpha * 255));
    }

    //取得图片的相应的主颜色
    public int getImageMainColor(Bitmap bitmap) {
        Palette.Swatch swatch = new Palette.Builder(bitmap).generate().getDarkVibrantSwatch();
        if (swatch == null) {
            mainColor = ContextCompat.getColor(getContext(),R.color.content_default_bg );
            return mainColor;
        }
        return swatch.getRgb();
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    public void loadData() {
        NetApiService netApiService = ServiceFactory.createService(NetApiService.class, NetApiService.ENDPOINT);
        netApiService.getEpisodesInfo(objectBean.getId())
                .map(episodesInfo -> {
                        if (episodesInfo.getMeta().getStatus() == 200) {
                            list.clear();
                            Collections.sort(episodesInfo.getData().getResult());
                            list.addAll(episodesInfo.getData().getResult());
                        }
                        return list;
                    }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(()->DBUtil.saveEpisodeList(getContext(),list))
                .subscribe(list -> {
                    loadFinish();
                }, throwable -> {
                    //initStatusView();
                    Log.e("EpisodeFragment:", throwable.getMessage());
                });
    }

    public void loadFinish() {
        statusView.setGone();
        episodeCountTxt.setText(String.valueOf(list.size()));
        adapter.notifyDataSetChanged();

        //初始化底部导航
        contentLayout.addClickListener((ContentScreenFragment) this.getParentFragment(),list.get(list.size()-1).getId());
    }
}
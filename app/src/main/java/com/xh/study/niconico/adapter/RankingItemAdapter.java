package com.xh.study.niconico.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.entity.renderable.RankItemBean;
import com.xh.study.niconico.util.NumberUtil;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.RankView;
import com.xh.study.niconico.widget.RatioImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xh on 1/8/17.
 */
public class RankingItemAdapter extends RecyclerView.Adapter{
    private static final int TYPE_VIEW_ITEM = 0;
    private static final int TYPE_PROG_ITEM = 1;

    private List<RankItemBean> list;

    private Context context;

    int total = 0 ;

    public void setTotal(int total) {
        this.total = total;
    }

    private boolean mOpenLoadMore;

    public RankingItemAdapter(Context context,List<RankItemBean> rankItemBeans,boolean isLoadMore){
        this.context = context;
        this.list = rankItemBeans==null ? new ArrayList<>():rankItemBeans;
        this.mOpenLoadMore = isLoadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_PROG_ITEM) {
            return new LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.parts_page_loading, parent, false));
        }else {
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_item_ranking, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            RankItemBean item = list.get(position);

            itemViewHolder.rankView.setRank(item.getRank());
            itemViewHolder.thumbnailImg.setTag(item.getMeta().getThumbnail_url());
            itemViewHolder.titleTxt.setText(item.getMeta().getTitle());
            itemViewHolder.userTxt.setText(item.getMeta().getDisplay_author_name());
            itemViewHolder.counterImg.setImageResource(R.drawable.icon_favorite_star_green);
            itemViewHolder.counterTxt.setText(NumberUtil.converString(item.getMeta().getCounter().getFavorite()));
            itemViewHolder. updatedTxt.toDrawData(item.getMeta().getUpdated_at() * 1000);
        }else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.emptyTxt.setText(context.getResources().getString(R.string.loading_complete));

            if(position >= total){
                //complete
                loadingViewHolder.emptyTxt.setVisibility(View.VISIBLE);
                loadingViewHolder.loadingLayout.setVisibility(View.GONE);
            }else {
                //loading
                loadingViewHolder.emptyTxt.setVisibility(View.GONE);
                loadingViewHolder.loadingLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + getFooterViewCount() ;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == list.size()){    //将载入按钮放在最后
            return TYPE_PROG_ITEM;
        }
        return TYPE_VIEW_ITEM;
    }

    public int getFooterViewCount() {
        return mOpenLoadMore && !list.isEmpty() ? 1 : 0;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.rank)
        RankView rankView;

        @BindView(R.id.img_episode_thumbnail)
        RatioImageView thumbnailImg;

        @BindView(R.id.layout_content_meta)
        LinearLayout linearLayout;

        @BindView(R.id.txt_title)
        TextView titleTxt;

        @BindView(R.id.txt_author_name)
        TextView userTxt;

        @BindView(R.id.img_counter_icon)
        ImageView counterImg;

        @BindView(R.id.txt_counter)
        TextView counterTxt;

        @BindView(R.id.txt_updated)
        ExtraTextView updatedTxt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_empty_message)
        TextView emptyTxt;

        @BindView(R.id.layout_page_loading)
        LinearLayout loadingLayout;

        @BindView(R.id.image_loading)
        ImageView loadingImage;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ((AnimationDrawable)loadingImage.getDrawable()).start();
            emptyTxt.setVisibility(View.GONE);
        }
    }
}

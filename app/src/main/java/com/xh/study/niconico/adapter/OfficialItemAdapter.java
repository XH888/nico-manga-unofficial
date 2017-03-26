package com.xh.study.niconico.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.study.niconico.R;
import com.xh.study.niconico.callback.SendViewClickCallBack;
import com.xh.study.niconico.entity.renderable.OfficialItemBean;
import com.xh.study.niconico.widget.ExtraTextView;
import com.xh.study.niconico.widget.RatioImageView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xh on 1/9/17.
 */

public class OfficialItemAdapter extends RecyclerView.Adapter<OfficialItemAdapter.ItemViewHolder> {

    private List<OfficialItemBean> list;

    private Context context;

    private SendViewClickCallBack sendViewClickCallBack;

    public void setSendViewClickCallBack(SendViewClickCallBack sendViewClickCallBack) {
        this.sendViewClickCallBack = sendViewClickCallBack;
    }

    public OfficialItemAdapter(Context context, List<OfficialItemBean> list){
        this.context = context ;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_official_card,null);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        OfficialItemBean item = list.get(position);
        holder.cardView.setCardBackgroundColor(Color.rgb(item.getMeta().getBackground_color().getR(),item.getMeta().getBackground_color().getG(),item.getMeta().getBackground_color().getB()));
        holder.ratioImageView.setTag(item.getMeta().getThumbnail_url());
        holder.officialNameTxt.setText(item.getMeta().getShort_name());
        holder.officialDescTxt.setText(item.getMeta().getDescription());
        holder.dateTxt.toDrawData(item.getMeta().getLast_content_updated_at() * 1000);
        holder.cardView.setOnClickListener(v ->
            sendViewClickCallBack.onClick(position)
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.card_pickup)
        CardView cardView;

        @BindView(R.id.img_official_thumbnail)
        RatioImageView ratioImageView;

        @BindView(R.id.txt_date)
        ExtraTextView dateTxt;

        @BindView(R.id.txt_official_name)
        TextView officialNameTxt;

        @BindView(R.id.txt_official_description)
        TextView officialDescTxt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

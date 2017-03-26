package com.xh.study.niconico.adapter;

import com.xh.study.niconico.R;
import com.xh.study.niconico.adapter.render.AttentionRender;
import com.xh.study.niconico.adapter.render.EpisodeSingleFrameRender;
import com.xh.study.niconico.adapter.render.MoreButtonRender;
import com.xh.study.niconico.adapter.render.OfficialLogoRender;
import com.xh.study.niconico.adapter.render.OfficialMagazineRender;
import com.xh.study.niconico.adapter.render.OfficialSerialRender;
import com.xh.study.niconico.adapter.render.OfficialTrialRender;
import com.xh.study.niconico.adapter.render.ShareButtonRender;
import com.xh.study.niconico.adapter.render.TopContentRender;
import com.xh.study.niconico.adapter.render.HeadlineRender;
import com.xh.study.niconico.adapter.render.CardViewImageRender;
import com.xh.study.niconico.adapter.render.TopPickupBannerRender;
import com.xh.study.niconico.adapter.render.TopPickupPromotionRender;
import com.xh.study.niconico.adapter.render.TopPickupSecondaryRender;
import com.xh.study.niconico.adapter.viewholder.MoreButtonViewHolder;

import me.alexrs.recyclerviewrenderers.interfaces.RendererFactory;
import me.alexrs.recyclerviewrenderers.renderer.Renderer;

public class RenderFactory implements RendererFactory{

    @Override
    public Renderer getRenderer(int id) {
        switch (id) {
            //top
            case R.layout.renderer_item_top_primary_v:
                return new TopPickupBannerRender(id);
            case R.layout.renderer_item_top_primary_h:
                return new TopPickupPromotionRender(id);
            case R.layout.renderer_item_top_secondary:
                return new TopPickupSecondaryRender(id);
            case R.layout.renderer_feature_headline:
                return new HeadlineRender(id);
            case R.layout.renderer_item_feature:
                return new TopContentRender(id);

            //attention
            case R.layout.renderer_item_attention:
                return new AttentionRender(id);


            //official logo
            case R.layout.renderer_logo_image:
                return new OfficialLogoRender(id);
            //official serial
            case R.layout.renderer_official_serial_content:
                return new OfficialSerialRender(id);
            //official trial
            case R.layout.renderer_official_trial_content:
                return new OfficialTrialRender(id);
            //official magazine
            case R.layout.renderer_official_magazine:
                return new OfficialMagazineRender(id);



            //top how to create
            case R.layout.renderer_how_to_create:
                return new CardViewImageRender(id);

            //headLine
            case R.layout.renderer_headline:
                return new HeadlineRender(id);

            //ShareButton
            case R.layout.renderer_share:
                return new ShareButtonRender(id);

            //MoreButton
            case R.layout.renderer_btn_more:
                return new MoreButtonRender(id);

            //frame;
            case R.layout.renderer_scroll_single_frame:
                return new EpisodeSingleFrameRender(id);
        }
        return null;
    }


}
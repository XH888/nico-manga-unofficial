package com.xh.study.niconico.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xh.study.niconico.R;

/**
 * Created by xh on 1/26/17.
 */

public class RankView extends RelativeLayout {
    public RankView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RankView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    private Context context;

    public RankView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public void setRank(int rank) {
        createRankingNumView(rank);
    }

    public View createRankingNumView(int rank){
        View view = LayoutInflater.from(context).inflate(R.layout.widget_rank_icon,RankView.this,true);
        char[] ranks = String.valueOf(rank).toCharArray();
        Drawable drawable = getNumBgDraw(rank);
        FrameLayout higherLayout = (FrameLayout) view.findViewById(R.id.layout_higher_rank_icon);
        FrameLayout nomalLayout = (FrameLayout) view.findViewById(R.id.layout_rank_icon);
        if(isHigh(ranks)) {
            //high
            ImageView highBackground = (ImageView) view.findViewById(R.id.higher_digit_background);
            ImageView highDigitBackground = (ImageView) view.findViewById(R.id.higher_digit);

            highBackground.setImageDrawable(drawable);
            highDigitBackground.setImageResource(getRankId(ranks[0]));
            higherLayout.setVisibility(VISIBLE);
            nomalLayout.setVisibility(GONE);
        } else {
            //normal
            ImageView normalBackground = (ImageView) view.findViewById(R.id.digit_background);
            ImageView firstDigitBackground = (ImageView) view.findViewById(R.id.first_digit);
            ImageView secondDigitBackground = (ImageView) view.findViewById(R.id.second_digit);
            ImageView thirdDigitBackground = (ImageView) view.findViewById(R.id.third_digit);

            normalBackground.setImageDrawable(drawable);
            switch (ranks.length){
                case 1:
                    secondDigitBackground.setVisibility(GONE);
                    thirdDigitBackground.setVisibility(GONE);
                    firstDigitBackground.setVisibility(VISIBLE);

                    firstDigitBackground.setImageResource(getRankId(ranks[0]));
                    break;
                case 2:
                    thirdDigitBackground.setVisibility(GONE);
                    firstDigitBackground.setVisibility(VISIBLE);
                    secondDigitBackground.setVisibility(VISIBLE);

                    firstDigitBackground.setImageResource(getRankId(ranks[0]));
                    secondDigitBackground.setImageResource(getRankId(ranks[1]));
                    break;
                case 3:
                    thirdDigitBackground.setVisibility(VISIBLE);
                    firstDigitBackground.setVisibility(VISIBLE);
                    secondDigitBackground.setVisibility(VISIBLE);

                    firstDigitBackground.setImageResource(getRankId(ranks[0]));
                    secondDigitBackground.setImageResource(getRankId(ranks[1]));
                    thirdDigitBackground.setImageResource(getRankId(ranks[2]));
                    break;
            }
            higherLayout.setVisibility(GONE);
            nomalLayout.setVisibility(VISIBLE);
        }
        return view;
    }

    private boolean isHigh(char[] ranks){
        return ranks.length == 1;
    }

    private int getRankId(char rank){
        return context.getResources().getIdentifier("image_rank_no_" + rank,"drawable",context.getPackageName());
    }

    private Drawable getNumBgDraw(int rank){
        Drawable drawable = null;
        if(rank == 1 ){
            drawable = context.getDrawable(R.drawable.image_rank_bg_1);
        }else if (rank == 2){
            drawable = context.getDrawable(R.drawable.image_rank_bg_2);
        }else if(rank ==3){
            drawable = context.getDrawable(R.drawable.image_rank_bg_3);
        }else if(rank >= 4 && rank <=10){
            drawable = context.getDrawable(R.drawable.image_rank_bg_4_10);
        }else if(rank <= 100 && rank >= 11){
            drawable = context.getDrawable(R.drawable.image_rank_bg_11_100);
        }
        return drawable;
    }
}

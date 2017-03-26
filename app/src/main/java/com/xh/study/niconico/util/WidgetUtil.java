package com.xh.study.niconico.util;

import android.content.Context;

import com.xh.study.niconico.entity.OfficialDetailInfo;
import com.xh.study.niconico.entity.renderable.HeadLineItem;
import com.xh.study.niconico.entity.renderable.PickupPrimaryItem;

import java.util.List;

/**
 * Created by xh on 1/18/17.
 */

public class WidgetUtil {
    public static List<PickupPrimaryItem> addContainerColor(OfficialDetailInfo officialDetailInfo){
        for (PickupPrimaryItem temp :officialDetailInfo.getData().getResult().getPickups()){
            temp.setContainerColor(officialDetailInfo.getData().getResult().getBackground_color());
        }
        return officialDetailInfo.getData().getResult().getPickups();
    }

    public static HeadLineItem addHeadLine(String title){
        HeadLineItem headLineItem = new HeadLineItem();
        headLineItem.setTitle(title);
        return headLineItem;
    }

    public static int dip2Px(Context context, float dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2Dip(Context context,float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}

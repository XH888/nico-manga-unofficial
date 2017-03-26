package com.xh.study.niconico.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.util.TypedValue;

/**
 * Created by xh on 1/20/17.
 */

public class Utils {

    public static Bitmap drawableToBitmap(Drawable drawable ){
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width,height,config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0 , 0, width,height);
        drawable.draw(canvas);
        return bitmap;
    }

    //取出自定义属性的资源ID
    public static int getResourceId(@NonNull Context context ,@VolumeProviderCompat.ControlType int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes,typedValue,true);
        return typedValue.resourceId;
    }

    //自定义颜色的属性id
    public static int getAttrColor(@NonNull Context context,@VolumeProviderCompat.ControlType int attrRes){
        return ContextCompat.getColor(context,getResourceId(context,attrRes));
    }

    public static Drawable getAttrDrawable(@NonNull Context context,@VolumeProviderCompat.ControlType int attrRes){
        return ContextCompat.getDrawable(context,getResourceId(context,attrRes));
    }



    public static float dp2sp(Context context, float dipValue) {
        float pxValue = dp2px(context, dipValue);
        return px2sp(context, pxValue);
    }

    public static float sp2dp(@NonNull Context context, float spValue) {
        float pxValue = sp2px(context, spValue);
        return px2dp(context, pxValue);
    }

    public static float px2dp(@NonNull Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
    }

    public static float dp2px(@NonNull Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale);
    }

    public static float px2sp(@NonNull Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale);
    }

    public static float sp2px(@NonNull Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale);
    }
}

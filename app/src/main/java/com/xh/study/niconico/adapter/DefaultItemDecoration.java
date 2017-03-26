package com.xh.study.niconico.adapter;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by xh on 12/28/16.
 * 设置recyclerviwe的item等距离
 */

public class DefaultItemDecoration extends RecyclerView.ItemDecoration{
    private int spanCount;      //多少个快元素
    private int spacing;        //快元素的间隔
    //private boolean includeEdge;
    private boolean includeTop;//是否包括recycler item top的边缘
    private int half_spacing;

    private int left, right, top , bottom;

    public DefaultItemDecoration(int spacing,boolean includeTop){
        this.spacing = spacing;
        this.half_spacing = spacing/2;
        this.includeTop = includeTop;
        left = right = top = bottom = half_spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        RecyclerView.Adapter adapter =  parent.getAdapter();
        int itemCount = adapter.getItemCount();


//        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
//        if(layoutManager instanceof GridLayoutManager){
//            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
//            int viewPosition = layoutManager.getPosition(view) + 1;
//
//
//            int colNum = itemCount / spanCount;
//            if(itemCount % spanCount > 0){
//                colNum++;
//            }
//
//
//            int currColNum = viewPosition % spanCount + 1;
//            int currRowNum = viewPosition / spanCount + 1;
//
//            //左右距离
//
//            //1.正好最右边
//            if(currColNum == 1 ){
//                left = includeEdge?spacing:0;
//            }
//
//            //2.正好是最左边
//            if(currColNum == spanCount){
//                right = includeEdge?spacing:0;
//            }
//
//            //上下距离
//
//            //1.第一行
//            if(currRowNum == 1){
//                top = includeEdge?spacing:0;
//            }
//
//            //2.最后一行
//            if(currRowNum == colNum){
//                bottom = includeEdge?spacing:0;
//            }
//        }
//
//
//        outRect.set(left,top,right,bottom);



//        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
//        if(layoutManager instanceof GridLayoutManager) {
//            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
//        }else {
//            spanCount = 1;
//        }
//        int position = layoutManager.getPosition(view); // item position
//        int column = position % spanCount; // item column
//
//        if (includeEdge) {
//            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
//            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
//
//            if (position < spanCount) { // top edge
//                outRect.top = spacing;
//            }
//            outRect.bottom = spacing; // item bottom
//        } else {
//            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
//            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//            if (position >= spanCount) {
//                outRect.top = spacing; // item top
//            }
//        }
    }
}
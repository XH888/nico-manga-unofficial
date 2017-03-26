package com.xh.study.niconico.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xh.study.niconico.adapter.RenderFactory;
import com.xh.study.niconico.entity.renderable.FrameBean;

import java.util.ArrayList;
import java.util.List;

import me.alexrs.recyclerviewrenderers.adapter.RendererAdapter;
import me.alexrs.recyclerviewrenderers.builder.RendererBuilder;
import me.alexrs.recyclerviewrenderers.interfaces.Renderable;

/**
 * Created by xh on 2/3/17.
 */

public class ScalableRecyclerView extends RecyclerView {

    public ScalableRecyclerView(Context context) {
        super(context);
    }

    public ScalableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    private List<FrameBean> frameBeans = new ArrayList<>() ;

    private RendererAdapter adapter ;

    public ScalableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        adapter = new RendererAdapter(frameBeans,new RendererBuilder(new RenderFactory()));
        setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        setAdapter(adapter);
    }

    public void setFrameBeans(ArrayList<FrameBean> frameBeans) {
        this.frameBeans.clear();
        this.frameBeans.addAll(frameBeans);
        adapter.notifyDataSetChanged();
    }
}

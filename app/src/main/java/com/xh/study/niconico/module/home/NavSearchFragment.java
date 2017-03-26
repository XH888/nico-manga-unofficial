package com.xh.study.niconico.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xh.study.niconico.R;


import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xh on 1/10/17.
 * 侧边搜索
 */

public class NavSearchFragment extends SupportFragment {

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.category_list)
    ListView listView;

    public static NavSearchFragment newInstance() {
        return new NavSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_screen,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);
         listView= (ListView) view.findViewById(R.id.category_list);

        initToolBar();
        initListView();
    }



    private void initListView() {
        View searchHeader = LayoutInflater.from(_mActivity).inflate(R.layout.template_search_header,null);
        listView.addHeaderView(searchHeader);
        listView.setAdapter(new SearchAdapter());
    }

    private void initToolBar() {
        toolBar.setTitle(getContext().getString(R.string.screen_search));
        _mActivity.setSupportActionBar(toolBar);
        _mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(view ->  pop());
    }

    class SearchAdapter extends BaseAdapter {

        private String[] categoryTxt;

        public SearchAdapter(){
            categoryTxt = getContext().getResources().getStringArray(R.array.rankingTabSections);
        }

        @Override
        public int getCount() {
            return categoryTxt.length;
        }

        @Override
        public String getItem(int position) {
            return categoryTxt[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView ==null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_search_category,parent,false);

            TextView txt = (TextView) convertView.findViewById(R.id.txt_name);
            txt.setText( categoryTxt[position] );
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }
}

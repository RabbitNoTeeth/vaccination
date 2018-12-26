package com.god.gl.vaccination.main.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.main.home.activity.TimeActivity;
import com.god.gl.vaccination.main.my.adapter.SearchAdapter;
import com.god.gl.vaccination.widget.TitleView;
import com.god.gl.vaccination.widget.divider.DividerListItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/8
 * @desc
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mStrings = new ArrayList<>();

    private SearchAdapter mSearchAdapter;

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        for (int i=0;i<8;i++){
            mStrings.add("");
        }
        mSearchAdapter = new SearchAdapter(mContext,R.layout.item_search,mStrings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mSearchAdapter);
    }

    @Override
    protected void handleData() {
        mTitleView.setRightTitleListener(new TitleView.RightTitleListener() {
            @Override
            public void rightTitleClick() {
                startActivity(new Intent(mContext,TimeActivity.class));
            }
        });

    }


}

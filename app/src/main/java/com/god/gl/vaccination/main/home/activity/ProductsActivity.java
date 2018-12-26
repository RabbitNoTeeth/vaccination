package com.god.gl.vaccination.main.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.main.home.adapter.ProductsAdapter;
import com.god.gl.vaccination.widget.divider.DividerListItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/8
 * @desc
 */
public class ProductsActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ProductsAdapter mProductsAdapter;

    private List<String> mStrings = new ArrayList<>();
    @Override
    protected int getActivityViewById() {
        return R.layout.activity_products;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        for (int i=0;i<8;i++){
            mStrings.add("");
        }
        mProductsAdapter = new ProductsAdapter(mContext,R.layout.item_products,mStrings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mProductsAdapter);
        mProductsAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                         startActivity(new Intent(mContext,ProductsDetailActivity.class));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    @Override
    protected void handleData() {

    }
}

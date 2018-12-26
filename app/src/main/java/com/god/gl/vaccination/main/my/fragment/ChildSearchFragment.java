package com.god.gl.vaccination.main.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseFragment;
import com.god.gl.vaccination.main.my.adapter.SearchAdapter;
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
public class ChildSearchFragment extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mStrings = new ArrayList<>();

    private SearchAdapter mSearchAdapter;

    public static ChildSearchFragment newIntance() {
        return new ChildSearchFragment();
    }

    @Override
    public void initView(View rootView) {
        for (int i=0;i<6;i++){
            mStrings.add("");
        }
        mSearchAdapter = new SearchAdapter(mContext,R.layout.item_search,mStrings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mSearchAdapter);

    }

    @Override
    public int getFragmentViewId(Bundle savedInstanceState) {
        return R.layout.fragment_search;
    }


}

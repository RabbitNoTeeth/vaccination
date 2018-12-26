package com.god.gl.vaccination.main.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseFragment;
import com.god.gl.vaccination.main.home.adapter.PregantTimeAdapter;
import com.god.gl.vaccination.widget.divider.DividerListItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class PregnantTimeFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mStrings = new ArrayList<>();

    private PregantTimeAdapter mPregantTimeAdapter;

    public static PregnantTimeFragment newIntance() {
        return new PregnantTimeFragment();
    }

    @Override
    public void initView(View rootView) {

        for (int i=0;i<6;i++){
            mStrings.add("");
        }
        mPregantTimeAdapter = new PregantTimeAdapter(mContext,R.layout.item_article,mStrings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mPregantTimeAdapter);

    }

    @Override
    public int getFragmentViewId(Bundle savedInstanceState) {
        return R.layout.fragment_pregnant_time;
    }


}

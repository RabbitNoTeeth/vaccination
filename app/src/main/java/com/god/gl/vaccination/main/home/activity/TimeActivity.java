package com.god.gl.vaccination.main.home.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.main.home.adapter.TimeAdapter;
import com.god.gl.vaccination.main.home.bean.StandardTimeBean;
import com.god.gl.vaccination.main.login.LoginInfoCache;
import com.god.gl.vaccination.util.GsonUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/9
 * @desc
 */
public class TimeActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<StandardTimeBean.DataBean> mDataBeanList = new ArrayList<>();
    private TimeAdapter mTimeAdapter;

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_time;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void handleData() {
        mTimeAdapter = new TimeAdapter(mContext,R.layout.item_time_one,mDataBeanList);
        mRecyclerView.setAdapter(mTimeAdapter);
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.autoRefresh();
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                  getData();
            }
        });

    }

    private void getData(){
        Map<String,String> params = new HashMap<>();
        params.put("user_token", LoginInfoCache.getToken(mContext));
        OkGoUtil.request(mContext, false, CommonUrl.STANDARD_TIME, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                        StandardTimeBean standardTimeBean = GsonUtil.jsonToBean(temp,StandardTimeBean.class);
                        mTimeAdapter.refresh(standardTimeBean.data);
                        mSmartRefreshLayout.finishRefresh();
                    }

                    @Override
                    public void responseFail(String msg) {
                        mSmartRefreshLayout.finishRefresh();
                        ToastUtils.showMsg(mContext,msg);

                    }
                });
    }
}

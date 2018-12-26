package com.god.gl.vaccination.main.home.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.main.home.adapter.TimeAdapter;
import com.god.gl.vaccination.main.login.LoginInfoCache;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
    private List<String> mStrings = new ArrayList<>();
    private TimeAdapter mTimeAdapter;

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_time;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        for (int i=0;i<8;i++){
            mStrings.add("");
        }
        mTimeAdapter = new TimeAdapter(mContext,R.layout.item_time_one,mStrings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mTimeAdapter);
    }

    @Override
    protected void handleData() {

        getData();
    }

    private void getData(){
        Map<String,String> params = new HashMap<>();
        params.put("user_token", LoginInfoCache.getToken(mContext));
        OkGoUtil.request(mContext, true, CommonUrl.STANDARD_TIME, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {

                    }

                    @Override
                    public void responseFail(String msg) {

                    }
                });
    }
}

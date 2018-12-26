package com.god.gl.vaccination.main.home.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.common.Global;
import com.god.gl.vaccination.main.home.adapter.DailyHealthAdapter;
import com.god.gl.vaccination.main.home.bean.ArticleBean;
import com.god.gl.vaccination.util.GsonUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.god.gl.vaccination.widget.FrameLayoutLoading;
import com.god.gl.vaccination.widget.divider.DividerListItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.MultiItemTypeRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class DailyHealthActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_view)
    FrameLayoutLoading mFrameLayoutLoading;

    private List<ArticleBean.DataBeanX.DataBean> mDataBeanList = new ArrayList<>();
    private DailyHealthAdapter mHealthAdapter;
    private int page=1;
    private  int rows=15;


    @Override
    protected int getActivityViewById() {
        return R.layout.comment_refresh_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mHealthAdapter = new DailyHealthAdapter(mContext,R.layout.item_article,mDataBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mHealthAdapter);
        mSmartRefreshLayout.autoRefresh();

    }


    @Override
    protected void handleData() {
        mTitleView.setTitle("日常保健");
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                getList();

            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page = page + 1;
                getList();


            }
        });

        mHealthAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                DetailActivity.goDetailActivity(mContext,String.valueOf(mDataBeanList.get(position).id));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mFrameLayoutLoading.setRefreashClickListener(new FrameLayoutLoading.RefreashClickListener() {
            @Override
            public void setRefresh() {
                mFrameLayoutLoading.hideView();
                mSmartRefreshLayout.autoRefresh();
            }
        });

    }



    private void getList(){
        Map<String,String> params = new HashMap<>();
        params.put("list_rows",String.valueOf(rows));
        params.put("page",String.valueOf(page));
        params.put("category_id", Global.HEALTH_ID);
        OkGoUtil.request(mContext, false, CommonUrl.ARTICLE_LIST, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                        mFrameLayoutLoading.hideView();
                        ArticleBean articleBean = GsonUtil.jsonToBean(temp,ArticleBean.class);
                        if (null != articleBean && null != articleBean.data && null != articleBean.data.data){
                            if (page==1){
                                mSmartRefreshLayout.finishRefresh();
                                if (articleBean.data.data.size() > 0) {
                                    mHealthAdapter.refresh(articleBean.data.data);
                                } else {
                                    mFrameLayoutLoading.showErrorView();
                                    ToastUtils.showMsg(mContext, "暂无数据");
                                }
                            }else {
                                mSmartRefreshLayout.finishLoadMore();
                                if (articleBean.data.data.size()>0){
                                    mHealthAdapter.addAll(articleBean.data.data);
                                }else {
                                    ToastUtils.showMsg(mContext, "没有更多数据了");
                                }
                            }
                        }

                    }

                    @Override
                    public void responseFail(String msg) {
                        ToastUtils.showMsg(mContext, msg);
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.finishLoadMore();
                        mFrameLayoutLoading.showErrorView();
                    }
                });
    }
}

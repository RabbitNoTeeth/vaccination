package com.god.gl.vaccination.main.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.main.home.bean.ArticleDetailBean;
import com.god.gl.vaccination.util.GsonUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.img)
    ImageView mImg;

    private String articleId;

    public static void goDetailActivity(Context context, String articleId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("article_id", articleId);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_attention_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleData() {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("article_id"))) {
            articleId = getIntent().getStringExtra("article_id");
            getDetail(articleId);

        } else {
            ToastUtils.showMsg(mContext, "暂无详细信息");
        }

    }

    private void getDetail(String articleId) {
        Map<String, String> map = new HashMap<>();
        map.put("article_id", articleId);
        OkGoUtil.request(mContext, true, CommonUrl.ARTICLE_DETAIL, getTAG(), map,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                        ArticleDetailBean articleDetailBean = GsonUtil.jsonToBean(temp, ArticleDetailBean.class);
                        mTvTitle.setText(articleDetailBean.data.article_details.name);
                        mTvTime.setText(articleDetailBean.data.article_details.update_time);
                     // mTvContent.setText(articleDetailBean.data.article_details.content);
                        mTvContent.setText(Html.fromHtml(articleDetailBean.data.article_details.content));

                    }

                    @Override
                    public void responseFail(String msg) {
                        ToastUtils.showMsg(mContext, "暂无详细信息");
                    }
                });

    }

}

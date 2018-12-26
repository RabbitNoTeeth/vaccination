package com.god.gl.vaccination.main.home.adapter;

import android.content.Context;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.AdapterBase;
import com.god.gl.vaccination.main.home.bean.ArticleBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class AttentionAdapter extends AdapterBase <ArticleBean.DataBeanX.DataBean>{

    public AttentionAdapter(Context context, int layoutId, List<ArticleBean.DataBeanX.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ArticleBean.DataBeanX.DataBean dataBean, int position) {
        holder.setText(R.id.tv_title,dataBean.name);
        holder.setText(R.id.tv_content,dataBean.describe);
        holder.setText(R.id.tv_time,dataBean.create_time);

    }
}

package com.god.gl.vaccination.main.home.adapter;

import android.content.Context;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.main.home.bean.BaseInfo;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/23
 * @desc
 */
public class InfoBaseAdpter extends CommonAdapter <BaseInfo.DataBean.ChildrenListBean> {

    public InfoBaseAdpter(Context context, int layoutId, List<BaseInfo.DataBean.ChildrenListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, BaseInfo.DataBean.ChildrenListBean item, int position) {
        viewHolder.setText(R.id.tv_baby_name,"婴儿姓名:"+item.name);
        viewHolder.setText(R.id.tv_birthday,"出生日期:"+item.birth_date);
        viewHolder.setText(R.id.tv_weight,"体重(kg):"+item.weight);
    }
}

package com.god.gl.vaccination.main.home.adapter;

import android.content.Context;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.AdapterBase;
import com.god.gl.vaccination.main.home.bean.StandardTimeBean;
import com.god.gl.vaccination.widget.WrapContentListView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/9
 * @desc
 */
public class TimeAdapter extends AdapterBase <StandardTimeBean.DataBean>{

    public TimeAdapter(Context context, int layoutId, List<StandardTimeBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert( ViewHolder holder, StandardTimeBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_name,dataBean.name);
        WrapContentListView listView = holder.getView(R.id.listView);
        listView.setAdapter(new CommonAdapter<StandardTimeBean.DataBean.MonthListBean>(mContext,R.layout.item_time_two,dataBean.month_list) {

            @Override
            protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, StandardTimeBean.DataBean.MonthListBean item, int position) {

                if (item.gap_month!=0 && item.gap_month%12==0 && item.month_end==0){//以年为单位
                    viewHolder.setText(R.id.tv_time,(item.gap_month/12)+"岁："+" "+"第"+(position+1)+"剂");
                }else {//以月为单位
                    if (item.month_end==0 && item.gap_month==0){
                        viewHolder.setText(R.id.tv_time, "出生时："+" "+"第"+(position+1)+"剂");
                    }else {
                        if (item.month_end==0){
                            viewHolder.setText(R.id.tv_time,(item.gap_month) +"月："+" "+"第"+(position+1)+"剂");
                        }else {
                            viewHolder.setText(R.id.tv_time,(item.gap_month)+"~"+(item.month_end)+"月："+" "+"第"+(position+1)+"剂");
                        }

                    }
                }

                viewHolder.setText(R.id.tv_desc,item.type);



            }
        });

    }
}

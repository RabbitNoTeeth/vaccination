package com.god.gl.vaccination.main.fragment.adapter;

import android.content.Context;

import com.god.gl.vaccination.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * @author gl
 * @date 2018/7/22
 * @desc
 */
public class HomeAdapter extends CommonAdapter<String> {

    public HomeAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        if (position==0){
            viewHolder.setText(R.id.tv_head,"信息登记");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index1);
        }else if (position ==1){
            viewHolder.setText(R.id.tv_head,"接种提醒");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index2);
        }else  if (position ==2){
            viewHolder.setText(R.id.tv_head,"接种查询");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index7);
        } else if (position==3){
            viewHolder.setText(R.id.tv_head,"宣传教育");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index4);
        }else if (position ==4){
            viewHolder.setText(R.id.tv_head,"日常保健");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index3);
        }else if (position==5){
            viewHolder.setText(R.id.tv_head,"注意事项");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index5);
        }else {
            viewHolder.setText(R.id.tv_head,"更多");
            viewHolder.setImageResource(R.id.iv_head,R.mipmap.index6);
        }


    }
}

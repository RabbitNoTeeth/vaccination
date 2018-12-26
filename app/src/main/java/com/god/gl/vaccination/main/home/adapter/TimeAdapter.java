package com.god.gl.vaccination.main.home.adapter;

import android.content.Context;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.AdapterBase;
import com.god.gl.vaccination.widget.WrapContentListView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gl
 * @date 2018/12/9
 * @desc
 */
public class TimeAdapter extends AdapterBase <String>{

    private List<String> mStrings = new ArrayList<>();
    public TimeAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        for (int i=0;i<4;i++){
            mStrings.add("");
        }
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        WrapContentListView listView = holder.getView(R.id.listView);
        listView.setAdapter(new CommonAdapter<String>(mContext,R.layout.item_time_two,mStrings) {
            @Override
            protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, String item, int position) {

            }
        });




    }
}

package com.god.gl.vaccination.main.my.adapter;

import android.content.Context;

import com.god.gl.vaccination.base.AdapterBase;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/8
 * @desc
 */
public class SearchAdapter extends AdapterBase<String>{

    public SearchAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {

    }
}

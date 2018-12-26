package com.god.gl.vaccination.main.home.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.apputils.PicBean;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.main.my.activity.SearchActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class MoreActivity extends BaseActivity {
    @BindView(R.id.listView)
    ListView mListView;


    @Override
    protected int getActivityViewById() {
        return R.layout.activity_more;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mListView.setAdapter(new CommonAdapter<PicBean.ListBean>(mContext,R.layout.item_more,PicBean.getList()) {
            @Override
            protected void convert(ViewHolder viewHolder, PicBean.ListBean item, int position) {
                TextView tvName = viewHolder.getView(R.id.tv_name);
                tvName.setText(item.name);
                Drawable left = mContext.getResources().getDrawable(item.picRes);
                left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
                tvName.setCompoundDrawables(left, null, null, null);


            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        startActivity(new Intent(mContext, RegistrationInfoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, VacciantionRemindActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, SearchActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, EducationActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mContext, DailyHealthActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(mContext, AttentionActivity.class) );
                        break;
                    case 6:
                        startActivity(new Intent(mContext, ProductsActivity.class) );
                        break;
                }
            }
        });

    }

    @Override
    protected void handleData() {

    }
}

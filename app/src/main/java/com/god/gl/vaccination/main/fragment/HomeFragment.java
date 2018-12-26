package com.god.gl.vaccination.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseFragment;
import com.god.gl.vaccination.main.fragment.adapter.HomeAdapter;
import com.god.gl.vaccination.main.home.activity.AttentionActivity;
import com.god.gl.vaccination.main.home.activity.DailyHealthActivity;
import com.god.gl.vaccination.main.home.activity.EducationActivity;
import com.god.gl.vaccination.main.home.activity.MoreActivity;
import com.god.gl.vaccination.main.home.activity.RegistrationInfoActivity;
import com.god.gl.vaccination.main.home.activity.VacciantionRemindActivity;
import com.god.gl.vaccination.main.login.LoginInfoCache;
import com.god.gl.vaccination.main.login.bean.LoginBean;
import com.god.gl.vaccination.main.my.activity.SearchActivity;
import com.god.gl.vaccination.widget.WrapContentGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/5
 * @desc
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.gridView)
    WrapContentGridView mGridView;
    @BindView(R.id.tv_site)
    TextView mSite;
    private HomeAdapter mHomeAdapter;


    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void initView(View rootView) {
        LoginBean.DataBean loginBean = LoginInfoCache.getInstance().getLoginBean(mContext);
        mSite.setText(loginBean.site_name);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(i + "");
        }
        mHomeAdapter = new HomeAdapter(mContext, R.layout.item_gridview_view, list);
        mGridView.setAdapter(mHomeAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        startActivity(new Intent(mContext, MoreActivity.class) );
                        break;
                }
            }
        });

    }

    @Override
    public int getFragmentViewId(Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }





}

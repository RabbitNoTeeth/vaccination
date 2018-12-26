package com.god.gl.vaccination.main.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.main.home.adapter.InfoBaseAdpter;
import com.god.gl.vaccination.main.home.bean.BaseInfo;
import com.god.gl.vaccination.main.login.LoginInfoCache;
import com.god.gl.vaccination.util.GsonUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.god.gl.vaccination.widget.TitleView;
import com.god.gl.vaccination.widget.WrapContentListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author gl
 * @date 2018/12/6
 * @desc
 */
public class RegistrationInfoActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_adress)
    TextView mTvAdress;
    @BindView(R.id.listView)
    WrapContentListView mListView;

   private InfoBaseAdpter mInfoBaseAdpter ;
   private List<BaseInfo.DataBean.ChildrenListBean> mList = new ArrayList<>();
    private BaseInfo mBaseInfo;


    @Override
    protected int getActivityViewById() {
        return R.layout.activity_registrationinfo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void handleData() {
        mTitleView.setRightTitleListener(new TitleView.RightTitleListener() {
            @Override
            public void rightTitleClick() {
                EditInfoActivity.goEditInfoActivity(mContext,mBaseInfo.data);
            }
        });
        mInfoBaseAdpter = new InfoBaseAdpter(mContext,R.layout.item_info,mList);
        mListView.setAdapter(mInfoBaseAdpter);
        getInfo();


    }

    private void getInfo(){
        Map<String,String> params = new HashMap<>();
        params.put("user_token", LoginInfoCache.getToken(mContext));
        OkGoUtil.request(mContext, true, CommonUrl.REGISTER_INFO, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                        mBaseInfo = GsonUtil.jsonToBean(temp, BaseInfo.class);
                        mTvName.setText("姓名:"+ mBaseInfo.data.username);
                        mTvId.setText("身份证:"+ mBaseInfo.data.id_card);
                        mTvPhone.setText("手机号:"+ mBaseInfo.data.mobile);
                        mTvAdress.setText("姓名:"+ mBaseInfo.data.site_name);
                        mList.clear();
                        mList.addAll(mBaseInfo.data.children_list);
                        mInfoBaseAdpter.notifyDataSetChanged();
                    }

                    @Override
                    public void responseFail(String msg) {
                        ToastUtils.showMsg(mContext,msg);

                    }
                });
    }



}

package com.god.gl.vaccination.main.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.apputils.TextStringUtils;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.main.home.bean.BaseInfo;
import com.god.gl.vaccination.main.home.bean.ChildBean;
import com.god.gl.vaccination.main.login.LoginInfoCache;
import com.god.gl.vaccination.util.LogX;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.god.gl.vaccination.widget.TitleView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jsc.kit.wheel.dialog.DateTimeWheelDialog;

/**
 * @author gl
 * @date 2018/12/19
 * @desc
 */
public class EditInfoActivity extends BaseActivity {
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id)
    EditText mEtId;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_address)
    EditText mEtAddress;

    /*@BindView(R.id.et_babyName)
    EditText mEtBabyName;
    @BindView(R.id.et_brithday)
    EditText mEtBrithday;
    @BindView(R.id.et_kg)
    EditText mEtKg;*/


    @BindView(R.id.content)
    LinearLayout mContent;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;


    private DateTimeWheelDialog mDateTimeWheelDialog;
    private List<View> mViewList = new ArrayList<>();
    private List<View> mTopViewList = new ArrayList<>();

    private String nickName;
    private String phone;
    private String idCard;
    private String address;

    private BaseInfo.DataBean mDataBean;

    private List<ChildBean> mChildrenListBeans = new ArrayList<>();

    public static void goEditInfoActivity(Context context, BaseInfo.DataBean dataBean) {
        Intent intent = new Intent(context, EditInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataBean", dataBean);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_editinfo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (null !=  getIntent().getExtras().getSerializable("dataBean")) {
            mDataBean = (BaseInfo.DataBean) getIntent().getExtras().getSerializable("dataBean");
            mEtName.setText(mDataBean.nickname);
            mEtPhone.setText(mDataBean.mobile);
            mEtAddress.setText(mDataBean.address);
            mEtId.setText(mDataBean.id_card);

            for (int i = 0; i < mDataBean.children_list.size(); i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.view_info, null);
                EditText et_babyName = view.findViewById(R.id.et_babyName);
                final EditText et_brithday = view.findViewById(R.id.et_brithday);
                EditText et_kg = view.findViewById(R.id.et_kg);
                et_babyName.setText( mDataBean.children_list.get(i).name);
                et_brithday.setText(mDataBean.children_list.get(i).birth_date);
                et_kg.setText(mDataBean.children_list.get(i).weight);
                mTopViewList.add(view);
                et_brithday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createDialog(et_brithday);
                    }
                });
                mLlContent.addView(view);
            }
        }


    }


    @Override
    protected void handleData() {


        mTitleView.setRightTitleListener(new TitleView.RightTitleListener() {
            @Override
            public void rightTitleClick() {
                mChildrenListBeans.clear();
                if (TextStringUtils.isEmpty(mEtName)) {
                    ToastUtils.showMsg(mContext, "请填写用户名");
                    return;
                }
                nickName = mEtName.getText().toString().trim();
                /*if (TextStringUtils.isEmpty(mEtPhone)) {
                    ToastUtils.showMsg(mContext, "请填写手机号");
                    return;
                }
                phone = mEtPhone.getText().toString().trim();
                if (!Utils.isMobile(phone)) {
                    ToastUtils.showMsg(mContext, "请正确格式手机号");
                    return;
                }*/
                if (TextStringUtils.isEmpty(mEtAddress)) {
                    address = "";
                } else {
                    address = mEtAddress.getText().toString().trim();
                }

                if (TextStringUtils.isEmpty(mEtId)){
                    idCard = "";
                }else {
                    idCard = mEtId.getText().toString();
                }

                ChildBean childBean = new ChildBean();
                childBean.children_list = new ArrayList<>();
                childBean.children_list.clear();
                for (int i = 0; i < mTopViewList.size(); i++) {
                    View view = mTopViewList.get(i);
                    ChildBean.ChildrenListBean childrenListBean = new ChildBean.ChildrenListBean();
                    EditText babyName = view.findViewById(R.id.et_babyName);
                     EditText brithday = view.findViewById(R.id.et_brithday);
                    EditText kg = view.findViewById(R.id.et_kg);

                    childrenListBean.id = String.valueOf( mDataBean.children_list.get(i).id);
                    if (TextStringUtils.isEmpty(kg)) {
                        childrenListBean.weight = "";
                    } else {
                        childrenListBean.weight = kg.getText().toString().trim();
                    }
                    if (!TextStringUtils.isEmpty(babyName) && !TextStringUtils.isEmpty(brithday)) {
                        childrenListBean.name = babyName.getText().toString().trim();
                        childrenListBean.birth_date = brithday.getText().toString().trim();
                        childBean.children_list.add(childrenListBean);
                    }else {
                        if (TextStringUtils.isEmpty(babyName)){
                            ToastUtils.showMsg(mContext, "请填写婴儿姓名");
                            break;
                        }
                        if (TextStringUtils.isEmpty(brithday)){
                            ToastUtils.showMsg(mContext, "请填写婴儿出生日期");
                            break;
                        }
                    }

                }

                if (mTopViewList.size() > 0 && childBean.children_list.size() <= 0) {
                    return;

                }

                if (null != mViewList && mViewList.size() > 0) {
                    for (View view : mViewList) {
                        EditText et_babyName = view.findViewById(R.id.et_babyName);
                        EditText et_brithday = view.findViewById(R.id.et_brithday);
                        EditText et_kg = view.findViewById(R.id.et_kg);
                        if (!TextStringUtils.isEmpty(et_babyName) && !TextStringUtils.isEmpty(et_brithday)) {
                            ChildBean.ChildrenListBean childrenListBean = new ChildBean.ChildrenListBean();
                            childrenListBean.name = et_babyName.getText().toString().trim();
                            childrenListBean.birth_date = et_brithday.getText().toString();
                            childrenListBean.id = "";
                            if (TextStringUtils.isEmpty(et_kg)) {
                                childrenListBean.weight = "";
                            } else {
                                childrenListBean.weight = et_kg.getText().toString();
                            }
                            childBean.children_list.add(childrenListBean);
                        }
                    }
                }

                Gson gson = new Gson();
                String json = gson.toJson(childBean.children_list);
                LogX.e("json:",json);
                setInfo(nickName, idCard, address, json);


            }
        });

    }

    @OnClick(R.id.add)
    public void add() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_info, null);
        final EditText et_brithday = view.findViewById(R.id.et_brithday);
        mViewList.add(view);
        et_brithday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog(et_brithday);
            }
        });
        mContent.addView(view);
    }


    private DateTimeWheelDialog createDialog(final EditText editText) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2200);
        Date endDate = calendar.getTime();

        if (null == mDateTimeWheelDialog) {
            mDateTimeWheelDialog = new DateTimeWheelDialog(mContext);
        }
        mDateTimeWheelDialog.show();
        mDateTimeWheelDialog.setTitle("选择时间");
        int config = DateTimeWheelDialog.SHOW_YEAR_MONTH_DAY;
        mDateTimeWheelDialog.configShowUI(config);
        mDateTimeWheelDialog.setCancelButton("取消", null);
        mDateTimeWheelDialog.setOKButton("确定", new DateTimeWheelDialog.OnClickCallBack() {
            @Override
            public boolean callBack(View v, @NonNull Date selectedDate) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                editText.setText(dateFormat.format(selectedDate));
                return false;
            }
        });
        mDateTimeWheelDialog.setDateArea(startDate, endDate, true);
        mDateTimeWheelDialog.updateSelectedDate(new Date());
        return mDateTimeWheelDialog;
    }


    private void setInfo(String nickName, String idCard, String address, String childrenList) {
        Map<String, String> params = new HashMap<>();
        params.put("user_token", LoginInfoCache.getToken(mContext));
        params.put("nickname", nickName);
        params.put("id_card", idCard);
        params.put("address", address);
        params.put("children_list", childrenList);
        OkGoUtil.request(mContext, true, CommonUrl.UPDATE_REGISTER_INFO, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {

                    }

                    @Override
                    public void responseFail(String msg) {

                    }
                });

    }



}

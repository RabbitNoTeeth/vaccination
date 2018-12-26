package com.god.gl.vaccination.main.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.apputils.TextStringUtils;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.main.login.bean.SiteBean;
import com.god.gl.vaccination.util.GsonUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.Utils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jsc.kit.wheel.base.WheelItem;
import jsc.kit.wheel.dialog.ColumnWheelDialog;
import jsc.kit.wheel.dialog.DateTimeWheelDialog;

/**
 * @author gl
 * @date 2018/12/5
 * @desc
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_brithday)
    EditText mEtBrithday;
    @BindView(R.id.et_point)
    EditText mEtPoint;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.et_comPwd)
    EditText mEtComPwd;



    private ColumnWheelDialog mWheelDialog;
    private DateTimeWheelDialog mDateTimeWheelDialog;
    private WheelItem[] mWheelItems;
    private List<SiteBean.DataBean.SiteListBean> mDataBeanList;
    private String mSiteId;
    private String phone;
    private String pwd;
    private String time;
    private String comPwd;


    @Override
    protected int getActivityViewById() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void handleData() {
        mDataBeanList = new ArrayList<>();
        getSite();

    }

    @OnClick(R.id.btn_register)
    void register() {
        if (TextStringUtils.isEmpty(mEtPhone)){
            ToastUtils.showMsg(mContext, "请输入手机号");
            return;
        }
       if (!Utils.isMobile(mEtPhone.getText().toString())){
           ToastUtils.showMsg(mContext, "请输入正确手机号码");
           return;
       }

       if (TextStringUtils.isEmpty(mEtBrithday)){
           ToastUtils.showMsg(mContext, "请输入婴儿出生日期");
           return;
       }
       if (TextStringUtils.isEmpty(mEtPoint)){
           ToastUtils.showMsg(mContext, "请选择服务站点");
           return;
       }
        if (TextStringUtils.isEmpty(mEtPwd)){
            ToastUtils.showMsg(mContext, "请输入密码");
            return;
        }
        pwd = mEtPwd.getText().toString().trim();
        if (pwd.length()<6){
            ToastUtils.showMsg(mContext,"密码不能少于6位");
            return;
        }
        if (TextStringUtils.isEmpty(mEtComPwd)){
            ToastUtils.showMsg(mContext, "请输入确认密码");
            return;
        }
        comPwd = mEtComPwd.getText().toString().trim();
        if (!pwd.equals(comPwd)){
            ToastUtils.showMsg(mContext, "两次密码不一致");
            return;
        }
        phone = mEtPhone.getText().toString();
        time = mEtBrithday.getText().toString();
        register(phone,time,mSiteId,pwd,comPwd);
    }

    @OnClick(R.id.et_point)
    void getPoint() {
        if (null != mWheelItems) {
            if (null == mWheelDialog) {
                mWheelDialog = createDialog();
            }
            mWheelDialog.show();
        }


    }

    @OnClick(R.id.et_brithday)
    void getBrithday() {
        createTimeDialog();

    }

    private void register(String phone, String birthday, String siteId, String password, String Conpassword) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        params.put("born_at", birthday);
        params.put("site_id", siteId);
        params.put("password", password);
        params.put("password_confirm", Conpassword);
        OkGoUtil.request(mContext, true, CommonUrl.REGISTER, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                    ToastUtils.showMsg(mContext,"注册成功");
                    finish();

                    }

                    @Override
                    public void responseFail(String msg) {
                        ToastUtils.showMsg(mContext,msg);
                    }
                });
    }

    private void getSite() {
        Map<String, String> params = new HashMap<>();
        OkGoUtil.request(mContext, true, CommonUrl.SITELIST, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                        SiteBean siteBean = GsonUtil.jsonToBean(temp, SiteBean.class);
                        mDataBeanList.clear();
                        mDataBeanList.addAll(siteBean.data.site_list);
                        getItems(siteBean.data);
                    }

                    @Override
                    public void responseFail(String msg) {

                    }
                });
    }


    private ColumnWheelDialog createDialog() {
        ColumnWheelDialog<WheelItem, WheelItem, WheelItem, WheelItem, WheelItem> dialog = new ColumnWheelDialog<>(mContext);
        dialog.show();
        dialog.setTitle("选择站点");
        dialog.setCancelButton("取消", null);
        dialog.setOKButton("确定", new ColumnWheelDialog.OnClickCallBack<WheelItem, WheelItem, WheelItem, WheelItem, WheelItem>() {
            @Override
            public boolean callBack(View v, @Nullable WheelItem item0, @Nullable WheelItem item1, @Nullable WheelItem item2, @Nullable WheelItem item3, @Nullable WheelItem item4) {
                String result = "";
                if (item0 != null) {
                    result = item0.getShowText();
                    for (SiteBean.DataBean.SiteListBean siteListBean : mDataBeanList) {
                        if (siteListBean.name.endsWith(result)) {
                            mSiteId = String.valueOf(siteListBean.id);
                            break;
                        }
                    }
                }

                mEtPoint.setText(result);
                return false;
            }
        });

        dialog.setItems(
                mWheelItems,
                null,
                null,
                null,
                null
        );

        return dialog;
    }


    private WheelItem[] getItems(SiteBean.DataBean dataBean) {
        mWheelItems = new WheelItem[dataBean.site_list.size()];
        for (int i = 0; i < dataBean.site_list.size(); i++) {
            mWheelItems[i] = new WheelItem(dataBean.site_list.get(i).name);
        }
        return mWheelItems;
    }

    private DateTimeWheelDialog createTimeDialog() {

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
        mDateTimeWheelDialog.setTitle("选择婴儿出生日期");
        int config = DateTimeWheelDialog.SHOW_YEAR_MONTH_DAY;
        mDateTimeWheelDialog.configShowUI(config);
        mDateTimeWheelDialog.setCancelButton("取消", null);
        mDateTimeWheelDialog.setOKButton("确定", new DateTimeWheelDialog.OnClickCallBack() {
            @Override
            public boolean callBack(View v, @NonNull Date selectedDate) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                mEtBrithday.setText(dateFormat.format(selectedDate));

                return false;
            }
        });
        mDateTimeWheelDialog.setDateArea(startDate, endDate, true);
        mDateTimeWheelDialog.updateSelectedDate(new Date());
        return mDateTimeWheelDialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

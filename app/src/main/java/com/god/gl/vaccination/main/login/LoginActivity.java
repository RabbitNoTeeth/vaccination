package com.god.gl.vaccination.main.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.god.gl.vaccination.MainActivity;
import com.god.gl.vaccination.R;
import com.god.gl.vaccination.apputils.TextStringUtils;
import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.CommonUrl;
import com.god.gl.vaccination.common.Global;
import com.god.gl.vaccination.main.login.bean.LoginBean;
import com.god.gl.vaccination.util.GsonUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.okgo.OkGoUtil;
import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.god.gl.vaccination.util.sharepreference.PreferenceHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * @author gl
 * @date 2018/12/5
 * @desc
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.cbRember)
    CheckBox mCbRember;
    private String phone;
    private String pwd;
    private PreferenceHelper mPreferenceHelper;
    private boolean isQuit;

    @Override
    protected int getActivityViewById() {
        return R.layout.activity_login;
    }

    /**
     * @param context
     * @param isQuit 是否走退出
     */
    public static void goLoginActivity(Context context, boolean isQuit) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("isQuit", isQuit);
        context.startActivity(intent);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPreferenceHelper = PreferenceHelper.getDefault();
        isQuit = getIntent().getBooleanExtra("isQuit", false);
        if (!TextUtils.isEmpty(mPreferenceHelper.getString(mContext, Global.PHONE))) {
            mPhone.setText(mPreferenceHelper.getString(mContext, Global.PHONE));
        }
        if (!isQuit) {
            if (LoginInfoCache.getInstance().isLogin()
                    && !TextUtils.isEmpty(mPreferenceHelper.getString(mContext, Global.LOGIN_PWD))) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        }


    }

    @Override
    protected void handleData() {


    }

    @OnClick(R.id.btn_login)
    void loginIn() {
        if (TextStringUtils.isEmpty(mPhone)) {
            ToastUtils.showMsg(mContext, "请输入手机号");
            return;
        }
        if (TextStringUtils.isEmpty(mPassword)) {
            ToastUtils.showMsg(mContext, "请输入密码");
            return;
        }
        phone = mPhone.getText().toString().trim();
        pwd = mPassword.getText().toString().trim();

        login(phone, pwd);

    }

    @OnClick(R.id.tv_register)
    void register() {
        startActivity(new Intent(mContext, RegisterActivity.class));
    }

    @OnClick(R.id.forgetPwd)
    void modifyPwd() {
        startActivity(new Intent(mContext, ForgetPwdActivity.class));
    }


    private void login(final String phone, final String pwd) {
        Map<String, String> params = new HashMap<>();
      /*  params.put("username", "18811112222");
        params.put("password", "admin666666");*/
        params.put("username", phone);
        params.put("password", pwd);
        OkGoUtil.request(mContext, true, CommonUrl.LOGIN, getTAG(), params,
                new OnResponse<String>() {
                    @Override
                    public void responseOk(String temp) {
                        LoginBean loginBean = GsonUtil.jsonToBean(temp, LoginBean.class);
                        LoginInfoCache loginInfoCache = LoginInfoCache.getInstance();

                        loginInfoCache.save(mContext, loginBean.data);
                        loginInfoCache.initLoginInfo(mContext);
                        //极光设置别名（userid）与 tag(站点id)
                        Set<String> set = new HashSet<>();
                        set.add(String.valueOf(loginBean.data.site_id));

                        JPushInterface.setAlias(mContext,1,String.valueOf(loginBean.data.user_id));
                        JPushInterface.setTags(mContext,1,set);

                        mPreferenceHelper.save(mContext, Global.PHONE, phone);
                        if (mCbRember.isChecked()) {
                            mPreferenceHelper.save(mContext, Global.LOGIN_PWD, pwd);
                        } else {
                            mPreferenceHelper.save(mContext, Global.LOGIN_PWD, "");
                        }
                        startActivity(new Intent(mContext, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void responseFail(String msg) {
                        ToastUtils.showMsg(mContext, msg);

                    }
                });
    }


}

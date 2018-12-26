package com.god.gl.vaccination.main.login;

import android.content.Context;
import android.text.TextUtils;

import com.god.gl.vaccination.main.login.bean.LoginBean;
import com.god.gl.vaccination.util.sharepreference.PreferenceHelper;


/**
 * @author gl
 * @date 2018/5/24
 * @desc 登录信息缓存
 */
public class LoginInfoCache {
    /**
     * 文件名
     */
    public static final String LOGIN_FILE_NAME = "login";
    /**
     * 登录状态
     */
    private boolean login = false;
    private static LoginInfoCache sInstance = new LoginInfoCache();
    private PreferenceHelper preferenceHelper;

    private LoginInfoCache() {
        preferenceHelper = PreferenceHelper.getInstance(LOGIN_FILE_NAME);
    }

    public static LoginInfoCache getInstance() {
        return sInstance;
    }

    /**
     * @return 是否登录
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * @param context 初始化用户登录信息
     */
    public void initLoginInfo(Context context) {
        LoginBean.DataBean account = getLoginBean(context);
        if (account != null && !TextUtils.isEmpty(account.user_token)) {
            login = true;
        } else {
            login = false;
            clear(context);
        }
    }
    /**
     * 清除登录信息
     * @param context
     */
    public void clear(Context context) {
        preferenceHelper.clear(context);
        login = false;
    }
    /**
     * @param context
     * @param bean 登录bean
     * 保存登录信息
     */
    public void save(Context context, LoginBean.DataBean bean) {

        preferenceHelper.save(context, LoginConstance.TOKEN, bean.user_token);
        preferenceHelper.save(context, LoginConstance.SITE_NAME,bean.site_name);
        preferenceHelper.save(context, LoginConstance.PHONE,bean.mobile);
        preferenceHelper.save(context, LoginConstance.SITE_ID,bean.site_id);
        preferenceHelper.save(context, LoginConstance.NICKNAME, bean.nickname);
        preferenceHelper.save(context, LoginConstance.USERNAME, bean.username);
        preferenceHelper.save(context, LoginConstance.IDCARD, bean.id_card);
        preferenceHelper.save(context, LoginConstance.WEIGHT, bean.weight);
        preferenceHelper.save(context, LoginConstance.USERID, bean.user_id);
        preferenceHelper.save(context, LoginConstance.PREGNANTDATE, bean.pregnant_date);
        login = true;
    }
    /**
     * @param context
     * @return 登录信息
     */
    public LoginBean.DataBean getLoginBean(Context context) {
        LoginBean.DataBean bean = new LoginBean.DataBean();
        bean.user_token = preferenceHelper.getString(context, LoginConstance.TOKEN, "");
        bean.mobile = preferenceHelper.getString(context, LoginConstance.PHONE, "");
        bean.site_id = preferenceHelper.getInt(context, LoginConstance.SITE_ID, -1);
        bean.site_name = preferenceHelper.getString(context, LoginConstance.SITE_NAME, "");
        bean.nickname = preferenceHelper.getString(context, LoginConstance.NICKNAME, "");
        bean.username = preferenceHelper.getString(context, LoginConstance.USERNAME, "");
        bean.id_card = preferenceHelper.getString(context, LoginConstance.IDCARD, "");
        bean.weight = preferenceHelper.getString(context, LoginConstance.WEIGHT, "");
        bean.user_id = preferenceHelper.getInt(context, LoginConstance.USERID, -1);
        bean.pregnant_date = preferenceHelper.getString(context, LoginConstance.PREGNANTDATE, "");

        return bean;
    }

    /**
     * @param context
     * @return token
     */
    public static String getToken(Context context) {
        return getInstance().getLoginBean(context).user_token;
    }

    /**
     * 保存的key
     */
    public static class LoginConstance {
        public static final String TOKEN = "TOKEN";
        public static final String SITE_ID = "SITE_ID";
        public static final String SITE_NAME = "SITE_NAME";
        public static final String PHONE = "PHONE";
        public static final String USERNAME = "USERNAME";
        public static final String NICKNAME= "NICKNAME";

        public static final String IDCARD = "IDCARD";
        public static final String WEIGHT = "WEIGHT";
        public static final String USERID = "USERID";
        public static final String PREGNANTDATE = "PREGNANTDATE";




    }
}

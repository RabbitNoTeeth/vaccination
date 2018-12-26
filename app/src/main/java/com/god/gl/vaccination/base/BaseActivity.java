package com.god.gl.vaccination.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.widget.TitleView;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author gl
 * @date 2018/12/4
 * @desc
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected TitleView mTitleView;
    protected Context mContext;
    private String tag = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityViewById());
        ButterKnife.bind(this);
        setStatusBar();
        mContext = this;
        initView(savedInstanceState);
        findTitleViewId();
        handleData();
        setback();

    }


    protected abstract int getActivityViewById();

    protected abstract void initView(Bundle savedInstanceState);
    /**
     * 数据处理
     */
    protected abstract void handleData();


    /**
     * 获取头部标题栏
     */
    protected void findTitleViewId() {
        if (null != findViewById(R.id.titleView)) {
            mTitleView = findViewById(R.id.titleView);
        }
    }


    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.blue), 80);

    }

    /**
     * 头部返回键处理
     */
    private void setback() {
        if (null != mTitleView) {
            mTitleView.setBackListener(new TitleView.BackListener() {
                @Override
                public void backClick() {
                    finish();
                }
            });
        }

    }

    public String getTAG() {
        try {
            if ("".equals(tag)) {
                tag = getClass().getSimpleName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(getTAG());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}

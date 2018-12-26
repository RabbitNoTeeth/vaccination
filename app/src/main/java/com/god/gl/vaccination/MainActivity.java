package com.god.gl.vaccination;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.god.gl.vaccination.base.BaseActivity;
import com.god.gl.vaccination.common.Global;
import com.god.gl.vaccination.main.fragment.HomeFragment;
import com.god.gl.vaccination.main.fragment.MyFragment;
import com.god.gl.vaccination.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.indexBtn)
    Button indexBtn;
    @BindView(R.id.myBtn)
    Button myBtn;
    private Button[] mTabs;
    private Fragment[] fragments;
    private int index;
    // 当前fragment的index
    private int currentTabIndex;


    @Override
    protected int getActivityViewById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTabs();
        initContentFragment();
        initPermission();

    }

    @Override
    protected void handleData() {

    }

    private void initTabs() {
        mTabs = new Button[2];
        mTabs[0] = indexBtn;
        mTabs[1] = myBtn;
        // 把第一个tab设为选中状态
        mTabs[0].setSelected(true);
    }

    private void initContentFragment() {
        fragments = new Fragment[]{HomeFragment.getInstance(),
                MyFragment.getInstance()};
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, fragments[0])
                .show(fragments[0]).commit();

    }
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.indexBtn:
                index = 0;
                break;
            case R.id.myBtn:
                index = 1;
                break;
        }
        switchFragment(index);

    }

    private void switchFragment(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fl_content, fragments[index]);
            }
            trx.show(fragments[index]).commitAllowingStateLoss();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    private void initPermission() {
        String[] perms = {Manifest.permission.CAMERA
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
        };

        if (EasyPermissions.hasPermissions(mContext, perms)) {
            // 已有权限

        } else {
            // 没有权限 申请
            EasyPermissions.requestPermissions(this, "因为功能需要，需要使用相关权限，请允许",
                    Global.PERMSSION_CODE, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtils.showMsg(mContext, "您拒绝了相关权限，可能会导致相关功能不可用");
    }
}

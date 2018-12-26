package com.god.gl.vaccination.main.fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.god.gl.vaccination.R;
import com.god.gl.vaccination.base.BaseFragment;
import com.god.gl.vaccination.common.Global;
import com.god.gl.vaccination.main.home.activity.RegistrationInfoActivity;
import com.god.gl.vaccination.main.login.LoginActivity;
import com.god.gl.vaccination.main.login.LoginInfoCache;
import com.god.gl.vaccination.main.my.activity.AboutActivity;
import com.god.gl.vaccination.main.my.activity.MyInfoActivity;
import com.god.gl.vaccination.main.my.activity.OpinionActivity;
import com.god.gl.vaccination.main.my.activity.SearchActivity;
import com.god.gl.vaccination.util.TakePhotoUtil;
import com.god.gl.vaccination.util.ToastUtils;
import com.god.gl.vaccination.util.image.ImageLoad;
import com.god.gl.vaccination.widget.MyAlertDialog;
import com.god.gl.vaccination.widget.SelectPicPopWindow;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * @author gl
 * @date 2018/12/5
 * @desc
 */
public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_photo)
    ImageView mPhoto;

    private List<String> mStringList = new ArrayList<>();
    private SelectPicPopWindow mSelectPicPopWindow;
    private boolean isPission;
    private File mFile;

    public static MyFragment getInstance() {
        return new MyFragment();
    }

    @Override
    public void initView(View rootView) {
        initPermission();
        mStringList.add("拍照");
        mStringList.add("从相册中选取");
        if (null == mSelectPicPopWindow) {
            mSelectPicPopWindow = new SelectPicPopWindow(mContext, mStringList);
        }

    }

    @Override
    public int getFragmentViewId(Bundle savedInstanceState) {
        return R.layout.fragment_my;
    }

    @OnClick(R.id.info)
    void goInfo() {
        startActivity(new Intent(mContext, RegistrationInfoActivity.class));
    }

    @OnClick(R.id.search)
    void goSearch() {
        startActivity(new Intent(mContext, SearchActivity.class));
    }

    @OnClick(R.id.about)
    void goAbout() {
        startActivity(new Intent(mContext, AboutActivity.class));
    }

    @OnClick(R.id.opinion)
    void goOpinion() {
        startActivity(new Intent(mContext, OpinionActivity.class));
    }

    @OnClick(R.id.tv_info)
    void goPersonalInfo() {
        startActivity(new Intent(mContext, MyInfoActivity.class));
    }

    @OnClick(R.id.btn_quit)
    void quit(){
        new MyAlertDialog(mContext).builder()
                .setTitle("温馨提示")
                .setMsg("确定退出？")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoginInfoCache.getInstance().clear(mContext);
                        LoginActivity.goLoginActivity(mContext,true);
                        getActivity().finish();
                    }
                }).setNegativeButton("取消",null)
                .show();

    }

    @OnClick(R.id.iv_photo)
    void modifyPhoto() {
        if (!isPission) {
            ToastUtils.showMsg(mContext, "请开启相关权限");
            return;
        }
        if (!mSelectPicPopWindow.isShowing()) {
            mSelectPicPopWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
        mSelectPicPopWindow.setOnIetmSelectListener(new SelectPicPopWindow.OnIetmSelectListener() {
            @Override
            public void select(int position) {
                switch (position) {
                    case 0:
                        mFile = TakePhotoUtil.takePhoto(MyFragment.this, Global.PHOTO_REQUEST_CAREMA);
                        break;
                    case 1:
                        Matisse.from(MyFragment.this)
                                .choose(MimeType.ofImage())//图片类型
                                .countable(true)//true:选中后显示数字;false:选中后显示对号
                                .maxSelectable(1)//可选的最大数
                                .capture(false)//选择照片时，是否显示拍照
                                .captureStrategy(new CaptureStrategy(true, "com.god.gl.vaccination.fileprovider"))//参数1 true表示拍照存储在共有目录，// false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                                .imageEngine(new GlideEngine())//图片加载引擎
                                .forResult(Global.PHOTO_REQUEST_SELECT);//
                        break;
                }
                mSelectPicPopWindow.dismiss();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Global.PHOTO_REQUEST_CAREMA:
                if (resultCode == RESULT_OK) {
                    ImageLoad.loadRoundImage(mContext, mFile.getAbsolutePath(), R.mipmap.ic_user, mPhoto);
                }
                break;

            case Global.PHOTO_REQUEST_SELECT:
                if (resultCode == RESULT_OK) {
                    List<String> pathList = Matisse.obtainPathResult(data);
                    ImageLoad.loadRoundImage(mContext, pathList.get(0), R.mipmap.ic_user, mPhoto);
                }
                break;
        }

    }

    private void initPermission() {
        String[] perms = {Manifest.permission.CAMERA
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
        };

        if (EasyPermissions.hasPermissions(mContext, perms)) {
            // 已有权限
            isPission = true;
        } else {
            // 没有权限 申请
            EasyPermissions.requestPermissions(this, "因为功能需要，需要使用相关权限，请允许",
                    Global.PERMSSION_CODE, perms);
        }
    }


}

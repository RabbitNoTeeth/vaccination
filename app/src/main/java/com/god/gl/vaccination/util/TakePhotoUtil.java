package com.god.gl.vaccination.util;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import com.god.gl.vaccination.common.Global;
import com.god.gl.vaccination.util.file.FileUtils;

import java.io.File;

/**
 * @author gl
 * @date 2018/5/30
 * @desc
 */
public class TakePhotoUtil {

    public static void takePhoto(Activity activity, int code){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Global.getPicPath(activity).getDirPath()+ FileUtils.getFileName()+"jpg");
        file.getParentFile().mkdirs();
        Uri mFileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //改变Uri com.god.gl.vaccination.fileprovider 注意和manifest中的一致
            mFileUri = FileProvider.getUriForFile(activity, "com.god.gl.vaccination.fileprovider", file);
        } else {
            mFileUri = Uri.fromFile(file);
        }
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        activity.startActivityForResult(intent, code);

    }


    public static File takePhoto(Fragment fragment, int code){
        File file = new File(Global.getPicPath(fragment.getActivity()).getDirPath()+ FileUtils.getFileName()+"jpg");
        file.getParentFile().mkdirs();
        Uri mFileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //改变Uri  com.god.gl.vaccination.fileprovider注意和manifest中的一致
            mFileUri = FileProvider.getUriForFile(fragment.getActivity(), "com.god.gl.vaccination.fileprovider", file);
        } else {
            mFileUri = Uri.fromFile(file);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        fragment.startActivityForResult(intent, code);
        return file;

    }

}

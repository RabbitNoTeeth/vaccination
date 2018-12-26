package com.god.gl.vaccination.common;

import android.content.Context;

import com.god.gl.vaccination.util.file.PathHelper;

import java.io.File;

/**
 * @author gl
 * @date 2018/12/12
 * @desc 存放公共配置,获取公共配置
 */
public class Global {
    public static final int PHOTO_REQUEST_CAREMA=100;
    public static final int PHOTO_REQUEST_SELECT=101;
    public static final int PERMSSION_CODE=102;
    public static final String BASE_FILE_PATH = "vaccination";
    public static final String PATH_PIC = "image";
    public static final String PATH_FILE = "file";
    public static final String LOGIN_PWD="login_pwd";
    public static final String PHONE="phone";
    public static  final String TOKEN="6a972ef87e447f7a8d6176cad2bbc23b";
    /*"data": [
        {
            "id": 12,
            "name": "婴儿注意事项"
        },
        {
            "id": 11,
            "name": "怀孕时期"
        }

    ],*/
    public static final String  EDUCATION_ID = "7";
    public static final String   HEALTH_ID = "8";
    public static final String   GREGENT_ATTENTION = "9";
    public static final String   BABY_ATTENTION = "12";


    public static void initAppPath(Context context) {
        PathHelper.getInstance(context, BASE_FILE_PATH + File.separator + PATH_PIC, false);
        PathHelper.getInstance(context, BASE_FILE_PATH + File.separator + PATH_FILE, false);

    }

    public static PathHelper getPicPath(Context context) {
        return PathHelper.getInstance(context, BASE_FILE_PATH + File.separator + PATH_PIC, false);
    }




}

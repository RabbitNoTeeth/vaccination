package com.god.gl.vaccination.apputils;


import com.god.gl.vaccination.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gl
 * @date 2018/7/29
 * @desc
 */
public class AppUtils {

    public static List<Integer> getPicList(){
        List<Integer> list = new ArrayList();
        list.add(R.mipmap.more1);
        list.add(R.mipmap.more2);
        list.add(R.mipmap.more3);
        list.add(R.mipmap.more4);
        list.add(R.mipmap.more5);
        list.add(R.mipmap.more6);
        list.add(R.mipmap.more7);

        return list;
    }


    public static List<String> getNameList(){
        List<String> list = new ArrayList();
        list.add("信息登记");
        list.add("接种提醒");
        list.add("接种查询");
        list.add("宣传教育");
        list.add("日常保健");
        list.add("注意事项");
        list.add("妇幼用品");
        return list;
    }

}

package com.god.gl.vaccination.apputils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gl
 * @date 2018/7/29
 * @desc
 */
public class PicBean {

    public List<ListBean> list;

    public static class ListBean {
        public String name;
        public int picRes;

    }

    public static List<ListBean> getList() {
        List<ListBean> listBeans = new ArrayList<>();
        for (int i = 0; i < AppUtils.getPicList().size(); i++) {
            ListBean listBean = new ListBean();
            listBean.picRes = AppUtils.getPicList().get(i);
            listBean.name = AppUtils.getNameList().get(i);
            listBeans.add(listBean);
        }
        return listBeans;
    }


}

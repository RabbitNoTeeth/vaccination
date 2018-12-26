package com.god.gl.vaccination.main.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author gl
 * @date 2018/12/23
 * @desc
 */
public class ChildBean implements Serializable{
    public String id_card;
    public String mobile;
    public String nickname;
    public String address;

    public List<ChildBean.ChildrenListBean> children_list;

    public static class ChildrenListBean implements Serializable{
        /**
         * name : 小明2
         * birth_date : 2018-10-01
         * weight : 4.5
         */
        public String id;
        public String name;
        public String birth_date;
        public String weight;
    }

}

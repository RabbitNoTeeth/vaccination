package com.god.gl.vaccination.main.home.bean;

import com.god.gl.vaccination.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author gl
 * @date 2018/12/23
 * @desc
 */
public class BaseInfo extends BaseBean implements Serializable{


    /**
     * data : {"children_list":[{"id":1,"name":"小明2","birth_date":"2018-10-01","weight":"4.5"}],"create_time":"2018-12-02 23:07:39","id_card":"1231****3123","mobile":"111","nickname":"aaa","site_id":2,"site_name":"太和城关站点","user_id":2,"username":"18811112222"}
     * exe_time : 0.003652
     */

    public DataBean data;
    public String exe_time;

    public static class DataBean implements Serializable{
        /**
         * children_list : [{"id":1,"name":"小明2","birth_date":"2018-10-01","weight":"4.5"}]
         * create_time : 2018-12-02 23:07:39
         * id_card : 1231****3123
         * mobile : 111
         * nickname : aaa
         * site_id : 2
         * site_name : 太和城关站点
         * user_id : 2
         * username : 18811112222
         */

        public String create_time;
        public String id_card="";
        public String mobile="";
        public String nickname="";
        public int site_id;
        public String site_name="";
        public int user_id;
        public String username="";
        public String address="";
        public List<ChildrenListBean> children_list;

        public static class ChildrenListBean implements Serializable {
            /**
             * id : 1
             * name : 小明2
             * birth_date : 2018-10-01
             * weight : 4.5
             */

            public int id;
            public String name;
            public String birth_date;
            public String weight;
        }
    }
}

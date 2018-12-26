package com.god.gl.vaccination.main.home.bean;

import com.god.gl.vaccination.base.BaseBean;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/22
 * @desc
 */
public class ArticleDetailBean extends BaseBean {

    /**
     * data : {"article_category_list":[{"id":10,"name":"妇幼保健"},{"id":9,"name":"注意事项"},{"id":8,"name":"日常保健"},{"id":7,"name":"宣传教育"}],"article_details":{"id":43,"member_id":967,"name":"好好","category_id":7,"describe":"哇哇哇哇哇","content":"反反复复烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦","cover_id":196,"file_id":0,"img_ids":"","create_time":"2018-12-22 12:38:56","update_time":"2018-12-22 12:38:56","status":1,"nickname":"admin","category_name":"宣传教育"}}
     * exe_time : 0.002958
     */

    public DataBean data;
    public String exe_time;

    public static class DataBean {
        /**
         * article_category_list : [{"id":10,"name":"妇幼保健"},{"id":9,"name":"注意事项"},{"id":8,"name":"日常保健"},{"id":7,"name":"宣传教育"}]
         * article_details : {"id":43,"member_id":967,"name":"好好","category_id":7,"describe":"哇哇哇哇哇","content":"反反复复烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦","cover_id":196,"file_id":0,"img_ids":"","create_time":"2018-12-22 12:38:56","update_time":"2018-12-22 12:38:56","status":1,"nickname":"admin","category_name":"宣传教育"}
         */

        public ArticleDetailsBean article_details;
        public List<ArticleCategoryListBean> article_category_list;

        public static class ArticleDetailsBean {
            /**
             * id : 43
             * member_id : 967
             * name : 好好
             * category_id : 7
             * describe : 哇哇哇哇哇
             * content : 反反复复烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦
             * cover_id : 196
             * file_id : 0
             * img_ids :
             * create_time : 2018-12-22 12:38:56
             * update_time : 2018-12-22 12:38:56
             * status : 1
             * nickname : admin
             * category_name : 宣传教育
             */

            public int id;
            public int member_id;
            public String name;
            public int category_id;
            public String describe;
            public String content;
            public int cover_id;
            public int file_id;
            public String img_ids;
            public String create_time;
            public String update_time;
            public int status;
            public String nickname;
            public String category_name;
        }

        public static class ArticleCategoryListBean {
            /**
             * id : 10
             * name : 妇幼保健
             */

            public int id;
            public String name;
        }
    }
}

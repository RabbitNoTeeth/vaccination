package com.god.gl.vaccination.main.home.bean;

import com.god.gl.vaccination.base.BaseBean;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/22
 * @desc
 */
public class ArticleBean extends BaseBean {

    /**
     * data : {"total":2,"per_page":"15","current_page":"1","last_page":1,"data":[{"id":43,"name":"好好","category_id":7,"describe":"哇哇哇哇哇","create_time":"2018-12-22 12:38:56"},{"id":42,"name":"配合开展疫苗预防接种工作宣传教育","category_id":7,"describe":"《疫苗流通和预防接种管理条例》（国务院令第434号，自2005年6月1日起施行）。","create_time":"2018-12-08 23:09:46"}]}
     * exe_time : 0.003161
     */

    public DataBeanX data;
    public String exe_time;

    public static class DataBeanX {
        /**
         * total : 2
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":43,"name":"好好","category_id":7,"describe":"哇哇哇哇哇","create_time":"2018-12-22 12:38:56"},{"id":42,"name":"配合开展疫苗预防接种工作宣传教育","category_id":7,"describe":"《疫苗流通和预防接种管理条例》（国务院令第434号，自2005年6月1日起施行）。","create_time":"2018-12-08 23:09:46"}]
         */

        public int total;
        public String per_page;
        public String current_page;
        public int last_page;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * id : 43
             * name : 好好
             * category_id : 7
             * describe : 哇哇哇哇哇
             * create_time : 2018-12-22 12:38:56
             */

            public int id;
            public String name;
            public int category_id;
            public String describe;
            public String create_time;
        }
    }
}

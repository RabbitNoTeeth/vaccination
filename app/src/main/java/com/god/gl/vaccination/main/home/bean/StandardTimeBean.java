package com.god.gl.vaccination.main.home.bean;

import com.god.gl.vaccination.base.BaseBean;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/24
 * @desc
 */
public class StandardTimeBean extends BaseBean {

    /**
     * data : [{"id":1,"name":"乙肝疫苗","month_list":[{"id":1,"gap_month":0,"month_end":0,"type":"加强免疫"},{"id":2,"gap_month":1,"month_end":0,"type":"加强免疫"},{"id":3,"gap_month":6,"month_end":0,"type":"加强免疫"}]},{"id":2,"name":"卡介苗","month_list":[{"id":4,"gap_month":0,"month_end":0,"type":"加强免疫"}]},{"id":3,"name":"脊灰疫苗","month_list":[{"id":5,"gap_month":2,"month_end":0,"type":"加强免疫"},{"id":6,"gap_month":3,"month_end":0,"type":"加强免疫"},{"id":7,"gap_month":4,"month_end":0,"type":"加强免疫"},{"id":8,"gap_month":48,"month_end":0,"type":"加强免疫"}]},{"id":4,"name":"百白破疫苗","month_list":[{"id":9,"gap_month":3,"month_end":0,"type":"加强免疫"},{"id":10,"gap_month":4,"month_end":0,"type":"加强免疫"},{"id":11,"gap_month":5,"month_end":0,"type":"加强免疫"},{"id":12,"gap_month":18,"month_end":24,"type":"加强免疫"}]},{"id":5,"name":"白破疫苗","month_list":[{"id":13,"gap_month":72,"month_end":0,"type":"加强免疫"}]},{"id":6,"name":"麻疹疫苗","month_list":[{"id":14,"gap_month":8,"month_end":0,"type":"加强免疫"},{"id":15,"gap_month":18,"month_end":24,"type":"加强免疫"}]}]
     * exe_time : 0.001629
     */

    public String exe_time;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * name : 乙肝疫苗
         * month_list : [{"id":1,"gap_month":0,"month_end":0,"type":"加强免疫"},{"id":2,"gap_month":1,"month_end":0,"type":"加强免疫"},{"id":3,"gap_month":6,"month_end":0,"type":"加强免疫"}]
         */

        public int id;
        public String name;
        public List<MonthListBean> month_list;

        public static class MonthListBean {
            /**
             * id : 1
             * gap_month : 0
             * month_end : 0
             * type : 加强免疫
             */

            public int id;
            public int gap_month;
            public int month_end;
            public String type;
        }
    }
}

package com.god.gl.vaccination.main.login.bean;

import com.god.gl.vaccination.base.BaseBean;

import java.util.List;

/**
 * @author gl
 * @date 2018/12/20
 * @desc
 */
public class SiteBean extends BaseBean {

    /**
     * data : {"site_list":[{"id":1,"name":"太和蔡庙站点"},{"id":2,"name":"太和城关站点"},{"id":3,"name":"太和大庙站点"},{"id":4,"name":"太和大新站点"},{"id":5,"name":"太和二郎站点"},{"id":6,"name":"太和坟台站点"},{"id":7,"name":"太和高庙站点"},{"id":8,"name":"太和宫集站点"},{"id":9,"name":"太和关集站点"},{"id":10,"name":"太和郭苗站点"},{"id":11,"name":"太和洪山站点"},{"id":12,"name":"太和胡总站点"},{"id":13,"name":"太和镜湖站点"},{"id":14,"name":"太和旧县站点"},{"id":15,"name":"太和李兴站点"},{"id":16,"name":"太和马集站点"},{"id":17,"name":"太和苗集站点"},{"id":18,"name":"太和倪邱站点"},{"id":19,"name":"太和皮条孙站点"},{"id":20,"name":"太和清浅站点"},{"id":21,"name":"太和阮桥站点"},{"id":22,"name":"太和三塔站点"},{"id":23,"name":"太和三堂站点"},{"id":24,"name":"太和桑营站点"},{"id":25,"name":"太和双浮站点"},{"id":26,"name":"太和双庙站点"},{"id":28,"name":"太和复兴站点"},{"id":29,"name":"太和回民站点"},{"id":30,"name":"太和五星站点"},{"id":31,"name":"太和肖口站点"},{"id":32,"name":"太和原墙站点"},{"id":33,"name":"太和赵集站点"},{"id":34,"name":"太和赵庙站点"},{"id":35,"name":"太和预防疾控中心 "}]}
     * exe_time : 0.001982
     */

    public DataBean data;
    public String exe_time;

    public static class DataBean {
        public List<SiteListBean> site_list;

        public static class SiteListBean {
            /**
             * id : 1
             * name : 太和蔡庙站点
             */

            public int id;
            public String name;
        }
    }
}

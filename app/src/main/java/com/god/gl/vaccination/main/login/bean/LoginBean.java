package com.god.gl.vaccination.main.login.bean;

import com.god.gl.vaccination.base.BaseBean;

import java.io.Serializable;

/**
 * @author gl
 * @date 2018/12/13
 * @desc
 */
public class LoginBean extends BaseBean implements Serializable {


    /**
     * data : {"create_time":"2018-12-21 21:25:10","id_card":"","mobile":"15609619116","nickname":"user_15609619116","pregnant_date":"","site_id":1,"site_name":"太和蔡庙站点","user_id":8,"user_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJWYWNpIEpXVCIsImlhdCI6MTU0NTM5OTczMiwiZXhwIjozMDkwNzk5NDY0LCJhdWQiOiJWYWNpIiwic3ViIjoiVmFjaSIsImRhdGEiOnsidXNlcl9pZCI6OCwibmlja25hbWUiOiJ1c2VyXzE1NjA5NjE5MTE2IiwidXNlcm5hbWUiOiIxNTYwOTYxOTExNiIsImNyZWF0ZV90aW1lIjoiMjAxOC0xMi0yMSAyMToyNToxMCJ9fQ.p-no6kcjs01laUjOjT81goXBpudtujJqLpcj1E3MaXY","username":"15609619116","weight":"0"}
     * exe_time : 0.001555
     */

    public DataBean data;
    public String exe_time;

    public static class DataBean {
        /**
         * create_time : 2018-12-21 21:25:10
         * id_card :
         * mobile : 15609619116
         * nickname : user_15609619116
         * pregnant_date :
         * site_id : 1
         * site_name : 太和蔡庙站点
         * user_id : 8
         * user_token :6kcjs01laUjOjT81goXBpudtujJqLpcj1E3MaXY
         * username : 15609619116
         * weight : 0
         */
        public String create_time;
        public String id_card;
        public String mobile;
        public String nickname;
        public String pregnant_date;
        public int site_id;
        public String site_name;
        public int user_id;
        public String user_token;
        public String username;
        public String weight;
    }
}

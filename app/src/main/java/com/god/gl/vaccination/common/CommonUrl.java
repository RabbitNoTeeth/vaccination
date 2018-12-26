package com.god.gl.vaccination.common;

/**
 * @author gl
 * @date 2018/5/22
 * @desc url
 */
public class CommonUrl {

    public static final String BASE_URL_TEST = "http://vaci.xiangline.com/";
    //登录
    public static final String LOGIN = BASE_URL_TEST+"api.php/Common/userLogin";
   //注册
    public static final String REGISTER = BASE_URL_TEST+"api.php/Common/userRegister";
    //获取站点列表
    public static final String SITELIST = BASE_URL_TEST+"api.php/Common/sitelist";

    //文章列表
    public static final String ARTICLE_LIST = BASE_URL_TEST+"api.php/article/articlelist";
    //文章详情
    public static final String ARTICLE_DETAIL = BASE_URL_TEST+"api.php/combination/details";
    //获取登录信息
    public static final String REGISTER_INFO = BASE_URL_TEST+"api.php/Common/getUserInfo";
    //修改登录信息
    public static final String UPDATE_REGISTER_INFO = BASE_URL_TEST+"api.php/Common/updateInfo";
    //修改登录信息
    public static final String STANDARD_TIME = BASE_URL_TEST+"api.php/vaccine/standardList";




    

}

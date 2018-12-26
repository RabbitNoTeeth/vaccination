package com.god.gl.vaccination.util.okgo;

import android.content.Context;

import com.god.gl.vaccination.util.okgo.callback.OnResponse;
import com.god.gl.vaccination.util.okgo.callback.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author gl
 * @date 2018/12/7
 * @desc
 */
public class OkGoUtil {
    public static void  request(Context context, boolean isShow, String url, String tag, Map<String,String> params,
                                final OnResponse<String> onResponse){
        OkGo.<String>post(url)
                .tag(tag)
                .params(params)
                .execute(new StringDialogCallback(context,isShow) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseStr = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            int code = jsonObject.getInt("code");
                            if (code == 0){
                                onResponse.responseOk(responseStr);
                            }else {
                                String msg = jsonObject.getString("msg");
                                onResponse.responseFail(msg);
                            }
                        }catch (Exception e){
                            onResponse.responseFail("请求失败，请稍后再试");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        onResponse.responseFail("请求失败，请稍后再试");
                    }
                });
    }

}

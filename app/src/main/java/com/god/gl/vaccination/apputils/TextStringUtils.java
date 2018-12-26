package com.god.gl.vaccination.apputils;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * @author gl
 * @date 2018/12/13
 * @desc
 */
public class TextStringUtils {
    public static boolean isEmpty(EditText editText){
        String text = editText.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            return true;
        }
        return false;
    }
}

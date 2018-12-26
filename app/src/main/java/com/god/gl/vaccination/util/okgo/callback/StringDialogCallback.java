/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.god.gl.vaccination.util.okgo.callback;

import android.content.Context;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public abstract class StringDialogCallback extends StringCallback {
    private QMUITipDialog mDialog;
    public StringDialogCallback(Context context,boolean isShow) {
        if (isShow){
            if (null == mDialog){
                mDialog = new QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("请求网络中...")
                        .create();
            }
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(false);
            mDialog.show();

        }

    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}

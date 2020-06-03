package com.my.crack4399;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.m4399.framework.helpers.AppNativeHelper;
import com.my.crack4399.util.MyOkHttp;

import java.util.ArrayList;
import java.util.Collections;

public class Login {

    //给密码签名
    public String buildSignValue(String str) {
        return AppNativeHelper.getServerApi(str);
    }

    /* access modifiers changed from: protected */
    public void buildRequestParams(ArrayMap arrayMap) {
        ArrayList arrayList = new ArrayList(arrayMap.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < arrayList.size()) {
                String str2 = (String) arrayList.get(i2);
                String str3 = (String) arrayMap.get(str2);
                if (!TextUtils.isEmpty(str3)) {
                    arrayMap.put(str2, str3);
                    sb.append(str3);
                }
                i = i2 + 1;
            } else {
//                Log.e("t1", "sign:\n" + sb.toString());
                arrayMap.put("sign", buildSignValue(sb.toString()));
                return;
            }
        }
    }

    /* 签名参数拼接 */
    public void buildSignRequestParams(ArrayMap<String, String> arrayMap) {
        String ciE = AppNativeHelper.desCbcEncrypt("1qaz2wsx");
        arrayMap.put("password", ciE);
        arrayMap.put("deviceIdentifier", "359250050014594");
        arrayMap.put("dateline", "" + (System.currentTimeMillis() / 1000));//"1591118730");//

        arrayMap.put("username", "2190370238");
        arrayMap.put("info", "1");

        arrayMap.put("captcha", "");
        arrayMap.put("captchaId", "");
    }

    //网络请求时 再次拼接参数
    public void buildRequestParams2(ArrayMap arrayMap) {
        arrayMap.put("model", "iPhone 8");
    }

    public void login_func() {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        //拼接签名所需的参数
        buildSignRequestParams(arrayMap);
        //进行签名,并把签名后的参数添加进map
        buildRequestParams(arrayMap);

        //网络请求时 再次拼接参数
        buildRequestParams2(arrayMap);
        Log.e("t1", "param:\n" + arrayMap);
        String urlStr = "https://mapi.4399api.net/user/box/android/v1.0/log-in.html";
        MyOkHttp.getInstance().submitFormdata(urlStr, arrayMap, new MyOkHttp.OkHttpCallBack<String>() {
            @Override
            public void requestSuccess(String s) {
                Log.e("t1", "success:" + s);
            }

            @Override
            public void requestFailure(String message) {
                Log.e("t1", "failure:" + message);
            }
        }, String.class);
    }
}

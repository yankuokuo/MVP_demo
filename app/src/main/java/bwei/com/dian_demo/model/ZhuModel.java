package bwei.com.dian_demo.model;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZhuModel {
    public void Zlogin(final String account,String password,final ZhuModelCallback zhuModelCallback){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder()
                .add("mobile", account)
                .add("password", password)
                .build();
        final Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/user/reg")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                ZhuBean zhuBean = gson.fromJson(json, ZhuBean.class);
                String msg = zhuBean.getMsg();
                zhuModelCallback.setuseecd(msg);
            }
        });
    }
    public interface ZhuModelCallback {
        void setuseecd(String msg);
        void seterror(String error);
    }
}

package bwei.com.dian_demo.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainModel {
    public void login(final String account,String password,final ILoginModelCallback iLoginModelCallback){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        FormBody formBody = new FormBody.Builder()
                .add("mobile", account)
                .add("password", password)
                .build();

        final Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/user/login ")
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
                Log.d("model","数据"+json);
                Gson gson = new Gson();
                MainBean mainBean = gson.fromJson(json, MainBean.class);
                String code = mainBean.getCode();
                String msg = mainBean.getMsg();
                if ("0".equalsIgnoreCase(code)){
                if (iLoginModelCallback!=null){
                    iLoginModelCallback.getcuseccd(mainBean);
                }else{
                    if (iLoginModelCallback!=null){
                        iLoginModelCallback.geterror(msg);
                    }
                }
                }
            }
        });
    }
    public interface ILoginModelCallback{
        void getcuseccd(MainBean mainBean);
        void geterror(String error);
    }
}

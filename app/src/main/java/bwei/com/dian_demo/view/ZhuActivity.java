package bwei.com.dian_demo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwei.com.dian_demo.R;
import bwei.com.dian_demo.model.ZhuBean;
import bwei.com.dian_demo.presenter.ZhuPresenter;

public class ZhuActivity extends Activity implements View.OnClickListener,ZhuView {

    private EditText ed_z;
    private EditText ed_m;
    private Button zhu;
    private ZhuPresenter zhuPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        ed_z = findViewById(R.id.Zhu_et01);
        ed_m = findViewById(R.id.Zhu_et02);
        zhu = findViewById(R.id.Zhu_Zhuce);
        zhu.setOnClickListener(this);
        zhuPresenter = new ZhuPresenter(this);
    }
    @Override
    public void onClick(View v) {
        String zhu = ed_z.getText().toString();
        String pass = ed_m.getText().toString();
        Log.d("输入的内容","内容"+zhu+" "+pass);
        zhuPresenter.Zlogin(zhu,pass);
    }

    @Override
    public void setuseecd(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuActivity.this,"注册信息 ："+msg, Toast.LENGTH_LONG).show();
                if (msg.equals("注册成功")){
                    Intent intent = new Intent(ZhuActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void seterror(String error) {

    }
}

package bwei.com.dian_demo.view;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;


import java.util.Map;

import bwei.com.dian_demo.R;
import bwei.com.dian_demo.base.BaseActivity;
import bwei.com.dian_demo.model.MainBean;
import bwei.com.dian_demo.myimageloader.MyImageLoader;
import bwei.com.dian_demo.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener,LoginView {

    private EditText ed_z;
    private EditText ed_m;
    private Button deng;
    private Button zhu;
    private Button fx;
    private Button code;
    private TextView textView;
    private ImageView imageView;
    private TextView tx;

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter providePresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initListener() {
        ed_z = findViewById(R.id.main_et01);
        ed_m = findViewById(R.id.main_et02);
        deng = findViewById(R.id.main_denglu);
        zhu = findViewById(R.id.mian_zhuce);
        fx = findViewById(R.id.main_fx);
        tx = findViewById(R.id.main_tx);
        imageView = findViewById(R.id.main_image);
        code = findViewById(R.id.main_code);
        textView = findViewById(R.id.main_text);
        deng.setOnClickListener(this);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CodeActivity.class);
                startActivity(intent);
            }
        });
        zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuActivity.class);
                startActivity(intent);
            }
        });
        deng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UMShareAPI mShareAPI = UMShareAPI.get(MainActivity.this);
                mShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ,authListener);
                return false;
            }
        });
        fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage Image = new UMImage(MainActivity.this, R.mipmap.ic_launcher);
                new ShareAction(MainActivity.this).withText("hello")
                        .withMedia(Image)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View v) {
        String zhu = ed_z.getText().toString();
        String pass = ed_m.getText().toString();
        providePresenter().login(zhu,pass);
    }

    @Override
    public void getcuseccd(final MainBean mainBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"登陆成功 ：" +mainBean.getData().getMobile(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void geterror(String error) {
        Toast.makeText(MainActivity.this,"登陆失败 ：" +error, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Map<String, String> data1=data;
           String url = data1.get("profile_image_url");
            MyImageLoader.getInstance().display(url,imageView);
            textView.setText(data1.get("screen_name"));
            tx.setText(data1.get("gender"));
            Log.d("Map","数据++"+data1.get("screen_name"));
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}

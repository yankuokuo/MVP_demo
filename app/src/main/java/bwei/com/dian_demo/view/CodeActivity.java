package bwei.com.dian_demo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import bwei.com.dian_demo.R;

public class CodeActivity extends Activity {
    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private CheckBox mCheckBox;
    private Button scanBarCodeButton;
    private Button generateQRCodeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        mCheckBox = (CheckBox) findViewById(R.id.logo);
        scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        //扫描
        scanBarCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });
        //生成二维码
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentString = qrStrEditText.getText().toString();
                if (!TextUtils.isEmpty(contentString)){
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_qq) : null);
                    qrImgImageView.setImageBitmap(qrCodeBitmap);
                }else{
                    Toast.makeText(CodeActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            resultTextView.setText(result);
        }
    }
}

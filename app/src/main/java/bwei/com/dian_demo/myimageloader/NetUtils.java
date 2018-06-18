package bwei.com.dian_demo.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络缓存
 */
public class NetUtils {
    private LoadBitmapListener loadBitmapListener;
    private static final String TAG = "NetUtils---";
    //从网络获取图片
    public void getBitMapFromNet(String url) {
        MyTask myTask = new MyTask();
        myTask.execute(url);
    }

    //使用AsyncTask+HttpURLConnection进行图片请求
    class MyTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String picUrl = strings[0];
            try {
                URL u = new URL(picUrl);
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setConnectTimeout(5000);
                if (connection.getResponseCode() == 200) {
                    InputStream inputStream = connection.getInputStream();
                    //将流---bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //得到图片
            Log.d(TAG, "从网络取得图片");
            loadBitmapListener.sendBitmap(bitmap);
        }
    }

    //接口回调，---监听
    public interface LoadBitmapListener {
        void sendBitmap(Bitmap bitmap);
    }

    //定义一个供外部访问的方法
    public void setLoadBitmapListener(LoadBitmapListener loadBitmapListener){
        this.loadBitmapListener = loadBitmapListener;
    }

}

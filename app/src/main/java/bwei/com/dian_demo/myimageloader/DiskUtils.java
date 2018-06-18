 package bwei.com.dian_demo.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 磁盘缓存
 * <p>
 * LruCache
 * 最近最少使用原则
 * <p>
 * 强引用
 * 直接new对象，int i = 0;OOM对象也不会被回收
 * OOM  out of memeory  内存溢出
 * 软引用
 * SoftReference<对象>
 * 内存不够的时候会对对象进行回收
 * 弱引用
 * WeakRefrence
 * <p>
 * 虚引用
 */
public class DiskUtils {
    private String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/images";
    private static final String TAG = "DiskUtils---";
    private String enUrl;

    //将图片存入disk
    public void saveToDisk(String url, Bitmap bitmap) {

        try {
            enUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File file = new File(sdPath + "/" + enUrl);
        Log.d(TAG, "saveToDisk: " + file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
                //写入内存卡
                //compress 压缩
                FileOutputStream fos = new FileOutputStream(file);
                //将图片压入磁盘
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                Log.d(TAG, "sdcard保存成功");
                //关闭流
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //从disk中取出图片
    public Bitmap getBitmapFromDisk(String url) {

        try {
            enUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "---getBitmapFromDisk: " + url);
        File file = new File(sdPath + "/" + enUrl);
        if (file.exists()) {
            //通过bitmap工厂类 将文件转成Bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(file.getName());
            Log.d(TAG, "从sdcard取出图片" + bitmap);
            return bitmap;
        }
        return null;
    }
}

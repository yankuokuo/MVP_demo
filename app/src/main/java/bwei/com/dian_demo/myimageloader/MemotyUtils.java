package bwei.com.dian_demo.myimageloader;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * 内存缓存
 */
public class MemotyUtils {

    private static final String TAG = "MemotyUtils---";
    //    Runtime.getRuntime().freeMemory() 获取可用内存
    private LruCache<String, Bitmap> lruCache = new LruCache<>((int) (Runtime.getRuntime().freeMemory() / 8));

    //存入磁盘的方法
    public void saveToMemory(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
        Log.d(TAG, "内存保存完毕---");
    }

    //从磁盘取出的方法
    public Bitmap getBitMap(String url) {

        Bitmap bitmap = lruCache.get(url);
        Log.d(TAG, "内存取到数据: " + bitmap);
        return bitmap;
    }

}

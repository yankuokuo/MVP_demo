package bwei.com.dian_demo.myimageloader;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * 进行图片加载的类
 * <p>
 * 单利模式：
 * 1.饿汉式
 * 2.懒汉式
 */
public class MyImageLoader {
    private static final String TAG = "MyImageLoader----";
    private static MyImageLoader myImageLoader = null;
    private static MemotyUtils memotyUtils = null;
    private static DiskUtils diskUtils = null;
    private static NetUtils netUtils = null;

    public static MyImageLoader getInstance() {
        if (myImageLoader == null) {
            myImageLoader = new MyImageLoader();
            //内存
            memotyUtils = new MemotyUtils();
            //SDCrd
            diskUtils = new DiskUtils();
            //网络
            netUtils = new NetUtils();
        }
        return myImageLoader;
    }

    //加载网络图片的方法
    public void display(final String url, final ImageView imageView) {
        //查找内存中是否有这个图片
        Bitmap bitMap1 = memotyUtils.getBitMap(url);
        //如果有，直接加载
        if (bitMap1 != null) {
            imageView.setImageBitmap(bitMap1);
        } else {
            //没有 --- Disk
            Log.d(TAG, "去sdcard取数据-----");
            Bitmap bitmap2 = diskUtils.getBitmapFromDisk(url);
            Log.d(TAG, "display: "+bitmap2);
            //Disk 有，直接加载，没有 -----net
            if (bitmap2 != null) {
                imageView.setImageBitmap(bitmap2);
                //保存到内存
                memotyUtils.saveToMemory(url, bitmap2);
            } else {
                //如果网络有 加载  没有呢？图片不存在
                netUtils.getBitMapFromNet(url);
                //使用
                netUtils.setLoadBitmapListener(new NetUtils.LoadBitmapListener() {
                    @Override
                    public void sendBitmap(Bitmap bitmap) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                            //将图片保存到内存和sdcard
                            memotyUtils.saveToMemory(url, bitmap);
                            diskUtils.saveToDisk(url, bitmap);
                        } else {
                            Log.d(TAG, "网络也没有");
                        }
                    }
                });
            }
        }
    }
}

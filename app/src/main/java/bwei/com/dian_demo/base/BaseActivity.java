package bwei.com.dian_demo.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity<P extends BasePresenter> extends Activity {
   protected P presenter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        initView();
        initListener();
        presenter=providePresenter();
    }

    protected abstract int LayoutId();

    protected abstract P providePresenter();

    protected abstract void initListener();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}

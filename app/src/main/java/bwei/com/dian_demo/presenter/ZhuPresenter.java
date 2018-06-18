package bwei.com.dian_demo.presenter;

import bwei.com.dian_demo.model.ZhuModel;
import bwei.com.dian_demo.view.ZhuView;

public class ZhuPresenter {
    ZhuView zhuView;
    private final ZhuModel zhuModel;

    public ZhuPresenter(ZhuView zhuView) {
        this.zhuView = zhuView;
        zhuModel = new ZhuModel();
    }
    public void Zlogin(String account,String password){
zhuModel.Zlogin(account, password, new ZhuModel.ZhuModelCallback() {
    @Override
    public void setuseecd(String msg) {
        zhuView.setuseecd(msg);
}

    @Override
    public void seterror(String error) {

    }
});
    }
}

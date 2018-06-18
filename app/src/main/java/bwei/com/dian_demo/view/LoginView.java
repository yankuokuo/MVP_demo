package bwei.com.dian_demo.view;

import bwei.com.dian_demo.base.IView;
import bwei.com.dian_demo.model.MainBean;

public interface LoginView extends IView {
    void getcuseccd(MainBean mainBean);
    void geterror(String error);
}

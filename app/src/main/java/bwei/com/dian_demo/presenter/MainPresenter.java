package bwei.com.dian_demo.presenter;

import bwei.com.dian_demo.base.BasePresenter;
import bwei.com.dian_demo.model.MainBean;
import bwei.com.dian_demo.model.MainModel;
import bwei.com.dian_demo.view.LoginView;

public class MainPresenter extends BasePresenter<LoginView> {
    private  MainModel mainModel;
    public MainPresenter(LoginView loginView) {
        super(loginView);

    }

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }

    public void login(String account,String password){
        if (account==null){
            if (view!=null){
                view.geterror("手机号不能为空");
            }
            return;
        }
        mainModel.login(account, password, new MainModel.ILoginModelCallback() {
            @Override
            public void getcuseccd(MainBean mainBean) {
                if (view!=null){
                    view.getcuseccd(mainBean);
                }
            }

            @Override
            public void geterror(String error) {
                if (view!=null){
                    view.geterror(error);
                }
            }
        });
    }

}

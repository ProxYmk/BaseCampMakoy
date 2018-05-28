package basecamp.everest.com.basecamp.view.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.databinding.ActivityLoginBinding;
import basecamp.everest.com.basecamp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements Observer{


    private LoginViewModel loginViewModel;
    private ActivityLoginBinding loginActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpObserver(loginViewModel);
    }

    private void initDataBinding() {
        loginActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        loginActivityBinding.setLoginViewModel(loginViewModel);
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginViewModel.reset();
    }
}

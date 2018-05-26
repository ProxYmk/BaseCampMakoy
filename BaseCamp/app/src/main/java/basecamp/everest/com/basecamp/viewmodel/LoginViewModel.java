package basecamp.everest.com.basecamp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Observable;

import basecamp.everest.com.basecamp.MainActivity;
import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends Observable{

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CallbackManager callbackManager;

    public LoginViewModel(@NonNull Context context) {
        this.context = context;
        registerCallback();

    }

    private void registerCallback() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(context.getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(context.getApplicationContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(context.getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        callbackManager = null;
        context = null;
    }
}

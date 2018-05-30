package basecamp.everest.com.basecamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;

import basecamp.everest.com.basecamp.view.ui.LoginActivity;
import basecamp.everest.com.basecamp.view.ui.MakesActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Intent intent;
        if(!isLoggedIn){
            intent = new Intent(this, LoginActivity.class);
        }else{
            intent = new Intent(this, MakesActivity.class);
        }
        startActivity(intent);

    }

}

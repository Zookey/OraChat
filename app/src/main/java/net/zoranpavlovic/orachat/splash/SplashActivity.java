package net.zoranpavlovic.orachat.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.account.AccountActivity;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        makeFullScreen();

        if(isUserLoggedIn()){
            openMainScreen();
        } else{
            openAccountScreenForLoginOrRegister();
        }
    }

    private boolean isUserLoggedIn() {
        return !getUserToken().isEmpty();
    }

    private void openAccountScreenForLoginOrRegister() {
        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }

    private void openMainScreen() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    @NonNull
    private String getUserToken() {
        sharedPreferences = ((App) this.getApplicationContext()).getAppComponent().getSharedPreferences();
        return sharedPreferences.getString("Authorization", "");
    }

    private void makeFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}

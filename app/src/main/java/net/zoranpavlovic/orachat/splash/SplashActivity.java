package net.zoranpavlovic.orachat.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.account.AccountActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        makeFullScreen();

        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }

    private void makeFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}

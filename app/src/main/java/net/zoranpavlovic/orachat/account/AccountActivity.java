package net.zoranpavlovic.orachat.account;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.account.login.LoginAccountFragment;
import net.zoranpavlovic.orachat.account.register.DaggerRegisterAccountComponent;
import net.zoranpavlovic.orachat.account.register.RegisterAccountFragment;
import net.zoranpavlovic.orachat.account.register.RegisterAccountModule;
import net.zoranpavlovic.orachat.account.register.RegisterAccountPresenterImpl;
import net.zoranpavlovic.orachat.core.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        setUpToolbar();

        showFragment();
    }

    private void setUpToolbar() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void showFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        RegisterAccountFragment registerAccountFragment = new RegisterAccountFragment();
        LoginAccountFragment loginAccountFragment = new LoginAccountFragment();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.frame_layout, loginAccountFragment);
                ft.commit();
            }
        }
    }
}

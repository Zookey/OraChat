package net.zoranpavlovic.orachat.account.login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.account.register.AccountResponse;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.core.Constants;
import net.zoranpavlovic.orachat.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAccountFragment extends Fragment implements LoginAccountView {

    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;

    @Inject LoginAccountPresenter loginPresenter;
    @Inject SharedPreferences sharedPreferences;

    public LoginAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login_account, container, false);

        ButterKnife.bind(this, v);

        initDagger();

        return v;
    }

    private void initDagger() {

        sharedPreferences = ((App) getActivity().getApplicationContext()).getAppComponent().getSharedPreferences();

        DaggerLoginAccountComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .loginAccountModule(new LoginAccountModule(this, sharedPreferences))
                .build()
                .inject(this);

    }

    @OnClick(R.id.btn_login)
    void loginClick(){
        loginPresenter.login(getEmail(), getPassword());
    }

    private String getEmail(){
        return etEmail.getText().toString();
    }

    private String getPassword(){
        return etPassword.getText().toString();
    }

    @Override
    public void onLoginSuccess(AccountResponse accountResponse) {
        openMainScreen();
    }

    private void openMainScreen() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onLoginError(String error) {
        Log.d("TAG", error);
    }
}

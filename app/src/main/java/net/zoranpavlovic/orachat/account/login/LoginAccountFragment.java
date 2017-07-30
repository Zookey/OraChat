package net.zoranpavlovic.orachat.account.login;


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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAccountFragment extends Fragment implements LoginAccountView {

    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;

    @Inject
    LoginAccountPresenter loginPresenter;


    public LoginAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login_account, container, false);

        ButterKnife.bind(this, v);

        DaggerLoginAccountComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .loginAccountModule(new LoginAccountModule(this))
                .build()
                .inject(this);

        return v;
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
        Log.d("TAG", "LOGIN: "+accountResponse.toString());
    }

    @Override
    public void onLoginError(String error) {
        Log.d("TAG", error);
    }
}

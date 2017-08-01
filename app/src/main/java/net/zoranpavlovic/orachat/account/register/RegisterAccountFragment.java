package net.zoranpavlovic.orachat.account.register;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;
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
public class RegisterAccountFragment extends Fragment implements RegisterAccountView {


    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_confirm) EditText etConfirm;

    @Inject RegisterAccountPresenter registerPresenter;
    @Inject SharedPreferences sharedPreferences;

    public RegisterAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_register_account, container, false);

        ButterKnife.bind(this, v);

        initDagger();

        return v;
    }

    private void initDagger() {
        DaggerRegisterAccountComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .registerAccountModule(new RegisterAccountModule(this))
                .build()
                .inject(this);

        sharedPreferences = ((App) getActivity().getApplicationContext()).getAppComponent().getSharedPreferences();
    }

    @OnClick(R.id.btn_register)
    void registerAccountClick(){
        if(validate()) {
            registerPresenter.register(getName(), getEmail(), getPassword(), getConfirm());
        } else{
            Toast.makeText(getActivity(), "Not valid information for account!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        if(getName().isEmpty() == false && getEmail().isEmpty() == false && getPassword().isEmpty() == false && getConfirm().isEmpty() == false && getPassword().equals(getConfirm())){
            return  true;
        }
        return false;
    }

    private String getName(){
        return etName.getText().toString();
    }

    private String getEmail(){
        return etEmail.getText().toString();
    }

    private String getPassword(){
        return etPassword.getText().toString();
    }

    private String getConfirm(){
        return etConfirm.getText().toString();
    }

    @Override
    public void onRegisterAccountSuccess(Response<AccountResponse> accountResponse) {
        saveToken(accountResponse);
        saveUser(accountResponse);
        openMainScreen();
    }

    private void saveUser(Response<AccountResponse> accountResponse) {
        int id = accountResponse.body().getData().getId();
        sharedPreferences.edit().putInt(Constants.USER_ID, id).apply();
    }

    @Override
    public void onRegisterAccountError(String error) {
        Log.d("TAG", error);
    }

    private void saveToken(Response<AccountResponse> accountResponse) {
        Headers headers = accountResponse.headers();
        String token = headers.get(Constants.AUTHORIZATION);
        sharedPreferences.edit().putString(Constants.AUTHORIZATION, token).apply();
    }

    private void openMainScreen() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}

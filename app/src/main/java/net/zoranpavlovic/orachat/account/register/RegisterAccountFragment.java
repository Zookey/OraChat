package net.zoranpavlovic.orachat.account.register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.account.AccountActivity;
import net.zoranpavlovic.orachat.core.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterAccountFragment extends Fragment implements RegisterAccountView {


    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_confirm) EditText etConfirm;

    @Inject
    RegisterAccountPresenterImpl registerPresenter;

    public RegisterAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_register_account, container, false);

        ButterKnife.bind(this, v);

        DaggerRegisterAccountComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .registerAccountModule(new RegisterAccountModule(this))
                .build()
                .inject(this);

        return v;
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
    public void onRegisterAccountSuccess(AccountResponse accountResponse) {
        Log.d("TAG", accountResponse.toString());
    }

    @Override
    public void onRegisterAccountError(String error) {
        Log.d("TAG", error);
    }
}

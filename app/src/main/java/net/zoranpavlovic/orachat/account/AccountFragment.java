package net.zoranpavlovic.orachat.account;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.account.current.GetCurrentUserModule;
import net.zoranpavlovic.orachat.account.current.GetCurrentUserPresenter;
import net.zoranpavlovic.orachat.account.current.GetCurrentUserView;
import net.zoranpavlovic.orachat.account.logout.LogoutAccountModule;
import net.zoranpavlovic.orachat.account.logout.LogoutAccountPresenter;
import net.zoranpavlovic.orachat.account.logout.LogoutAccountView;
import net.zoranpavlovic.orachat.account.register.AccountResponse;
import net.zoranpavlovic.orachat.account.update.UpdateCurrentUserModule;
import net.zoranpavlovic.orachat.account.update.UpdateCurrentUserPresenter;
import net.zoranpavlovic.orachat.account.update.UpdateCurrentUserView;
import net.zoranpavlovic.orachat.core.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements GetCurrentUserView, UpdateCurrentUserView, LogoutAccountView {

    @BindView(R.id.et_name)  EditText etName;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_confirm) EditText etConfirm;

    @Inject
    GetCurrentUserPresenter getCurrentUserPresenter;

    @Inject
    UpdateCurrentUserPresenter updateCurrentUserPresenter;

    @Inject
    LogoutAccountPresenter logoutAccountPresenter;

    @Inject
    SharedPreferences sharedPreferences;


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_account, container, false);

        setHasOptionsMenu(true);

        ButterKnife.bind(this, v);

        initDagger();

        getCurrentUser();

        return v;
    }

    private void getCurrentUser() {
        getCurrentUserPresenter.getCurrentUser();
    }

    private void initDagger() {
        DaggerAccountComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .getCurrentUserModule(new GetCurrentUserModule(this))
                .updateCurrentUserModule(new UpdateCurrentUserModule(this))
                .logoutAccountModule(new LogoutAccountModule(this))
                .build()
                .inject(this);

        sharedPreferences = ((App) getActivity().getApplicationContext()).getAppComponent().getSharedPreferences();
    }

    @Override
    public void onCurrentUserLoaded(AccountResponse response) {
        setUserData(response);
    }

    private void setUserData(AccountResponse response) {
        if(response != null){
            etName.setText(response.getData().getName());
            etEmail.setText(response.getData().getEmail());
        }
    }

    @Override
    public void onCurrentUserError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCurrentUserUpdated(AccountResponse accountResponse) {
        Toast.makeText(getActivity(), R.string.user_updated, Toast.LENGTH_SHORT).show();
        setUserData(accountResponse);
    }

    @Override
    public void onCurrentUserUpdateError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogoutSuccess() {
        sharedPreferences.edit().clear().apply();
        openAccountActivityForLoginOrRegister();
    }

    private void openAccountActivityForLoginOrRegister() {
        Intent intent = new Intent(getActivity(), AccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onLogoutError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.account_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout){
            logoutAccountPresenter.logout();
            return true;
        } else if(item.getItemId() == R.id.update) {
            updateCurrentUserPresenter.updateCurrentUser(getName(), getEmail(), getPassword(), getConfirm());
            return true;
        }
        return true;
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
}

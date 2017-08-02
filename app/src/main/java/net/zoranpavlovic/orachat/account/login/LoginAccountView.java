package net.zoranpavlovic.orachat.account.login;

import net.zoranpavlovic.orachat.account.register.AccountResponse;

import retrofit2.Response;

/**
 * Created by osx on 29/07/2017.
 */

public interface LoginAccountView {


    void onLoginSuccess(AccountResponse accountResponse);

    void onLoginError(String error);
}

package net.zoranpavlovic.orachat.account.register;

import retrofit2.Response;

/**
 * Created by osx on 29/07/2017.
 */

public interface RegisterAccountView {

    void onRegisterAccountSuccess(AccountResponse accountResponse);

    void onRegisterAccountError(String error);
}

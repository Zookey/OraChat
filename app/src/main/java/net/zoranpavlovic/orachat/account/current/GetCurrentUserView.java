package net.zoranpavlovic.orachat.account.current;

import net.zoranpavlovic.orachat.account.register.AccountResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface GetCurrentUserView {

    void onCurrentUserLoaded(AccountResponse response);

    void onCurrentUserError(String error);
}

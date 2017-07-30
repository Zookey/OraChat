package net.zoranpavlovic.orachat.account.update;

import net.zoranpavlovic.orachat.account.register.AccountResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface UpdateCurrentUserView {

    void onCurrentUserUpdated(AccountResponse accountResponse);

    void onCurrentUserUpdateError(String error);
}

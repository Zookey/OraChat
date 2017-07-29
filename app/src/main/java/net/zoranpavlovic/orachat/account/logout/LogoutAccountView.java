package net.zoranpavlovic.orachat.account.logout;

/**
 * Created by osx on 29/07/2017.
 */

public interface LogoutAccountView {

    void onLogoutSuccess();

    void onLogoutError(String error);
}

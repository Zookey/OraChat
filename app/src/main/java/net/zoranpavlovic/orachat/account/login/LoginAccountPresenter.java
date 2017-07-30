package net.zoranpavlovic.orachat.account.login;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 29/07/2017.
 */

public interface LoginAccountPresenter extends BasePresenter{

    void login(String email, String password);
}

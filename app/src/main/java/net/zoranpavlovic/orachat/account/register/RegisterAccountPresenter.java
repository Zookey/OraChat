package net.zoranpavlovic.orachat.account.register;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 29/07/2017.
 */

public interface RegisterAccountPresenter extends BasePresenter {

    void register(String name, String email, String password, String confirm);
}

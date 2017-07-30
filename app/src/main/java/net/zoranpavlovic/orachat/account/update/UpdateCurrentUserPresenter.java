package net.zoranpavlovic.orachat.account.update;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 30/07/2017.
 */

public interface UpdateCurrentUserPresenter extends BasePresenter {

    void updateCurrentUser(String name, String email, String password, String confirm);
}

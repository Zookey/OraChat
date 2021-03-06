package net.zoranpavlovic.orachat.account;

import net.zoranpavlovic.orachat.account.current.GetCurrentUserModule;
import net.zoranpavlovic.orachat.account.logout.LogoutAccountModule;
import net.zoranpavlovic.orachat.account.update.UpdateCurrentUserModule;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@FragmentScoped
@Component(dependencies = AppComponent.class, modules = {GetCurrentUserModule.class, UpdateCurrentUserModule.class, LogoutAccountModule.class})
public interface AccountComponent {
    void inject(AccountFragment accountFragment);
}

package net.zoranpavlovic.orachat.account.logout;

import net.zoranpavlovic.orachat.account.AccountFragment;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;

import dagger.Component;

/**
 * Created by osx on 29/07/2017.
 */

@FragmentScoped
@Component(dependencies = AppComponent.class, modules = LogoutAccountModule.class)
public interface LogoutAccountComponent {
    void inject(AccountFragment accountFragment);
}

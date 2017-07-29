package net.zoranpavlovic.orachat.account.logout;

import net.zoranpavlovic.orachat.account.view.AccountFragment;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;

import dagger.Component;

/**
 * Created by osx on 29/07/2017.
 */

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = LogoutAccountModule.class)
public interface LogoutAccountComponent {
    void inject(AccountFragment accountFragment);
}

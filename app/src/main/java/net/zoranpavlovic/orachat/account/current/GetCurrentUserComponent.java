package net.zoranpavlovic.orachat.account.current;

import net.zoranpavlovic.orachat.account.AccountFragment;
import net.zoranpavlovic.orachat.core.di.ActivityScoped;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = GetCurrentUserModule.class)
public interface GetCurrentUserComponent {
    void inject(AccountFragment accountFragment);
}

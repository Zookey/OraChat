package net.zoranpavlovic.orachat.account.update;

import net.zoranpavlovic.orachat.account.AccountFragment;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = UpdateCurrentUserModule.class)
public interface UpdateCurrentUserComponent {
    void inject(AccountFragment accountFragment);
}

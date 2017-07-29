package net.zoranpavlovic.orachat.account.login;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;

import dagger.Component;

/**
 * Created by osx on 29/07/2017.
 */

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = LoginAccountModule.class)
public interface LoginAccountComponent {
    void inject(LoginAccountFragment fragment);
}

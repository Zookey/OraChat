package net.zoranpavlovic.orachat.account.login;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;

import dagger.Component;

/**
 * Created by osx on 29/07/2017.
 */

@FragmentScoped
@Component(dependencies = {AppComponent.class}, modules = LoginAccountModule.class)
public interface LoginAccountComponent {
    void inject(LoginAccountFragment fragment);
}

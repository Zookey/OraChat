package net.zoranpavlovic.orachat.account.register;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;

import dagger.Component;

/**
 * Created by osx on 29/07/2017.
 */

@FragmentScoped
@Component(dependencies = AppComponent.class, modules = RegisterAccountModule.class)
public interface RegisterAccountComponent {
    void inject(RegisterAccountFragment fragment);
}
package net.zoranpavlovic.orachat.account.login;

import android.support.v4.content.SharedPreferencesCompat;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;
import net.zoranpavlovic.orachat.core.di.component.SharedPreferenceComponent;
import net.zoranpavlovic.orachat.core.di.module.SharedPreferenceModule;

import dagger.Component;

/**
 * Created by osx on 29/07/2017.
 */

@FragmentScoped
@Component(dependencies = {NetComponent.class}, modules = LoginAccountModule.class)
public interface LoginAccountComponent {
    void inject(LoginAccountFragment fragment);
}

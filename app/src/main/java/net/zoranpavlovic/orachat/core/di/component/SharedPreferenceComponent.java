package net.zoranpavlovic.orachat.core.di.component;

import android.content.SharedPreferences;

import net.zoranpavlovic.orachat.core.di.module.SharedPreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@Singleton
@Component(modules = {SharedPreferenceModule.class})
public interface SharedPreferenceComponent {
    SharedPreferences getSharedPreference();
}

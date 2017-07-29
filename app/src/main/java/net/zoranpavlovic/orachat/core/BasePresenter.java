package net.zoranpavlovic.orachat.core;

import android.view.View;

/**
 * Created by osx on 29/07/2017.
 */

public interface BasePresenter<T extends View> {

    void onViewDestroy();

}
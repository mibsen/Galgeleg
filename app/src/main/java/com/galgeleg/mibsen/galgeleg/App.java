package com.galgeleg.mibsen.galgeleg;

import android.app.Application;
import android.support.v7.preference.PreferenceManager;

import com.galgeleg.mibsen.galgeleg.database.AppDatabase;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize GameState
        GameState.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        GameState.db = AppDatabase.getAppDatabase(this);

        // Hack to prefill with testdata
        GameState.db.scoreDao().getAllOrderedByLevel();

    }
}

package com.galgeleg.mibsen.galgeleg;

import android.content.SharedPreferences;

import com.galgeleg.mibsen.galgeleg.database.AppDatabase;

public class GameState {

    public static int level = 1;

    public static Galgelogik spil = new Galgelogik();

    public static long score = 0;

    public static SharedPreferences preferences;

    public static AppDatabase db;

    public static void reset() {
        GameState.level = 1;
        GameState.score = 0;
    }
}



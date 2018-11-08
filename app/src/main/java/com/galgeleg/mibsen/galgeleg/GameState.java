package com.galgeleg.mibsen.galgeleg;

import android.content.SharedPreferences;

public class GameState {

    public static int level = 1;

    public static Galgelogik spil = new Galgelogik();

    public static long score = 0;

    public static SharedPreferences preferences;

    public static void reset() {
        GameState.level = 1;
        GameState.score = 0;
    }
}



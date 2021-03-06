package com.galgeleg.mibsen.galgeleg;

import android.content.SharedPreferences;

import com.galgeleg.mibsen.galgeleg.database.AppDatabase;
import com.galgeleg.mibsen.galgeleg.database.Word;

import java.util.ArrayList;

public class GameState {

    public static int level = 1;

    public static Galgelogik spil = new Galgelogik();

    public static int score = 0;

    public static int prevScore = 0;


    public static int multiplier = 1;

    public static SharedPreferences preferences;

    public static AppDatabase db;

    public static ArrayList<Word> words = new ArrayList<>();

    public static void reset() {

        words = new ArrayList<>();
        GameState.level = 1;
        GameState.score = 0;
        GameState.prevScore = 0;
    }

    public static void wrongGuess() {
            GameState.multiplier = 1;
    }

    public static void correctGuess() {
        GameState.score = GameState.score + (1*GameState.multiplier);
        GameState.multiplier++;
    }
}



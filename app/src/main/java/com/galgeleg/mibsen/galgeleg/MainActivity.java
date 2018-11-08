package com.galgeleg.mibsen.galgeleg;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    /**
     * Tema ?
     */

    /**
     * - HighScore (SQLlite)
     * - Points pr korrekt
     * - Multiplier hvis flere i træk
     * - New Keyboard
     * - Sværhedsgrad
     * - Tegn i ord
     * - Tegn på keyboard
     * - Ord fra DR
     * - Loader Screen
     * - Lost screen med position på highscore samt navn <- gem i pref
     * - Win screen med antal samlet score og tillæg samt score fra næste score og position
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //https://stackoverflow.com/questions/2591036/how-to-hide-the-title-bar-for-an-activity-in-xml-with-existing-custom-theme
        ///
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ///

        setContentView(R.layout.activity_main);

        // Set SharedPreferences

        GameState.preferences = PreferenceManager.getDefaultSharedPreferences(this);

        GameState.db = AppDatabase.getAppDatabase(this);

        Fragment menu = new MenuFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menu).commit();
        }

    }
}

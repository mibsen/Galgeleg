package com.galgeleg.mibsen.galgeleg;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import com.galgeleg.mibsen.galgeleg.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    /**
     * Tema ?
     */

    /**
     * X - HighScore (SQLlite)
     * X - Points pr korrekt
     * X - Multiplier hvis flere i træk
     * X - New Keyboard
     * - Sværhedsgrad
     * X - Ord fra DR
     * X - Loader Screen
     * X - Lost screen med position på highscore samt navn <- gem i pref
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

        Fragment menu = new MenuFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, menu).commit();
        }

    }

    private void handleBackPress(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(f instanceof BaseGame){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);

            builder.setMessage("Er du sikker på at du vil forloade spillet?");
            builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handleBackPress();
                }
            });
            builder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        } else {
            super.onBackPressed();
        }
    }
}

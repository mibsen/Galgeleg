package com.galgeleg.mibsen.galgeleg;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.galgeleg.mibsen.galgeleg.database.Score;


/**
 * A simple {@link Fragment} subclass.
 */
public class LostFragment extends BaseGame {


    private View fragment;

    public LostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment = inflater.inflate(R.layout.fragment_lost, container, false);


        ((TextView) fragment.findViewById(R.id.lost_level)).setText("" + GameState.level);

        // Med static
        //        ((TextView) fragment.findViewById(R.id.lost_word)).setText(GameState.spil.getOrdet());


        // Med bundle
        Bundle args = getArguments();
        ((TextView) fragment.findViewById(R.id.lost_word)).setText(args.getString("word"));

        ((TextView) fragment.findViewById(R.id.lost_score)).setText("" + GameState.score);

        String username = GameState.preferences.getString(GamePreferences.USERNAME.getKey(), "");

        ((EditText) fragment.findViewById(R.id.lost_username_edittext)).setText(username);


        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard(getActivity());

                String username = ((EditText) fragment.findViewById(R.id.lost_username_edittext)).getText().toString();

                // Gem i highscore og opdater preferences
                if (!username.equals("")) {

                    // Opdater preferences
                    GameState.preferences.edit().putString(GamePreferences.USERNAME.getKey(), username).commit();

                    Score score = new Score();
                    score.username = username;
                    score.level = GameState.level;
                    score.score = GameState.score;

                    new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            // Opdatere Highscore
                            GameState.db.scoreDao().insertAll(score);
                            return null;
                        }
                    }.execute();
                }

                GameState.reset();

                Fragment load = new LoadingFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                ft.replace(R.id.content_frame, load).commit();

            }
        });

        return fragment;

    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.getApplicationContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}

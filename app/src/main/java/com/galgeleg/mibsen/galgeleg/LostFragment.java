package com.galgeleg.mibsen.galgeleg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LostFragment extends Fragment {


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
        ((TextView) fragment.findViewById(R.id.lost_word)).setText(GameState.spil.getOrdet());
        ((TextView) fragment.findViewById(R.id.lost_score)).setText("" +GameState.score );

        String username = GameState.preferences.getString(GamePreferences.USERNAME.getKey(),"");

        ((EditText) fragment.findViewById(R.id.lost_username_edittext)).setText(username);


        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String username = ((EditText) fragment.findViewById(R.id.lost_username_edittext)).getText().toString();

                // Gem i highscore og opdater preferences
                if(!username.equals("")){

                    // Opdater preferences
                    GameState.preferences.edit().putString(GamePreferences.USERNAME.getKey(),username);

                    // Opdatere Highscore
                }


                GameState.reset();


                Fragment load = new LoadingFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                ft.replace(R.id.content_frame,load).commit();

            }
        });

        return fragment;

    }

}

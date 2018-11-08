package com.galgeleg.mibsen.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galgeleg.mibsen.galgeleg.highscore.HighScoreFragment;


public class MenuFragment extends Fragment implements View.OnClickListener {


    private View btnStart;
    private View btnPref;
    private View btnHighScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_menu, container, false);

        btnStart = fragment.findViewById(R.id.menu_start_btn);
        btnStart.setOnClickListener(this);

        btnPref = fragment.findViewById(R.id.menu_pref_btn);
        btnPref.setOnClickListener(this);

        btnHighScore = fragment.findViewById(R.id.menu_highscore_btn);
        btnHighScore.setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {

        if (v == btnStart) {
            GameState.reset();
            Fragment intro = new LoadingFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, intro).addToBackStack(null).commit();
        } else if (v == btnPref) {
            Fragment pref = new IndstillingeFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, pref).addToBackStack(null).commit();
        } else if (v == btnHighScore) {
            Fragment score = new HighScoreFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, score).addToBackStack(null).commit();
        }
    }
}

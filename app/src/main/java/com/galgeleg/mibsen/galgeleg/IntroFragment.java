package com.galgeleg.mibsen.galgeleg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment implements View.OnClickListener {


    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_intro, container, false);

        fragment.findViewById(R.id.intro_game_start_Btn).setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {

        Fragment game = new GameFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_frame,game).commit();


    }
}

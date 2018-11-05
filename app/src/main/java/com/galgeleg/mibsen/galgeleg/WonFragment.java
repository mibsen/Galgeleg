package com.galgeleg.mibsen.galgeleg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WonFragment extends Fragment {


    public WonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_won, container, false);

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment game = new GameFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                ft.replace(R.id.content_frame,game).commit();

            }
        });

        return fragment;
    }

}

package com.galgeleg.mibsen.galgeleg;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {

    public LoadingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_loading, container, false);

        ((TextView) fragment.findViewById(R.id.level_number)).setText("" + GameState.level);
        GameState.spil.nulstil();

        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    GameState.spil.hentOrdFraDr();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                Fragment game = new GameFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_frame,game).commit();
            }
        }.execute();

        return fragment;
    }

}

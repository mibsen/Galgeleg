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
public class LoadingFragment extends BaseGame {

    private LoadData loadData;

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
         GameState.spil.setLevel(GameState.level);

         loadData = new LoadData();
         
         loadData.execute();

        return fragment;
    }

    @Override
    public void onDestroy() {
        if(loadData != null){
            loadData.cancel(true);
        }

        super.onDestroy();
    }

    private class LoadData extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                GameState.spil.hentOrdFraDr();
            } catch (Exception e) {
                // Ingen internet forbindelse. Fald tilbage til Pre implementeret ord i Spil
                e.printStackTrace();
                try {
                    // Sleep for at vise lvl skærm
                    Thread.sleep(600);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Fragment game = new GameFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,game).commit();
        }
    };

}

package com.galgeleg.mibsen.galgeleg;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighScoreList extends Fragment {

    List<Score> scores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = new RecyclerView(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);

        Bundle bundle=getArguments();
        int position = bundle.getInt("position");

        if(position == 0){
            scores = GameState.db.scoreDao().getAllOrderedByScore();
        } else {
            scores = GameState.db.scoreDao().getAllOrderedByLevel();
        }

        return recyclerView;
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        @Override
        public int getItemCount()  {
            return scores.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.listeelement, parent, false);
            return new RecyclerView.ViewHolder(itemView) {};
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {

            TextView username = vh.itemView.findViewById(R.id.listeelem_username);
            TextView score = vh.itemView.findViewById(R.id.listeelem_score);
            TextView place = vh.itemView.findViewById(R.id.listeelem_place);
            TextView level = vh.itemView.findViewById(R.id.listeelem_level);

            Score s = scores.get(position);

            username.setText(s.username);
            score.setText(s.score+ "");
            place.setText(""+(position+1));
            level.setText("" + s.level);
        }
    };

}

package com.galgeleg.mibsen.galgeleg.highscore;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.galgeleg.mibsen.galgeleg.GameState;
import com.galgeleg.mibsen.galgeleg.R;
import com.galgeleg.mibsen.galgeleg.database.Score;
import com.galgeleg.mibsen.galgeleg.database.Word;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends android.support.v4.app.DialogFragment {


    public DialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();

        if (arguments == null) {
            dismiss();
            return null;
        }

        int id = arguments.getInt("id", 1);
        int position = arguments.getInt("position", 0);

        Score score = GameState.db.scoreDao().get(id);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ((TextView) view.findViewById(R.id.dialog_plads)).setText("" + position);
        ((TextView) view.findViewById(R.id.dialog_username)).setText(score.username);
        ((TextView) view.findViewById(R.id.dialog_score)).setText("score: " + score.score);
        ((TextView) view.findViewById(R.id.dialog_level)).setText("level: " + score.level);


        List<Word> ord = GameState.db.wordDao().getWords(id);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item, R.id.text1, ord) {

            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {

                Word o = ord.get(position);

                View view = super.getView(position, cachedView, parent);
                ((TextView) view.findViewById(R.id.score)).setText("score: " + o.score);
                return view;
            }
        };

        ListView listView = view.findViewById(R.id.dialog_list);

        listView.setAdapter(adapter);

        return view;
    }

}

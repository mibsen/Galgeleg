package com.galgeleg.mibsen.galgeleg;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WonFragment extends BaseGame {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_won, container, false);

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameState.prevScore = GameState.score;

                Fragment load = new LoadingFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                ft.replace(R.id.content_frame,load).commit();

            }
        });

        TextView tries = fragment.findViewById(R.id.won_tries);
        tries.setText("Du brugte "+GameState.spil.getAntalForkerteBogstaver()+" Forsøg");

        TextView score = fragment.findViewById(R.id.won_score);
        score.setText("" + GameState.prevScore);

        ValueAnimator animator = ValueAnimator.ofInt(GameState.prevScore, GameState.score);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                score.setText(animation.getAnimatedValue().toString());
            }
        });

        // Wait for animation
        new Handler().postDelayed(()->{
            animator.start();
        },2000);

        return fragment;
    }

}

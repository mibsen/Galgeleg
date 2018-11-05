package com.galgeleg.mibsen.galgeleg;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    protected Galgelogik spil = new Galgelogik();
    protected TextView wordTextView;
    protected TextView guessTextView;
    protected ImageView imageView;
    protected EditText inputGuess;

    protected Map<Character,View> keyboard;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_game, container, false);

        this.wordTextView = fragment.findViewById(R.id.game_word);
        this.guessTextView = fragment.findViewById(R.id.game_guess);
        this.imageView = fragment.findViewById(R.id.game_image);

        buildKeyboard(fragment);

        /*
        this.inputGuess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = s.toString();

                if(text.equals("")){
                    return;
                }

                spil.gætBogstav(text.toLowerCase());
                updateView();

                if(!spil.erSidsteBogstavKorrekt()){
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    imageView.startAnimation(shake);
                }

                // Show success screen / overlay
                if(spil.erSpilletVundet()){
                    won();
                }

                // Show lost screen / overlay
                if(spil.erSpilletTabt()){
                    lost();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!inputGuess.getText().toString().equals("")){
                    inputGuess.setText("");
                }

            }
        });
        */

        updateView();

        return fragment;
    }

    private void buildKeyboard(View fragment) {

        HashMap<Character, View> key = new HashMap<Character, View>();

        key.put('Q', fragment.findViewById(R.id.button_Q));
        key.put('W', fragment.findViewById(R.id.button_W));
        key.put('E', fragment.findViewById(R.id.button_E));
        key.put('R', fragment.findViewById(R.id.button_R));
        key.put('T', fragment.findViewById(R.id.button_T));
        key.put('Y', fragment.findViewById(R.id.button_Y));
        key.put('U', fragment.findViewById(R.id.button_U));
        key.put('I', fragment.findViewById(R.id.button_I));
        key.put('O', fragment.findViewById(R.id.button_O));
        key.put('P', fragment.findViewById(R.id.button_P));
        key.put('Å', fragment.findViewById(R.id.button_Å));

        key.put('A', fragment.findViewById(R.id.button_A));
        key.put('S', fragment.findViewById(R.id.button_S));
        key.put('D', fragment.findViewById(R.id.button_D));
        key.put('F', fragment.findViewById(R.id.button_F));
        key.put('G', fragment.findViewById(R.id.button_G));
        key.put('H', fragment.findViewById(R.id.button_H));
        key.put('J', fragment.findViewById(R.id.button_J));
        key.put('K', fragment.findViewById(R.id.button_K));
        key.put('L', fragment.findViewById(R.id.button_L));
        key.put('Æ', fragment.findViewById(R.id.button_Æ));
        key.put('Ø', fragment.findViewById(R.id.button_Ø));

        key.put('Z', fragment.findViewById(R.id.button_Z));
        key.put('X', fragment.findViewById(R.id.button_X));
        key.put('C', fragment.findViewById(R.id.button_C));
        key.put('V', fragment.findViewById(R.id.button_V));
        key.put('B', fragment.findViewById(R.id.button_B));
        key.put('N', fragment.findViewById(R.id.button_N));
        key.put('M', fragment.findViewById(R.id.button_M));

        for(View btn : key.values()){
            ((Button)btn).setVisibility(View.INVISIBLE);

            btn.setOnClickListener((view) -> {

                ((Button) view).setEnabled(false);

                String text = ((Button) view).getText().toString();

                spil.gætBogstav(text.toLowerCase());

                updateView();

                if(!spil.erSidsteBogstavKorrekt()){
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    imageView.startAnimation(shake);
                }

                // Show success screen / overlay
                if(spil.erSpilletVundet()){
                    won();
                }

                // Show lost screen / overlay
                if(spil.erSpilletTabt()){
                    lost();
                }

            });
        }

        for(Character c : this.spil.muligeBogstaver()){
            key.get(Character.toUpperCase(c)).setVisibility(View.VISIBLE);
        }


        this.keyboard = key;
    }


    private void updateView(){

        // Lets update the drawing
        switch (spil.getAntalForkerteBogstaver()){
            case 1:
                imageView.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.forkert6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.forkert6);
                break;
        }


        // Update Word
        wordTextView.setText(spil.getSynligtOrd());

        // Update guesses
        guessTextView.setText(TextUtils.join(",", spil.getBrugteBogstaver()));
    }

    private void won(){

        Fragment won = new WonFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        ft.replace(R.id.content_frame,won).commit();
    }

    private void lost(){

        Fragment lost = new LostFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        ft.replace(R.id.content_frame,lost).commit();
    }
}

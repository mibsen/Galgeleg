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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    protected Galgelogik spil = new Galgelogik();
    protected TextView wordTextView;
    protected TextView guessTextView;
    protected ImageView imageView;
    protected EditText inputGuess;

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
        this.inputGuess = fragment.findViewById(R.id.game_input_guess);

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

        updateView();

        return fragment;
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
            default:
                imageView.setImageResource(R.drawable.forkert6);
                break;
        }


        // Update Word
        wordTextView.setText(spil.getSynligtOrd());

        // Update guesses
        guessTextView.setText(TextUtils.join(",", spil.getBrugteBogstaver()));
    }

    private void hideKeyboard(){

        InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void won(){

        hideKeyboard();

        Fragment won = new WonFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        ft.replace(R.id.content_frame,won).commit();
    }

    private void lost(){
        hideKeyboard();

        Fragment lost = new LostFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        ft.replace(R.id.content_frame,lost).commit();
    }
}

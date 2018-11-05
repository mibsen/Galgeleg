package com.galgeleg.mibsen.galgeleg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuFragment extends Fragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_menu, container, false);

        fragment.findViewById(R.id.menu_start_btn).setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {

        Fragment intro = new IntroFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,intro).commit();

    }
}

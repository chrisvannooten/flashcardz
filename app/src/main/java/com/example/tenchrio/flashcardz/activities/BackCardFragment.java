package com.example.tenchrio.flashcardz.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tenchrio.flashcardz.R;


public class BackCardFragment extends Fragment {

    public static BackCardFragment newInstance(String text) {

        BackCardFragment b = new BackCardFragment();

        Bundle bun = new Bundle();
        bun.putString("text", text);
        b.setArguments(bun);
        return b;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_back_card, container, false);
        ((TextView) v.findViewById(R.id.txtback)).setText(getArguments().getString("text"));
        // Inflate the layout for this fragment
        return v;
    }
}

package com.example.tenchrio.flashcardz.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tenchrio.flashcardz.R;


public class FrontCardFragment extends Fragment {

    public static FrontCardFragment newInstance(String text) {

        FrontCardFragment f = new FrontCardFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_front_card, container, false);

        ((TextView) v.findViewById(R.id.txtfront)).setText(getArguments().getString("text"));
        return v;
    }

}

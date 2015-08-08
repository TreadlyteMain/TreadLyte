package com.treadlyte.mainapp;


import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment; //TODO: Need to investigate this
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Explore_Fragment extends Fragment {

    public static final String TAG = "exploreFragment";


    public Explore_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Explore Fragment you little fucks");
        return textView;
    }


}

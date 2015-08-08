package com.treadlyte.mainapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurchasedItems_Fragment extends Fragment {

    public static final String TAG = "purchaseditemsFragment";


    public PurchasedItems_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("purchaseditemsFragment");
        return textView;
    }


}

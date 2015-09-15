package com.treadlyte.mainapp;


import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treadlyte.mainapp.adapters.ExploreProdAdapter;
import com.treadlyte.mainapp.model.Product;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Explore_Fragment extends Fragment {

    public static final String TAG = "exploreFragment";
    private RecyclerView rvProducts;
    private List prodList;



    public Explore_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.explore_screen, container, false);

        Product prod1 = new Product("RPM Womens Top","$50");
        Product prod2 = new Product("RPM Womens Top","$50");
        Product prod3 = new Product("RPM Womens Top","$50");
        Product prod4 = new Product("RPM Womens Top","$50");
        Product prod5 = new Product("RPM Womens Top","$50");
        Product prod6 = new Product("RPM Womens Top","$50");
        Product prod7 = new Product("RPM Womens Top","$50");
        Product prod8 = new Product("RPM Womens Top","$50");
        Product prod9 = new Product("RPM Womens Top","$50");
        Product prod10 = new Product("RPM Womens Top","$50");
        Product prod11 = new Product("RPM Womens Top","$50");
        Product prod12 = new Product("RPM Womens Top","$50");

        prodList = new ArrayList();

        prodList.add(prod1);
        prodList.add(prod2);
        prodList.add(prod3);
        prodList.add(prod4);
        prodList.add(prod5);
        prodList.add(prod6);
        prodList.add(prod7);
        prodList.add(prod8);
        prodList.add(prod9);
        prodList.add(prod10);
        prodList.add(prod11);
        prodList.add(prod12);
        rvProducts = (RecyclerView) view.findViewById(R.id.exploreRecycleview);

        ExploreProdAdapter exploreProdAdapter = new ExploreProdAdapter(prodList);

        rvProducts.setAdapter(exploreProdAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);

        rvProducts.setLayoutManager(gridLayoutManager);
        return view;
    }


}

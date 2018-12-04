package com.example.rivas.salon_rmr.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rivas.salon_rmr.Apputilities.BaseFragment;
import com.example.rivas.salon_rmr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment {


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

}

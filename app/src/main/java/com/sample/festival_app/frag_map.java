package com.sample.festival_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class frag_map extends Fragment {
    private View view;
    private Spinner spinner;
    public static frag_map newInstance(){
        frag_map frag_list =new frag_map();
        return frag_list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_map, container, false);
        //return view;
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment1 fragment1=new Fragment1();
        transaction.replace(R.id.map_frame, fragment1);
        transaction.addToBackStack(null);
        transaction.commit();
        return view;
    }
}

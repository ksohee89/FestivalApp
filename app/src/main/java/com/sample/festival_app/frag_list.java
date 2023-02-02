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

public class frag_list extends Fragment {
    private View view;
    private Spinner spinner;
    public static frag_list newInstance(){
        frag_list frag_list =new frag_list();
        return frag_list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_list, container, false);
        //return view;
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment2 fragment2=new Fragment2();
        transaction.replace(R.id.list_frame, fragment2);
        transaction.addToBackStack(null);
        transaction.commit();
        return view;
    }
}

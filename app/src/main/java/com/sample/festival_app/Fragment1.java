package com.sample.festival_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment1 extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //전체 지도
        view = inflater.inflate(R.layout.frag_map1, container, false);
        TextView text1=view.findViewById(R.id.text1);
        TextView text2=view.findViewById(R.id.text2);
        TextView text3=view.findViewById(R.id.text3);
        TextView text4=view.findViewById(R.id.text4);
        TextView text5=view.findViewById(R.id.text5);
        TextView text6=view.findViewById(R.id.text6);
        TextView text7=view.findViewById(R.id.text7);
        TextView text8=view.findViewById(R.id.text8);
        TextView text9=view.findViewById(R.id.text9);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                gyeonggido gyeonggido=new gyeonggido();
                transaction.replace(R.id.map_frame, gyeonggido);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                gangwondo gangwondo=new gangwondo();
                transaction.replace(R.id.map_frame,gangwondo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                chungcheongnamdo chungcheongnamdo=new chungcheongnamdo();
                transaction.replace(R.id.map_frame, chungcheongnamdo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                chungcheongbukdo chungcheongbukdo=new chungcheongbukdo();
                transaction.replace(R.id.map_frame, chungcheongbukdo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                gyeongsangbukdo gyeongsangbukdo=new gyeongsangbukdo();
                transaction.replace(R.id.map_frame,gyeongsangbukdo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                jeollabukdo jeollabukdo=new jeollabukdo();
                transaction.replace(R.id.map_frame, jeollabukdo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                gyeongsangnamdo gyeongsangnamdo=new gyeongsangnamdo();
                transaction.replace(R.id.map_frame,gyeongsangnamdo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                jeollanamdo jeollanamdo=new jeollanamdo();
                transaction.replace(R.id.map_frame, jeollanamdo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                jeju jeju=new jeju();
                transaction.replace(R.id.map_frame,jeju);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;


    }
}

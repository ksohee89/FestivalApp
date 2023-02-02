package com.sample.festival_app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Scheduler extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private Menu_fragment menu_fragment;
    private Calender_fragment calender_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler);

        bottomNavigationView=findViewById(R.id.bottomNavi);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_memo:
                        setFrag(0);
                        break;
                    case R.id.action_calender:
                        setFrag(1);
                        break;
                }
                return true;
            }
        });
        menu_fragment=new Menu_fragment();
        calender_fragment=new Calender_fragment();

        setFrag(0);

    }

    private void setFrag(int n){
        fm=getSupportFragmentManager();
        fragmentTransaction=fm.beginTransaction();
        switch (n){
            case 0:
                fragmentTransaction.replace(R.id.main_frame,menu_fragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame,calender_fragment);
                fragmentTransaction.commit();
                break;
        }

    }
}
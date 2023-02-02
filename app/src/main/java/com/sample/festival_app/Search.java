package com.sample.festival_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Search extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Button search_btn=findViewById(R.id.search_btn1);
        EditText et=findViewById(R.id.search1);
        Intent intent = getIntent();
        String str=intent.getExtras().getString("search_str");
        et.setText(str);


        Search_frag searchFrag=new Search_frag();
        Bundle bundle=new Bundle();
        bundle.putString("Search",str);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        searchFrag.setArguments(bundle);
        fragmentTransaction.replace(R.id.search_frag, searchFrag);
        fragmentTransaction.commit();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str2=et.getText().toString();
                Search_frag searchFrag=new Search_frag();
                Bundle bundle=new Bundle();
                bundle.putString("Search",str2);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                searchFrag.setArguments(bundle);
                fragmentTransaction.replace(R.id.search_frag, searchFrag);
                fragmentTransaction.commit();
            }
        });


    }
}

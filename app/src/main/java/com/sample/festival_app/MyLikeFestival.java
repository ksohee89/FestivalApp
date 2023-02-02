package com.sample.festival_app;

import static com.sample.festival_app.MainScreen.userid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyLikeFestival extends AppCompatActivity {

    private RecyclerView recycler_view;
    private LikeDBHelper LikeDBHelper;
    private ArrayList<LikeItem> likeItems;
    private LikeAdapter mAdapter;
    private TextView festivalNum;
    private String user_name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfavoritefestival);
        user_name= userid;
        festivalNum=findViewById(R.id.festival_num);
        recycler_view=findViewById(R.id.like_recycler_view);
        recycler_view.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        LikeDBHelper=new LikeDBHelper(getApplicationContext());

        likeItems =LikeDBHelper.getLikeList(user_name,"Like");
        String num=Integer.toString(likeItems.size());
        festivalNum.setText(num);
        mAdapter=new LikeAdapter(likeItems,getApplicationContext());
        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(mAdapter);
    }

}

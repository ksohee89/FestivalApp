package com.sample.festival_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity  {

    Animation anim_ball;
    ConstraintLayout constraintLayout;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        constraintLayout=findViewById(R.id.constraintLayout);
        logo=findViewById(R.id.logo);

        anim_ball= AnimationUtils.loadAnimation(this,R.anim.anim_splash_ball);
        logo.startAnimation(anim_ball);//튀는 애니메이션


        moveMain(2);//2초뒤에 넘어감



    }

    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
                startActivity(intent);
                finish();
            }
        },1000*sec);
    }

}
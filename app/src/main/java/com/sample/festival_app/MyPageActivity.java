package com.sample.festival_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid;

    private Button changePwBtn;
    private ImageView myRv_Btn;

    private TextView LikeFestival;
    private ImageView LikeFestivalImage;
    private TextView callText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        changePwBtn = (Button) findViewById(R.id.btn_pw_change);
        callText=findViewById(R.id.call);

        changePwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPageActivity.this,ForgotPasswordActivity.class));
            }
        });

        myRv_Btn = (ImageView) findViewById(R.id.myrv_btn);

        myRv_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPageActivity.this,MyReview.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();
        LikeFestival=findViewById(R.id.LikeFestival);
        LikeFestivalImage=findViewById(R.id.LikeFestivalImage);


        final TextView nameTextView = (TextView) findViewById(R.id.user_name);
        final TextView nameTextView2 = (TextView) findViewById(R.id.user_name2);
        final TextView idTextView = (TextView) findViewById(R.id.user_id);
        final TextView telTextView = (TextView) findViewById(R.id.user_tel);
        final TextView passwordTextView = (TextView) findViewById(R.id.user_password);
        final TextView pointTextView = (TextView) findViewById(R.id.point);

        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.userName;
                    String email = userProfile.userId;
                    String tel = userProfile.userTel;
                    String password = userProfile.userPw;
                    int point = userProfile.userPoint;

                    nameTextView.setText(name+"님");
                    nameTextView2.setText(name);
                    idTextView.setText(email);
                    telTextView.setText(tel);
                    passwordTextView.setText(password);
                    pointTextView.setText(point+"p");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyPageActivity.this, "오류 발생", Toast.LENGTH_SHORT).show();
            }
        });


        LikeFestival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyPageActivity.this,MyLikeFestival.class);
                startActivity(intent);
            }
        });


        LikeFestivalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyPageActivity.this,MyLikeFestival.class);
                startActivity(intent);
            }
        });

        callText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:070-6569-4523"));
                startActivity(intent);
            }
        });
    }

}
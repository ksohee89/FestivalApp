package com.sample.festival_app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.sample.festival_app.LoginActivity.loginState;
import static com.sample.festival_app.MainScreen.userid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class review extends AppCompatActivity {

    private RecyclerView mRv_review;
    private ArrayList<ReviewItem> mReviewItems;
    private Button add_review;
    private String Name; //festivalName
    private ReviewAdapter mReviewAdapter;
    private int rvNum = 0;
    private TextView review_num;
    private ImageView backbtn;
    private TextView point_text;
    private TextView point_text2;
    private TextView point_text5;
    private TextView point_text4;
    private AppCompatButton point_btn;
    private TextView my_user;
    private TextView my_review;
    private TextView my_writedate;
    private FirebaseFirestore mStore;
    private int btn_click=0;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid;
    private int pre_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review2);
        Intent intent = getIntent();
        Name = intent.getStringExtra("fstvlName");
        review_num = findViewById(R.id.review_num);
        point_text = findViewById(R.id.point_text);
        point_text2 = findViewById(R.id.point_text2);
        point_text4 = findViewById(R.id.point_text4);
        point_text5 = findViewById(R.id.point_text5);
        point_btn = findViewById(R.id.point_btn2);
        mRv_review = findViewById(R.id.rv_review);
        my_user=findViewById(R.id.my_user);
        my_review=findViewById(R.id.my_review);
        my_writedate=findViewById(R.id.my_writeDate);
        setInit();

        if (loginState){
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            uid = user.getUid();

            reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if(userProfile != null){
                        pre_point = userProfile.userPoint;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(review.this, "오류 발생", Toast.LENGTH_SHORT).show();
                }
            });
            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            DocumentReference docIdRef = rootRef.collection(userid).document(Name);
            docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            point_text.setVisibility(View.GONE);
                            point_text.setVisibility(View.GONE);
                            point_text2.setVisibility(View.GONE);
                            point_text5.setVisibility(View.GONE);
                            point_text4.setVisibility(View.GONE);
                            mRv_review.setVisibility(View.VISIBLE);
                            btn_click++;
                            point_btn.setVisibility(View.GONE);
                            my_review.setVisibility(View.GONE);
                            my_user.setVisibility(View.GONE);
                            my_writedate.setVisibility(View.GONE);
                        }
                    }
                }
            });
        }

        point_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loginState) {
                    Toast.makeText(review.this, "로그인 후 이용가능합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    if (pre_point < 5)
                        Toast.makeText(review.this, "포인트가 부족합니다.", Toast.LENGTH_SHORT).show();
                    else {
                        Map<String, Object> Updates = new HashMap<>();
                        Map<String, Object> data = new HashMap<>();
                        data.put("festival", Name);
                        mStore.collection(userid).document(Name).set(data);
                        Updates.put("userPoint", pre_point - 5);
                        reference.child(uid).updateChildren(Updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(review.this, "포인트가 차감되었습니다.", Toast.LENGTH_SHORT).show();
                                    point_text.setVisibility(View.GONE);
                                    point_text.setVisibility(View.GONE);
                                    point_text2.setVisibility(View.GONE);
                                    point_text5.setVisibility(View.GONE);
                                    point_text4.setVisibility(View.GONE);
                                    mRv_review.setVisibility(View.VISIBLE);
                                    btn_click++;
                                    point_btn.setVisibility(View.GONE);
                                    my_review.setVisibility(View.GONE);
                                    my_user.setVisibility(View.GONE);
                                    my_writedate.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(review.this, "오류 발생", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });

        backbtn = (ImageView) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mStore.collection("review")
                .whereEqualTo("festival", Name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult() != null) {
                                int i = 0;
                                for(DocumentSnapshot snap : task.getResult()) {
                                    Map<String, Object> shot = snap.getData();
                                    String rid = snap.getId();
                                    String userid = String.valueOf(shot.get("userid"));
                                    String festival = String.valueOf(shot.get("festival"));
                                    String contents = String.valueOf(shot.get("contents"));
                                    String date = String.valueOf(shot.get("date"));
                                    ReviewItem data = new ReviewItem(rid, userid,festival,contents,date);
                                    mReviewItems.add(data);
                                    i++;
                                    review_num.setText(String.valueOf(i));
                                }
                                mReviewAdapter = new ReviewAdapter(mReviewItems,review.this);
                                mRv_review.setAdapter(mReviewAdapter);
                            }
                        }
                    }
                });
    }

    private void setInit(){

        mStore = FirebaseFirestore.getInstance();
        mReviewItems = new ArrayList<>();
        mReviewAdapter=new ReviewAdapter(mReviewItems,this);
        mRv_review.setAdapter(mReviewAdapter);
        add_review=findViewById(R.id.add_review);

        add_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!loginState){
                    Toast.makeText(review.this, "로그인 후 작성가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Dialog dialog = new Dialog(review.this, android.R.style.Theme_Material_Light_Dialog);
                    dialog.setContentView(R.layout.dialog_reviewedit);
                    EditText edit_review = dialog.findViewById(R.id.edit_review);
                    ImageView action_save = dialog.findViewById(R.id.edit_reviewAdd);
                    action_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(edit_review.getText().toString().equals(""))
                                Toast.makeText(review.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            else {
                                String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                Map<String, Object> data = new HashMap<>();
                                data.put("userid", userid);
                                data.put("festival", Name);
                                data.put("contents", edit_review.getText().toString());
                                data.put("date", currentTime);
                                my_user.setText(userid);
                                my_review.setText(edit_review.getText().toString());
                                my_writedate.setText(currentTime);

                                if (btn_click != 1) {
                                    my_user.setVisibility(View.VISIBLE);
                                    my_review.setVisibility(View.VISIBLE);
                                    my_writedate.setVisibility(View.VISIBLE);
                                }
                                reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        User userProfile = snapshot.getValue(User.class);
                                        if (userProfile != null) {
                                            pre_point = userProfile.userPoint;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(review.this, "오류 발생", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                mStore.collection("review")
                                        .add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Map<String, Object> Updates = new HashMap<>();
                                                Updates.put("userPoint", pre_point + 10);
                                                reference.child(uid).updateChildren(Updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(review.this, "댓글이 등록되었습니다.\n(포인트 10p 적립)", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(review.this, "댓글 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
//                            mStore.collection("review").document(edit_review.getText().toString()).set(data, SetOptions.merge());
                                dialog.dismiss();
                                loadDB();
                            }
                        }
                    });
                    dialog.show();
                }
            }
        });
    }


    public void loadDB() {

        mReviewItems.clear();
        mStore.collection("review")
                .whereEqualTo("festival", Name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null) {
                                int i = 0;
                                for (DocumentSnapshot snap : task.getResult()) {
                                    Map<String, Object> shot = snap.getData();
                                    String rid = snap.getId();
                                    String userid = String.valueOf(shot.get("userid"));
                                    String festival = String.valueOf(shot.get("festival"));
                                    String contents = String.valueOf(shot.get("contents"));
                                    String date = String.valueOf(shot.get("date"));
                                    ReviewItem data = new ReviewItem(rid, userid, festival, contents, date);
                                    mReviewItems.add(data);
                                    i++;
                                    review_num.setText(String.valueOf(i));
                                }

                                mReviewAdapter = new ReviewAdapter(mReviewItems,review.this);
                                mRv_review.setAdapter(mReviewAdapter);
                            }
                        }
                    }
                });
    }

}



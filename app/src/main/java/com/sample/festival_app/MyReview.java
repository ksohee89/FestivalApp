package com.sample.festival_app;

import static com.sample.festival_app.MainScreen.userid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MyReview extends AppCompatActivity {

    private FirebaseFirestore mStore;
    private RecyclerView mRv_review;
    private ArrayList<ReviewItem> mReviewItems;
    private MyReviewAdapter mReviewAdapter;
    private TextView myReviewNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myreview);

        mStore = FirebaseFirestore.getInstance();
        mReviewItems = new ArrayList<>();
        mRv_review = findViewById(R.id.rv_myreview);
        mReviewAdapter = new MyReviewAdapter(mReviewItems,this);
        mRv_review.setAdapter(mReviewAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myReviewNum = findViewById(R.id.myreview_num);
        mStore.collection("review")
                .whereEqualTo("userid", userid)
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
                                    myReviewNum.setText(String.valueOf(i));
                                }
                                mReviewAdapter = new MyReviewAdapter(mReviewItems,MyReview.this);
                                mRv_review.setAdapter(mReviewAdapter);
                            }
                        }
                    }
                });
    }
}
package com.sample.festival_app;

import static com.sample.festival_app.MainScreen.userid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private ArrayList<ReviewItem> reviewItems;
    private Context mContext;
    private String user;

    public ReviewAdapter(ArrayList<ReviewItem> reviewItems, Context mContext) {
        this.reviewItems = reviewItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewitem_list,parent,false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_user.setText(reviewItems.get(position).getUser());
        holder.tv_review.setText(reviewItems.get(position).getContents());
        holder.tv_writeDate.setText(reviewItems.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return reviewItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_user;
        private TextView tv_review;
        private TextView tv_writeDate;
        private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_user=itemView.findViewById(R.id.reviewitem_list_user);
            tv_review=itemView.findViewById(R.id.reviewitem_list_review);
            tv_writeDate=itemView.findViewById(R.id.reviewitem_list_writeDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curpos = getAdapterPosition();
                    ReviewItem reviewItem = reviewItems.get(curpos);
                    if (reviewItem.getUser().equals(userid)) {
                        String[] strChoiceItems = {"수정하기", "삭제하기"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("원하는 작업을 선택해주세요");

                        builder.setItems(strChoiceItems, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                if (position == 0) {
                                    Dialog dialog2 = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
                                    dialog2.setContentView(R.layout.dialog_reviewedit);
                                    EditText edit_review = dialog2.findViewById(R.id.edit_review);
                                    ImageView review_add = dialog2.findViewById(R.id.edit_reviewAdd);

                                    edit_review.setText(reviewItem.getContents());
                                    edit_review.setSelection(edit_review.getText().length());


                                    review_add.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String pre_review = reviewItem.getContents();
                                            String new_review = edit_review.getText().toString();
                                            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                                            Map<String,Object> data = new HashMap<>();
                                            data.put("contents",new_review);
                                            mStore.collection("review")
                                                    .document(reviewItem.getRid())
                                                    .update(data)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(mContext, "댓글 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(mContext, "댓글 수정에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            reviewItem.setContents(new_review);
                                            reviewItem.setDate(currentTime);
                                            reviewItems.add(reviewItem);
                                            notifyDataSetChanged();
                                            dialog2.dismiss();
                                        }
                                    });
                                    dialog2.show();
                                } else if (position == 1) {
                                    mStore.collection("review")
                                            .document(reviewItem.getRid())
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(mContext, "댓글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(mContext, "댓글 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                                reviewItems.remove(curpos);
                                notifyItemRemoved(curpos);
                            }

                        });
                        builder.show();
                    }
                }
            });
        }
    }


    public void addItem(ReviewItem _item){
        reviewItems.add(0,_item);
        notifyItemInserted(0);
    }

}

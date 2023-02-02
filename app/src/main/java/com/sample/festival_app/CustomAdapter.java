package com.sample.festival_app;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<MemoItem> memoItems;
    private Context context;
    private DBHelper dbHelper;

    public CustomAdapter(ArrayList<MemoItem> memoItems, Context context) {
        this.memoItems = memoItems;
        this.context = context;
        dbHelper=new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.edit_title.setText(memoItems.get(position).getTitle());
        holder.edit_memo.setText(memoItems.get(position).getMemo());
        holder.edit_writeDate.setText(memoItems.get(position).getWriteDate());
        holder.edit_duration.setText(memoItems.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return memoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView edit_title;
        private TextView edit_memo;
        private TextView edit_duration;
        private TextView edit_writeDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edit_title=itemView.findViewById(R.id.item_list_title);
            edit_memo=itemView.findViewById(R.id.item_list_memo);
            edit_writeDate=itemView.findViewById(R.id.item_list_writeDate);
            edit_duration=itemView.findViewById(R.id.item_list_duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curpos=getAdapterPosition();
                    MemoItem memoItem=memoItems.get(curpos);

                    String[] strChoiceItems={"수정하기","삭제하기"};
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("원하는 작업을 선택해주세요");
                    builder.setItems(strChoiceItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            if(position==0){
                                //수정하기
                               Dialog dialog1=new Dialog(context, android.R.style.Theme_Material_Light_Dialog);
                                dialog1.setContentView(R.layout.dialog_edit);
                                EditText edit_title=dialog1.findViewById(R.id.edit_title);
                                EditText edit_memo=dialog1.findViewById(R.id.edit_memo);
                                EditText edit_duration=dialog1.findViewById(R.id.edit_duration);
                                ImageView action_save=dialog1.findViewById(R.id.action_save);

                                edit_title.setText(memoItem.getTitle());
                                edit_memo.setText(memoItem.getMemo());
                                edit_duration.setText(memoItem.getDuration());
                                edit_title.setSelection(edit_title.getText().length());


                                action_save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String new_title=edit_title.getText().toString();
                                        String new_memo=edit_memo.getText().toString();
                                        String currentTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                        String beforeDate=memoItem.getWriteDate();
                                        String new_duration=edit_duration.getText().toString();
                                        dbHelper.Update(new_title,new_memo,currentTime,beforeDate,new_duration);
                                        memoItem.setTitle(new_title);
                                        memoItem.setMemo(new_memo);
                                        memoItem.setWriteDate(currentTime);
                                        notifyItemChanged(curpos,memoItem);
                                        memoItem.setDuration(new_duration);
                                        dialog1.dismiss();
                                    }
                                });
                                dialog1.show();
                            }
                            else if(position==1){
                                String beforeDate=memoItem.getWriteDate();
                                dbHelper.delete(beforeDate);
                                memoItems.remove(curpos);
                                notifyItemRemoved(curpos);
                            }
                        }
                    });
                    builder.show();
                }
            });
        }
    }
    public void addItem(MemoItem _item){
        memoItems.add(0,_item);
        notifyItemInserted(0);
    }
}

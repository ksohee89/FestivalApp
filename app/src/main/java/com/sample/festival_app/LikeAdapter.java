package com.sample.festival_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {

    private ArrayList<LikeItem> likeItems;
    private Context context;
    private LikeDBHelper LikeDBHelper;

    public LikeAdapter(ArrayList<LikeItem> likeItems, Context context) {
        this.likeItems = likeItems;
        this.context = context;
        LikeDBHelper=new LikeDBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder= LayoutInflater.from(parent.getContext()).inflate(R.layout.likeitem_list,parent,false);

        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_list_festivalName.setText(likeItems.get(position).getFestivalName());
        holder.item_list_duration.setText(likeItems.get(position).getStartDate()+"~"+likeItems.get(position).getEndDate());
    }

    @Override
    public int getItemCount() {
        return likeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_list_duration;
        private TextView item_list_festivalName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_list_duration=itemView.findViewById(R.id.item_list_duration);
            item_list_festivalName=itemView.findViewById(R.id.item_list_festivalName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curpos = getAdapterPosition();
                    String festivalName=likeItems.get(curpos).getFestivalName();
                    Intent intent= new Intent(context,Likefestival_info.class);
                    intent.putExtra("fstvlName",festivalName);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }



    public void addItem(LikeItem _item){
        likeItems.add(0,_item);
        notifyItemInserted(0);
    }
}

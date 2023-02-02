package com.sample.festival_app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.sample.festival_app.MainScreen.userid;

public class Menu_fragment extends Fragment {
    private View view;
    private FloatingActionButton btn_add;
    private RecyclerView recycler_view;
    private ArrayList<MemoItem> memoItems;
    private DBHelper dbHelper;
    private CustomAdapter mAdapter;
    private String Name="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.menu_frag,container,false);
        Name = userid;
        setInit();
        return view;
    }

    private void setInit() {
        memoItems=new ArrayList<>();
        dbHelper=new DBHelper(getActivity());
        recycler_view=view.findViewById(R.id.recycler_view);
        recycler_view.setAdapter(mAdapter);
        btn_add=view.findViewById(R.id.btn_add);

        loadRecentDB();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity(), android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_edit);
                EditText edit_title=dialog.findViewById(R.id.edit_title);
                EditText edit_memo=dialog.findViewById(R.id.edit_memo);
                ImageView action_save=dialog.findViewById(R.id.action_save);
                EditText edit_duration=dialog.findViewById(R.id.edit_duration);
                memoItems=new ArrayList<>();
                dbHelper=new DBHelper(getActivity());
                action_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String currentTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        dbHelper.save(Name,edit_title.getText().toString(),edit_memo.getText().toString(),currentTime,edit_duration.getText().toString());
                        com.sample.festival_app.MemoItem item=new com.sample.festival_app.MemoItem();
                        item.setTitle(edit_title.getText().toString());
                        item.setMemo(edit_memo.getText().toString());
                        item.setWriteDate(currentTime);
                        item.setDuration(edit_duration.getText().toString());
                        mAdapter.addItem(item);
                        recycler_view.smoothScrollToPosition(0);
                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });

    }


    private void loadRecentDB() {
        memoItems =dbHelper.getMemoList(Name);
        mAdapter=new CustomAdapter(memoItems,getActivity());
        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(mAdapter);
    }

}

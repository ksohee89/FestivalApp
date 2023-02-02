package com.sample.festival_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.sample.festival_app.MainScreen.userid;

public class Calender_fragment extends Fragment {
    private View view;
    private CalendarView calendarView;
    private TextView tv_text;
    private ArrayList<MemoItem> memoItems;
    private RecyclerView recycler_view;
    private CustomAdapter mAdapter;
    private  DBHelper dbHelper;
    private String Name="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.calender_frag,container,false);
        calendarView=view.findViewById(R.id.calendar_view);
        tv_text=view.findViewById(R.id.tv_text);
        recycler_view=view.findViewById(R.id.recycler_view);
        recycler_view.setAdapter(mAdapter);
        dbHelper=new DBHelper(getActivity());
        Name=userid; //ID

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String str="";
                if(month<=8) {
                    str = year + "-" + "0" + (month + 1) + "-" + dayOfMonth;
                    if(dayOfMonth<=9){
                        str = year + "-" + "0" + (month + 1) + "-" + "0"+dayOfMonth;
                    }
                }
                else {
                    str = year + "-" + (month + 1) + "-" + dayOfMonth;
                    if(dayOfMonth<=9){
                        str = year + "-" + (month + 1) + "-" + "0"+dayOfMonth;
                    }
                }
                tv_text.setText(str);
                memoItems =dbHelper.getMemoList_calender(str,Name);
                mAdapter=new CustomAdapter(memoItems,getActivity());
                recycler_view.setHasFixedSize(true);
                recycler_view.setAdapter(mAdapter);
            }
        });


        return view;
    }
}

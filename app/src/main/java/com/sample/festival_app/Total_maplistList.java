package com.sample.festival_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Total_maplistList extends Fragment {
    private View view;
    private ListViewAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.total_maplist, container, false);
        adapter = new ListViewAdapter();
        TextView text=view.findViewById(R.id.exist_text);
        ListView listView = view.findViewById(R.id.map_listview);
        listView.setAdapter(adapter);
        ArrayList<String> festival_list=getArguments().getStringArrayList("fstvlName");
        ArrayList<String> opar_list=getArguments().getStringArrayList("opar");
        ArrayList<String> fstvlStartDate_list=getArguments().getStringArrayList("fstvlStartDate");
        ArrayList<String> fstvlEndDate_list=getArguments().getStringArrayList("fstvlEndDate");
        ArrayList<String> fstvlCo_list=getArguments().getStringArrayList("fstvlCo");
        ArrayList<String> mnnst_list=getArguments().getStringArrayList("mnnst");
        ArrayList<String> phoneNumber_list=getArguments().getStringArrayList("phoneNumber");
        ArrayList<String> hompageUrl_list=getArguments().getStringArrayList("homepageUrl");
        ArrayList<String> rdnmadr_list=getArguments().getStringArrayList("rdnmadr");
        ArrayList<String> latitude_list=getArguments().getStringArrayList("latitude");
        ArrayList<String> longitude_list=getArguments().getStringArrayList("longtitude");


        for (int i = 0; i < festival_list.size(); i++) {
            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.icon_whiteheart), festival_list.get(i), fstvlStartDate_list.get(i) + "~" + fstvlEndDate_list.get(i) + "\n" + rdnmadr_list.get(i));
        }
        if(festival_list.size()==0){
            text.setText("해당 축제 정보는 존재하지 않습니다.");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(getActivity(),festival_info.class);
                intent.putExtra("fstvlName",festival_list.get(position));
                intent.putExtra("opar",opar_list.get(position));
                intent.putExtra("fstvlStartDate",fstvlStartDate_list.get(position));
                intent.putExtra("fstvlEndDate",fstvlEndDate_list.get(position));
                intent.putExtra("fstvlCo",fstvlCo_list.get(position));
                intent.putExtra("mnnst",mnnst_list.get(position));
                intent.putExtra("phoneNumber",phoneNumber_list.get(position));
                intent.putExtra("homepage",hompageUrl_list.get(position));
                intent.putExtra("rdnmadr",rdnmadr_list.get(position));
                intent.putExtra("latitude",latitude_list.get(position));
                intent.putExtra("longitude",longitude_list.get(position));
                startActivity(intent);

            }
        });

        return view;
    }

}

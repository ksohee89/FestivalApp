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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Month12 extends Fragment {
    private View view;
    private ListViewAdapter adapter;
    private ListView listView;
    int i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.month1, container, false);
        adapter=new ListViewAdapter();
        Pattern pattern=Pattern.compile("^2022-12(.*)$");
        ListView listView=view.findViewById(R.id.map_listview);
        TextView text=view.findViewById(R.id.not_exist_text);
        listView.setAdapter(adapter);
        ArrayList<String> festival_list=getArguments().getStringArrayList("fstvlName");
        ArrayList<String> opar_list=getArguments().getStringArrayList("opar");
        ArrayList<String> fstvlStartDate_list=getArguments().getStringArrayList("fstvlStartDate");
        ArrayList<String> fstvlEndDate_list=getArguments().getStringArrayList("fstvlEndDate");
        ArrayList<String> fstvlCo_list=getArguments().getStringArrayList("fstvlCo");
        ArrayList<String> mnnst_list=getArguments().getStringArrayList("mnnst");
        ArrayList<String> phoneNumber_list=getArguments().getStringArrayList("phoneNumber");
        ArrayList<String> homepageUrl_list=getArguments().getStringArrayList("homepageUrl");
        ArrayList<String> rdnmadr_list=getArguments().getStringArrayList("rdnmadr");
        ArrayList<String> latitude_list=getArguments().getStringArrayList("latitude");
        ArrayList<String> longitude_list=getArguments().getStringArrayList("longtitude");

        ArrayList<String> temp_fstvalName_list =new ArrayList<>();
        ArrayList<String> temp_opar_list=new ArrayList<>();
        ArrayList<String> temp_fstvlStartDate_list=new ArrayList<>();
        ArrayList<String> temp_fstvlEndDate_list=new ArrayList<>();
        ArrayList<String> temp_fstvlCo_list=new ArrayList<>();
        ArrayList<String> temp_mnnst_list=new ArrayList<>();
        ArrayList<String> temp_phoneNumber_list=new ArrayList<>();
        ArrayList<String> temp_hompageUrl_list=new ArrayList<>();
        ArrayList<String> temp_rdnmadr_list=new ArrayList<>();
        ArrayList<String> temp_longitude_list=new ArrayList<>();
        ArrayList<String> temp_latitude_list=new ArrayList<>();
        int cnt=0;

        for(i=0; i<festival_list.size();i++) {
            Matcher matcher=pattern.matcher(fstvlStartDate_list.get(i));
            if(matcher.find())
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.icon_whiteheart), festival_list.get(i), fstvlStartDate_list.get(i) + "~" + fstvlEndDate_list.get(i) + "\n" + rdnmadr_list.get(i));
                temp_fstvalName_list.add(festival_list.get(i));
                temp_opar_list.add(opar_list.get(i));
                temp_fstvlStartDate_list.add(fstvlStartDate_list.get(i));
                temp_fstvlEndDate_list.add(fstvlEndDate_list.get(i));
                temp_fstvlCo_list.add(fstvlCo_list.get(i));
                temp_mnnst_list.add(mnnst_list.get(i));
                temp_phoneNumber_list.add(phoneNumber_list.get(i));
                temp_hompageUrl_list.add(homepageUrl_list.get(i));
                temp_latitude_list.add(latitude_list.get(i));
                temp_longitude_list.add(longitude_list.get(i));
                temp_rdnmadr_list.add(rdnmadr_list.get(i));

                cnt++;
            }
        }

        if(cnt==0){
            text.setText("해당 축제정보는 존재하지 않습니다");
        }


        if(festival_list.size()==0){
            text.setText("해당 축제 정보는 존재하지 않습니다");
        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(getActivity(),festival_info.class);
                intent.putExtra("fstvlName", temp_fstvalName_list.get(position));
                intent.putExtra("opar",temp_opar_list.get(position));
                intent.putExtra("fstvlStartDate",temp_fstvlStartDate_list.get(position));
                intent.putExtra("fstvlEndDate",temp_fstvlEndDate_list.get(position));
                intent.putExtra("fstvlCo",temp_fstvlCo_list.get(position));
                intent.putExtra("mnnst",temp_mnnst_list.get(position));
                intent.putExtra("phoneNumber",temp_phoneNumber_list.get(position));
                intent.putExtra("homepage",temp_hompageUrl_list.get(position));
                intent.putExtra("rdnmadr",temp_rdnmadr_list.get(position));
                intent.putExtra("latitude",temp_latitude_list.get(position));
                intent.putExtra("longitude",temp_longitude_list.get(position));
                startActivity(intent);


            }
        });

        /*

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(getActivity(),festival_info.class);
                intent.putExtra("fstvlName", Listtemp_fstvalName_list.get(position));
                intent.putExtra("opar",Listtemp_opar_list.get(position));
                intent.putExtra("fstvlStartDate",Listtemp_fstvlStartDate_list.get(position));
                intent.putExtra("fstvlEndDate",Listtemp_fstvlEndDate_list.get(position));
                intent.putExtra("fstvlCo",Listtemp_fstvlCo_list.get(position));
                intent.putExtra("mnnst",Listtemp_mnnst_list.get(position));
                intent.putExtra("phoneNumber",Listtemp_phoneNumber_list.get(position));
                intent.putExtra("homepage",Listtemp_hompageUrl_list.get(position));
                intent.putExtra("rdnmadr",Listtemp_rdnmadr_list.get(position));
                intent.putExtra("latitude",Listtemp_latitude_list.get(position));
                intent.putExtra("longitude",Listtemp_longitude_list.get(position));
                startActivity(intent);


            }
        });


         */

        return view;
    }
}

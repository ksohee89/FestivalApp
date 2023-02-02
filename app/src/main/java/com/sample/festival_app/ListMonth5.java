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

public class ListMonth5 extends Fragment {
    private View view;
    private ListViewAdapter adapter;
    private ListView listView;
    int i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.month1, container, false);
        adapter=new ListViewAdapter();
        Pattern pattern=Pattern.compile("^2022-05(.*)$");
        ListView listView=view.findViewById(R.id.map_listview);
        TextView text=view.findViewById(R.id.not_exist_text);
        listView.setAdapter(adapter);
        ArrayList<String> Listfestival_list=getArguments().getStringArrayList("fstvlName");
        ArrayList<String> Listopar_list=getArguments().getStringArrayList("opar");
        ArrayList<String> ListfstvlStartDate_list=getArguments().getStringArrayList("fstvlStartDate");
        ArrayList<String> ListfstvlEndDate_list=getArguments().getStringArrayList("fstvlEndDate");
        ArrayList<String> ListfstvlCo_list=getArguments().getStringArrayList("fstvlCo");
        ArrayList<String> Listmnnst_list=getArguments().getStringArrayList("mnnst");
        ArrayList<String> ListphoneNumber_list=getArguments().getStringArrayList("phoneNumber");
        ArrayList<String> ListhomepageUrl_list=getArguments().getStringArrayList("homepageUrl");
        ArrayList<String> Listrdnmadr_list=getArguments().getStringArrayList("rdnmadr");
        ArrayList<String> Listlatitude_list=getArguments().getStringArrayList("latitude");
        ArrayList<String> Listlongitude_list=getArguments().getStringArrayList("longtitude");

        ArrayList<String> Listtemp_fstvalName_list =new ArrayList<>();
        ArrayList<String> Listtemp_opar_list=new ArrayList<>();
        ArrayList<String> Listtemp_fstvlStartDate_list=new ArrayList<>();
        ArrayList<String> Listtemp_fstvlEndDate_list=new ArrayList<>();
        ArrayList<String> Listtemp_fstvlCo_list=new ArrayList<>();
        ArrayList<String> Listtemp_mnnst_list=new ArrayList<>();
        ArrayList<String> Listtemp_phoneNumber_list=new ArrayList<>();
        ArrayList<String> Listtemp_hompageUrl_list=new ArrayList<>();
        ArrayList<String> Listtemp_rdnmadr_list=new ArrayList<>();
        ArrayList<String> Listtemp_longitude_list=new ArrayList<>();
        ArrayList<String> Listtemp_latitude_list=new ArrayList<>();
        int cnt=0;

        for(i=0; i<Listfestival_list.size();i++) {
            Matcher matcher=pattern.matcher(ListfstvlStartDate_list.get(i));
            if(matcher.find())
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.icon_whiteheart), Listfestival_list.get(i), ListfstvlStartDate_list.get(i) + "~" + ListfstvlEndDate_list.get(i) + "\n" + Listrdnmadr_list.get(i));
                Listtemp_fstvalName_list.add(Listfestival_list.get(i));
                Listtemp_opar_list.add(Listopar_list.get(i));
                Listtemp_fstvlStartDate_list.add(ListfstvlStartDate_list.get(i));
                Listtemp_fstvlEndDate_list.add(ListfstvlEndDate_list.get(i));
                Listtemp_fstvlCo_list.add(ListfstvlCo_list.get(i));
                Listtemp_mnnst_list.add(Listmnnst_list.get(i));
                Listtemp_phoneNumber_list.add(ListphoneNumber_list.get(i));
                Listtemp_hompageUrl_list.add(ListhomepageUrl_list.get(i));
                Listtemp_latitude_list.add(Listlatitude_list.get(i));
                Listtemp_longitude_list.add(Listlongitude_list.get(i));
                Listtemp_rdnmadr_list.add(Listrdnmadr_list.get(i));

                cnt++;
            }
        }

        if(cnt==0){
            text.setText("해당 축제정보는 존재하지 않습니다");
        }


        if(Listfestival_list.size()==0){
            text.setText("해당 축제 정보는 존재하지 않습니다");
        }




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

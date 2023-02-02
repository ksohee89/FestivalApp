package com.sample.festival_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class mapfestival_info extends Fragment {
    private View view;
    private Spinner spinner;
    public static frag_list newInstance(){
        frag_list frag_list =new frag_list();
        return frag_list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //festival_info
        view = inflater.inflate(R.layout.mapfestival_info, container, false);
        spinner=view.findViewById(R.id.spinner1);
        Button prev_btn=view.findViewById(R.id.prev);
        int num=getArguments().getInt("num");
        ArrayList<String> festival_list=getArguments().getStringArrayList("fstvlName");
        ArrayList<String> opar_list=getArguments().getStringArrayList("opar");
        ArrayList<String> fstvlStartDate_list=getArguments().getStringArrayList("fstvlStartDate");
        ArrayList<String> fstvlEndDate_list=getArguments().getStringArrayList("fstvlEndDate");
        ArrayList<String> fstvlCo_list=getArguments().getStringArrayList("fstvlCo");
        ArrayList<String> mnnst_list=getArguments().getStringArrayList("mnnst");
        ArrayList<String> latitude_list=getArguments().getStringArrayList("latitude");
        ArrayList<String> longtitude_list=getArguments().getStringArrayList("longtitude");
        ArrayList<String> phoneNumber_list=getArguments().getStringArrayList("phoneNumber");
        ArrayList<String> hompageUrl_list=getArguments().getStringArrayList("homepageUrl");
        ArrayList<String> rdnmadr_list=getArguments().getStringArrayList("rdnmadr");


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){
                    Total_maplist total_maplist=new Total_maplist();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    total_maplist.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,total_maplist);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
                if(position==1){

                    Month1 month1=new Month1();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month1.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month1);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

                if(position==2){
                    Month2 month2=new Month2();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month2.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month2);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==3){
                    Month3 month3=new Month3();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month3.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month3);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==4){
                    Month4 month4=new Month4();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month4.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month4);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==5){
                    Month5 month5=new Month5();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month5.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month5);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==6){
                    Month6 month6=new Month6();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month6.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month6);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==7){
                    Month7 month7=new Month7();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month7.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month7);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==8){
                    Month8 month8=new Month8();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month8.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month8);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==9){
                    Month9 month9=new Month9();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month9.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month9);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==10){
                    Month10 month10=new Month10();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month10.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month10);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==11){
                    Month11 month11=new Month11();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month11.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month11);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==12){
                    Month12 month12=new Month12();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("rdnmadr",rdnmadr_list);
                    bundle.putStringArrayList("fstvlName", festival_list);
                    bundle.putStringArrayList("opar", opar_list);
                    bundle.putStringArrayList("fstvlStartDate", fstvlStartDate_list);
                    bundle.putStringArrayList("fstvlEndDate", fstvlEndDate_list);
                    bundle.putStringArrayList("fstvlCo", fstvlCo_list);
                    bundle.putStringArrayList("mnnst", mnnst_list);
                    bundle.putStringArrayList("longtitude", longtitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    month12.setArguments(bundle);
                    transaction.replace(R.id.maplist_frame,month12);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num==1){
                    FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                    gyeonggido gyeonggido=new gyeonggido();
                    transaction.replace(R.id.map_frame,gyeonggido);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==2){
                    FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                    gangwondo gangwondo=new gangwondo();
                    transaction.replace(R.id.map_frame,gangwondo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==3){
                    FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                    chungcheongnamdo chungcheongnamdo=new chungcheongnamdo();
                    transaction.replace(R.id.map_frame, chungcheongnamdo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==4) {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    chungcheongbukdo chungcheongbukdo = new chungcheongbukdo();
                    transaction.replace(R.id.map_frame, chungcheongbukdo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==5){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    gyeongsangbukdo gyeongsangbukdo=new gyeongsangbukdo();
                    transaction.replace(R.id.map_frame,gyeongsangbukdo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==6){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    jeollabukdo jeollabukdo=new jeollabukdo();
                    transaction.replace(R.id.map_frame, jeollabukdo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==7){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    gyeongsangnamdo gyeongsangnamdo=new gyeongsangnamdo();
                    transaction.replace(R.id.map_frame,gyeongsangnamdo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==8){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    jeollanamdo jeollanamdo=new jeollanamdo();
                    transaction.replace(R.id.map_frame, jeollanamdo);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(num==9){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    jeju jeju=new jeju();
                    transaction.replace(R.id.map_frame, jeju);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        return view;
    }

}

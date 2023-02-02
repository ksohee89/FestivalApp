package com.sample.festival_app;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment2 extends Fragment {
    private View view;
    private Spinner spinner;
    private TextView list_theme;
    private TextView list_Month;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //전체 지도
        view = inflater.inflate(R.layout.frag_list1, container, false);
        spinner = view.findViewById(R.id.list_spinner);
        list_theme=view.findViewById(R.id.list_theme);
        list_Month=view.findViewById(R.id.list_Month);


        list_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListMonth listMonth=new ListMonth();
                transaction.replace(R.id.list_frame,listMonth);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        ArrayList<String> festival_list = new ArrayList<>();
        ArrayList<String> opar_list = new ArrayList<>();
        ArrayList<String> fstvlStartDate_list = new ArrayList<>();
        ArrayList<String> fstvlEndDate_list = new ArrayList<>();
        ArrayList<String> fstvlCo_list = new ArrayList<>();
        ArrayList<String> mnnst_list = new ArrayList<>();
        ArrayList<String> rdnmadr_list = new ArrayList<>();
        ArrayList<String> phoneNumber_list = new ArrayList<>();
        ArrayList<String> hompageUrl_list = new ArrayList<>();
        ArrayList<String> latitude_list = new ArrayList<>();
        ArrayList<String> longitude_list = new ArrayList<>();

        StrictMode.enableDefaults();
        Pattern pattern = Pattern.compile("^2022(.*)$");

        boolean initem = false, fstvlNm = false, fstvlStartDate = false, fstvlEndDate = false, homepageUrl = false;
        boolean phoneNumber = false, rdnmadr = false, opar = false, fstvlCo = false, mnnst = false,longitude=false, latitude=false;

        String festivalName = null, opar_info = null, festivalStartDate = null, festivalEndDate = null, homepage = null, tel = null, Roadaddress = null, mnnst_info = null;
        String festival_info = null,longitude_num=null, latitude_num=null;


        try {
            URL url = new URL("http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api?serviceKey=3hXQlHCtH1cIo517u1VMHYbBoU7NgRm2TwS%2Bh%2FRJ6LbqeDoGlescVhVtYayfpP0wutZs9iJi4BnO6RXn62HoHQ%3D%3D&type=xml&pageNo=0&numOfRows=1013"); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("fstvlNm")) {
                            fstvlNm = true;
                        }
                        if (parser.getName().equals("opar")) {
                            opar = true;
                        }
                        if (parser.getName().equals("fstvlStartDate")) {
                            fstvlStartDate = true;
                        }
                        if (parser.getName().equals("fstvlEndDate")) {
                            fstvlEndDate = true;
                        }
                        if (parser.getName().equals("fstvlCo")) {
                            fstvlCo = true;
                        }
                        if (parser.getName().equals("mnnst")) {
                            mnnst = true;
                        }

                        if (parser.getName().equals("phoneNumber")) {
                            phoneNumber = true;
                        }
                        if (parser.getName().equals("homepageUrl")) {
                            homepageUrl = true;
                        }
                        if (parser.getName().equals("rdnmadr")) {
                            rdnmadr = true;
                        }
                        if (parser.getName().equals("longitude")) {
                            longitude = true;
                        }
                        if (parser.getName().equals("latitude")) {
                            latitude = true;
                        }
                        if (parser.getName().equals("message")) {
                            // status1.setText(status1.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (fstvlNm) {
                            festivalName = parser.getText();
                            fstvlNm = false;
                        }
                        if (opar) {
                            opar_info = parser.getText();
                            opar = false;
                        }
                        if (fstvlStartDate) {
                            festivalStartDate = parser.getText();
                            fstvlStartDate = false;
                        }
                        if (fstvlEndDate) {
                            festivalEndDate = parser.getText();
                            fstvlEndDate = false;
                        }
                        if (fstvlCo) {
                            festival_info = parser.getText();
                            fstvlCo = false;
                        }
                        if (mnnst) {
                            mnnst_info = parser.getText();
                            mnnst = false;
                        }
                        if (phoneNumber) {
                            tel = parser.getText();
                            phoneNumber = false;
                        }
                        if (homepageUrl) {
                            homepage = parser.getText();
                            homepageUrl = false;
                        }

                        if (rdnmadr) {
                            Roadaddress = parser.getText();
                            rdnmadr = false;
                        }
                        if (longitude) {
                            longitude_num = parser.getText();
                            longitude = false;
                        }
                        if (latitude) {
                            latitude_num = parser.getText();
                            latitude = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            Matcher matcher = pattern.matcher(festivalStartDate);
                            if (matcher.find()) {
                                festival_list.add(festivalName);
                                opar_list.add(opar_info);
                                fstvlStartDate_list.add(festivalStartDate);
                                fstvlEndDate_list.add(festivalEndDate);
                                fstvlCo_list.add(festival_info);
                                mnnst_list.add(mnnst_info);
                                phoneNumber_list.add(tel);
                                hompageUrl_list.add(homepage);
                                rdnmadr_list.add(Roadaddress);
                                latitude_list.add(latitude_num);
                                longitude_list.add(longitude_num);
                            }
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){

                    Total_maplistList total_maplistList=new Total_maplistList();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    total_maplistList.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,total_maplistList);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
                if(position==1){
                    ListMonth1 listMonth1=new ListMonth1();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth1.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth1);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
                if(position==2){
                    ListMonth2 listMonth2=new ListMonth2();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth2.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth2);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==3){
                    ListMonth3 listMonth3=new ListMonth3();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth3.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth3);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==4){
                    ListMonth4 listMonth4=new ListMonth4();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth4.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth4);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==5){
                    ListMonth5 listMonth5=new ListMonth5();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth5.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth5);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==6){
                    ListMonth6 listMonth6=new ListMonth6();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth6.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth6);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==7){
                    ListMonth7 listMonth7=new ListMonth7();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth7.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth7);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==8){
                    ListMonth8 listMonth8=new ListMonth8();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth8.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth8);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==9){
                    ListMonth9 listMonth9=new ListMonth9();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth9.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth9);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==10){
                    ListMonth10 listMonth10=new ListMonth10();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth10.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth10);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==11){
                    ListMonth11 listMonth11=new ListMonth11();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth11.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth11);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(position==12){
                    ListMonth12 listMonth12 =new ListMonth12();
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
                    bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                    bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                    bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                    bundle.putStringArrayList("longtitude", longitude_list);
                    bundle.putStringArrayList("latitude", latitude_list);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    listMonth12.setArguments(bundle);
                    transaction.replace(R.id.list_frame2,listMonth12);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                list_theme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ListTheme listTheme=new ListTheme();
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
                        bundle.putStringArrayList("phoneNumber", phoneNumber_list);
                        bundle.putStringArrayList("homepageUrl", hompageUrl_list);
                        bundle.putStringArrayList("rdnmadr", rdnmadr_list);
                        bundle.putStringArrayList("longtitude", longitude_list);
                        bundle.putStringArrayList("latitude", latitude_list);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        listTheme.setArguments(bundle);
                        transaction.replace(R.id.list_frame, listTheme);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }
}
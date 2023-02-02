package com.sample.festival_app;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class jeollabukdo extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jeollabukdo, container, false);

        int num=6;

        TextView jeollabukdo_text1=view.findViewById(R.id.jeollabukdo_text1);
        TextView jeollabukdo_text2=view.findViewById(R.id.jeollabukdo_text2);
        TextView jeollabukdo_text3=view.findViewById(R.id.jeollabukdo_text3);
        TextView jeollabukdo_text4=view.findViewById(R.id.jeollabukdo_text4);
        TextView jeollabukdo_text5=view.findViewById(R.id.jeollabukdo_text5);
        TextView jeollabukdo_text6=view.findViewById(R.id.jeollabukdo_text6);
        TextView jeollabukdo_text7=view.findViewById(R.id.jeollabukdo_text7);
        TextView jeollabukdo_text8=view.findViewById(R.id.jeollabukdo_text8);
        TextView jeollabukdo_text9=view.findViewById(R.id.jeollabukdo_text9);
        TextView jeollabukdo_text10=view.findViewById(R.id.jeollabukdo_text10);
        TextView jeollabukdo_text11=view.findViewById(R.id.jeollabukdo_text11);
        TextView jeollabukdo_text12=view.findViewById(R.id.jeollabukdo_text12);
        TextView jeollabukdo_text13=view.findViewById(R.id.jeollabukdo_text13);
        TextView jeollabukdo_text14=view.findViewById(R.id.jeollabukdo_text14);

        ImageView jeollabukdo_pin1=view.findViewById(R.id.jeollabukdo_pin1);
        ImageView jeollabukdo_pin2=view.findViewById(R.id.jeollabukdo_pin2);
        ImageView jeollabukdo_pin3=view.findViewById(R.id.jeollabukdo_pin3);
        ImageView jeollabukdo_pin4=view.findViewById(R.id.jeollabukdo_pin4);
        ImageView jeollabukdo_pin5=view.findViewById(R.id.jeollabukdo_pin5);
        ImageView jeollabukdo_pin6=view.findViewById(R.id.jeollabukdo_pin6);
        ImageView jeollabukdo_pin7=view.findViewById(R.id.jeollabukdo_pin7);
        ImageView jeollabukdo_pin8=view.findViewById(R.id.jeollabukdo_pin8);
        ImageView jeollabukdo_pin9=view.findViewById(R.id.jeollabukdo_pin9);
        ImageView jeollabukdo_pin10=view.findViewById(R.id.jeollabukdo_pin10);
        ImageView jeollabukdo_pin11=view.findViewById(R.id.jeollabukdo_pin11);
        ImageView jeollabukdo_pin12=view.findViewById(R.id.jeollabukdo_pin12);
        ImageView jeollabukdo_pin13=view.findViewById(R.id.jeollabukdo_pin13);
        ImageView jeollabukdo_pin14=view.findViewById(R.id.jeollabukdo_pin14);
        ;
        Button jeollabukdo_map_btn=view.findViewById(R.id.jeollabukdo_map_btn);

        ArrayList<String> festival_list = new ArrayList<>();
        ArrayList<String> opar_list = new ArrayList<>();
        ArrayList<String> fstvlStartDate_list = new ArrayList<>();
        ArrayList<String> fstvlEndDate_list = new ArrayList<>();
        ArrayList<String> fstvlCo_list = new ArrayList<>();
        ArrayList<String> mnnst_list = new ArrayList<>();
        ArrayList<String> rdnmadr_list = new ArrayList<>();
        ArrayList<String> phoneNumber_list = new ArrayList<>();
        ArrayList<String> homepageUrl_list = new ArrayList<>();
        ArrayList<String> latitude_list = new ArrayList<>();
        ArrayList<String> longitude_list = new ArrayList<>();

        StrictMode.enableDefaults();
        Pattern pattern=Pattern.compile("^2022(.*)$");


        Pattern pattern1=Pattern.compile("^전라북도 군산시(.*)$");
        Pattern pattern2=Pattern.compile("^전라북도 익산시(.*)$");
        Pattern pattern3=Pattern.compile("^전라북도 완주군(.*)$");
        Pattern pattern4=Pattern.compile("^전라북도 진안군(.*)$");
        Pattern pattern5=Pattern.compile("^전라북도 무주군(.*)$");
        Pattern pattern6=Pattern.compile("^전라북도 김제시(.*)$");
        Pattern pattern7=Pattern.compile("^전라북도 전주시(.*)$");
        Pattern pattern8=Pattern.compile("^전라북도 장수군(.*)$");
        Pattern pattern9=Pattern.compile("^전라북도 부안군(.*)$");
        Pattern pattern10=Pattern.compile("^전라북도 정읍시(.*)$");
        Pattern pattern11=Pattern.compile("^전라북도 임실군(.*)$");
        Pattern pattern12=Pattern.compile("^전라북도 고창군(.*)$");
        Pattern pattern13=Pattern.compile("^전라북도 순창군(.*)$");
        Pattern pattern14=Pattern.compile("^전라북도 남원시(.*)$");



        boolean initem = false, fstvlNm = false, fstvlStartDate = false, fstvlEndDate = false, homepageUrl = false;
        boolean phoneNumber = false, rdnmadr = false, opar = false, fstvlCo = false, mnnst=false,longitude=false, latitude=false;

        String festivalName = null, opar_info=null,festivalStartDate = null, festivalEndDate = null, homepage = null, tel = null, Roadaddress = null, mnnst_info = null;
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
                            Matcher matcher=pattern.matcher(festivalStartDate);
                                if(matcher.find()) {
                                    festival_list.add(festivalName);
                                    opar_list.add(opar_info);
                                    fstvlStartDate_list.add(festivalStartDate);
                                    fstvlEndDate_list.add(festivalEndDate);
                                    fstvlCo_list.add(festival_info);
                                    mnnst_list.add(mnnst_info);
                                    phoneNumber_list.add(tel);
                                    homepageUrl_list.add(homepage);
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


        for(int i=0; i<festival_list.size();i++) {
            Matcher matcher1=pattern1.matcher(rdnmadr_list.get(i));
            Matcher matcher2=pattern2.matcher(rdnmadr_list.get(i));
            Matcher matcher3=pattern3.matcher(rdnmadr_list.get(i));
            Matcher matcher4=pattern4.matcher(rdnmadr_list.get(i));
            Matcher matcher5=pattern5.matcher(rdnmadr_list.get(i));
            Matcher matcher6=pattern6.matcher(rdnmadr_list.get(i));
            Matcher matcher7=pattern7.matcher(rdnmadr_list.get(i));
            Matcher matcher8=pattern8.matcher(rdnmadr_list.get(i));
            Matcher matcher9=pattern9.matcher(rdnmadr_list.get(i));
            Matcher matcher10=pattern10.matcher(rdnmadr_list.get(i));
            Matcher matcher11=pattern11.matcher(rdnmadr_list.get(i));
            Matcher matcher12=pattern12.matcher(rdnmadr_list.get(i));
            Matcher matcher13=pattern13.matcher(rdnmadr_list.get(i));
            Matcher matcher14=pattern14.matcher(rdnmadr_list.get(i));

            if(matcher1.find()){
                jeollabukdo_pin1.setImageResource(R.drawable.icon_pin);
            }
            if(matcher2.find()){
                jeollabukdo_pin2.setImageResource(R.drawable.icon_pin);
            }
            if(matcher3.find()){
                jeollabukdo_pin3.setImageResource(R.drawable.icon_pin);
            }
            if (matcher4.find()) {
                jeollabukdo_pin4.setImageResource(R.drawable.icon_pin);
            }
            if(matcher5.find()){
                jeollabukdo_pin5.setImageResource(R.drawable.icon_pin);
            }
            if(matcher6.find()){
                jeollabukdo_pin6.setImageResource(R.drawable.icon_pin);
            }
            if(matcher7.find()){
                jeollabukdo_pin7.setImageResource(R.drawable.icon_pin);
            }
            if(matcher8.find()){
                jeollabukdo_pin8.setImageResource(R.drawable.icon_pin);
            }
            if (matcher9.find()) {
                jeollabukdo_pin9.setImageResource(R.drawable.icon_pin);
            }
            if(matcher10.find()){
                jeollabukdo_pin10.setImageResource(R.drawable.icon_pin);
            }
            if(matcher11.find()){
                jeollabukdo_pin11.setImageResource(R.drawable.icon_pin);
            }
            if(matcher12.find()){
                jeollabukdo_pin12.setImageResource(R.drawable.icon_pin);
            }
            if(matcher13.find()){
                jeollabukdo_pin13.setImageResource(R.drawable.icon_pin);
            }
            if(matcher14.find()){
                jeollabukdo_pin14.setImageResource(R.drawable.icon_pin);
            }

        }

        jeollabukdo_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher1=pattern1.matcher(rdnmadr_list.get(i));
                    if(!matcher1.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher2=pattern2.matcher(rdnmadr_list.get(i));
                    if(!matcher2.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher3=pattern3.matcher(rdnmadr_list.get(i));
                    if(!matcher3.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher4=pattern4.matcher(rdnmadr_list.get(i));
                    if(!matcher4.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher5=pattern5.matcher(rdnmadr_list.get(i));
                    if(!matcher5.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher6=pattern6.matcher(rdnmadr_list.get(i));
                    if(!matcher6.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher7=pattern7.matcher(rdnmadr_list.get(i));
                    if(!matcher7.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;



                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher8=pattern8.matcher(rdnmadr_list.get(i));
                    if(!matcher8.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher9=pattern9.matcher(rdnmadr_list.get(i));
                    if(!matcher9.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher10=pattern10.matcher(rdnmadr_list.get(i));
                    if(!matcher10.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher11=pattern11.matcher(rdnmadr_list.get(i));
                    if(!matcher11.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher12=pattern12.matcher(rdnmadr_list.get(i));
                    if(!matcher12.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher13=pattern13.matcher(rdnmadr_list.get(i));
                    if(!matcher13.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });

        jeollabukdo_text14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> temp_fstvalName_list=festival_list;
                ArrayList<String> temp_opar_list=opar_list;
                ArrayList<String> temp_fstvlStartDate_list=fstvlStartDate_list;
                ArrayList<String> temp_fstvlEndDate_list=fstvlEndDate_list;
                ArrayList<String>temp_fstvlCo_list=fstvlCo_list;
                ArrayList<String>temp_mnnst_list=mnnst_list;
                ArrayList<String>temp_phoneNumber_list=phoneNumber_list;
                ArrayList<String>temp_hompageUrl_list=homepageUrl_list;
                ArrayList<String> temp_rdnmadr_list=rdnmadr_list;
                ArrayList<String> temp_longitude_list=longitude_list;
                ArrayList<String> temp_latitude_list=latitude_list;


                int size=festival_list.size();
                for(int i=0; i<size; i++){
                    Matcher matcher14=pattern14.matcher(rdnmadr_list.get(i));
                    if(!matcher14.find()){
                        temp_fstvalName_list.remove(i);
                        temp_opar_list.remove(i);
                        temp_fstvlStartDate_list.remove(i);
                        temp_fstvlEndDate_list.remove(i);
                        temp_fstvlCo_list.remove(i);
                        temp_mnnst_list.remove(i);
                        temp_phoneNumber_list.remove(i);
                        temp_hompageUrl_list.remove(i);
                        temp_rdnmadr_list.remove(i);
                        temp_latitude_list.remove(i);
                        temp_longitude_list.remove(i);

                        size--;
                        i--;
                    }
                }

                mapfestival_info mapfestival_info = new mapfestival_info();
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                bundle.putStringArrayList("fstvlName", temp_fstvalName_list);
                bundle.putStringArrayList("opar", temp_opar_list);
                bundle.putStringArrayList("fstvlStartDate", temp_fstvlStartDate_list);
                bundle.putStringArrayList("fstvlEndDate", temp_fstvlEndDate_list);
                bundle.putStringArrayList("fstvlCo", temp_fstvlCo_list);
                bundle.putStringArrayList("mnnst", temp_mnnst_list);
                bundle.putStringArrayList("phoneNumber", temp_phoneNumber_list);
                bundle.putStringArrayList("homepageUrl", temp_hompageUrl_list);
                bundle.putStringArrayList("rdnmadr", temp_rdnmadr_list);
                bundle.putStringArrayList("longtitude", temp_longitude_list);
                bundle.putStringArrayList("latitude", temp_latitude_list);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                mapfestival_info.setArguments(bundle);
                transaction.replace(R.id.map_frame, mapfestival_info);
                transaction.commit();
            }
        });


        jeollabukdo_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment1 fragment1=new Fragment1();
                transaction.replace(R.id.map_frame, fragment1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}

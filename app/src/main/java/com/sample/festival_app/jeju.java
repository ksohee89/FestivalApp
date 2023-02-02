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

public class jeju extends Fragment {
    private View view;
    //경기도
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //제주도
        view = inflater.inflate(R.layout.jeju, container, false);

        int num=9;

        TextView jeju_text1=view.findViewById(R.id.jeju_text1);
        TextView jeju_text2=view.findViewById(R.id.jeju_text2);

        ImageView jeju_pin1=view.findViewById(R.id.jeju_pin1);
        ImageView jeju_pin2=view.findViewById(R.id.jeju_pin2);

        Button jeju_map_btn=view.findViewById(R.id.jeju_map_btn);

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


        Pattern pattern1=Pattern.compile("^제주특별자치도 제주시(.*)$");
        Pattern pattern2=Pattern.compile("^제주특별자치도 서귀포시(.*)$");

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
            Matcher matcher1 = pattern1.matcher(rdnmadr_list.get(i));
            Matcher matcher2 = pattern2.matcher(rdnmadr_list.get(i));
            if(matcher1.find()){
                jeju_pin1.setImageResource(R.drawable.icon_pin);
            }
            if(matcher2.find()){
                jeju_pin2.setImageResource(R.drawable.icon_pin);
            }
        }

        jeju_text1.setOnClickListener(new View.OnClickListener() {
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

        jeju_text2.setOnClickListener(new View.OnClickListener() {
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

        jeju_map_btn.setOnClickListener(new View.OnClickListener() {
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

package com.sample.festival_app;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListMonth extends Fragment {
    private View view;
    private TextView list_theme;
    private TextView all_list;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //전체 지도
        view = inflater.inflate(R.layout.list_month, container, false);
        list_theme=view.findViewById(R.id.list_theme);
        all_list=view.findViewById(R.id.all_list);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String getTime = dateFormat.format(date);

        all_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment2 fragment2=new Fragment2();
                transaction.replace(R.id.list_frame, fragment2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        list_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListTheme listTheme=new ListTheme();
                transaction.replace(R.id.list_frame, listTheme);
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
        Pattern pattern = Pattern.compile("^"+getTime+"(.*)$");

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

        Total_maplist total_maplist=new Total_maplist();
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
        total_maplist.setArguments(bundle);
        transaction.replace(R.id.list_month_fragment,total_maplist);
        transaction.addToBackStack(null);
        transaction.commit();

        return view;
    }
}

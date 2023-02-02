package com.sample.festival_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTheme1 extends Fragment {
    private View view;
    private TextView all_list;
    private TextView theme;
    private TextView theme2;
    private TextView theme3;
    private  TextView theme4;
    private  TextView list_Month;
    private ListViewAdapter adapter;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //전체 지도
        view = inflater.inflate(R.layout.list_theme1, container, false);
        all_list=view.findViewById(R.id.all_list);
        theme=view.findViewById(R.id.theme);
        theme2=view.findViewById(R.id.theme2);
        theme3=view.findViewById(R.id.theme3);
        theme4=view.findViewById(R.id.theme4);
        list_Month=view.findViewById(R.id.list_Month);
        adapter=new ListViewAdapter();
        ListView listView=view.findViewById(R.id.list_theme_listview1);
        listView.setAdapter(adapter);


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

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListTheme listTheme=new ListTheme();
                transaction.replace(R.id.list_frame, listTheme);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListTheme2 listTheme2=new ListTheme2();
                transaction.replace(R.id.list_frame, listTheme2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListTheme3 listTheme3=new ListTheme3();
                transaction.replace(R.id.list_frame, listTheme3);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        theme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListTheme4 listTheme4=new ListTheme4();
                transaction.replace(R.id.list_frame, listTheme4);
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
        ArrayList<String> homepageUrl_list = new ArrayList<>();
        ArrayList<String> latitude_list = new ArrayList<>();
        ArrayList<String> longitude_list = new ArrayList<>();

        StrictMode.enableDefaults();
        Pattern pattern=Pattern.compile("^2022(.*)$");
        Pattern pattern1=Pattern.compile("^(.*)꽃(.*)$");
        Pattern pattern7=Pattern.compile("^(.*)불꽃(.*)$");
        Pattern pattern2=Pattern.compile("^(.*)장미(.*)$");
        Pattern pattern3=Pattern.compile("^(.*)튤립(.*)$");
        Pattern pattern4=Pattern.compile("^(.*)매화(.*)$");
        Pattern pattern5=Pattern.compile("^(.*)진달래(.*)$");
        Pattern pattern6=Pattern.compile("^(.*)수국(.*)$");




        boolean initem = false, fstvlNm = false, fstvlStartDate = false, fstvlEndDate = false, homepageUrl = false;
        boolean phoneNumber = false, rdnmadr = false, opar = false, fstvlCo = false, mnnst=false,  longitude=false, latitude=false;

        String festivalName = null, opar_info=null,festivalStartDate = null, festivalEndDate = null, homepage = null, tel = null, Roadaddress = null, mnnst_info = null;
        String festival_info = null, longitude_num=null, latitude_num=null;


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
                            Matcher matcher1=pattern1.matcher(festivalName);
                            Matcher matcher2=pattern2.matcher(festivalName);
                            Matcher matcher3=pattern3.matcher(festivalName);
                            Matcher matcher4=pattern4.matcher(festivalName);
                            Matcher matcher5=pattern5.matcher(festivalName);
                            Matcher matcher6=pattern6.matcher(festivalName);
                            Matcher matcher7=pattern7.matcher(festivalName);

                            if(matcher.find()) {
                                if(matcher1.find()&&!matcher7.find()||matcher2.find()||matcher3.find()||matcher4.find()||matcher5.find()||matcher6.find()) {
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
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
        }

        for(int i=0; i<festival_list.size();i++) {
            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.icon_whiteheart), festival_list.get(i), fstvlStartDate_list.get(i)+ "~" + fstvlEndDate_list.get(i) + "\n" + rdnmadr_list.get(i));
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
                intent.putExtra("homepage",homepageUrl_list.get(position));
                intent.putExtra("rdnmadr",rdnmadr_list.get(position));
                intent.putExtra("latitude",latitude_list.get(position));
                intent.putExtra("longitude",longitude_list.get(position));
                startActivity(intent);


            }
        });

        return view;
    }
}

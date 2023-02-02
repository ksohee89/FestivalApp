package com.sample.festival_app;

import static com.sample.festival_app.MainScreen.userid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class festival_info extends AppCompatActivity implements OnMapReadyCallback {
    //축제 정보창
    private String user_Name=""; //userName
    private TextView Name_tv;
    private TextView duration_tv;
    private TextView opar_tv;
    private TextView festivalCo_tv;
    private TextView mnnst_tv;
    private TextView phoneNumber_tv;
    private TextView homepageUrl_tv;
    private TextView rdnmadr_tv;
    private MapView mapView;
    private ImageView call;
    private ImageView webview;
    private Button map2;
    private ImageView backbtn;
    private ImageView find_map;


    String tel="";
    String url="";
    String Name;
    String startDate;
    String endDate;
    String homepage;
    String rdnmadr;

    private static final int GPS_ENABLE_REQUEST_CODE=2001;//
    private static final int PERMISSION_REQUEST_CODE=100;//
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};//



    private static NaverMap naverMap;
    private Marker marker1 = new Marker();



    double lat=0;
    double lng=0;//위경도 0으로 초기화
    int image_index=0;
    ImageView imageview;
    private LikeDBHelper LikeDBHelper;
    private ArrayList<LikeItem> likeItems;
    int count=0;
    ImageView heartImage;
    private Button ing_btn;

    private TextView review_user1;
    private TextView review_user2;
    private TextView review_user3;
    private TextView review1;
    private TextView review2;
    private TextView review3;
    private ArrayList<ReviewItem> reviewItems;
    private FirebaseFirestore mStore;

    Pattern pattern=Pattern.compile("^http(.*)$");
    Pattern pattern1=Pattern.compile("^2022(.*)$");
    Pattern pattern2=Pattern.compile("^2022(.*)$");
    Pattern pattern3=Pattern.compile("^(.*)-(.*)$");
    Pattern pattern4=Pattern.compile("^(.*).(.*)$");
    Pattern pattern5=Pattern.compile("^(.*).(.*)$");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.festival_info);




        user_Name = userid;
        heartImage=findViewById(R.id.heartImage);
        LikeDBHelper=new LikeDBHelper(getApplicationContext());



        Name_tv=findViewById(R.id.Name_tv);
        opar_tv=findViewById(R.id.opar_tv);
        duration_tv=findViewById(R.id.duration_tv);
        festivalCo_tv=findViewById(R.id.festivalCo_tv);
        mnnst_tv=findViewById(R.id.mnnst_tv);
        phoneNumber_tv=findViewById(R.id.phoneNumber_tv);
        homepageUrl_tv=findViewById(R.id.homepageUrl_tv);
        rdnmadr_tv=findViewById(R.id.rdnmadr_tv);
        TextView review1_btn=findViewById(R.id.review1_btn);
        ImageView review2_btn=findViewById(R.id.review2_btn);
        ImageView review3_btn=findViewById(R.id.review3_btn);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        imageview=findViewById(R.id.noimage);
        call=findViewById(R.id.call_icon);
        webview=findViewById(R.id.homepage_icon);
        map2=findViewById(R.id.map2);
        backbtn=findViewById(R.id.backbtn);
        ing_btn=findViewById(R.id.ing_btn);
        find_map=findViewById(R.id.find_map);



        Intent intent=getIntent();
         Name=intent.getStringExtra("fstvlName");
        String opar=intent.getStringExtra("opar");
         startDate=intent.getStringExtra("fstvlStartDate");
         endDate=intent.getStringExtra("fstvlEndDate");
        String festivalCo=intent.getStringExtra("fstvlCo");
        String mnnst=intent.getStringExtra("mnnst");
        String phoneNumber=intent.getStringExtra("phoneNumber");
        homepage=intent.getStringExtra("homepage");
        rdnmadr=intent.getStringExtra("rdnmadr");
        String latitude=intent.getStringExtra("latitude");
        String longitude=intent.getStringExtra("longitude");
        String latitude_real=null;
        String longitude_real = null;

        review_user1=findViewById(R.id.review_user1);
        review_user2=findViewById(R.id.review_user2);
        review_user3=findViewById(R.id.review_user3);
        review1=findViewById(R.id.review1);
        review2=findViewById(R.id.review2);
        review3=findViewById(R.id.review3);
        reviewItems=new ArrayList<>();

        review_user1.setText("");
        review1.setText("등록된 댓글이 없습니다.");
        review_user2.setText("");
        review2.setText("등록된 댓글이 없습니다.");
        review_user3.setText("");
        review3.setText("등록된 댓글이 없습니다.");

        mStore = FirebaseFirestore.getInstance();
        mStore.collection("review")
                .whereEqualTo("festival", Name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null) {
                                int i = 0;
                                for (DocumentSnapshot snap : task.getResult()) {
                                    Map<String, Object> shot = snap.getData();
                                    String rid = snap.getId();
                                    String userid = String.valueOf(shot.get("userid"));
                                    String festival = String.valueOf(shot.get("festival"));
                                    String contents = String.valueOf(shot.get("contents"));
                                    String date = String.valueOf(shot.get("date"));
                                    ReviewItem data = new ReviewItem(rid, userid, festival, contents, date);
                                    reviewItems.add(data);
                                    i++;
                                    if (i == 1){
                                        review_user1.setText(userid);
                                        review1.setText(contents);
                                    }
                                    else if (i == 2){
                                        review_user2.setText(userid);
                                        review2.setText(contents);
                                    }
                                    else if (i == 3) {
                                        review_user3.setText(userid);
                                        review3.setText(contents);
                                    }
                                }
                            }
                        }
                    }
                });


        String likeTrue=LikeDBHelper.getLike(user_Name,Name);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        int compare=startDate.compareTo(getTime);
        int compare2=endDate.compareTo(getTime);
        if(compare<=0&&compare2>=0){
            ing_btn.setVisibility(View.VISIBLE);
        }


        Name_tv.setText(Name);
        duration_tv.setText(startDate+"~"+endDate);
        opar_tv.setText(opar);
        rdnmadr_tv.setText(rdnmadr);
        mnnst_tv.setText(mnnst);
        festivalCo_tv.setText(festivalCo);

        Matcher matcher1=pattern1.matcher(longitude);
        Matcher matcher4=pattern4.matcher(longitude);
        if(!matcher1.find()) {
            if(matcher4.find()){
                longitude_real=longitude;
                lng=Double.parseDouble(longitude_real);
            }

        }
        Matcher matcher2=pattern2.matcher(latitude);
        Matcher matcher5=pattern5.matcher(latitude);
        if(!matcher2.find()) {
            if(matcher5.find()){
                latitude_real=latitude;
                lat=Double.parseDouble(latitude_real);
            }

        }
        final Geocoder geocoders = new Geocoder(this);  // 지도에 위치정보 찍기
        if(lat==0 || lng == 0){//위도 또는 경도 정보가 없으면
            List<Address> address1= null;
            try{
                address1=geocoders.getFromLocationName(rdnmadr,100);
            }catch(IOException e){
                e.printStackTrace();
                Log.e("test","입출력 오류 - 서버에서 주소변환시 에러 발생");
            }
            if(address1!= null){
                if(address1.size()==0){
                    Toast.makeText(getApplicationContext(),"해당되는 주소 정보는 없습니다.",Toast.LENGTH_SHORT).show();
                }else{
                    lat=address1.get(0).getLatitude();
                    lng=address1.get(0).getLongitude();

                    mapView.onCreate(savedInstanceState);
                    mapView.getMapAsync(this);
                }
            }
        }else{//위경도 정보가 있으면
            image_index=0;
            change();
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }


        Matcher matcher3=pattern3.matcher(phoneNumber);
        int compare3= phoneNumber.compareTo(rdnmadr);
        if((phoneNumber!=homepage)&&(compare3!=0)){
            if(matcher3.find()) {
                phoneNumber_tv.setText(phoneNumber);
                tel=phoneNumber;
            }
        }
        Matcher matcher=pattern.matcher(homepage);
        if(matcher.find()){
            homepageUrl_tv.setText(homepage);
            url=homepage;
        }

        review1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),review.class);
                intent.putExtra("fstvlName",Name);
                startActivity(intent);
            }
        });
        review2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),review.class);
                intent.putExtra("fstvlName",Name);
                startActivity(intent);
            }
        });
        review3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),review.class);
                intent.putExtra("fstvlName",Name);
                startActivity(intent);
            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        GpsTracker gpsTracker = new GpsTracker(festival_info.this);
        double slat=gpsTracker.getLatitude();
        double slng=gpsTracker.getLongitude();
        String sname = getCurrentAddress(slat, slng);



        final Geocoder geocoder = new Geocoder(this);
        find_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(lat==0 || lng == 0){//위도 또는 경도 정보가 없으면
                    List<Address> address1= null;
                    try{
                        address1=geocoder.getFromLocationName(rdnmadr,100);
                    }catch(IOException e){
                        e.printStackTrace();
                        Log.e("test","입출력 오류 - 서버에서 주소변환시 에러 발생");
                    }
                    if(address1!= null){
                        if(address1.size()==0){
                            Toast.makeText(getApplicationContext(),"해당되는 주소 정보는 없습니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            lat=address1.get(0).getLatitude();
                            lng=address1.get(0).getLongitude();

                            String url="nmap://route/public?slat="+slat+"&slng="+slng+"&sname="+sname+"&dlat="+lat+"&dlng="+lng+"&dname="+rdnmadr+"&appname=com.sample.myapplication_example";
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                            if (list == null || list.isEmpty()) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.nmap")));


                            } else {
                                startActivity(intent);
                            }
                        }
                    }


                }else{//위경도 정보가 있으면
                    String url="nmap://route/public?slat="+slat+"&slng="+slng+"&sname="+sname+"&dlat="+lat+"&dlng="+lng+"&dname="+rdnmadr+"&appname=com.sample.myapplication_example";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if (list == null || list.isEmpty()) {

                        startActivity(intent);

                    } else {
                        startActivity(intent);
                    }
                }


            }
        });

        if (!(user_Name==null)) {
            if (likeTrue.equals("NoExist")) {
                likeItems = new ArrayList<>();
                LikeDBHelper = new LikeDBHelper(getApplicationContext());
                LikeDBHelper.Insert(user_Name, Name, "NotLike", "2022", "2022", "Opar", "festivalCo", "mnst", "phoneNumber", "homepage", "rdnmadr", "latitude", "longitude");
                LikeItem item = new LikeItem();
                item.setUser(user_Name);
                item.setFestivalName(Name);
                item.setLikeTrue("NotLike");
            } else {
                if (likeTrue.equals("Like")) {
                    heartImage.setImageResource(R.drawable.icon_heart);
                } else if (likeTrue.equals("NotLike")) {
                    heartImage.setImageResource(R.drawable.icon_blackheart);
                }
            }

            heartImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count == 0) {
                        count++;
                        heartImage.setImageResource(R.drawable.icon_heart);
                        LikeDBHelper.LikeUpdate(user_Name, Name, "Like", startDate, endDate, opar, festivalCo, mnnst, phoneNumber, homepage, rdnmadr, latitude, longitude);
                    } else if (count == 1) {
                        count--;
                        heartImage.setImageResource(R.drawable.icon_blackheart);
                        LikeDBHelper.LikeUpdate(user_Name, Name, "NotLike", startDate, endDate, opar, festivalCo, mnnst, phoneNumber, homepage, rdnmadr, latitude, longitude);
                    }
                }
            });
        }
        if(!(tel.isEmpty())) {
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + tel));
                    startActivity(intent);
                }
            });
        }
        if(!(url.isEmpty())) {
            webview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(festival_info.this, Webview.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            });
        }else{
            webview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast= Toast.makeText(getApplicationContext(),"홈페이지 정보가 없습니다.",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

        }

        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lat==0 || lng == 0){//위도 또는 경도 정보가 없으면
                    Toast toast= Toast.makeText(getApplicationContext(),"위치 정보가 없습니다.",Toast.LENGTH_SHORT);
                    toast.show();
                }else{//위경도 정보가 있으면
                    Intent intent = new Intent(festival_info.this,festival_map.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lng",lng);
                    intent.putExtra("rdnmadr",rdnmadr);
                    intent.putExtra("slat",slat);
                    intent.putExtra("slng",slng);
                    startActivity(intent);
                }

            }
        });

    }



    public void change(){//imageindex가 0이면 map,1이면 image
        if(image_index==0){
            mapView.setVisibility(View.VISIBLE);
            imageview.setVisibility(View.INVISIBLE);
        }
        else if(image_index==1){
            mapView.setVisibility(View.INVISIBLE);
            imageview.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap= naverMap;
        CameraPosition cameraPosition = new CameraPosition(new LatLng(lat,lng),12);
        naverMap.setCameraPosition(cameraPosition);
        setMark(marker1,lat,lng, R.drawable.ic_baseline_place_24,3);//핀 지도에 나타내기

    }
    private void setMark(Marker marker, double lat, double lng, int resourceID,int zlndex){  //핀위치 잡기
        marker.setIconPerspectiveEnabled(true);
        marker.setIcon(OverlayImage.fromResource(resourceID));
        marker.setAlpha(0.8f);
        //마커 위치
        marker.setPosition(new LatLng(lat, lng));
        //마커 우선순위
        marker.setZIndex(zlndex);
        //마커 표시
        marker.setMap(naverMap);

    }
    public String getCurrentAddress( double latitude, double longitude) {//위경도를 주소로 변환
        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    100);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();

            return "잘못된 GPS 좌표";
        }
        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "위치 서비스 활성화를 해야합니다.", Toast.LENGTH_LONG).show();

            return "주소 미발견";

        }
        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";
    }
    public void btnClick(View view){

        FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder(String.valueOf(Name),
                                "https://k.kakaocdn.net/14/dn/btrPL304IJJ/nV44NuFxC7XbLZvD9gNP90/o.jpg",
                                LinkObject.newBuilder().setWebUrl("https://developers.kakao.com")
                                        .setMobileWebUrl("https://developers.kakao.com").build())
                        .setDescrption(String.valueOf(startDate+" ~ "+endDate+"\n"+rdnmadr))
                        .build())

                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl("https://developers.kakao.com")
                        .setMobileWebUrl("https://developers.kakao.com")
                        .setAndroidExecutionParams("key2=value2")
                        .setIosExecutionParams("key2=value2")
                        .build()))
                .build();

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");


        KakaoLinkService.getInstance().sendDefault(this, params, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {}

            @Override
            public void onSuccess(KakaoLinkResponse result) {
            }
        });

    }


}

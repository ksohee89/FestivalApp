package com.sample.festival_app;

import static com.sample.festival_app.MainScreen.userid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Likefestival_info extends AppCompatActivity implements OnMapReadyCallback {
    //축제 정보창
    private String user_Name = ""; //userName

    private TextView Name_tv;
    private TextView duration_tv;
    private TextView opar_tv;
    private TextView festivalCo_tv;
    private TextView mnnst_tv;
    private TextView auspcInstt_tv;
    private TextView phoneNumber_tv;
    private TextView homepageUrl_tv;
    private TextView rdnmadr_tv;
    private MapView mapView;
    private static NaverMap naverMap;
    private Marker marker1 = new Marker();
    double lat = 0;
    double lng = 0;//위경도 0으로 초기화
    int image_index = 0;
    ImageView imageview;
    private LikeDBHelper LikeDBHelper;
    private ArrayList<LikeItem> likeItems;
    int count = 0;
    ImageView heartImage;
    private ImageView call;
    private ImageView webview;
    String tel = "";
    String url = "";
    private Button ing_btn;

    private TextView review_user1;
    private TextView review_user2;
    private TextView review_user3;
    private TextView review1;
    private TextView review2;
    private TextView review3;
    private ArrayList<ReviewItem> reviewItems;
    private FirebaseFirestore mStore;

    Pattern pattern = Pattern.compile("^http(.*)$");
    Pattern pattern1 = Pattern.compile("^2022(.*)$");
    Pattern pattern2 = Pattern.compile("^2022(.*)$");
    Pattern pattern3 = Pattern.compile("^(.*)-(.*)$");
    Pattern pattern4 = Pattern.compile("^(.*).(.*)$");
    Pattern pattern5 = Pattern.compile("^(.*).(.*)$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.like_festival_info);

        user_Name = userid;
        heartImage = findViewById(R.id.heartImage);
        LikeDBHelper = new LikeDBHelper(getApplicationContext());


        Name_tv = findViewById(R.id.Name_tv);
        opar_tv = findViewById(R.id.opar_tv);
        duration_tv = findViewById(R.id.duration_tv);
        festivalCo_tv = findViewById(R.id.festivalCo_tv);
        mnnst_tv = findViewById(R.id.mnnst_tv);
        phoneNumber_tv = findViewById(R.id.phoneNumber_tv);
        homepageUrl_tv = findViewById(R.id.homepageUrl_tv);
        rdnmadr_tv = findViewById(R.id.rdnmadr_tv);
        TextView review1_btn = findViewById(R.id.review1_btn);
        ImageView review2_btn = findViewById(R.id.review2_btn);
        ImageView review3_btn = findViewById(R.id.review3_btn);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        imageview = findViewById(R.id.noimage);
        mapView = (MapView) findViewById(R.id.map);
        call = findViewById(R.id.call_icon);
        webview = findViewById(R.id.homepage_icon);
        ing_btn=findViewById(R.id.ing_btn);


        Intent intent = getIntent();
        String Name = intent.getStringExtra("fstvlName");
        likeItems = LikeDBHelper.LikeList(Name);
        String opar = likeItems.get(0).getOpar();
        String startDate = likeItems.get(0).getStartDate();
        String endDate = likeItems.get(0).getEndDate();
        String festivalCo = likeItems.get(0).getFestivalCo();
        String mnnst = likeItems.get(0).getMnnst();
        String phoneNumber = likeItems.get(0).getPhoneNumber();
        String homepage = likeItems.get(0).getHomepage();
        String rdnmadr = likeItems.get(0).getRdnmadr();
        String longitude = likeItems.get(0).getLongitude();
        String latitude = likeItems.get(0).getLatitude();
        String latitude_real = null;
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


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);

        Name_tv.setText(Name);
        duration_tv.setText(startDate + "~" + endDate);
        opar_tv.setText(opar);
        rdnmadr_tv.setText(rdnmadr);
        mnnst_tv.setText(mnnst);
        festivalCo_tv.setText(festivalCo);

        int compare=startDate.compareTo(getTime);
        int compare2=endDate.compareTo(getTime);
        if(compare<=0&&compare2>=0){
            ing_btn.setVisibility(View.VISIBLE);
        }


        Matcher matcher1 = pattern1.matcher(longitude);
        Matcher matcher4 = pattern4.matcher(longitude);
        if (!matcher1.find()) {
            if (matcher4.find()) {
                longitude_real = longitude;
                lng = Double.parseDouble(longitude_real);
            }

        }
        Matcher matcher2 = pattern2.matcher(latitude);
        Matcher matcher5 = pattern5.matcher(latitude);
        if (!matcher2.find()) {
            if (matcher5.find()) {
                latitude_real = latitude;
                lat = Double.parseDouble(latitude_real);
            }

        }

        if (lat == 0 || lng == 0) {//위도 또는 경도 정보가 없으면
            image_index = 1;
            change();
        } else {//위경도 정보가 있으면
            image_index = 0;
            change();
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }


        Matcher matcher3 = pattern3.matcher(phoneNumber);
        int compare3= phoneNumber.compareTo(rdnmadr);
        if ((phoneNumber != homepage) && (compare3!=0)) {
            if (matcher3.find()) {
                phoneNumber_tv.setText(phoneNumber);
                tel = phoneNumber;
            }
        }
        Matcher matcher = pattern.matcher(homepage);
        if (matcher.find()) {
            homepageUrl_tv.setText(homepage);
            url = homepage;
        }

        review1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), review.class);
                intent.putExtra("fstvlName",Name);
                startActivity(intent);
            }
        });
        review2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), review.class);
                intent.putExtra("fstvlName",Name);
                startActivity(intent);
            }
        });
        review3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), review.class);
                intent.putExtra("fstvlName",Name);
                startActivity(intent);
            }
        });

        if (!(tel.isEmpty())) {
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + tel));
                    startActivity(intent);
                }
            });
        }
        if (!(url.isEmpty())) {
            webview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Likefestival_info.this, Webview.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            });
        }

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
        CameraPosition cameraPosition = new CameraPosition(new LatLng(lat,lng),9);
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
}

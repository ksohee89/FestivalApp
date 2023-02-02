package com.sample.festival_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class festival_map extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private ImageView backbtn;
    private ImageView findmapbtn;
    private ImageView mylocationbtn;

    private static NaverMap naverMap;
    private Marker marker1 = new Marker();
    private Marker marker2 = new Marker();
    double lat=0;
    double lng=0;//위경도 0으로 초기화
    double slat,slng;
    int image_index=0;
    ImageView imageview;
    int count;



    Pattern pattern1=Pattern.compile("^2022(.*)$");
    Pattern pattern2=Pattern.compile("^2022(.*)$");
    Pattern pattern4=Pattern.compile("^(.*).(.*)$");
    Pattern pattern5=Pattern.compile("^(.*).(.*)$");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.festival_map);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        imageview=findViewById(R.id.noimage);
        findmapbtn=findViewById(R.id.findmapbtn);
        mylocationbtn=findViewById(R.id.mylocationbtn);

        backbtn=findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mylocationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    onMapReady2(naverMap);
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                                new LatLng(slat, slng),15)
                        .animate(CameraAnimation.Fly, 3000);

                naverMap.moveCamera(cameraUpdate);


            }
        });


        GpsTracker gpsTracker = new GpsTracker(festival_map.this);
        slat=gpsTracker.getLatitude();
        slng=gpsTracker.getLongitude();
        String sname = getCurrentAddress(slat, slng);

        Intent intent=getIntent();
        lat=intent.getDoubleExtra("lat",0);
        lng=intent.getDoubleExtra("lng",0);

        String rdnmadr =intent.getStringExtra("rdnmadr");

        findmapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });





        if(lat==0 || lng == 0){//위도 또는 경도 정보가 없으면
            image_index=1;
            change();
        }else{//위경도 정보가 있으면
            image_index=0;
            change();
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
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
    public void onMapReady(@NonNull NaverMap naverMap) {//목적지마크
        this.naverMap= naverMap;
        CameraPosition cameraPosition = new CameraPosition(new LatLng(lat,lng),9);
        naverMap.setCameraPosition(cameraPosition);
        setMark(marker1,lat,lng, R.drawable.ic_baseline_place_24,3);//핀 지도에 나타내기
    }


    public void onMapReady2(NaverMap naverMap){//내위치마크
        this.naverMap= naverMap;
        CameraPosition cameraPosition2 = new CameraPosition(new LatLng(lat,lng),9);
        naverMap.setCameraPosition(cameraPosition2);

        setMark(marker2,slat,slng, R.drawable.mylocation,3);//핀 지도에 나타내기

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
}

package com.sample.festival_app;

import static com.sample.festival_app.LoginActivity.loginState;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NewPagerAdpator fragmentPagerAdapter;
    private FirebaseUser user;
    private DatabaseReference reference;
    public static String uid, username, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText et;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        DrawerLayout layout_drawer = (DrawerLayout) findViewById(R.id.layout_drawer);
        fragmentPagerAdapter = new NewPagerAdpator(getSupportFragmentManager());
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        ImageView btn_navi = (ImageView) findViewById(R.id.btn_navi);
        Button search_btn=findViewById(R.id.search_button);
        et=(EditText)findViewById(R.id.search);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=et.getText().toString();
                Intent intent=new Intent(MainScreen.this, Search.class);
                intent.putExtra("search_str",str);
                startActivity(intent);
                et.setText(null);
            }
        });

        if (loginState) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            uid = user.getUid();

            reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);

                    if (userProfile != null) {
                        userid = userProfile.userId;
                        username = userProfile.userName;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        btn_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginState) {
                    TextView userName = (TextView) findViewById(R.id.userName);
                    TextView userEmail = (TextView) findViewById(R.id.Email);
                    userName.setText(username);
                    userEmail.setText(userid);
                }
                layout_drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navi_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//   }

    @Override
    public void onBackPressed() {
        DrawerLayout layout_drawer=(DrawerLayout)findViewById(R.id.layout_drawer);
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Login) {
            if (!loginState) {
                startActivity(new Intent(this, LoginActivity.class));
            }else{
                Toast.makeText(MainScreen.this, "이미 로그인 상태입니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.scheduler) {
            if(!loginState) {
                Toast.makeText(MainScreen.this, "로그인 후 이용가능합니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent=new Intent(MainScreen.this, Scheduler.class);
                startActivity(intent);
            }
        }
        else if (id == R.id.MyPage) {
            if(!loginState) {
                Toast.makeText(MainScreen.this, "로그인 후 이용가능합니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                startActivity(new Intent(this, MyPageActivity.class));
            }
        }
        else if (id == R.id.Logout) {
            if (loginState) {
                FirebaseAuth.getInstance().signOut();
                loginState = false;
                userid=null;
                TextView userName = (TextView) findViewById(R.id.userName);
                TextView userEmail = (TextView) findViewById(R.id.Email);
                userName.setText("GUEST");
                userEmail.setText("로그인 해주세요.");
                Toast.makeText(MainScreen.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainScreen.this, "로그인 상태가 아닙니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.Assign) {
            if(!loginState) {
                startActivity(new Intent(this, SignUpActivity.class));
            }
            else {
                Toast.makeText(MainScreen.this, "로그아웃 후 이용해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}
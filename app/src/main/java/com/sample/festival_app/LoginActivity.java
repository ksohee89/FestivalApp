package com.sample.festival_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static boolean loginState = false;

    private TextView signup, forgotPassword;
    private EditText emailId, password;
    private Button loginBtn, cancelBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signup = (TextView) findViewById(R.id.login_signup);
        signup.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.login_searchPw);
        forgotPassword.setOnClickListener(this);

        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(this);

        cancelBtn = (Button) findViewById(R.id.login_cancel);
        cancelBtn.setOnClickListener(this);

        emailId = (EditText) findViewById(R.id.login_id);
        emailId.setOnKeyListener(new View.OnKeyListener() {//id입력 후 키보드 내리기
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(LoginActivity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(emailId.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        password = (EditText) findViewById(R.id.login_pw);

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_signup:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.login_searchPw:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
            case R.id.login_cancel:
                finish();
                break;
        }
    }

    private void userLogin() {
        String eid = emailId.getText().toString().trim();
        String pw = password.getText().toString().trim();

        if(eid.isEmpty()){
            emailId.setError("아이디를 입력해주세요.");
            emailId.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(eid).matches()){
            emailId.setError("유효한 이메일을 입력해주세요.");
            emailId.requestFocus();
            return;
        }

        if(pw.isEmpty()){
            password.setError("비밀번호를 입력해주세요.");
            password.requestFocus();
            return;
        }

        if(pw.length()<8){
            password.setError("비밀번호는 최소 8자 이상입니다.");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(eid,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    loginState = true;
                    startActivity(new Intent(LoginActivity.this, MainScreen.class));
                }else{
                    Toast.makeText(LoginActivity.this,"아이디나 비밀번호를 다시 확인해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

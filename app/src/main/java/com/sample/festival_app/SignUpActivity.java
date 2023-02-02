package com.sample.festival_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputId, inputPw, inputPwCheck, inputName, inputTel;
    private Button signupBtn, cancelBtn;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth = FirebaseAuth.getInstance();

        signupBtn = (Button) findViewById(R.id.signup);
        signupBtn.setOnClickListener(this);

        cancelBtn = (Button) findViewById(R.id.signup_cancel);
        cancelBtn.setOnClickListener(this);
        inputId= (EditText) findViewById(R.id.sign_id);
        inputPw = (EditText) findViewById(R.id.sign_pw);
        inputPwCheck = (EditText) findViewById(R.id.sign_pw2);
        inputName = (EditText) findViewById(R.id.sign_name);
        inputTel = (EditText) findViewById(R.id.sign_tel);

        mStore = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                registerUser();
                break;
            case R.id.signup_cancel:
                startActivity(new Intent(this, MainScreen.class));
                break;
        }
    }

    private void registerUser() {
        String id = inputId.getText().toString().trim();
        String pw = inputPw.getText().toString().trim();
        String pwCheck = inputPwCheck.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String Tel = inputTel.getText().toString().trim();
        int point = 50;

        if (id.isEmpty()) {
            inputId.setError("이메일(아이디)를 입력해주세요.");
            inputId.requestFocus();
            return;
        }

        if (pw.isEmpty()) {
            inputPw.setError("비밀번호를 입력해주세요.");
            inputPw.requestFocus();
            return;
        }

        if (pwCheck.isEmpty()){
            inputPwCheck.setError("비밀번호 확인을 입력해주세요.");
            inputPwCheck.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            inputName.setError("이름을 입력해주세요.");
            inputName.requestFocus();
            return;
        }

        if (Tel.isEmpty()) {
            inputTel.setError("전화번호를 입력해주세요.");
            inputTel.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(id).matches()){
            inputId.setError("이메일 형식에 맞게 입력해주세요");
            inputId.requestFocus();
            return;
        }

        if(pw.length() < 8) {
            inputPw.setError("비밀번호는 최소 8자 이상이여야 합니다.");
            inputPw.requestFocus();
            return;
        }

        if(!pw.equals(pwCheck)) {
            inputPwCheck.setError("비밀번호 확인 칸의 입력값과 비밀번호 칸의 입력값이 같아야 합니다.");
            inputPwCheck.requestFocus();
            return;
        }

        if(Tel.contains("-")) {
            inputTel.setError("전화번호는 숫자만 입력해주세요.");
            inputTel.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(id,pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(id,pw,name,Tel,point);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task){
                                    if(task.isSuccessful()) {
                                        Map<String, Object> data = new HashMap<>();
                                        data.put("festival", "포인트 사용 축제");
                                        mStore.collection(id).document("포인트 사용 축제").set(data);
                                        Toast.makeText(SignUpActivity.this, "회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUpActivity.this,MainScreen.class));
                                    }
                                    else {
                                        Toast.makeText(SignUpActivity.this,"회원가입에 실패했습니다.\n 다시 시도해주세요.",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });

    }
}
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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailText;
    private Button sendEmailButton, cancelButton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw_change);

        emailText = (EditText) findViewById(R.id.pwChange_email);
        sendEmailButton = (Button) findViewById(R.id.pwChange);
        cancelButton = (Button) findViewById(R.id.pwChange_cancel);

        auth = FirebaseAuth.getInstance();

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void resetPassword() {
        String email = emailText.getText().toString().trim();

        if(email.isEmpty()){
            emailText.setError("이메일을 입력해주세요.");
            emailText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("이메일 형식이 잘못되었습니다.");
            emailText.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "비밀번호 재설정 이메일이 전송되었습니다.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(ForgotPasswordActivity.this,"이메일 전송에 실패했습니다.\n이메일을 다시 확인해주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
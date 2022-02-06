package com.example.onschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        EditText tb_namaDepan = findViewById(R.id.tbx_nama_depan);
        EditText tb_namaBelakang = findViewById(R.id.tbx_nama_belakang);
        EditText tb_email = findViewById(R.id.tbx_email);
        EditText tb_username = findViewById(R.id.tbx_username);
        EditText tb_password = findViewById(R.id.tbx_password_signup);

        Button btn_register = findViewById(R.id.btn_register);

        String email = tb_email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.matches(emailPattern)){
                    Toast.makeText(SignupActivity.this, "Match\n" + email, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignupActivity.this, "No Match\n" + email, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

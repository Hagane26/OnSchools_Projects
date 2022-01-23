package com.example.onschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        TextView tv_username = findViewById(R.id.nama_user_layout_home);

        HashMap<String,String> user = sessionManager.getUserDetail();
        String d_name = user.get(sessionManager.user_name);

        tv_username.setText(d_name);
    }
}

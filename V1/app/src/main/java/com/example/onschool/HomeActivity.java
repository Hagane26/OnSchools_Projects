package com.example.onschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lokakarya);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        TextView tv_username = findViewById(R.id.nama_user_layout_home);
        Button btn_mapel_ips = findViewById(R.id.btn_mapel_IPS);

        HashMap<String,String> user = sessionManager.getUserDetail();
        String d_name = user.get(sessionManager.user_name);

        tv_username.setText(d_name);

        btn_mapel_ips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logOut();
            }
        });
    }
}

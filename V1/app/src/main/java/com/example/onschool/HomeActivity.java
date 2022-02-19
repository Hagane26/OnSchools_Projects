package com.example.onschool;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btn_mapel_ips = findViewById(R.id.btn_mapel_IPS);

        //navigation define btns
        Button btn_home = findViewById(R.id.btn_homeOnschools);
        Button btn_kelas = findViewById(R.id.btn_kelas);
        Button btn_forum = findViewById(R.id.btn_forum);
        Button btn_materi = findViewById(R.id.btn_materi);
        Button btn_settting = findViewById(R.id.btn_setting);
        //--------------------------------------

        HashMap<String,String> user = sessionManager.getUserDetail();
        String d_name = user.get(sessionManager.user_name);

        tv_username.setText(d_name);

        btn_mapel_ips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logOut();
            }
        });

        //Navigation code menu
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Kelas Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Forum Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Materi Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_settting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Setting Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        //---------------------------------------
    }
}

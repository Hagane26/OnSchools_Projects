package com.example.onschool;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        //navigation define btns
        Context ctxnow = ProfileActivity.this;
        Button btn_home = findViewById(R.id.btn_home);
        Button btn_kelas = findViewById(R.id.btn_kelas);
        Button btn_forum = findViewById(R.id.btn_forum);
        Button btn_materi = findViewById(R.id.btn_materi);
        Button btn_settting = findViewById(R.id.btn_setting);
        //--------------------------------------

        Button btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logOut();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logOut();
            }
        });

        //Navigation code menu
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctxnow,HomeActivity.class));
            }
        });

        btn_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctxnow,LokakaryaActivity.class));
            }
        });

        btn_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctxnow, "Materi Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_settting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctxnow,ProfileActivity.class));
            }
        });
        //---------------------------------------

    }
}

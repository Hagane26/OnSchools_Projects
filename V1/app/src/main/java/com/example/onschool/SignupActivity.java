package com.example.onschool;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(SignupActivity.this,
                        tb_namaDepan.getText().toString(),
                        tb_namaBelakang.getText().toString(),
                        tb_email.getText().toString(),
                        tb_username.getText().toString(),
                        tb_password.getText().toString());
            }
        });
    }

    public Boolean EmailValidation(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)){
            return true;
        }else{
            return false;
        }
    }

    public void checkField(Context ctx, String ND, String NB,String email, String Username, String Password){
        if (ND.equals(null)){
            Toast.makeText(ctx, "Field Nama Depan Kosong", Toast.LENGTH_SHORT).show();
        }else{
            if(NB.equals(null)){
                Toast.makeText(ctx, "Field Nama Belakang Kosong", Toast.LENGTH_SHORT).show();
            }else{
                if(EmailValidation(email).equals(false)){
                    Toast.makeText(ctx, "Email tidak Valid", Toast.LENGTH_SHORT).show();
                }else{
                   if (ND.equals(null) || NB.equals(null)){
                       Toast.makeText(ctx, "Field username atau password kosong", Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(ctx, "Berhasil", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        }
    }

    public void jsonParse(Context ctx, String ND,String NB, String email, String Username, String Password){
        String url = "";
        RequestQueue rq = Volley.newRequestQueue(ctx);

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();

                return params;
            }
        };
        rq.add(sr);
    }

}

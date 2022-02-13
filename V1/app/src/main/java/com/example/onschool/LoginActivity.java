package com.example.onschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        sessionManager = new SessionManager(this);

        EditText et_Username = findViewById(R.id.tbx_username_login);
        EditText et_Password = findViewById(R.id.tbx_password_login);
        Button btn_Login = findViewById(R.id.btn_login_layout_login);
        Button btn_Regis = findViewById(R.id.btn_register_layout_login);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse(et_Username.getText().toString(),et_Password.getText().toString(),LoginActivity.this);
                }
        });

        btn_Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }

    private void jsonParse(String in_Username, String in_Password, Context ctx){
        String url = "http://onschool.id/api/login";
        RequestQueue rq = Volley.newRequestQueue(LoginActivity.this);

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Semua Data Respon
                    JSONObject jo = new JSONObject(response);
                    String metaInfo = jo.getString("meta");     // data respon bagian meta
                    String dataInfo = jo.getString("data");     // data respon bagian data

                    JSONObject meta = new JSONObject(metaInfo);         // jadikan object untuk bagian meta
                    JSONObject data = new JSONObject(dataInfo);         // jadikan object untuk bagian data

                    // pengambilan informasi meta
                    String meta_status = meta.getString("status");

                    // pengambilan informasi data


                    // data user dari bagian respon data
                    String data_user = data.getString("user");              // pengambilan data user
                    JSONObject data_user_detail = new JSONObject(data_user);        // pembuatan object untuk user

                    // pengambilan data user
                    Integer data_user_detail_id = data_user_detail.getInt("id");
                    String data_user_detail_nama = data_user_detail.getString("nama");
                    String data_user_detail_kelas = data_user_detail.getString("kelas");
                    String data_user_detail_asalSekolah = data_user_detail.getString("asal_sekolah");
                    String data_user_detail_alamat = data_user_detail.getString("alamat");
                    String data_user_detail_role = data_user_detail.getString("role");
                    String data_user_detail_email = data_user_detail.getString("email");
                    String data_user_detail_photoUrl = data_user_detail.getString("photo_url");

                    if (meta_status.equals("success")){

                        sessionManager.createSession(String.valueOf(data_user_detail_id),
                                data_user_detail_nama,
                                data_user_detail_kelas,
                                data_user_detail_asalSekolah,
                                data_user_detail_alamat,
                                data_user_detail_role,
                                data_user_detail_email,
                                data_user_detail_photoUrl);

                        startActivity(new Intent(ctx,HomeActivity.class));
                        Toast.makeText(ctx,"Login Status : " + meta_status + "\n Selamat Datang " + data_user_detail_nama,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ctx,"Request Error : \n" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 500 ){
                    Toast.makeText(ctx, "Login Status : Gagal \n Username atau Password Salah", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ctx, "Connection Error : \n" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",in_Username);
                params.put("password",in_Password);
                return params;
            }
        };
        rq.add(sr);
    }

}

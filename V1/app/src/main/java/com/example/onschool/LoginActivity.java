package com.example.onschool;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    final int[] out_code = new int[1];
    final String[] out_status = new String[1];
    final String[] out_message = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        EditText et_Username = findViewById(R.id.tbx_username_login);
        EditText et_Password = findViewById(R.id.tbx_password_login);
        Button btn_Login = findViewById(R.id.btn_login_layout_login);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse(et_Username.getText().toString(),et_Password.getText().toString(),LoginActivity.this);
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
                    JSONObject jo = new JSONObject(response);
                    String meta = jo.getString("meta");
                    jo = new JSONObject(meta);
                    String status = jo.getString("status");
                    out_status[0] = jo.getString("status");
                    out_message[0] = jo.getString("message");
                    Toast.makeText(ctx, "" + out_status[0] + "\n" + out_message[0], Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("email",in_Username);
                params.put("password",in_Password);
                return params;
            }
        };
        rq.add(sr);
    }

}

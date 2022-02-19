package com.example.onschool;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextPaint;
import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 201;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        EditText tb_namaDepan = findViewById(R.id.tbx_nama_depan);
        EditText tb_namaBelakang = findViewById(R.id.tbx_nama_belakang);
        EditText tb_email = findViewById(R.id.tbx_email);
        EditText tb_password = findViewById(R.id.tbx_password_signup);

        Button btn_register = findViewById(R.id.btn_register);
        SignInButton signInButtonGoogle = findViewById(R.id.btn_google);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(SignupActivity.this,
                        tb_namaDepan.getText().toString(),
                        tb_namaBelakang.getText().toString(),
                        tb_email.getText().toString(),
                        tb_password.getText().toString());
            }
        });

        signInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(SignupActivity.this, gso);
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Toast.makeText(SignupActivity.this, "-> " + account.getEmail().toString() + "\n" + account.getDisplayName().toString() , Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "signInResult:failed code=" + e.getMessage());
        }
    }

    public Boolean EmailValidation(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)){
            return true;
        }else{
            return false;
        }
    }

    public void checkField(Context ctx, String ND, String NB,String email,  String Password){
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
                       jsonParse(ctx,ND,NB,email,Password);
                   }
                }
            }
        }
    }

    public void jsonParse(Context ctx, String ND,String NB, String email,  String Password){
        String url = "http://onschool.id/api/register";
        RequestQueue rq = Volley.newRequestQueue(ctx);

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

                    Toast.makeText(ctx, "Register " + meta_status, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ctx,LoginActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("nama",ND + " " + NB);
                params.put("asal_sekolah","t");
                params.put("alamat","t");
                params.put("email",email);
                params.put("password",Password);
                return params;
            }
        };
        rq.add(sr);
    }

}

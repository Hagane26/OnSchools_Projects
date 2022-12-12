package id.onschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor, BegginEditor;
    public Context ctx;
    int PRIVATE_MODE = 0;

    private static final String BegginApp = "BegginApp";

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";

    public static final String user_id = "ID";
    public static final String user_name = "NAME";
    public static final String user_kelas = "KELAS";
    public static final String user_asalSekolah = "ASAL_SEKOLAH";
    public static final String user_alamat = "ALAMAT";
    public static final String user_role = "ROLE";
    public static final String user_email = "EMAIL";
    public static final String user_photoUrl = "PHOTO_URL";

    public SessionManager(Context ctx){
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
        BegginEditor = sharedPreferences.edit();
    }

    public void createSession(String data_id, String data_name, String data_kelas,
                              String data_asalSekolah, String data_alamat, String data_role,
                              String data_email,String data_photoUrl){

        editor.putBoolean(LOGIN,true);
        editor.putString(user_id,data_id);
        editor.putString(user_name,data_name);
        editor.putString(user_kelas,data_kelas);
        editor.putString(user_asalSekolah,data_asalSekolah);
        editor.putString(user_alamat,data_alamat);
        editor.putString(user_role,data_role);
        editor.putString(user_email,data_email);
        editor.putString(user_photoUrl,data_photoUrl);
        editor.apply();
    }

    public boolean isFirst() {return sharedPreferences.getBoolean(BegginApp,true);}

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if(!this.isFirst()){
            if(!this.isLoggin()){
                ctx.startActivity(new Intent(ctx,LoginActivity.class));
                ((HomeActivity)ctx).finish();
            }
        }else{
            ctx.startActivity(new Intent(ctx,MainActivity.class));
            BegginEditor.putBoolean(BegginApp,false);
            BegginEditor.apply();
        }
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(user_id,sharedPreferences.getString(user_id,"0"));
        user.put(user_name,sharedPreferences.getString(user_name,null));
        user.put(user_kelas,sharedPreferences.getString(user_kelas,null));
        user.put(user_asalSekolah,sharedPreferences.getString(user_asalSekolah,null));
        user.put(user_alamat,sharedPreferences.getString(user_alamat,null));
        user.put(user_role,sharedPreferences.getString(user_role,null));
        user.put(user_email,sharedPreferences.getString(user_email,null));
        user.put(user_photoUrl,sharedPreferences.getString(user_photoUrl,null));
        return user;
    }

    public void logOut(){
        editor.clear();
        editor.commit();
        ctx.startActivity(new Intent(ctx,LoginActivity.class));
        ((HomeActivity)ctx).finish();
    }
}

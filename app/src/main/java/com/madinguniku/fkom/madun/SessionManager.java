package com.madinguniku.fkom.madun;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;

/**
 * Created by heartfilia on 02/10/18.
 */

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    FragmentTransaction fragmentTransaction;

    HomeFragment homeFragment;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";

    public static final String ID_USER = "ID_USER";
    public static final String NAMA = "NAMA";

    public static final String LEVEL = "LEVEL";
    public static final String ID_PRODI = "ID_PRODI";


    public SessionManager(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id_user, String nama, String level, String id_prodi)
    {
        editor.putBoolean(LOGIN , true);
        editor.putString(ID_USER, id_user);
        editor.putString(NAMA, nama);
        editor.putString(LEVEL, level);
        editor.putString(ID_PRODI, id_prodi);

        editor.apply();
    }

    public boolean isLogin()
    {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin()
    {
        if (!this.isLogin())
        {
            Intent i = new Intent(context , LoginActivity.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail()
    {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID_USER, sharedPreferences.getString(ID_USER, null));
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(LEVEL, sharedPreferences.getString(LEVEL, null));
        user.put(ID_PRODI, sharedPreferences.getString(ID_PRODI, null));

        return user;
    }

    public void logout()
    {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((MainActivity) context).finish();
    }

    public void logout_dosen()
    {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginDosenActivity.class);
        context.startActivity(intent);
        ((MainActivity) context).finish();
    }

}

package com.example.sayac;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.camera2.CaptureRequest;

public class SharedPreps {
    private static SharedPreps girdi;
    public int sayac=0;
    public int ustlimit;
    public int altlimit;
    public boolean ses;
    public boolean titreşim;
    private static final String KEY_COUNTERVALUE="CounterValue";
    private static final String KEY_USTLIMIT="UstLimit";
    private static final String KEY_ALTLIMIT="AltLimit";
    private static final String KEY_TITRESİM="Titresim";
    private static final String KEY_SES="Ses";

    private SharedPreferences sharedPreps;

    private SharedPreps(Context context){
        sharedPreps=context.getSharedPreferences(context.getApplicationContext().getPackageName(),Context.MODE_PRIVATE);
        loadValues();
    }
    public static SharedPreps getInstance(Context context){
        if (girdi==null){
            girdi = new SharedPreps(context);
        }
        return girdi;
    }
    private void loadValues(){
        sayac=sharedPreps.getInt(KEY_COUNTERVALUE,0);
        ustlimit=sharedPreps.getInt(KEY_USTLIMIT,20);
        altlimit=sharedPreps.getInt(KEY_ALTLIMIT,0);
        ses=sharedPreps.getBoolean(KEY_SES,true);
        titreşim=sharedPreps.getBoolean(KEY_TITRESİM,true);
    }
    public void saveValues(){
        SharedPreferences.Editor editor=sharedPreps.edit();
        editor.putInt(KEY_COUNTERVALUE,sayac);
        editor.putInt(KEY_USTLIMIT,ustlimit);
        editor.putInt(KEY_ALTLIMIT,altlimit);
        editor.putBoolean(KEY_SES,ses);
        editor.putBoolean(KEY_TITRESİM,titreşim);
        editor.commit();
    }

}

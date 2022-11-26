package com.example.sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Vibrator titresim=null;
    MediaPlayer ses=null;
    TextView yazi;
    String yazdir="";
    SharedPreps sharedPreps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context=getApplicationContext();
        sharedPreps= SharedPreps.getInstance(this);
        titresim=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        ses= MediaPlayer.create(this,R.raw.ses);
        yazi=findViewById(R.id.textView);//Text view tanımlandı
    }

    public void butontıklama(View view) {
        switch (view.getId()){
            case R.id.asagibuton        :   sayacazalt();  sharedPreps.sayac--;     break;
            case R.id.yukaributon       :   sayacarttir();  sharedPreps.sayac++;    break;
            case R.id.ayarlar           :   Intent intent=new Intent(MainActivity.this,Ayarlar.class);
            startActivity(intent);
            break;
        }
    }


    private void sayacarttir() {
        if (sharedPreps.sayac>=sharedPreps.ustlimit){
            sharedPreps.sayac= sharedPreps.ustlimit;
            if (sharedPreps.ses){
                SesCikar();
            }
            if (sharedPreps.titreşim){
                Titresim();
            }
        }
        yazdir=Integer.toString(sharedPreps.sayac);
        textyazdir(yazdir);
    }

    private void textyazdir(String s) {
        yazi.setText(yazdir);
    }

    private void sayacazalt() {
        if (sharedPreps.sayac<=sharedPreps.altlimit){
            sharedPreps.sayac= sharedPreps.altlimit;
            if (sharedPreps.ses){
                SesCikar();
            }
            if (sharedPreps.titreşim){
                Titresim();
            }
        }
        yazdir=Integer.toString(sharedPreps.sayac);
        textyazdir(yazdir);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action,keycode;
        action = event.getAction();
        keycode = event.getKeyCode();
        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP: {
                if (KeyEvent.ACTION_UP==action) {
                    sharedPreps.sayac= sharedPreps.sayac+5;
                    if (sharedPreps.sayac>=sharedPreps.ustlimit){
                        sharedPreps.sayac= sharedPreps.ustlimit;
                        if (sharedPreps.ses){
                            SesCikar();
                        }
                        if (sharedPreps.titreşim){
                            Titresim();
                        }
                    }
                    yazdir=Integer.toString(sharedPreps.sayac);
                    textyazdir(yazdir);
                }
            }
            break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:{
                if (KeyEvent.ACTION_UP==action){
                    sharedPreps.sayac= sharedPreps.sayac-5;
                    if (sharedPreps.sayac<=sharedPreps.altlimit){
                        sharedPreps.sayac= sharedPreps.altlimit;
                        if (sharedPreps.ses){
                            SesCikar();
                        }
                        if (sharedPreps.titreşim){
                            Titresim();
                        }
                    }
                    yazdir=Integer.toString(sharedPreps.sayac);
                    textyazdir(yazdir);
                }
            }
            break;
        }
        return super.dispatchKeyEvent(event);

    }
    private void SesCikar(){
        ses.seekTo(0);
        ses.start();
    }
    private void Titresim(){

        if (!titresim.hasVibrator()){
            return;
        }
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            titresim.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));
            Log.d("Mesaj","Titreşim çalıştı");
        }else{
            titresim.vibrate(1000);
            Log.d("Mesaj","Titreşim çalıştı");
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreps.saveValues();

    }

    @Override
    protected void onResume() {
        super.onResume();
        yazdir=Integer.toString(sharedPreps.sayac);
        textyazdir(yazdir);
    }
}
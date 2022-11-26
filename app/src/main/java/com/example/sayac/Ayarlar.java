package com.example.sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Ayarlar extends AppCompatActivity {

    EditText pozitif;
    EditText negatif;
    Switch titresimcheck;
    Switch ses;
    SharedPreps sharedPreps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        titresimcheck=(Switch)findViewById(R.id.switch2);
        ses=(Switch)findViewById(R.id.switch1);
        sharedPreps=SharedPreps.getInstance(this);
        ses=(Switch) findViewById(R.id.switch2);
        titresimcheck=(Switch) findViewById(R.id.switch2);
        ses.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreps.ses=b;
            }
        });

        titresimcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreps.titreşim=b;
            }
        });
    }
    public void ButonTıklamaAyarlar(View view) {
        pozitif=findViewById(R.id.sayigir);
        negatif=findViewById(R.id.eksisinirsayigir2);

        switch (view.getId()){
            case R.id.Geri      :   Intent intent=new Intent(Ayarlar.this,MainActivity.class);
            startActivity(intent);
            break;
            case R.id.kaydet            :   Kaydet(pozitif);     break;
            case R.id.kaydet2           :   KaydetNegatif(negatif); break;
        }
    }

    private void KaydetNegatif(EditText negatif) {
    }

    private void Kaydet(EditText i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        pozitif.setText(String.valueOf(sharedPreps.ustlimit));
        negatif.setText(String.valueOf(sharedPreps.altlimit));
        ses.setChecked(sharedPreps.ses);
        titresimcheck.setChecked(sharedPreps.titreşim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreps.saveValues();
    }
}
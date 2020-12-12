package com.cibertec.appdocente.appdocentecibertec;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent x= new Intent(InicioActivity.this, LoginDocenteActivity.class);
                startActivity(x);
            }
        }, 5000);
    }
}

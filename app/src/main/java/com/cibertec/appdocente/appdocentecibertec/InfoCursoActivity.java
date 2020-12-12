package com.cibertec.appdocente.appdocentecibertec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.appdocente.appdocentecibertec.Model.Cursos;
import com.cibertec.appdocente.appdocentecibertec.Model.Horario;

import java.util.ArrayList;

public class InfoCursoActivity extends AppCompatActivity {

    TextView tvcodinfocurso,tvnominfocurso,tvcicloinfocurso,tvseccioninfocurso;
    String codclase,codcurso,nomcurso,ciclo,seccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_curso);

        tvcodinfocurso=(TextView)findViewById(R.id.TVICOD_INFO_CURSO);
        tvnominfocurso=(TextView)findViewById(R.id.TVINOM_INFO_CURSO);
        tvcicloinfocurso=(TextView)findViewById(R.id.TVICICLO_INFO_CURSO);
        tvseccioninfocurso=(TextView)findViewById(R.id.TVSECCION_INFO_CURSO);

        Intent recibe = getIntent();
        codclase=recibe.getStringExtra("xcodclase").toString();
        codcurso=recibe.getStringExtra("xcodcurso").toString();
        nomcurso=recibe.getStringExtra("xnomcurso").toString();
        ciclo=recibe.getStringExtra("xciclomatricula").toString();
        seccion=recibe.getStringExtra("xseccurso").toString();

        tvcodinfocurso.setText(codcurso);
        tvnominfocurso.setText("-"+nomcurso);
        tvcicloinfocurso.setText(ciclo);
        tvseccioninfocurso.setText(seccion);

    }

    public void Retroceder(View v){
        Intent i = new Intent(this,CursosDocenteActivity.class);
        startActivity(i);
    }
    public void ListaTemas(View v){
        Intent i= new Intent(this,TemasCursoActivity.class);
        i.putExtra("xcodclase",codclase);
        i.putExtra("xcodCurso",codcurso);
        i.putExtra("xnomcurso",nomcurso);
        i.putExtra("xciclo",ciclo);
        i.putExtra("xseccion",seccion);
        startActivity(i);
    }
    public void ListaAlumnos(View v){
        Intent i= new Intent(this,AlumnosClaseActivity.class);
        i.putExtra("xcodclase",codclase);
        i.putExtra("xcodCurso",codcurso);
        i.putExtra("xnomcurso",nomcurso);
        i.putExtra("xciclo",ciclo);
        i.putExtra("xseccion",seccion);
        startActivity(i);
    }

}

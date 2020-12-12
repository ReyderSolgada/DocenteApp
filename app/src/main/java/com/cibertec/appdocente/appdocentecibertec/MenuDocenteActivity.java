package com.cibertec.appdocente.appdocentecibertec;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.appdocente.appdocentecibertec.Model.Docente;

import java.util.Calendar;

public class MenuDocenteActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Docente objD;
    TextView tvnombre_bienv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_docente);
        tvnombre_bienv=(TextView)findViewById(R.id.TVBIENV_NOMBRE);
        Bienvenida();
        //Fecha();
        /*selectorFecha =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                año=year;
                mes=month;
                dia=dayOfMonth;
            }
        };*/
    }
    public void SalirLogin(View v){
        Intent i = new Intent(this,LoginDocenteActivity.class);
        startActivity(i);
        Variables.usuarioD="";
    }
    public void Ver_Perfil(View v){
        Intent i = new Intent(this,PerfilDocenteActivity.class);
        startActivity(i);
    }
    public void Ver_Horario(View v){
        Intent i = new Intent(this,HorarioDocenteActivity.class);
        startActivity(i);
    }
    public void Ver_Cursos(View v){
        Intent i = new Intent(this,CursosDocenteActivity.class);
        startActivity(i);
    }
    public void EventosCalendario(View v){
        Intent i= new Intent(this,EventosCalendarioActivity.class);
        startActivity(i);
    }
    void Bienvenida(){
        String xcod=Variables.usuarioD;
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String cad_sql="select nomdoc from tb_docente where coddoc='"+xcod+"'";

        Cursor cursor=db.rawQuery(cad_sql,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                objD= new Docente();
                objD.setNomdocente(cursor.getString(0));
            }
            tvnombre_bienv.setText("Hola, "+objD.getNomdocente());
            cursor.close();
            db.close();
        }else{
            Toast.makeText(this, "No se pudo Obtener El nombre", Toast.LENGTH_SHORT).show();
            db.close();
        }

    }
}

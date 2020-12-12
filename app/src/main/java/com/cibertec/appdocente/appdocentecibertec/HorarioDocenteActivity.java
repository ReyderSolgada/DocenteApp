package com.cibertec.appdocente.appdocentecibertec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.appdocente.appdocentecibertec.Adapters.AdaptadorHorario;
import com.cibertec.appdocente.appdocentecibertec.Model.Horario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HorarioDocenteActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lvhorario;
    List<Horario> lista;

    SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
    String[] diasArray= new String[]{"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
    TextView tvfecha, tvdiasemana;
    String fechaAlterable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_docente);

        lvhorario=(ListView)findViewById(R.id.LVHORARIO);
        tvfecha=(TextView)findViewById(R.id.TVFECHA);
        tvdiasemana=(TextView)findViewById(R.id.TVDIA_SEMANA);
        tvfecha.setText(FechaHoy());
        tvdiasemana.setText(NombreDiaSemana());
        fechaAlterable=FechaHoy();

        ListarHorario(fechaAlterable);
        AdaptadorHorario adap= new AdaptadorHorario(this,R.layout.item_clase,lista);
        lvhorario.setAdapter(adap);
    }
    void ListarHorario(String fecha){
        lista=null;
        //String fecha=tvfecha.getText().toString();
        int nrodia=Nrodia(fecha);
        Log.i("Nrodia",nrodia+"");
        String xusu= Variables.usuarioD;
        Log.i("usuario",xusu);
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String cad_sql="select codclase,nomdia,descurso,horainicio,horafin,codaula,nomsede from tb_clase inner join tb_curso on tb_clase.codcurso=tb_curso.codcurso "+
                        "inner join tb_sede on tb_clase.codsede=tb_sede.codsede inner join tbdia_semana on tb_clase.nrodia=tbdia_semana.nrodia "+
                        "where coddoc='"+xusu+"' and tb_clase.nrodia="+nrodia+"";
        Cursor cursor=db.rawQuery(cad_sql,null);

        lista= new ArrayList<Horario>();
        if (cursor.getCount()>0){
            Toast.makeText(this, "Tienes clases", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                Horario x= new Horario();
                x.setCodclase(cursor.getInt(0));
                x.setNombredia(cursor.getString(1));
                x.setDesccurso(cursor.getString(2));
                x.setHorainicio(cursor.getString(3));
                x.setHorafin(cursor.getString(4));
                x.setCodaula(cursor.getString(5));
                x.setNomsede(cursor.getString(6));
                x.setFechaclase(fecha);

                lista.add(x);
            }
            cursor.close();
        }else{
            Toast.makeText(this, "No tienes Clases este Día", Toast.LENGTH_SHORT).show();
            db.close();
        }

    }
    //obtener fecha
    private String FechaHoy(){
        String hoy="";
        Date hoyDato= new Date();
        hoy=formato.format(hoyDato);
        return  hoy;
    }
    //obtener nombre del dia de semana
    private String NombreDiaSemana(){
        String diasemana="";
        Calendar fecha=Calendar.getInstance();
        diasemana=diasArray[fecha.get(Calendar.DAY_OF_WEEK)-1];
        return  diasemana;
    }
    private int Nrodia(String fecha){
        int nrodia=0;
        Date fecD=ConvertirFecha(fecha);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecD);
        nrodia=calendar.get(Calendar.DAY_OF_WEEK);
        return nrodia;
    }
    //convertir fecha cadena a Date
    private Date ConvertirFecha(String fecha){
        Date fechaConvert=null;
        try {
            fechaConvert=formato.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaConvert;
    }

    public void AumentarFecha(View v){
        String fecha=tvfecha.getText().toString();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(ConvertirFecha(fecha));
        calendar.add(Calendar.DAY_OF_YEAR,1);
        fechaAlterable=formato.format(calendar.getTime());
        tvfecha.setText(fechaAlterable);
        String diamod=diasArray[calendar.get(Calendar.DAY_OF_WEEK)-1];
        tvdiasemana.setText(diamod);
        ListarHorario(fechaAlterable);
        AdaptadorHorario adap= new AdaptadorHorario(this,R.layout.item_clase,lista);
        lvhorario.setAdapter(adap);
    }

    public void DisminuirFecha(View v){
        String fecha=tvfecha.getText().toString();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(ConvertirFecha(fecha));
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        fechaAlterable=formato.format(calendar.getTime());
        tvfecha.setText(fechaAlterable);
        String diamod=diasArray[calendar.get(Calendar.DAY_OF_WEEK)-1];
        tvdiasemana.setText(diamod);
        ListarHorario(fechaAlterable);
        AdaptadorHorario adap= new AdaptadorHorario(this,R.layout.item_clase,lista);
        lvhorario.setAdapter(adap);
    }
    public void Menu(View V){
        Intent i =  new Intent(this,MenuDocenteActivity.class);
        startActivity(i);
    }

}

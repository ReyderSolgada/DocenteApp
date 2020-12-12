package com.cibertec.appdocente.appdocentecibertec;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.cibertec.appdocente.appdocentecibertec.Adapters.AdaptadorCalendario;
import com.cibertec.appdocente.appdocentecibertec.Model.Calendario;
import com.cibertec.appdocente.appdocentecibertec.Model.Eventos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventosCalendarioActivity extends AppCompatActivity {
    SQLiteDatabase db;
    List<Eventos> listaEventos;
    //formatos para fecha
    SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatodia=new SimpleDateFormat("dd");
    SimpleDateFormat formatomes=new SimpleDateFormat("MM");
    //arreglos para NombreDias y NombreMeses
    String[] diasArray= new String[]{"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
    String[] mesArray= new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Novienbre","Diciembre"};

    ListView lveventoscalendario;
    TextView tvmes;
    List<Calendario> lista;
    String fecha;
    //variables para poblar la lista
    int meshoy,mes,dia;
    //Variables para la el DatePickerDialog
    int nroaño,nromes,nrodia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_calendario);

        lveventoscalendario=(ListView)findViewById(R.id.LVEVENTOS_CALENDARIO);
        tvmes=(TextView)findViewById(R.id.TVEVENTOS_MES);
        Date hoy=new Date();
        fecha=formato.format(hoy);
        meshoy=Integer.parseInt(formatomes.format(hoy));
        tvmes.setText(mesArray[meshoy-1]);

        poblarLista();
        AdaptadorCalendario adap= new AdaptadorCalendario(this,R.layout.item_calendario,lista);
        lveventoscalendario.setAdapter(adap);

        lveventoscalendario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int xdia=lista.get(position).getDia();
                int xmes=lista.get(position).getMes();
                String xnomdia=lista.get(position).getNombredia();
                String xnommes=lista.get(position).getNombremes();

                Intent envia= new Intent(EventosCalendarioActivity.this,AgregarEventoActivity.class);
                envia.putExtra("xdiaEvento",xdia+"");
                envia.putExtra("xmesEvento",xmes+"");
                envia.putExtra("xnomdiaEvento",xnomdia);
                envia.putExtra("xnommesEvento",xnommes);
                startActivity(envia);
            }
        });

    }
    public void Menu(View v){
        Intent i= new Intent(this,MenuDocenteActivity.class);
        startActivity(i);
    }
    void poblarLista(){
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String coddoc=Variables.usuarioD;
        String cad_sql="select titulo,nromes,nrodia from tbevento_calendario where coddoc='"+coddoc+"'";
        Cursor cursor= db.rawQuery(cad_sql,null);
        listaEventos= new ArrayList<Eventos>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
               Eventos objE= new Eventos();
                objE.setTitulo(cursor.getString(0));
                objE.setNromes(cursor.getInt(1));
                objE.setNrodia(cursor.getInt(2));
                listaEventos.add(objE);
            }
            cursor.close();
            db.close();
        }

        lista=new ArrayList<Calendario>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ConvertirFecha(fecha));

        while (lista.size()<=30){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dia=Integer.parseInt(formatodia.format(calendar.getTime()));
            mes=Integer.parseInt(formatomes.format(calendar.getTime()));
            fecha=formato.format(calendar.getTime());

            Calendario obj = new Calendario();
            obj.setDia(dia);
            obj.setNombredia(diasArray[calendar.get(Calendar.DAY_OF_WEEK)-1]);
            obj.setMes(mes);
            obj.setNombremes(mesArray[calendar.get(Calendar.MONTH)]);
            lista.add(obj);
        }

    }
    private Date ConvertirFecha(String fecha){
        Date fechaConvert=null;
        try {
            fechaConvert=formato.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaConvert;
    }
    //DatePickerDialog
    public void mostrarCalendario(View v){
        Calendar c= Calendar.getInstance();
        nroaño=c.get(Calendar.YEAR);
        nromes=c.get(Calendar.MONTH);
        nrodia=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            fecha=dayOfMonth+"/"+(month+1)+"/"+year;
            fecha=formato.format(ConvertirFecha(fecha));
                poblarLista();
                AdaptadorCalendario adap= new AdaptadorCalendario(EventosCalendarioActivity.this,R.layout.item_calendario,lista);
                lveventoscalendario.setAdapter(adap);
            }
        },nroaño,nromes,nrodia);
        datePickerDialog.show();

    }

}

package com.cibertec.appdocente.appdocentecibertec;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AgregarEventoActivity extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tvfecha_evento,tvhora_inicio,tvhora_fin;
    EditText edt_titulo,edt_ubicacion,edt_descripcion;
    RadioGroup rgTipoalerta;
    RadioButton rb1,rb2,rb3,rb4;
    String dia,mes,nomdia,nommes;
    String tipoalerta="";
    int horaInicio,MinInicio,horaFin,MinFin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);

        tvfecha_evento=(TextView)findViewById(R.id.TVFECHA_NUEVOEVENTO);
        tvhora_inicio=(TextView)findViewById(R.id.TVHORA_INICIO);
        tvhora_fin=(TextView)findViewById(R.id.TVHORA_FIN);
        edt_titulo=(EditText)findViewById(R.id.EDTTITULO_EVENTO);
        edt_ubicacion=(EditText)findViewById(R.id.EDTUBICACION_EVENTO);
        edt_descripcion=(EditText)findViewById(R.id.EDTDETALLE_EVENTO);
        rgTipoalerta=(RadioGroup)findViewById(R.id.RGTIPO_ALERTA);
        rb1=(RadioButton)findViewById(R.id.RB_NINGUNO);
        rb2=(RadioButton)findViewById(R.id.RB_5MIN_ANTES);
        rb3=(RadioButton)findViewById(R.id.RB_30MIN_ANTES);
        rb4=(RadioButton)findViewById(R.id.RB_1HORA_ANTES);

        Intent recibe= getIntent();
        dia=recibe.getStringExtra("xdiaEvento").toString();
        mes=recibe.getStringExtra("xmesEvento").toString();
        nomdia=recibe.getStringExtra("xnomdiaEvento").toString();
        nommes=recibe.getStringExtra("xnommesEvento").toString();

        tvfecha_evento.setText(nomdia+", "+dia+" "+nommes);

        rgTipoalerta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.RB_NINGUNO:
                        tipoalerta="Ninguno";
                        break;
                    case R.id.RB_5MIN_ANTES:
                        tipoalerta="5 Min antes";
                        break;
                    case R.id.RB_30MIN_ANTES:
                        tipoalerta="30 MIn antes";
                        break;
                    case R.id.RB_1HORA_ANTES:
                        tipoalerta="1 Hora antes";
                        break;

                }
            }
        });

    }
    public void Regresar(View v){
        Intent i= new Intent(this,EventosCalendarioActivity.class);
        startActivity(i);
    }
    public void GrabarEvento(View v){
        String coddocente=Variables.usuarioD;
        String titulo=edt_titulo.getText().toString().trim();
        int nrodia=Integer.parseInt(dia);
        int nromes=Integer.parseInt(mes);
        String horInicio=tvhora_inicio.getText().toString();
        String horFin=tvhora_fin.getText().toString();
        String ubicacion=edt_ubicacion.getText().toString().trim();
        String descripcion=edt_descripcion.getText().toString().trim();
        try {
            db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
            ContentValues oRegistro= new ContentValues();
            oRegistro.put("coddoc",coddocente);
            oRegistro.put("titulo",titulo);
            oRegistro.put("nromes",nromes);
            oRegistro.put("nrodia",nrodia);
            oRegistro.put("horainicio",horInicio);
            oRegistro.put("horafin",horFin);
            oRegistro.put("descripcion",descripcion);
            oRegistro.put("ubicacion",ubicacion);
            oRegistro.put("tipoalerta",tipoalerta);

            db.insert("tbevento_calendario",null,oRegistro);
            db.close();
            Toast.makeText(this, "Evento guardado para el día "+tvfecha_evento.getText().toString(), Toast.LENGTH_SHORT).show();

            Intent i= new Intent(this,EventosCalendarioActivity.class);
            startActivity(i);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No se pudo guardar el evento", Toast.LENGTH_SHORT).show();
        }

    }
    public void MostrarHoraInicio(View v){
        Calendar c= Calendar.getInstance();
        horaInicio=c.get(Calendar.HOUR_OF_DAY);
        MinInicio=c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvhora_inicio.setText(hourOfDay+":"+minute);
            }
        },horaInicio,MinInicio,false);
        timePickerDialog.show();
    }
    public void MostrarHoraFin(View v){
        Calendar c= Calendar.getInstance();
        horaFin=c.get(Calendar.HOUR_OF_DAY);
        MinFin=c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvhora_fin.setText(hourOfDay+":"+minute);
            }
        },horaFin,MinInicio,false);
        timePickerDialog.show();
    }
}

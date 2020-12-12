package com.cibertec.appdocente.appdocentecibertec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.appdocente.appdocentecibertec.Adapters.AdaptadorAlumnos;
import com.cibertec.appdocente.appdocentecibertec.Model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnosClaseActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lvalumnos;
    List<Alumno> lista;
    String codclase,codcurso,ciclomatr,nomcurso,seccioncurso;
    TextView tvcodcurso,tvnomcurso,tvseccurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_clase);

        lvalumnos=(ListView)findViewById(R.id.LVALUMNOS_CURSO);
        tvcodcurso=(TextView)findViewById(R.id.TVICOD_ALUM_CURSO);
        tvnomcurso=(TextView)findViewById(R.id.TVINOM_ALUM_CURSO);
        tvseccurso=(TextView)findViewById(R.id.TVSECCION_ALUM_CURSO);

        Intent recibe= getIntent();
        codclase=recibe.getStringExtra("xcodclase").toString();
        codcurso=recibe.getStringExtra("xcodCurso").toString();
        nomcurso=recibe.getStringExtra("xnomcurso").toString();
        ciclomatr=recibe.getStringExtra("xciclo").toString();
        seccioncurso=recibe.getStringExtra("xseccion").toString();

        tvcodcurso.setText(codcurso+"-");
        tvnomcurso.setText(nomcurso);
        tvseccurso.setText(seccioncurso);

        ListarAlumnos();
        AdaptadorAlumnos adap= new AdaptadorAlumnos(this,R.layout.item_alumno,lista);
        lvalumnos.setAdapter(adap);
    }
    public void Regresar(View v){
        Intent i= new Intent(this,InfoCursoActivity.class);
        i.putExtra("xcodclase",codclase);
        i.putExtra("xcodcurso",codcurso);
        i.putExtra("xnomcurso",nomcurso);
        i.putExtra("xciclomatricula",ciclomatr);
        i.putExtra("xseccurso",seccioncurso);
        startActivity(i);
    }
    void ListarAlumnos(){
        int xcod=Integer.parseInt(codclase);
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String cad_sql=" select tb_alumno.codalum,nomalum,apealum,correoalum from tb_alumno inner join tbmatricula_alumno "+
                        " on tb_alumno.codalum=tbmatricula_alumno.codalum where tbmatricula_alumno.codclase="+xcod;
        Cursor cursor=db.rawQuery(cad_sql,null);
        lista= new ArrayList<Alumno>();
        if (cursor.getCount()>0){
            Toast.makeText(this, "Total "+cursor.getCount()+" Alumnos", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                Alumno obj= new Alumno();
                obj.setCodalum(cursor.getString(0));
                obj.setNomalum(cursor.getString(1));
                obj.setApealum(cursor.getString(2));
                obj.setCorreoalum(cursor.getString(3));
                lista.add(obj);
            }
            cursor.close();
            db.close();
        }else{
            Toast.makeText(this, "No hay alumnos", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}

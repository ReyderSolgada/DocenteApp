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

import com.cibertec.appdocente.appdocentecibertec.Adapters.AdaptadorTemasCurso;
import com.cibertec.appdocente.appdocentecibertec.Model.TemasCurso;

import java.util.ArrayList;
import java.util.List;

public class TemasCursoActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lvtemascurso;
    List<TemasCurso> lista;
    String codclase,codcurso,ciclomatr,nomcurso,seccioncurso;
    TextView tvcodcurso,tvnomcurso,tvseccioncurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_curso);

        lvtemascurso=(ListView)findViewById(R.id.LVDETALLE_CURSO);
        tvcodcurso=(TextView)findViewById(R.id.TVICOD_TEMA_CURSO);
        tvnomcurso=(TextView)findViewById(R.id.TVINOM_TEMA_CURSO);
        tvseccioncurso=(TextView)findViewById(R.id.TVSECCION_TEMA_CURSO);

        Intent recibe= getIntent();
        codclase=recibe.getStringExtra("xcodclase").toString();
        codcurso=recibe.getStringExtra("xcodCurso").toString();
        nomcurso=recibe.getStringExtra("xnomcurso").toString();
        ciclomatr=recibe.getStringExtra("xciclo").toString();
        seccioncurso=recibe.getStringExtra("xseccion").toString();

        tvcodcurso.setText(codcurso+"-");
        tvnomcurso.setText(nomcurso);
        tvseccioncurso.setText(seccioncurso);

        ListarTemasCurso();
        AdaptadorTemasCurso adap= new AdaptadorTemasCurso(this,R.layout.item_temas_curso,lista);
        lvtemascurso.setAdapter(adap);
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
    void ListarTemasCurso(){
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        int xcod=Integer.parseInt(codcurso);
        String cad_sql="select codcurso,nrotema,descrtema,evaluacion from tbtemas_curso where codcurso="+xcod+"";
        Cursor cursor=db.rawQuery(cad_sql,null);
        lista=new ArrayList<TemasCurso>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                TemasCurso obj= new TemasCurso();
                obj.setCodcurso(cursor.getInt(0));
                obj.setNrotema(cursor.getInt(1));
                obj.setDestema(cursor.getString(2));
                obj.setDetsemana(cursor.getString(3));
                lista.add(obj);
            }
            cursor.close();
            db.close();
        }else{
            Toast.makeText(this, "No Hay Temas por Mostrar", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}

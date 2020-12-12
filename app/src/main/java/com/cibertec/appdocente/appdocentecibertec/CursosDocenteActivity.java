package com.cibertec.appdocente.appdocentecibertec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cibertec.appdocente.appdocentecibertec.Adapters.AdaptadorCursos;
import com.cibertec.appdocente.appdocentecibertec.Model.Cursos;

import java.util.ArrayList;
import java.util.List;

public class CursosDocenteActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lvcursos;
    List<Cursos> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_docente);

        lvcursos=(ListView)findViewById(R.id.LVCURSOS);

        LitarCursos();
        AdaptadorCursos adap= new AdaptadorCursos(this,R.layout.item_cursos,lista);
        lvcursos.setAdapter(adap);

        lvcursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codClase=lista.get(position).getCodclase();
                int codCurso=lista.get(position).getCodcurso();
                String nomCurso=lista.get(position).getDesccurso();
                String cicloMatr=lista.get(position).getPeriodoMatricula();
                String secCurso=lista.get(position).getSeccion();

                Intent envio= new Intent(CursosDocenteActivity.this,InfoCursoActivity.class);
                envio.putExtra("xcodclase",codClase+"");
                envio.putExtra("xcodcurso",codCurso+"");
                envio.putExtra("xnomcurso",nomCurso);
                envio.putExtra("xciclomatricula",cicloMatr);
                envio.putExtra("xseccurso",secCurso);
                startActivity(envio);
            }
        });
    }


    void LitarCursos(){
        String xusu=Variables.usuarioD;
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String cad_sql="select codclase,tb_clase.codcurso,descurso,periodomatricula,seccion from tb_clase inner join tb_curso on tb_clase.codcurso=tb_curso.codcurso "+
                        "where coddoc='"+xusu+"'";

        Cursor cursor=db.rawQuery(cad_sql,null);
        lista=new ArrayList<Cursos>();
        if (cursor.getCount()>0){
            Toast.makeText(this, "Tus cursos por clases", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                Cursos x = new Cursos();
                x.setCodclase(cursor.getInt(0));
                x.setCodcurso(cursor.getInt(1));
                x.setDesccurso(cursor.getString(2));
                x.setPeriodoMatricula(cursor.getString(3));
                x.setSeccion(cursor.getString(4));

                lista.add(x);
            }
            cursor.close();
        }else{
            Toast.makeText(this, "No tienes Cursos", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    public void Menu(View V){
        Intent i =  new Intent(this,MenuDocenteActivity.class);
        startActivity(i);
    }
}

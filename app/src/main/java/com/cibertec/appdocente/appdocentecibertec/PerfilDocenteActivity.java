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

import com.cibertec.appdocente.appdocentecibertec.Model.Docente;

public class PerfilDocenteActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Docente objD;
    TextView tvnom_docente,tvfecha_nac,tvgenero,tvcelular,tvdireccion,
            tvdistrito,tvnacionalidad,tvcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_docente);

        tvnom_docente=(TextView)findViewById(R.id.TVNOM_DOCENTE);
        tvfecha_nac=(TextView)findViewById(R.id.TVFECHA_NAC);
        tvgenero=(TextView)findViewById(R.id.TVGENERO);
        tvcelular=(TextView)findViewById(R.id.TVCELULAR);
        tvdireccion=(TextView)findViewById(R.id.TVDIRECCION);
        tvdistrito=(TextView)findViewById(R.id.TVDISTRITO);
        tvnacionalidad=(TextView)findViewById(R.id.TVNACIONALIDAD);
        tvcorreo=(TextView)findViewById(R.id.TVCORREO);
        LlenarPerfil();

    }
    public void Menu(View v){
        Intent i= new Intent(this,MenuDocenteActivity.class);
        startActivity(i);
    }

    public void LlenarPerfil(){
        String xcod=Variables.usuarioD;
        Log.i("UsuarioPerfil",xcod);

        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String cad_sql="select coddoc,nomdoc,apedoc,telf,descdistrito,direccion,correo from tb_docente inner join tb_distrito " +
                        "on tb_docente.coddis=tb_distrito.coddis where coddoc='"+xcod+"'";

        String nombre,apellido,celular,distrito,direccion,correo;

        Cursor curs=db.rawQuery(cad_sql,null);
        if (curs.getCount()>0){
            while (curs.moveToNext()){
                objD=new Docente();
                objD.setCoddocente(curs.getString(0));
                objD.setNomdocente(curs.getString(1));
                objD.setApedocente(curs.getString(2));
                objD.setTelfdocente(curs.getString(3));
                objD.setNomdistrito(curs.getString(4));
                objD.setDirecdocente(curs.getString(5));
                objD.setCorreodocente(curs.getString(6));
            }
            curs.close();
            db.close();
            nombre=objD.getNomdocente()+" "+objD.getApedocente();
            celular=objD.getTelfdocente();
            distrito=objD.getNomdistrito();
            direccion=objD.getDirecdocente();
            correo=objD.getCorreodocente();

            tvnom_docente.setText(nombre);
            tvcelular.setText(celular);
            tvdistrito.setText(distrito);
            tvdireccion.setText(direccion);
            tvcorreo.setText(correo);
            tvcorreo.setText(correo);

        }else{
            Toast.makeText(this, "No se pudo Rellenar El perfil", Toast.LENGTH_SHORT).show();
            db.close();
        }


    }
}

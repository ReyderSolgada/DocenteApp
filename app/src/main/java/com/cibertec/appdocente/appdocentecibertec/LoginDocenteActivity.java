package com.cibertec.appdocente.appdocentecibertec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginDocenteActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText edtusuario,edtpassword;
    CheckBox cbx_recordar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_docente);
        CrearBD();
        PoblarBD();
        edtusuario=(EditText)findViewById(R.id.EDTUSUARIO);
        edtpassword=(EditText)findViewById(R.id.EDTCLAVE);
        cbx_recordar=(CheckBox)findViewById(R.id.CKB_RECORDAR_CLAVE);

        cbx_recordar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    Variables.rec_usuario=edtusuario.getText().toString();
                    Variables.rec_password=edtpassword.getText().toString();
                    Variables.estadoCHK=true;
                }else if (isChecked==false){
                    Variables.rec_usuario="";
                    Variables.rec_password="";
                    Variables.estadoCHK=false;
                }
            }
        });

        if(Variables.estadoCHK==true) {
            edtusuario.setText(Variables.rec_usuario);
            edtpassword.setText(Variables.rec_password);
            cbx_recordar.setChecked(true);
        }
    }

    public void Login(View v){
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        String xusuario=edtusuario.getText().toString().trim();
        String xcontra=edtpassword.getText().toString().trim();
        Variables.usuarioD=xusuario;

        String cad_sql="select * from tb_docente where coddoc='"+xusuario+"' and contra='"+xcontra+"'";

        Cursor cursor=db.rawQuery(cad_sql,null);
        int cant=cursor.getCount();
        cursor.close();
        db.close();

        if (cant>0){
            Toast.makeText(this, "Bienvenido Usuario", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(this,MenuDocenteActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Error usuario o clave incorrecta", Toast.LENGTH_SHORT).show();
        }
    }
    //Cerrar aplicacion con el Boton de Retroceso
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            Toast.makeText(this, "Cerrando aplicación", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }
    //Crear BD
    public void CrearBD(){
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);
        db.execSQL(Variables.TB_DISTRITO);
        db.execSQL(Variables.TBDIA_SEMANA);
        db.execSQL(Variables.TB_PROFESION);
        db.execSQL(Variables.TBTIPO_AULA);
        db.execSQL(Variables.TB_CARRERA);
        db.execSQL(Variables.TB_CURSO);
        db.execSQL(Variables.TB_DOCENTE);
        db.execSQL(Variables.TBDETALLE_DOCENTE);
        db.execSQL(Variables.TB_ALUMNO);
        db.execSQL(Variables.TB_SEDE);
        db.execSQL(Variables.TB_AULA);
        db.execSQL(Variables.TBDETALLE_CURSO);
        db.execSQL(Variables.TBTEMAS_CURSO);
        db.execSQL(Variables.TB_CLASE);
        db.execSQL(Variables.TBMATRICULA_ALUMNO);
        db.execSQL(Variables.TBEVENTO_CALENDARIO);
        db.close();
    }
    public void PoblarBD() {
        db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);

        String cad_sql="select * from tb_distrito";
        Cursor cur= db.rawQuery(cad_sql,null);
        int cant=cur.getCount();
        cur.close();
        db.close();
        if (cant<=0) {
            db=openOrCreateDatabase("BD_DOCENTE",MODE_PRIVATE,null);

            db.execSQL("insert into tb_distrito values(101,'SAN JUAN DE LURIGANCHO')");
            db.execSQL("insert into tb_distrito values(102,'MIRAFLORES')");
            db.execSQL("insert into tb_distrito values(103,'INDEPENDENCIA')");
            db.execSQL("insert into tb_distrito values(104,'BREÑA')");
            db.execSQL("insert into tb_distrito values(105,'BELLAVISTA')");
            db.execSQL("insert into tb_distrito values(106,'AREQUIPA')");


            db.execSQL("insert into tbdia_semana values(1,'Domingo')");
            db.execSQL("insert into tbdia_semana values(2,'Lunes')");
            db.execSQL("insert into tbdia_semana values(3,'Martes')");
            db.execSQL("insert into tbdia_semana values(4,'Miercoles')");
            db.execSQL("insert into tbdia_semana values(5,'Jueves')");
            db.execSQL("insert into tbdia_semana values(6,'Viernes')");
            db.execSQL("insert into tbdia_semana values(7,'Sabado')");


            db.execSQL("insert into tb_profesion values('PRO01','Ingeniero de sistemas')");
            db.execSQL("insert into tb_profesion values('PRO02','Administrador de Base de datos')");
            db.execSQL("insert into tb_profesion values('PRO03','Docente de Idiomas')");


            db.execSQL("insert into tbtipo_aula values(1024,'Laboratorio')");
            db.execSQL("insert into tbtipo_aula values(1025,'Aula')");


            db.execSQL("insert into tb_carrera values('CA0001','Computacion e Informatica')");
            db.execSQL("insert into tb_carrera values('CA0002','Administracion y Sistemas')");
            db.execSQL("insert into tb_carrera values('CA0003','Administracion de Redes y Comunicaciones')");
            db.execSQL("insert into tb_carrera values('CA0004','Administracion de Empresas')");
            db.execSQL("insert into tb_carrera values('CA0005','Contabilidad')");
            db.execSQL("insert into tb_carrera values('CA0006','Marketing')");
            db.execSQL("insert into tb_carrera values('CA0007','Administracion de Negocios Internacionales')");
            db.execSQL("insert into tb_carrera values('CA0008','Gestion de Recursos Humanos')");
            db.execSQL("insert into tb_carrera values('CA0009','Diseño Grafico')");
            db.execSQL("insert into tb_carrera values('CA0010','Diseño de Videojuegos')");
            db.execSQL("insert into tb_carrera values('CA0011','Diseño de Interiores')");
            db.execSQL("insert into tb_carrera values('CA0012','Electricidad Industrial')");
            db.execSQL("insert into tb_carrera values('CA0013','Mecatronica Industrial')");
            db.execSQL("insert into tb_carrera values('CA0014','Seguridad y Prevencion de Riesgos')");
            db.execSQL("insert into tb_carrera values('CA0015','Publicidad y Branding')");


            db.execSQL("insert into tb_curso values(2356,'Desarrollo de Aplicaciones MovilesI')");
            db.execSQL("insert into tb_curso values(2357,'Desarrollo de Servicios Web')");
            db.execSQL("insert into tb_curso values(2358,'Desarrollo de Aplicaciones Web')");
            db.execSQL("insert into tb_curso values(2359,'Programacion Orientado a ObjetosI')");
            db.execSQL("insert into tb_curso values(2360,'Programacion Orientado a ObjetosII')");
            db.execSQL("insert into tb_curso values(2361,'Base de Datos AvanzadoI')");
            db.execSQL("insert into tb_curso values(2362,'Base de Datos AvanzadoII')");
            db.execSQL("insert into tb_curso values(2363,'Seguridad de Aplicaciones')");
            db.execSQL("insert into tb_curso values(2364,'Analisis y Diseño de SistemasI')");
            db.execSQL("insert into tb_curso values(2365,'Analisis y Diseño de SistemasII')");
            db.execSQL("insert into tb_curso values(2366,'Lenguaje de ProgramacionII')");
            db.execSQL("insert into tb_curso values(2367,'Lenguaje de ProgramacionI')");
            db.execSQL("insert into tb_curso values(2368,'Algoritmos y Estructura de Datos')");
            db.execSQL("insert into tb_curso values(2369,'Gestion de Servicios de TI')");
            db.execSQL("insert into tb_curso values(2370,'Lenguaje de ProgramacionI')");
            db.execSQL("insert into tb_curso values(2371,'Desarrollo de Entornos Web')");


            db.execSQL("insert into tb_docente values('pjlinan','Julio Cesar','Liñan Rodriguez','956854215',105,'Av. Los Heroes de Canepa 125 Lt9 Mz E','pjlinan@cibertec.pe','Pjuli25')");
            db.execSQL("insert into tb_docente values('phalvarez','Hugo Victor','Alvarez Fernandez','956841236',103,'Av. Los Husares de Junin s/n Lt15 Mz H','phalvarez@cibertec.pe','pHug456')");
            db.execSQL("insert into tb_docente values('pesoria','Elizabeth Michell','Soria Hurdens','987548625',106,'Jr. Palmeras de la rivera 056 Lt7 Mz Z','pesoria@cibertec.pe','pEsor123')");


            db.execSQL("insert into tb_alumno values('i201611387','Sergio Gabriel','Espinoza Cabello',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611387@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201611659','Percy','Huillcapuma Acha',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611659@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201615157','Edgar Benyi','Rivas Figueredo',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201615157@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201616202','Mirko Michael','Bermudez Aybar',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201616202@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201615041','Jose Luis','Quispe Aracayo',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201615041@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201611540','Alex Domingo','Atoche del Rosario',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611540@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201613717','Luis Antonio','Pizarro Leon',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201613717@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201612241','Jose Alberto','Gallardo joaquin',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201612241@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201611208','Leonardo Arturo','Saavedra Correa',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611208@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201612765','Cesar Alenxander','Crispin Rosas',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201612765@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201611027','Alexander','Reyes Guerra',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611027@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201613993','Jorge Miguel','Grabriel Benito',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201613993@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201611550','Angel Danif','Velasquez Gonzales',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611550@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201512690','Nathalia Violeta','Ticsihua Lopez',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201512690@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201612917','Andre Patrick','Chumbes Lizarraga',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201612917@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201613101','Yashmin Cinthya','Salinas Ramirez',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201613101@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201312675','Alexis','Roman Santibañez',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201312675@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201612394','Gerardo','Marin Parra',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201612394@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201615991','Ana Jhoselyn','Chuquivilca Alejo',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201615991@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201611639','Edson Fabricio','Huaman Burgos',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201611639@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201612598','Dante Moises','Tataje Granda',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201612598@cibertec.edu.pe')");
            db.execSQL("insert into tb_alumno values('i201614209','Reyder','Solgada Pino',101,'Av. calle nulla 154 Mz E4 Lt 9','985645230','i201614209@cibertec.edu.pe')");


            db.execSQL("insert into tb_sede values('SE01','San Juan de Lurigancho',101,'Av. Proceres de la Independencia 589')");
            db.execSQL("insert into tb_sede values('SE02','Miraflores',102,'Av. Proceres de la Independencia 589')");
            db.execSQL("insert into tb_sede values('SE03','Independencia',103,'Av. Proceres de la Independencia 589')");
            db.execSQL("insert into tb_sede values('SE04','Breña',101,'Av. Proceres de la Independencia 589')");
            db.execSQL("insert into tb_sede values('SE05','Bellavista',101,'Av. Proceres de la Independencia 589')");
            db.execSQL("insert into tb_sede values('SE06','Arequipa',101,'Av. Proceres de la Independencia 589')");


            db.execSQL("insert into tb_aula values('A101','SE01',1025,80)");
            db.execSQL("insert into tb_aula values('A201','SE01',1024,50)");
            db.execSQL("insert into tb_aula values('A205','SE01',1024,40)");
            db.execSQL("insert into tb_aula values('A207','SE01',1024,40)");
            db.execSQL("insert into tb_aula values('A305','SE01',1024,50)");
            db.execSQL("insert into tb_aula values('A307','SE01',1024,32)");


            db.execSQL("insert into tbtemas_curso values(2356,1,'Plataforma Android (1 horas)','Semana01')");
            db.execSQL("insert into tbtemas_curso values(2356,2,'IDE Android Studio (2 horas)','Semana02')");
            db.execSQL("insert into tbtemas_curso values(2356,3,'Fundamentos de aplicaciones Android (1 hora)','Semana02')");
            db.execSQL("insert into tbtemas_curso values(2356,4,'Componente de una pantalla, Views y ViewGroups','Semana03')");
            db.execSQL("insert into tbtemas_curso values(2356,5,'Componente Intent: definición y características (3 horas)','Primera Evaluacion de Laboratorio(Semana04)')");
            db.execSQL("insert into tbtemas_curso values(2356,6,'Orientación del dispositivo (3 horas)','Semana05,06')");
            db.execSQL("insert into tbtemas_curso values(2356,7,'Dialogos y notificaciones (3 horas)','Segunda Evaluacion de Laboratorio (Semana07)')");
            db.execSQL("insert into tbtemas_curso values(2356,8,'Acceso a recursos y archivos (2 horas)','Semana08,09')");
            db.execSQL("insert into tbtemas_curso values(2356,9,'Persistencia de datos usando SQLite (3 horas)','Tercera Evaluacion de Laboratorio (Semana10)')");
            db.execSQL("insert into tbtemas_curso values(2356,10,'Aplicando el Modelo Vista Controlador con SQLite (3 horas)','Semana11')");
            db.execSQL("insert into tbtemas_curso values(2356,11,'Networking (5 horas)','Cuarta Evaluacion de Laboratorio (Semana12)')");
            db.execSQL("insert into tbtemas_curso values(2356,12,'Consumiendo servicios web (3 horas)','Semana13')");
            db.execSQL("insert into tbtemas_curso values(2356,13,'Servicios basados en localización (2 horas)','Semana13')");
            db.execSQL("insert into tbtemas_curso values(2357,1,'Introducción a ASP.NET MVC (1hrs)','Semana01')");
            db.execSQL("insert into tbtemas_curso values(2357,2,'Arquitectura de ASP.NET MVC (5 hrs)','Semana Virtual 02: Trabajo Practico 1 (1 hora)')");
            db.execSQL("insert into tbtemas_curso values(2357,3,'Interacción con el Modelo de Datos (05 hrs)','Semana03')");
            db.execSQL("insert into tbtemas_curso values(2357,4,'Manejo de Vistas (3 hrs)','Semana 04: Evaluación de Laboratorio01 (01 hora)')");
            db.execSQL("insert into tbtemas_curso values(2357,5,'Arquitectura “N” Capas orientadas al dominio (05 hrs)','Semana05')");
            db.execSQL("insert into tbtemas_curso values(2357,6,'Semana Virtual 06: Foro de discusión-Arquitectura de N capas vs MVC','Semana06')");
            db.execSQL("insert into tbtemas_curso values(2357,7,'Implementación y consumo de servicios WCF (4 hrs)','Semana 07: Evaluación de Laboratorio02 (01 hora)')");
            db.execSQL("insert into tbtemas_curso values(2357,8,'Implementación y consumo de servicios Web API (4 hrs)','Semana08')");
            db.execSQL("insert into tbtemas_curso values(2357,9,'Inversion of Control (IoC) (6 hrs)','Semana 10: Evaluación de Laboratorio03 (01 hora)')");
            db.execSQL("insert into tbtemas_curso values(2357,10,'Foro de Discusión - Web API (01 hora)','Semana11,12')");
            db.execSQL("insert into tbtemas_curso values(2357,11,'Foro de discusión IOC','Semana 13: Evaluación de Laboratorio04 (01 hora)')");


            db.execSQL("insert into tb_clase values(2045,2356,'pjlinan','T5AJ','2018-I','SE01','A205',2,'19:45','22:00')");
            db.execSQL("insert into tb_clase values(2046,2357,'pjlinan','T5AJ0','2018-I','SE04','A101',6,'19:00','21:15')");
            db.execSQL("insert into tb_clase values(2047,2360,'pjlinan','G4AJ','2018-I','SE01','A305',4,'15:00','17:15')");
            db.execSQL("insert into tb_clase values(2048,2359,'pjlinan','M3AJ1','2018-I','SE02','A205',7,'20:00','10:15')");
            db.execSQL("insert into tb_clase values(2049,2356,'pjlinan','B4AJ0','2018-I','SE04','A207',3,'14:45','16:15')");
            db.execSQL("insert into tb_clase values(2050,2359,'pjlinan','T3AJ1','2018-I','SE03','A305',5,'19:00','10:45')");
            db.execSQL("insert into tb_clase values(2051,2361,'pjlinan','T3AJ0','2018-I','SE04','A205',5,'19:20','22:45')");
            db.execSQL("insert into tb_clase values(2052,2362,'pjlinan','T5AG0','2018-I','SE02','A207',6,'19:00','20:45')");
            db.execSQL("insert into tb_clase values(2053,2368,'pjlinan','G7PL1','2018-I','SE01','A101',3,'15:00','18:15')");
            db.execSQL("insert into tb_clase values(2054,2356,'pjlinan','G7PL0','2018-I','SE04','A201',3,'16:20','18:30')");
            db.execSQL("insert into tb_clase values(2055,2359,'pjlinan','M5BJ1','2018-I','SE05','A305',4,'15:30','19:00')");
            db.execSQL("insert into tb_clase values(2056,2360,'pjlinan','C3AJ0','2018-I','SE02','A307',7,'20:30','10:45')");
            db.execSQL("insert into tb_clase values(2057,2361,'pjlinan','T5AJ1','2018-I','SE03','A205',2,'16:00','20:30')");
            db.execSQL("insert into tb_clase values(2058,2356,'pjlinan','T4AG1','2018-I','SE01','A101',5,'14:00','16:15')");
            db.execSQL("insert into tb_clase values(2059,2362,'pjlinan','T4AP1','2018-I','SE04','A207',2,'13:20','15:45')");
            db.execSQL("insert into tb_clase values(2060,2357,'pjlinan','T3MJ0','2018-I','SE03','A305',4,'19:00','10:45')");


            db.execSQL("insert into tbmatricula_alumno VALUES('i201312675',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201512690',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201611027',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201611208',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201611387',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201611540',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201611550',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201611659',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201612241',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201612394',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201612598',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201612765',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201612917',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201613101',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201613717',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201613993',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201614209',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201615041',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201615157',2045)");
            db.execSQL("insert into tbmatricula_alumno VALUES('i201616202',2045)");

            db.close();
        }else{
            Toast.makeText(this, "Las Tablas ya estan pobladas", Toast.LENGTH_SHORT).show();
        }

    }
}

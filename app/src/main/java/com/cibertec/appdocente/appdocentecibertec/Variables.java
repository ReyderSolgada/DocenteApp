package com.cibertec.appdocente.appdocentecibertec;

public class Variables {
    public static String usuarioD="";
    public static String rec_usuario="";
    public static String rec_password="";
    public static boolean estadoCHK=false;
    public  final static  String TB_DISTRITO="create table if not exists tb_distrito(coddis integer primary key,descdistrito varchar(50))";

    public final  static String TBDIA_SEMANA="create table if not exists tbdia_semana(nrodia integer primary  key,nomdia varchar(20))";

    public final  static String TB_PROFESION="create table if not exists tb_profesion(codprofesion text primary key,descprofesion varchar(60))";

    public final static   String TBTIPO_AULA="create table if not exists tbtipo_aula(codtipo integer primary key,desctipo varchar(30))";

    public final  static String TB_CARRERA="create table if not exists tb_carrera(codcarrera text primary key,descarrera varchar(50))";

    public final  static String TB_CURSO="create table if not exists tb_curso(codcurso integer primary key,descurso varchar(45))";

    public final  static String TB_DOCENTE="create table if not exists tb_docente(coddoc varchar(12) primary key,nomdoc varchar(20),"+
                                        "apedoc varchar(40),telf varchar(9),coddis integer ,direccion varchar(80),correo varchar(50),"+
                                        "contra varchar(12),FOREIGN KEY (coddis) REFERENCES tb_distrito(coddis))";

    public  final static  String TBDETALLE_DOCENTE="create table if not exists tbdetalle_docente(coddoc varchar(12),codprofesion text,"+
                                        "FOREIGN KEY (coddoc) REFERENCES tb_docente(coddoc),"+
                                        "FOREIGN KEY (codprofesion) REFERENCES tb_profesion(codprofesion))";

    public final  static String TB_ALUMNO="create table if not exists tb_alumno(codalum text primary key,nomalum varchar(40),apealum varchar(60),"+
                                        "coddis integer,direccion varchar(80),telf varchar(9),correoalum varchar(50),"+
                                        "FOREIGN KEY (coddis) REFERENCES tb_distrito(coddis))";

    public final  static String TB_SEDE="create table if not exists tb_sede(codsede text primary key,nomsede varchar(30),coddis integer,"+
                                        "direcsede varchar(50),FOREIGN KEY (coddis) REFERENCES tb_distrito(coddis))";

    public final  static String TB_AULA="create table if not exists tb_aula(codaula text primary key,codsede text,codtipo integer,aforo integer,"+
                                        "FOREIGN KEY (codsede) REFERENCES tb_sede(codsede),"+
                                        "FOREIGN KEY (codtipo) REFERENCES tbtipo_aula(codtipo))";

    public final  static String TBDETALLE_CURSO="create table if not exists tbdetalle_curso(codcurso integer,codcarrera text,"+
                                        "FOREIGN KEY (codcurso) REFERENCES tb_curso(codcurso),"+
                                        "FOREIGN KEY (codcarrera) REFERENCES tb_carrera(codcarrera))";

    public final  static String TBTEMAS_CURSO="create table if not exists tbtemas_curso(codcurso integer,nrotema integer,descrtema varchar(120),"+
                                        "evaluacion varchar(100),FOREIGN KEY (codcurso) REFERENCES tb_curso(codcurso))";

    public final  static String TB_CLASE="create table if not exists tb_clase(codclase integer primary key,codcurso integer,coddoc varchar(12),seccion varchar(5),"+
                                        "periodomatricula varchar(7),codsede text,codaula text,nrodia integer,horainicio text,horafin text,"+
                                        "FOREIGN KEY (codcurso) REFERENCES tb_curso(codcurso),"+
                                        "FOREIGN KEY (coddoc) REFERENCES tb_docente(coddoc),"+
                                        "FOREIGN KEY (codsede) REFERENCES tb_sede(codsede),"+
                                        "FOREIGN KEY (codaula) REFERENCES tb_aula(codaula),"+
                                        "FOREIGN KEY (nrodia) REFERENCES tbdia_semana(nrodia))";

    public static final String TBMATRICULA_ALUMNO="create table if not exists tbmatricula_alumno(codalum text,codclase integer,"+
                                        "FOREIGN KEY (codalum) REFERENCES tb_alumno(codalum),"+
                                        "FOREIGN KEY (codclase) REFERENCES tb_clase(codclase))";
    public static final  String TBEVENTO_CALENDARIO="create table if not exists tbevento_calendario(coddoc varchar(12),titulo varchar(50),"+
                                        "nromes integer,nrodia integer,horainicio varchar(5),horafin varchar(5),descripcion varchar(120),"+
                                        "ubicacion varchar(50),tipoalerta varchar(50),"+
                                        "CONSTRAINT FK_tbevento_calendario_tbdocente FOREIGN KEY (coddoc) REFERENCES tb_docente(coddoc))";
}

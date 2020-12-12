package com.cibertec.appdocente.appdocentecibertec.Model;

public class Cursos {
    private int codclase;
    private int codcurso;
    private String desccurso;
    private String periodoMatricula;
    private String seccion;

    public int getCodclase() {
        return codclase;
    }

    public void setCodclase(int codclase) {
        this.codclase = codclase;
    }

    public int getCodcurso() {
        return codcurso;
    }

    public void setCodcurso(int codcurso) {
        this.codcurso = codcurso;
    }

    public String getDesccurso() {
        return desccurso;
    }

    public void setDesccurso(String desccurso) {
        this.desccurso = desccurso;
    }

    public String getPeriodoMatricula() {
        return periodoMatricula;
    }

    public void setPeriodoMatricula(String periodoMatricula) {
        this.periodoMatricula = periodoMatricula;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}

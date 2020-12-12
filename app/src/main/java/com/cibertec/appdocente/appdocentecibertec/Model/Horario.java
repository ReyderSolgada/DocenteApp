package com.cibertec.appdocente.appdocentecibertec.Model;

public class Horario {
    private int codclase;
    private String nombredia;
    private String desccurso;
    private String horainicio;
    private String horafin;
    private String codaula;
    private String nomsede;

    public String getFechaclase() {
        return fechaclase;
    }

    public void setFechaclase(String fechaclase) {
        this.fechaclase = fechaclase;
    }

    private String fechaclase;

    public int getCodclase() {
        return codclase;
    }

    public void setCodclase(int codclase) {
        this.codclase = codclase;
    }

    public String getNombredia() {
        return nombredia;
    }

    public void setNombredia(String nombredia) {
        this.nombredia = nombredia;
    }

    public String getDesccurso() {
        return desccurso;
    }

    public void setDesccurso(String desccurso) {
        this.desccurso = desccurso;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getCodaula() {
        return codaula;
    }

    public void setCodaula(String codaula) {
        this.codaula = codaula;
    }

    public String getNomsede() {
        return nomsede;
    }

    public void setNomsede(String nomsede) {
        this.nomsede = nomsede;
    }
}

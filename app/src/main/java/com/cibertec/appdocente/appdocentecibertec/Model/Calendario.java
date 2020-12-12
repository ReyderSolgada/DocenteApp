package com.cibertec.appdocente.appdocentecibertec.Model;

public class Calendario {
    private int dia;
    private String nombredia;
    private int mes;
    private String nombremes;
    private int año;
    private String titEvento;

    public String getTitEvento() {
        return titEvento;
    }

    public void setTitEvento(String titEvento) {
        this.titEvento = titEvento;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombredia() {
        return nombredia;
    }

    public void setNombredia(String nombredia) {
        this.nombredia = nombredia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombremes() {
        return nombremes;
    }

    public void setNombremes(String nombremes) {
        this.nombremes = nombremes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}

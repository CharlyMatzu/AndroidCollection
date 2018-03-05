package com.example.charly.firebasetest.Objetos;

public class Auto {

    private String marca;
    private String dueno;
    private String color;
    private int puertas;

    public Auto() {}

    public Auto(String marca, String dueno, String color, int puertas) {
        this.marca = marca;
        this.dueno = dueno;
        this.color = color;
        this.puertas = puertas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

}

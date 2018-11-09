package com.example.rivas.salon_rmr.Model;

public class Promociones {
    private String Tema;
    private String Subtema;
    private int ImagenesPro;

    public Promociones(String Tema, String Subtema, int ImagenesPro){
        setTema(Tema);
        setSubtema(Subtema);
        setImagenesPro(ImagenesPro);
    }

    public String getTema() {
        return Tema;
    }

    public void setTema(String Tema) {
        this.Tema = Tema;
    }

    public String getSubtema() {
        return Subtema;
    }

    public void setSubtema(String Subtema) {
        this.Subtema = Subtema;
    }

    public int getImagenesPro() {
        return ImagenesPro;
    }

    public void setImagenesPro(int ImagenesPro) {
        this.ImagenesPro = ImagenesPro;
    }
}

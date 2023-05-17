package com.example.epis_01.erosionhidrica;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class cGrafica  extends View{
    //... atibutos
    private float aAltura;
    private int aAnio;
    private float aLadera;
    private float aLongHorizontal;
    private float aPerdidaA;
    private Paint aPintar;
    //... constructor
    protected cGrafica(Context context) { super(context); }
    //... metodos primitivos
    private void iniciarLienzo(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        aPintar = new Paint();
    }
    protected void onDraw(Canvas canvas) {
        iniciarLienzo(canvas);
        int pAncho= canvas.getWidth();
        dibujarTitulo(canvas, pAncho, 50);
        dibujarLeyenda(canvas, 30);
        areaInicial(canvas, pAncho, 2);
        areaPerdida(canvas, pAncho);
        areaCuenca(canvas, ladoCuenca(), pAncho);
    }
    //... modificadores
    public void mDatos(int pAnio, float pLadera, float pAltura, float pLongHorizontal, float pPerdidaA) {
        aAnio= pAnio;
        aLadera= pLadera;
        aAltura= pAltura;
        aLongHorizontal= pLongHorizontal;
        aPerdidaA= pPerdidaA;
    }
    //... metodos auxiliares
    private float nuevaLadera() {
        return (float)Math.sqrt(aLongHorizontal*aLongHorizontal+(aAltura-aPerdidaA)*(aAltura-aPerdidaA));
    }
    private float nuevaArea() {
        float lado= nuevaLadera();
        float semiPerimetro= (aLadera+aPerdidaA+lado) / 2;
        return (float)Math.sqrt(semiPerimetro*(semiPerimetro-aLadera)*(semiPerimetro-aPerdidaA)*(semiPerimetro-lado));
    }
    private float ladoCuenca() {
        return (float)(Math.sqrt(nuevaArea()/Math.sqrt(3))*2);
    }
    //... graficadores
    private void dibujarTitulo(Canvas canvas, int pAncho, int pAlto) {
        aPintar.setTextSize(pAlto);
        aPintar.setARGB(255, 0, 0, 0);
        canvas.drawText(aAnio+"", pAncho/2, pAlto+20, aPintar);
    }
    private void dibujarLeyenda(Canvas canvas, int pAlto) {
        aPintar.setTextSize(pAlto);
        aPintar.setARGB(255, 0, 0, 0);
        canvas.drawText("Corte inicial topográfico", 20, 100, aPintar);
        aPintar.setARGB(255, 255, 0, 0);
        canvas.drawText("Indicador de erosión", 20, 130, aPintar);
        aPintar.setARGB(255, 0, 0, 255);
        canvas.drawText("Erosión que concluye en la base de cuenca", 20, 160, aPintar);
    }
    private void areaInicial(Canvas canvas, int pAncho, int pGrosor) {
        aPintar.setStyle(Paint.Style.STROKE);
        aPintar.setStrokeWidth(pGrosor);
        aPintar.setARGB(255, 0, 0, 0);
        float []puntos= new float[]{pAncho-50, 50, pAncho-50, aAltura+50, pAncho-50, aAltura+50, pAncho-50-aLongHorizontal, aAltura+50, pAncho-50-aLongHorizontal, aAltura+50, pAncho-50, 50};
        canvas.drawLines(puntos, aPintar);
    }
    private void areaPerdida(Canvas canvas, int pAncho) {
        aPintar.setARGB(255, 255, 0, 0);
        canvas.drawLine(pAncho-50-aLongHorizontal, aAltura+50,pAncho-50,aPerdidaA+50, aPintar);
    }
    private void areaCuenca(Canvas canvas, float pCuenca, int pAncho) {
        aPintar.setARGB(255, 0, 0, 255);
        float []puntos= new float[]{pAncho-50-aLongHorizontal, aAltura+50, pAncho-50-aLongHorizontal-pCuenca, aAltura+50, pAncho-50-aLongHorizontal-pCuenca, aAltura+50, pAncho-50-aLongHorizontal-pCuenca/2, aAltura+50+(float)Math.sqrt(3)*pCuenca/2, pAncho-50-aLongHorizontal-pCuenca/2, aAltura+50+(float)Math.sqrt(3)*pCuenca/2, pAncho-50-aLongHorizontal, aAltura+50};
        canvas.drawLines(puntos, aPintar);
    }
}
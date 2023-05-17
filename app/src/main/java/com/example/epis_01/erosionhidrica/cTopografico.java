package com.example.epis_01.erosionhidrica;

public class cTopografico {
    //... atributos
    private float aLadera, aAngulo;
    //float aAncho, aHorizonte, aUnidad;
    //... constructor
    protected cTopografico(){ aLadera= 0; aAngulo= 0; }
    protected cTopografico(float pLadera, float pAngulo){ aLadera= pLadera; aAngulo= pAngulo; }
    public void mLadera(float pLadera) { aLadera = pLadera; }
    public void mAngulo(float pAngulo) { aAngulo= pAngulo; }
    //... selectores
    public float sLadera(){ return aLadera; }
    public float sAngulo(){ return aAngulo; }
    //... otros metodos
    public float altura(){ return (float)(aLadera*Math.sin(aAngulo*Math.PI/180)); }
    public float horizonte(){ return (float)(aLadera*Math.cos(aAngulo*Math.PI/180)); }
    public boolean existencia(){
        boolean rta= false;
        if(aLadera>0 && aAngulo<90)
            rta= true;
        return rta;
    }
    private float teta(){ return altura()/horizonte(); }
    private float factorM(){
        float rta, angulo= teta();
        if(angulo>=5) rta= (float)0.5;
        else if(angulo>=3.5) rta= (float)0.4;
        else if(angulo>=1.0) rta= (float)0.3;
        else rta= (float)0.2;
        return rta;
    }
    public float factorLS(){
        float factorM= factorM(), angulo= teta();
        double seno= Math.sin(angulo);
        return (float)(Math.pow(aLadera/22.13,factorM)*(65.41*seno*seno+4.56*seno+0.0654));
    }
}
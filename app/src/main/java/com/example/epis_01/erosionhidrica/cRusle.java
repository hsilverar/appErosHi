package com.example.epis_01.erosionhidrica;

public class cRusle {
    //... atributos
    private float aR, aK, aC, aP;
    private cTopografico aLS;
    //... constructor
    protected cRusle(float pR, float pK, float pLadera, float pAngulo, float pC, float pP){
        aR= pR; aK= pK;
        aLS= new cTopografico(pLadera, pAngulo);
        aC= pC; aP= pP;
    }
    //... modificadores
    //... selectores
    public cTopografico sTopografico(){ return aLS; }
    //... rusle
    public float rusle(){
        return aR*aK*aLS.factorLS()*aC*aP;
    }
}
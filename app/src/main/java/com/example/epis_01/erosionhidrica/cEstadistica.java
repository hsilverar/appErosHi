package com.example.epis_01.erosionhidrica;

import java.util.Stack;

public class cEstadistica {
    //... atributos
    private cRusle aA;
    private float aAncho, aUnidad;
    private Stack< String > aAnio= new Stack < String >();
    private Stack <Float> aTonelada= new Stack < Float >(), aAltura = new Stack < Float >();
    //... constructor
    protected cEstadistica(float pR, float pK, float pLadera, float pAngulo, float pC, float pP, float pAncho, float pUnidad){
        aA= new cRusle(pR, pK, pLadera, pAngulo, pC, pP);
        aAncho= pAncho; aUnidad= pUnidad;
    }
    //... selectores
    public String anio(int pPosicion){ return aAnio.elementAt(pPosicion); }
    public float tonelada(int pPosicion){ return aTonelada.elementAt(pPosicion); }
    public float altura(int pPosicion){ return aAltura.elementAt(pPosicion); }
    public cRusle factorA(){ return aA; }
    //... otros metodos
    private float volumen(){ return (aA.sTopografico().horizonte()*aA.sTopografico().altura()/2)*aAncho; }
    public float totalTonelada(){ return aUnidad*volumen()/1000; }
    public float perdidaTonelada(){ return (aA.sTopografico().sLadera()*aAncho/10000)*aA.rusle(); }
    private float perdidaLadera(){ return aA.sTopografico().sLadera()*perdidaTonelada()/totalTonelada(); }
    public float perdidaAltura(){
        float laderaN= aA.sTopografico().sLadera()-perdidaLadera();
        float anguloN= (float)Math.acos(aA.sTopografico().horizonte()/laderaN);
        float alturaN= (float)(laderaN*Math.sin(anguloN));
        return aA.sTopografico().altura()-alturaN;
    }
    //... metodos
    public int simular(){
        float pTa= 0, pAa= 0, horizonte= aA.sTopografico().horizonte();
        //... inicia con las estadisticas para el año 2016
        int i= 0, c= 16;
        while(i<100 && aA.sTopografico().sAngulo()>0){
            //... determinando acumulado de perdida de tierra (tonelada/año) y altura
            pTa= pTa+perdidaTonelada();
            pAa= pAa+perdidaAltura();
            //... apilando nuevos valores
            if(c%4==0) {
                aAnio.push("20" + c);
                //... perdida de tonelada acumulada
                aTonelada.push(pTa);
                //... perdida altura acumulada
                aAltura.push(pAa);
                i++;
            }
            //... actualizando atributos
            aA.sTopografico().mLadera(aA.sTopografico().sLadera()-perdidaLadera());
            aA.sTopografico().mAngulo((float)(Math.acos(horizonte/aA.sTopografico().sLadera())*180/Math.PI));
            //... incrementando contador
            c++;
        }
        return i;
    }
    public String [] aniosArreglo(int pLongitud) {
        String []anios= new String[pLongitud];
        for(int i= 0; i<pLongitud; i++)
            anios[i]= aAnio.elementAt(i)+"";
        return anios;
    }
}
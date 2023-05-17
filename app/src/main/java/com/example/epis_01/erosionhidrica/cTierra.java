package com.example.epis_01.erosionhidrica;

public class cTierra {
    //... atributos
    private cRusle aA;
    private float aAncho, aUnidad;
    private String []aTmaterial;
    private int []aFmaterial;
    //... constructor
    protected cTierra(float pR, float pK, float pLadera, float pAngulo, float pC, float pP, float pAncho, float pUnidad){
        aTmaterial= new String[]{"Tierra suelta seca", "Tierra suelta humeda",
                "Tierra comprimida seca", "Tierra comprimida humeda", "Arena/grava suelta y seca",
                "Arena/grava comprimida y seca", "Arena/grava mojada", "Arena de tepetate",
                "Ripio de tezontle"};
        aFmaterial= new int[]{1200, 1300, 1400, 1600, 1600, 1650, 1700, 1450, 850};
        aA= new cRusle(pR, pK, pLadera, pAngulo, pC, pP);
        aAncho= pAncho; aUnidad= pUnidad;
    }
    //...modificadores
    public void mAnchoUnidad(float pAncho, float pUnidad){ aAncho= pAncho; aUnidad= pUnidad; }
    //... selectores
    public String [] sTmaterial() { return aTmaterial; }
    public int sFmaterial(int pP){ return aFmaterial[pP]; }
    //... otros metodos
    private float volumen(){
        //... volumen de la topografia (en metros cubicos)
        return (aA.sTopografico().horizonte()*aA.sTopografico().altura()/2)*aAncho;
    }
    public float totalTonelada(){
        //... cantidad de masa de la topografia (en tonelada)
        return aUnidad*volumen()/1000;
    }
    public float perdidaTonelada(){
        //... perdida de masa por topografia (en tonelada/año)
        return (aA.sTopografico().sLadera()*aAncho/10000)*aA.rusle();
    }
    private float perdidaLadera(){ return aA.sTopografico().sLadera()*perdidaTonelada()/totalTonelada(); }
    public float perdidaAltura(){
        float laderaN= aA.sTopografico().sLadera()-perdidaLadera();
        float anguloN= (float)Math.acos(aA.sTopografico().horizonte()/laderaN);
        float alturaN= (float)(laderaN*Math.sin(anguloN));
        return aA.sTopografico().altura()-alturaN;
    }
}
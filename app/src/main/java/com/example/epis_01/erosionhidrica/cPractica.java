package com.example.epis_01.erosionhidrica;

public class cPractica {
    //... atributos
    private String[] aTpractica, aDescripcion;
    private double [][]aContenido;
    private float aAngulo;
    //... constructor
    protected cPractica(float pAngulo){
        aTpractica= new String[]{"Contorno", "Cultivo en franjas", "Terraza", "Ninguno"};
        aContenido= new double[][]{ {0.55, 0.27, 0.1, 1.0}, {0.6, 0.3, 0.12, 1.0}, {0.8, 0.4, 0.16, 1.0},
                {0.9, 0.45, 0.18, 1.0}, {1, 0.5, 0.2, 1.0}};
        aDescripcion= new String[]{"Siembra en contra de la pendiente o siembra atravesada a la pendiente. Esta práctica consiste en hacer las hileras del cultivo en contra de la pendiente siguiendo las curvas a nivel.",
                "Constituye un arreglo sistemático de alternancia entre cultivos de escarda y tupidos.",
                "Llamado también andenes son bancales escalonadas construidas en las laderas de las montañas y rellenadas con tierra de cultivo.",
                "La tierra no cuenta con algún tipo de practicas de cultivo"};
        aAngulo= pAngulo;
    }
    //... modificadores
    //... otros metodos
    public float pendiente(){
        //... return altura/horizonte
        //return (aLadera*Math.sin(aAngulo*Math.PI/180))/(aLadera*Math.cos(aAngulo*Math.PI/180));
        return (float)Math.tan(aAngulo*Math.PI/180);
    }
    private int filaPendiente() {
        int f= 4;
        float pen= pendiente();
        if(pen<7) f= 0;
        else if(pen<11.3) f= 1;
        else if(pen<17.6) f= 2;
        else if(pen<26.8) f= 3;
        return f;
    }
    //... selectores
    public String[] sPractica(){ return aTpractica; }
    public float sP(int pP){
        int f= filaPendiente();
        return (float)aContenido[f][pP];
    }
    public String descripcion(int pP){ return aDescripcion[pP]; }
}
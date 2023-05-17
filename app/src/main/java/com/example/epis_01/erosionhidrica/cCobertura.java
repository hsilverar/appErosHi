package com.example.epis_01.erosionhidrica;

public class cCobertura {
    //... atributos
    private String [] aTsuelo, aContenido;
    private double [] aFactorC;
    //... constructores
    protected cCobertura(){
        aTsuelo= new String[]{"Tierra Cultivada", "Bosque", "Pradera", "Matorral", "Humedal",
                "Cuerpos de agua", "Tundra", "Las Superficies Artificiales", "Tierra Desolada",
                "Nieve y Hielo Permanente" };
        aContenido= new String[]{"Las tierras utilizadas para la agricultura, horticultura y jardines.",
                "Tierras cubiertas de árboles, con cubierta vegetal más del 30%",
                "Terrenos cubiertos de césped natural con una cubierta más del 10%",
                "Tierras cubiertas de arbustos con una cubierta de más del 30%",
                "Tierras cubiertas de plantas de humedales y cuerpos de agua.",
                "Las masas de agua en la superficie terrestre.",
                "Hierbas y arbustos en las regiones polares.",
                "Tierras modificadas por las actividades humanas.",
                "Tierras con cubierta vegetal inferior al 10%",
                "Tierras cubiertas por la nieve permanente, y la capa de hielo." };
        aFactorC= new double[]{0.63, 0.003, 0.09, 0.22, 0, 0, -1, 0.09, 0.5, 0};
    }
    //... selectores
    public String[] sTsuelo() { return aTsuelo; }
    public String sContenido(int pP) { return aContenido[pP]; }
    public float sC(int pP){ return (float)aFactorC[pP]; }
}
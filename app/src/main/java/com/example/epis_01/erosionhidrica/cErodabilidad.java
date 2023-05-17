package com.example.epis_01.erosionhidrica;

public class cErodabilidad {
    //... atributos
    private String[] aTipo, aDescripcion, aTextura;
    private double[][] aFactorK;
    //... constuctor
    protected cErodabilidad(){
        aTipo= new String[]{"Acrisoles", "Alisoles", "Andosoles", "Arenosoles", "Calcisoles",
                "Cambisoles", "Chernozem", "Durisoles", "Ferralsoles", "Fluvisoles", "Gleysoles",
                "Gipsisoles", "Histosoles", "Kastanozems", "Leptosoles", "Lixisoles", "Luvisoles",
                "Nitisoles", "Phaeozems", "Planosoles", "Plintosoles", "Podzoles", "Regosoles",
                "Solonchaks", "Solonetz", "Umbrisoles", "Vertisoles"};
        aDescripcion = new String[]{"Muy ácido, suelos fuertemente meteorizados con baja saturación con bases en alguna profundidad.",
                "Suelo con baja saturación con bases en alguna profundidad.",
                "Suelos negros de paisajes volcánicos, se desarrollan en eyecciones o vidrios volcánicos bajo casi cualquier clima.",
                "En la zona seca hay poco desarrollo del perfil, los Arenosoles en los trópicos perhúmedos desarrollan horizontes eluviales álbicos.",
                "Suelos con sustancial acumulación de calcáreo secundario, los Calcisoles típicos tienen un horizonte superficial pardo pálido.",
                "Suelos con por lo menos un principio de diferenciación de horizontes en el subsuelo evidentes por cambios en la estructura y color.",
                "Suelos negros ricos en materia orgánica. Horizonte superficial mólico pardo oscuro a negro.",
                "Suelos fuertemente meteorizados con una capa dura de sílice secundaria (horizonte petrodúrico) o nódulos de sílice secundaria (horizonte dúrico).",
                "Suelos rojos y amarillos tropicales con alto contenido de sesquióxidos.",
                "Suelos desarrollados en depósitos aluviales. Perfiles con evidencia de estratificación; débil diferenciación de horizontes.",
                "Suelos con signos claros de influencia del agua freática. Evidencia de procesos de reducción con segregación de compuestos de Fe.",
                "Suelos con acumulación de sulfato de calcio, con o sin carbonatos, concentrada en el subsuelo. Horizonte superficial de color claro.",
                "Suelos de turba y pantanos. La mineralización es lenta y la transformación de restos vegetales a través de la desintegración bioquímica crea una capa superficial de moho.",
                "Suelos pardos oscuros ricos en materia orgánica, los Kastanozems tienen un perfil similar a los Chernozems, pero el horizonte superficial rico en humus es más delgado.",
                "Suelos someros, finos; los Leptosoles son suelos azonales y particularmente comunes en regiones montañosas.",
                "Suelos con diferenciación pedogenética de arcilla (migración de arcilla), entre el suelo superficial con menor y el subsuelo con mayor contenido de arcilla.",
                "Diferenciación pedogénica del contenido de arcilla.",
                "Suelos tropicales rojos, profundos, bien drenados con un horizonte subsuperficial arcilloso nítico que tiene elementos estructurales de bordes planos o nuciformes.",
                "Suelos ricos en materia orgánica, los Phaeozems son muy parecidos a Chernozems y Kastanozems pero están más intensamente lixiviados.",
                "Suelos con un horizonte superficial de textura gruesa abruptamente sobre un subsuelo denso y de textura más fina, típicamente en tierras planas.",
                "Fuerte meteorización con segregación subsecuente de plintita a la profundidad de fluctuación del agua freática o drenaje superficial impedido.",
                "Suelos con un horizonte iluvial spódico debajo de un horizonte subsuperficial con apariencia de ceniza y cubierta por una capa orgánica.",
                "Suelos débilmente desarrollados en material no consolidado, que no tienen un horizonte mólico o úmbrico, no son muy someros.",
                "Suelos salinos, desde débil a fuertemente meteorizados, muchos Solonchaks tienen un patrón de color gléyico a cierta profundidad.",
                "Suelos con alto contenido de Na y/o Mg intercambiables.",
                "Suelo superficial oscuro, horizonte superficial úmbrico o cámbico con baja saturación de bases.",
                "Suelos muy arcillosos, que se mezclan, con alta proporción de arcillas expansibles. Estos suelos forman grietas anchas y profundas cuando se secan."};
        aTextura = new String[]{ "Gruesa", "Media", "Fina" };
        aFactorK= new double[][]{ {0.026, 0.04, 0.013}, {0.026, 0.04, 0.013}, {0.026, 0.04, 0.013},
                {0.013, 0.02, 0.007}, {0.053, 0.079, 0.026}, {0.026, 0.04,  0.013}, {0.013, 0.02, 0.007},
                {0.053, 0.079, 0.026}, {0.013, 0.02, 0.007}, {0.026, 0.04, 0.013}, {0.026, 0.04, 0.013},
                {0.053, 0.02, 0.007}, {0.053, 0.02, 0.007}, {0.026, 0.04, 0.013}, {0.013, 0.02, 0.007},
                {0.013,  0.02, 0.007}, {0.026, 0.04, 0.013}, {0.013, 0.02, 0.007}, {0.013, 0.02, 0.007},
                {0.053, 0.079, 0.026}, {0.026, 0.04, 0.013}, {0.053, 0.079, 0.026}, {0.026, 0.04, 0.013},
                {0.053, 0.04, 0.013}, {0.053, 0.079, 0.026}, {0.026, 0.04, 0.013}, {0.053, 0.079, 0.026} };
    }
    //... selectores
    public String sDescripcion(int pPosicion){ return aDescripcion[pPosicion]; }
    public float sK(int pFila, int pColumna){ return (float)aFactorK[pFila][pColumna]; }
    public String[] sTextura(){ return aTextura; }
    public String[] sTipo(){ return aTipo; }
}
package com.example.epis_01.erosionhidrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class estadistica extends AppCompatActivity {
    //... atributos
    private int anio, aPosicion;
    private float aR, aK, aC, aP, ladera, angulo, ancho, unidad;
    private float horizonte, altura;
    private TextView aIdentificadorA, aIdentificadorB;
    private GridView aAnios;
    private Button aSimular, aSiguiente;
    private cEstadistica aE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        //... recuperar datos
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR", aR);
        aK= getIntent().getFloatExtra("aK", aK);
        ladera= getIntent().getFloatExtra("ladera", ladera);
        angulo= getIntent().getFloatExtra("angulo", angulo);
        aC= getIntent().getFloatExtra("aC", aC);
        aP= getIntent().getFloatExtra("aP", aP);
        ancho= getIntent().getFloatExtra("ancho", ancho);
        unidad= getIntent().getFloatExtra("unidad", unidad);
        //... establecer enlace
        aIdentificadorA= (TextView) findViewById(R.id.tvIdentificadorA);
        aIdentificadorB= (TextView) findViewById(R.id.tvIdentificadorB);
        aAnios= (GridView) findViewById(R.id.gvAnio);
        aSimular= (Button) findViewById(R.id.buSimular);
        aSiguiente= (Button) findViewById(R.id.buSestadistico);
        //... construir clase
        aE= new cEstadistica(aR, aK, ladera, angulo, aC, aP, ancho, unidad);
        horizonte= aE.factorA().sTopografico().horizonte();
        altura= aE.factorA().sTopografico().altura();
        //... mostrar datos
        identificadosA();
    }
    //... otro metodos
    private void identificadosA(){
        aIdentificadorA.setText("Inicio año: "+anio+"\nTotal tierra: "+aE.totalTonelada()+
                " (tonelada)\nPerdida tierra: "+aE.perdidaTonelada()+
                " (tonelada/año)\nPerdida altura: "+aE.perdidaAltura()+" (metro/año)");
    }
    //... control de botones
    public void buSimular(View view){
        int i= aE.simular();
        //... cargar años
        ArrayAdapter<String> anios= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aE.aniosArreglo(i));
        aAnios.setAdapter(anios);
        //... mostrar eventos de los años
        aAnios.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                aIdentificadorB.setText("Año: "+aE.anio(posicion)+"\nPerdida de tierra: "+aE.tonelada(posicion)+" (tonelada/año)\nPerdida Altura: "+aE.altura(posicion)+" (metro/año)");
                aSiguiente.setVisibility(View.VISIBLE);
                aSimular.setVisibility(View.INVISIBLE);
                aPosicion= posicion;
            }
        });
    }
    public void aTierra(View view){
        Intent anterior= new Intent(this, tierra.class);
        anterior.putExtra("anio", anio);
        anterior.putExtra("aR", aR);
        anterior.putExtra("aK", aK);
        anterior.putExtra("ladera", ladera);
        anterior.putExtra("angulo", angulo);
        anterior.putExtra("aC", aC);
        anterior.putExtra("aP", aP);
        startActivity(anterior);
        finish();
    }
    public void sGrafica(View view){
        Intent siguiente= new Intent(this, grafica.class);
        siguiente.putExtra("anio", anio);
        siguiente.putExtra("aR", aR);
        siguiente.putExtra("aK", aK);
        siguiente.putExtra("ladera", ladera);
        siguiente.putExtra("angulo", angulo);
        siguiente.putExtra("aC", aC);
        siguiente.putExtra("aP", aP);
        siguiente.putExtra("ancho", ancho);
        siguiente.putExtra("unidad", unidad);

        siguiente.putExtra("anioN", Integer.parseInt(aE.anio(aPosicion)));
        siguiente.putExtra("altura", altura);
        siguiente.putExtra("horizonte", horizonte);
        siguiente.putExtra("perdidaA", aE.altura(aPosicion));
        startActivity(siguiente);
        finish();
    }
}
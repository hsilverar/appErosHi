package com.example.epis_01.erosionhidrica;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class grafica extends AppCompatActivity {
    //... atributos
    private int anio, anioN;
    private float aR, aK, aC, aP, ladera, angulo, ancho, unidad;
    private float perdidaA, altura, horizonte;
    private ConstraintLayout dibujar;
    private cGrafica aG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);
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
        //... nuevos valores para grafica
        anioN= getIntent().getIntExtra("anioN", anioN);
        altura= getIntent().getFloatExtra("altura", altura);
        horizonte= getIntent().getFloatExtra("horizonte", horizonte);
        perdidaA= getIntent().getFloatExtra("perdidaA", perdidaA);
        //... establecer enlace
        dibujar= (ConstraintLayout) findViewById(R.id.clCanvas);
        //... construir clase
        aG= new cGrafica(this);
        aG.mDatos(anioN, ladera, altura, horizonte, perdidaA);
        //... mostrar grafico
        dibujar.addView(aG);
    }
    //... control de botones
    public void aCerrar(View view) { finish(); }
    public void aEstadistica(View view) {
        Intent anterior= new Intent(this, estadistica.class);
        anterior.putExtra("anio", anio);
        anterior.putExtra("aR", aR);
        anterior.putExtra("aK", aK);
        anterior.putExtra("ladera", ladera);
        anterior.putExtra("angulo", angulo);
        anterior.putExtra("aC", aC);
        anterior.putExtra("aP", aP);
        anterior.putExtra("ancho", ancho);
        anterior.putExtra("unidad", unidad);
        startActivity(anterior);
        finish();
    }
}
package com.example.epis_01.erosionhidrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class perdida extends AppCompatActivity {
    //... atributos
    private int anio;
    private float aR, aK, aC, aP, ladera, angulo;
    private TextView aAperdida, aIdentificador;
    private cRusle aA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdida);
        //... recuperar datos
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR", aR);
        aK= getIntent().getFloatExtra("aK", aK);
        ladera=  getIntent().getFloatExtra("ladera", ladera);
        angulo= getIntent().getFloatExtra("angulo", angulo);
        aC= getIntent().getFloatExtra("aC", aC);
        aP= getIntent().getFloatExtra("aP", aP);
        //... construyendo clase
        aA= new cRusle(aR, aK, ladera, angulo, aC, aP);
        //... establececr enlace con xml
        aAperdida= (TextView) findViewById(R.id.tvAperdida);
        aIdentificador= (TextView) findViewById(R.id.tvIdentificador);
        //.. mostrar varibles y rusle
        mostrarDatos();
    }
    //... otros metodos
    private void mostrarDatos(){
        //... mostrar variables
        aAperdida.setText("Año: "+anio+"\nErosividad (R):"+aR+"\nErodabilidad (K):"+aK+"\nTopografia (LS):"+aA.sTopografico().factorLS()+"\nCobertura (C):"+aC+"\nPractica (P):"+aP);
        //... mostrar resultado
        aIdentificador.setText("Pérdida de suelo por unidad de área (RUSLE): "+aA.rusle()+" TONELADAS/(HECTAREAS AÑO)");
    }
    //... control de botones
    public void aPractica(View view){
        Intent anterior= new Intent(this, practica.class);
        //... pasando datos a ventana anterior
        anterior.putExtra("anio", anio);
        anterior.putExtra("aR", aR);
        anterior.putExtra("aK", aK);
        anterior.putExtra("ladera", ladera);
        anterior.putExtra("angulo", angulo);
        anterior.putExtra("aC", aC);
        startActivity(anterior);
        finish();
    }
    public void sTierra(View view){
        Intent siguiente= new Intent(this, tierra.class);
        //... pasando datos para estadisticos
        siguiente.putExtra("anio", anio);
        siguiente.putExtra("aR", aR);
        siguiente.putExtra("aK", aK);
        siguiente.putExtra("ladera", ladera);
        siguiente.putExtra("angulo", angulo);
        siguiente.putExtra("aC", aC);
        siguiente.putExtra("aP", aP);
        startActivity(siguiente);
        finish();
    }
}
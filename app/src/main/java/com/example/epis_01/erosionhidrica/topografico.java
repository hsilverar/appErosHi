package com.example.epis_01.erosionhidrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class topografico extends AppCompatActivity {
    //... atributos
    private TextView aLadera, aAngulo, aLS;
    private float aR, aK;
    private int anio;
    private cTopografico aT;
    private Button aSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topografico);
        //... recuperar datos
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR", aR);
        aK= getIntent().getFloatExtra("aK", aK);
        //... establecer enlace
        aLadera= (TextView) findViewById(R.id.etLadera);
        aAngulo= (TextView) findViewById(R.id.etAngulo);
        aLS= (TextView) findViewById(R.id.etFtopografico);
        aSiguiente= (Button) findViewById(R.id.buStopografico);
        //... construir clase
        aT= new cTopografico();
    }
    //... otros metodos
    public void calcular(View view){
        if(aLadera.getText().toString().equals("") || aAngulo.getText().toString().equals(""))
            Toast.makeText(this, "ERROR: Falta ingresar alguno de los factores...!", Toast.LENGTH_SHORT).show();
        else{
            float ladera= Float.parseFloat(aLadera.getText().toString());
            float angulo= Float.parseFloat(aAngulo.getText().toString());
            //... modificar atributos
            aT.mLadera(ladera);
            aT.mAngulo(angulo);
            if(!aT.existencia()) {
                Toast.makeText(this, "ERROR: Los factores ingresados no tienen coherencia...!", Toast.LENGTH_SHORT).show();
                aLS.setText("0.00");
                aSiguiente.setVisibility(View.INVISIBLE);
            }
            else{
                aLS.setText(aT.factorLS()+"");
                aSiguiente.setVisibility(View.VISIBLE);
            }
        }
    }
    public void aErodabilidad(View view){
        Intent anterior= new Intent(this, erodabilidad.class);
        anterior.putExtra("anio", anio);
        anterior.putExtra("aR", aR);
        startActivity(anterior);
        finish();
    }
    public void sCobertura(View view){
        if(Float.parseFloat(aLS.getText().toString())>0) {
            Intent siguiente= new Intent(this, cobertura.class);
            siguiente.putExtra("anio", anio);
            siguiente.putExtra("aR", aR);
            siguiente.putExtra("aK", aK);
            siguiente.putExtra("ladera", Float.parseFloat(aLadera.getText().toString()));
            siguiente.putExtra("angulo", Float.parseFloat(aAngulo.getText().toString()));
            startActivity(siguiente);
            finish();
        }else
            Toast.makeText(this, "ERROR: Falta ingresar el factor...!", Toast.LENGTH_SHORT).show();
    }
}
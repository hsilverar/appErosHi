package com.example.epis_01.erosionhidrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class erosividad extends AppCompatActivity {
    //... atributos
    private cErosividad aE;
    private Spinner aRegion;
    private GridView aAnio;
    private EditText aR;
    private TextView aIdentificador;
    private Button aSiguiente;
    private int anio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erosividad);
        //... establecer enlace
        aRegion= (Spinner) findViewById(R.id.spRegion);
        aAnio= (GridView) findViewById(R.id.gvAnio);
        aR= (EditText) findViewById(R.id.etFerosividad);
        aIdentificador= (TextView) findViewById(R.id.tvIdentificador);
        aSiguiente= (Button) findViewById(R.id.buSerosividad);
        //... crear clase
        aE= new cErosividad();
        //... cargar espinner
        cargarRegion();
        //... cargar GridView
        cargarAnio();
    }
    private void cargarRegion(){
        ArrayAdapter<String> region= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aE.sRegion());
        aRegion.setAdapter(region);
    }
    private void cargarAnio(){
        ArrayAdapter<String> anios= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aE.sAnio());
        aAnio.setAdapter(anios);
        aAnio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                anio= Integer.parseInt(aAnio.getItemAtPosition(posicion).toString());
                int fila= aRegion.getSelectedItemPosition();
                aR.setText(aE.sR(fila, posicion)+"");
                aIdentificador.setText(aRegion.getItemAtPosition(fila)+" "+anio+", Fuente:");
                if(Float.parseFloat(aR.getText().toString())>0)
                    aSiguiente.setVisibility(View.VISIBLE);
                else {
                    aR.setText("No hay factor");
                    aSiguiente.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    //... control de botones
    public void aErosion(View view){
        Intent anterior= new Intent(this, erosion.class);
        startActivity(anterior);
        finish();
    }
    public void sErodabilidad(View view){
        if(Float.parseFloat(aR.getText().toString())>0) {
            Intent siguiente= new Intent(this, erodabilidad.class);
            siguiente.putExtra("anio", anio);
            siguiente.putExtra("aR", Float.parseFloat(aR.getText().toString()));
            startActivity(siguiente);
            finish();
        }else
            Toast.makeText(this, "ERROR: Falta ingresar el factor...!", Toast.LENGTH_SHORT).show();
    }
}
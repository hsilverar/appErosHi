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
import android.widget.TextView;
import android.widget.Toast;

public class erodabilidad extends AppCompatActivity {
    //... atributos
    private GridView aSuelo, aTextura;
    private cErodabilidad aE;
    private TextView aIdentificador, aFuente;
    private EditText aK;
    private Button aSiguiente;
    private int fila, anio;
    private float aR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erodabilidad);
        //... recuperar valor
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR", aR);
        //... establecer enlace
        aSuelo= (GridView) findViewById(R.id.gvSuelo);
        aTextura= (GridView) findViewById(R.id.gvTextura);
        aIdentificador= (TextView) findViewById(R.id.tvIdentificador);
        aFuente= (TextView) findViewById(R.id.tvFuente);
        aK= (EditText) findViewById(R.id.etFerodabilidad);
        aSiguiente= (Button) findViewById(R.id.buSerodabilidad);
        //... crear clase
        aE= new cErodabilidad();
        //... cargar gridView
        cargarSuelo();
        cargarTextura();
    }
    private void cargarSuelo(){
        ArrayAdapter<String> suelo= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aE.sTipo());
        aSuelo.setAdapter(suelo);
        aSuelo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                aIdentificador.setText(aSuelo.getItemAtPosition(posicion)+"");
                aFuente.setText(aE.sDescripcion(posicion)+", Fuente: Servicio Nacional de Metereología e Hidrología (SENAMHI - USDA, IUSS, GSR)");
                aK.setText("0.00");
                aSiguiente.setVisibility(View.INVISIBLE);
                fila= posicion;
            }
        });
    }
    private void cargarTextura(){
        ArrayAdapter<String> textura= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aE.sTextura());
        aTextura.setAdapter(textura);
        aTextura.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aIdentificador.setText(aSuelo.getItemAtPosition(fila).toString()+" "+aTextura.getItemAtPosition(position));

                aK.setText(aE.sK(fila, position)+"");
                if(Float.parseFloat(aK.getText().toString())>0)
                    aSiguiente.setVisibility(View.VISIBLE);
                else
                    aSiguiente.setVisibility(View.INVISIBLE);
            }
        });
    }
    //... control de botone
    public void aErosividad(View view){
        Intent anterior= new Intent(this, erosividad.class);
        startActivity(anterior);
        finish();
    }
    public void sTopografico(View view){
        if(Float.parseFloat(aK.getText().toString())>0) {
            Intent siguiente= new Intent(this, topografico.class);
            siguiente.putExtra("anio", anio);
            siguiente.putExtra("aR", aR);
            siguiente.putExtra("aK", Float.parseFloat(aK.getText().toString()));
            startActivity(siguiente);
            finish();
        }else
            Toast.makeText(this, "ERROR: Falta ingresar el factor...!", Toast.LENGTH_SHORT).show();
    }
}
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

public class cobertura extends AppCompatActivity {
    //... atributos
    private int anio;
    private float aR, aK, ladera, angulo;
    private GridView aSuelo;
    private EditText aC;
    private TextView aIdentificador, aFuente;
    private Button aScobertura;
    private cCobertura aCo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobertura);
        //... recuperar datos
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR",  aR);
        aK= getIntent().getFloatExtra("aK", aK);
        //... recuperando datos para practicas
        ladera= getIntent().getFloatExtra("ladera", ladera);
        angulo= getIntent().getFloatExtra("angulo", angulo);
        //... establecer enlace
        aSuelo= (GridView) findViewById(R.id.gvSuelo);
        aC= (EditText) findViewById(R.id.etFcobertura);
        aIdentificador= (TextView) findViewById(R.id.tvIdentificador);
        aFuente= (TextView) findViewById(R.id.tvFuente);
        aScobertura= (Button) findViewById(R.id.buScobertura);
        //... crear clase
        aCo= new cCobertura();
        //... cargar suelo
        cargarSuelo();
    }
    //... otros metodos
    private void cargarSuelo(){
        ArrayAdapter<String> suelo= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aCo.sTsuelo());
        aSuelo.setAdapter(suelo);
        aSuelo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                aC.setText(aCo.sC(posicion)+"");
                aIdentificador.setText(aSuelo.getItemAtPosition(posicion).toString());
                aFuente.setText(aCo.sContenido(posicion)+", Fuente: Imagenes del Global Land Cover (GCL), se utilizaron para la estimación de la cubierta vegetal a travéz de imágenes satélitales.");
                if(Float.parseFloat(aC.getText().toString())<0) {
                    aC.setText("No hay factor");
                    aScobertura.setVisibility(View.INVISIBLE);
                } else if(Float.parseFloat(aC.getText().toString()) == 0)
                    aScobertura.setVisibility(View.INVISIBLE);
                else
                    aScobertura.setVisibility(View.VISIBLE);
            }
        });
    }
    //... control de botones
    public void aTopografico(View view){
        Intent anterior= new Intent(this, topografico.class);
        anterior.putExtra("anio", anio);
        anterior.putExtra("aR", aR);
        anterior.putExtra("aK", aK);
        startActivity(anterior);
        finish();
    }
    public void sPractica(View view){
        if(Float.parseFloat(aC.getText().toString())>0) {
            Intent siguiente= new Intent(this, practica.class);
            siguiente.putExtra("anio", anio);
            siguiente.putExtra("aR", aR);
            siguiente.putExtra("aK", aK);
            siguiente.putExtra("ladera", ladera);
            siguiente.putExtra("angulo", angulo);
            siguiente.putExtra("aC", Float.parseFloat(aC.getText().toString()));
            startActivity(siguiente);
            finish();
        }else
            Toast.makeText(this, "ERROR: Falta ingresar el factor...!", Toast.LENGTH_SHORT).show();
    }
}
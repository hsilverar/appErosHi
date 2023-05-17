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

public class tierra extends AppCompatActivity {
    //... atributos
    private int anio, fila;
    private float aR, aK, aC, aP, ladera, angulo;
    private GridView aTierra;
    private EditText aAncho, aUnidad;
    private TextView aIdentificador;
    private Button aCalcular, aSiguiente;
    private cTierra aT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tierra);
        //... recuperar datos
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR", aR);
        aK= getIntent().getFloatExtra("aK", aK);
        ladera=  getIntent().getFloatExtra("ladera", ladera);
        angulo= getIntent().getFloatExtra("angulo", angulo);
        aC= getIntent().getFloatExtra("aC", aC);
        aP= getIntent().getFloatExtra("aP", aP);
        //... estableer enlace
        aTierra= (GridView) findViewById(R.id.gvTierra);
        aAncho= (EditText) findViewById(R.id.etAncho);
        aUnidad= (EditText) findViewById(R.id.etUnidad);
        aIdentificador= (TextView) findViewById(R.id.tvIdentificador);
        aCalcular= (Button) findViewById(R.id.buCalcular);
        aSiguiente= (Button) findViewById(R.id.buStierra);
        //... crear clase
        aT = new cTierra(aR, aK, ladera, angulo, aC, aP, 0, 0);
        //... inicializar datos
        tipoSuelo();
    }
    //... otros metodos
    private void tipoSuelo(){
        ArrayAdapter<String> material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aT.sTmaterial());
        aTierra.setAdapter(material);
        aTierra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                aUnidad.setText(aT.sFmaterial(posicion)+"");
                aIdentificador.setText("Año: "+anio+"\nTipo de suelo: "+aTierra.getItemAtPosition(posicion).toString());
                fila= posicion;
                if(Float.parseFloat(aUnidad.getText().toString())>0)
                    aCalcular.setVisibility(View.VISIBLE);
                else
                    aCalcular.setVisibility(View.INVISIBLE);
                aSiguiente.setVisibility(View.INVISIBLE);
            }
        });
    }
    //... contro de botones
    public void calcular(View view) {
        String ancho= aAncho.getText().toString();
        String unidad= aUnidad.getText().toString();
        if(ancho.equals("") || unidad.equals("")) {
            Toast.makeText(this, "ERROR: Ingresar factor unidad o ancho de cuenca...!", Toast.LENGTH_SHORT).show();
            aSiguiente.setVisibility(View.INVISIBLE);
        }
        else if(Float.parseFloat(ancho)>0 && Float.parseFloat(unidad)>0){
            //... ingresamos ancho de cuenca y unidad
            aT.mAnchoUnidad(Float.parseFloat(ancho), Float.parseFloat(unidad));
            //... ingresando factores de RUSLE
            aIdentificador.setText("Año: "+anio+"\nTipo de suelo: "+aTierra.getItemAtPosition(fila).toString()+"\nTotal tierra: "+aT.totalTonelada()+" (tonelada)\nPerdida tierra: "+aT.perdidaTonelada()+" (tonelada/año)\nPerdida altura: "+aT.perdidaAltura()+" (metro/año)");
            //... activar boton
            aSiguiente.setVisibility(View.VISIBLE);
        } else
            Toast.makeText(this, "ERROR: Ingresar correctamente factores...!", Toast.LENGTH_SHORT).show();
    }
    public void aPerdida(View view){
        Intent anterior= new Intent(this, perdida.class);
        //... pasando datos para estadisticos
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
    public void sEstadistica(View view){
        float ancho= Float.parseFloat(aAncho.getText().toString());
        float unidad= Float.parseFloat(aUnidad.getText().toString());
        if(ancho>0 && unidad>0){
            Intent siguiente= new Intent(this, estadistica.class);
            //... pasando datos para estadisticos
            siguiente.putExtra("anio", anio);
            siguiente.putExtra("aR", aR);
            siguiente.putExtra("aK", aK);
            siguiente.putExtra("ladera", ladera);
            siguiente.putExtra("angulo", angulo);
            siguiente.putExtra("aC", aC);
            siguiente.putExtra("aP", aP);
            siguiente.putExtra("ancho", Float.parseFloat(aAncho.getText().toString()));
            siguiente.putExtra("unidad", Float.parseFloat(aUnidad.getText().toString()));
            startActivity(siguiente);
            finish();
        }else
            Toast.makeText(this, "ERROR: Falta ingresar los factores...!", Toast.LENGTH_SHORT).show();
    }
}
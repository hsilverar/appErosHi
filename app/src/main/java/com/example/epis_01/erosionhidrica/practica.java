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

public class practica extends AppCompatActivity {
    //... atributos
    private int anio;
    private float aR, aK, ladera, angulo, aC;
    private GridView aPractica;
    private TextView aIdentificador, aPendiente, aFuente;
    private EditText aP;
    private Button aSiguiente;
    private cPractica aPr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica);
        //... recuperar datos
        anio= getIntent().getIntExtra("anio", anio);
        aR= getIntent().getFloatExtra("aR", aR);
        aK= getIntent().getFloatExtra("aK", aK);
        ladera=  getIntent().getFloatExtra("ladera", ladera);
        angulo=  getIntent().getFloatExtra("angulo", angulo);
        aC= getIntent().getFloatExtra("aC", aC);
        //... construir clase
        aPr= new cPractica(angulo);
        //... establecer enlace
        aPractica= (GridView) findViewById(R.id.gvPractica);
        aIdentificador= (TextView) findViewById(R.id.tvIdentificador);
        aPendiente= (TextView) findViewById(R.id.tvPendiente);
        aFuente= (TextView) findViewById(R.id.tvFuenteP);
        aP= (EditText) findViewById(R.id.etFpractica);
        aSiguiente= (Button) findViewById(R.id.buSpractica);
        //... cargar practicas
        cargarPractica();
    }
    //... otros metodos
    private void cargarPractica(){
        //... mmostrar pendiente
        aPendiente.setText("Pendiente (%): "+aPr.pendiente());
        ArrayAdapter<String> practicas= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aPr.sPractica());
        aPractica.setAdapter(practicas);
        aPractica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                aIdentificador.setText(aPractica.getItemAtPosition(posicion)+"");
                aFuente.setText(aPr.descripcion(posicion)+"\nFactor de prácticas de conservación (Kim, 2006).");
                aP.setText(aPr.sP(posicion)+"");
                if(Float.parseFloat(aP.getText().toString())>0)
                    aSiguiente.setVisibility(View.VISIBLE);
                else
                    aSiguiente.setVisibility(View.INVISIBLE);
            }
        });
    }
    //... control de botones
    public void aCobertura(View view){
        Intent anterior= new Intent(this, cobertura.class);
        anterior.putExtra("anio", anio);
        anterior.putExtra("aR", aR);
        anterior.putExtra("aK", aK);
        anterior.putExtra("ladera", ladera);
        anterior.putExtra("angulo", angulo);
        startActivity(anterior);
        finish();
    }
    public void sPerdida(View view){
        if(Float.parseFloat(aP.getText().toString())>0) {
            Intent siguiente= new Intent(this, perdida.class);
            siguiente.putExtra("anio", anio);
            siguiente.putExtra("aR", aR);
            siguiente.putExtra("aK", aK);
            siguiente.putExtra("ladera", ladera);
            siguiente.putExtra("angulo", angulo);
            siguiente.putExtra("aC", aC);
            siguiente.putExtra("aP", Float.parseFloat(aP.getText().toString()));
            startActivity(siguiente);
            finish();
        }else
            Toast.makeText(this, "ERROR: Falta ingresar el factor...!", Toast.LENGTH_SHORT).show();
    }
}
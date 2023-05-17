package com.example.epis_01.erosionhidrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class erosion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erosion);
    }
    //... control de botones
    public void sErosividad(View view){
        Intent siguiente= new Intent(this, erosividad.class);
        startActivity(siguiente);
        finish();
    }
    public void aCerrar(View view){ finish(); }
}
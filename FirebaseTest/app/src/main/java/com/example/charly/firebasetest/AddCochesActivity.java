package com.example.charly.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.charly.firebasetest.Objetos.Auto;
import com.example.charly.firebasetest.Objetos.FirebaseReferencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCochesActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference autosRef;
    private Button addAuto;
    private EditText txtMarca, txtDueno, txtColor, txtPuertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coches);

        //Instancia (singleton de firebase)
        database = FirebaseDatabase.getInstance();
        //Referencia a la base de datos
        autosRef = database.getReference(FirebaseReferencias.AUTOS_REF);

        txtMarca = (EditText) findViewById(R.id.txtMarca);
        txtDueno = (EditText) findViewById(R.id.txtDueno);
        txtColor = (EditText) findViewById(R.id.txtColor);
        txtPuertas = (EditText) findViewById(R.id.txtPuertas);
    }


    public void addAuto(View v){
        String marca = txtMarca.getText().toString();
        String dueno = txtDueno.getText().toString();
        String color = txtColor.getText().toString();
        int puertas = Integer.parseInt( txtPuertas.getText().toString() );
        Auto auto = new Auto(marca, dueno, color, puertas);
        autosRef.push().setValue( auto );
    }
}

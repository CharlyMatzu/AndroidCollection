package com.example.charly.uicollect.BackButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.charly.uicollect.R;

public class ActivityBackToolbar extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);

        //Activa el boton de retroceso (debe indicar el padre en el manifest)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}

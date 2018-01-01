package com.example.charly.uicollect.BackButton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.charly.uicollect.R;

public class ActivityBackActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_menu);
        setTitle("BackBtn con ActionBar");

        //Muestra y activa la funcionalidad del boton de retroceso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}

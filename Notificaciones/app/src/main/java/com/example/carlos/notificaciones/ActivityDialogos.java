package com.example.carlos.notificaciones;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityDialogos extends AppCompatActivity {

    private Button btnBasic, btnOptions, btnCustom, btnSingle, btnMultiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogos);
        setTitle("Dialogo");

        btnBasic = (Button) findViewById(R.id.btnBasic);
        btnOptions = (Button) findViewById(R.id.btnOptions);
        btnCustom = (Button) findViewById(R.id.btnCustom);
        btnSingle = (Button) findViewById(R.id.btnSingle);
        btnMultiple = (Button) findViewById(R.id.btnMultiple);
    }




    public void basic(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Basico")
                .setMessage("Mensaje del dialogo");
        builder.create().show();
    }

    public void defaultButtons(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Con opciones")
                .setMessage("Mensaje del dialogo con opcion para confirmar");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Log.i("Dialogos", "Confirmacion Cancelada.");
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Log.i("Dialogos", "Confirmacion Cancelada.");
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    public void customButtons(View v){

        final String[] items = {"Español", "Inglés", "Francés"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Personalizado")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.i("Dialogos", "Opción elegida: " + items[item]);
                    }
                });

        builder.create().show();
    }

    public void singleOption(View v){

        final String[] items = {"Español", "Inglés", "Francés"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Individual")
                //.setMessage("Mensaje del dialogo con opcion para confirmar");
                //-1 indica ningun valor seleccionado por defecto
                .setSingleChoiceItems(items, -1,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Log.i("Dialogos", "Opción elegida: " + items[item]);
                        }
                });

        builder.create().show();
    }

    public void multiOption(View v){

        final String[] items = {"Español", "Inglés", "Francés"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Multiple")
                //.setMessage("Mensaje del dialogo con opcion para confirmar");
                .setMultiChoiceItems(items, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                            Log.i("Dialogos", "Opción elegida: " + items[item]);
                        }
                    });

        builder.create().show();
    }
}

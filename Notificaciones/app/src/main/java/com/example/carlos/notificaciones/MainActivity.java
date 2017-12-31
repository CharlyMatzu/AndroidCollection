package com.example.carlos.notificaciones;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnToast, btnSnack, btnState, btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = (Button) findViewById(R.id.btnToast);
        btnSnack = (Button) findViewById(R.id.btnSnackbar);
        btnDialog = (Button) findViewById(R.id.btnDialogo);
        btnState = (Button) findViewById(R.id.btnStatebar);
    }

    public void toastNotify(View v){
        Toast.makeText(getApplicationContext(), "Soy un TOAST", Toast.LENGTH_LONG).show();
    }

    public void snackbarNotify(View v){
        Snackbar.make(v, "Soy un Snackbar de Material Design", Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.snackbar_action))
                .setAction("Accion", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("Snackbar", "Pulsada acción snackbar!");
                        Toast.makeText(getApplicationContext(), "Accion de snackbar", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    public void dialogNotify(View v){
        Intent intent = new Intent(getApplicationContext(), ActivityDialogos.class);
        startActivity(intent);
    }

    public void statebarNotify(View v){
        Intent intent = new Intent(getApplicationContext(), ActivityEstado.class);
        startActivity(intent);
    }
}

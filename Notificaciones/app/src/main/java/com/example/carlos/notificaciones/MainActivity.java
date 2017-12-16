package com.example.carlos.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnToast, btnEstado, btnDialogo, btnSnack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = (Button) findViewById(R.id.btnToast);
        btnEstado = (Button) findViewById(R.id.btnEstado);
        btnDialogo = (Button) findViewById(R.id.btnDialogo);
        btnSnack = (Button) findViewById(R.id.btnSnack);
    }

    public void toast(View v){
        Toast t = Toast.makeText(getApplicationContext(), "SOY UN TOAST", Toast.LENGTH_LONG);
        t.show();
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    public void estado(View v){
        Intent intent = new Intent(getApplicationContext(), ActivityEstado.class);
        startActivity(intent);
    }

    public void dialogo(View v){

    }

    public void snack(View v){

    }


}

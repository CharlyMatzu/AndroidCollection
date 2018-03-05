package com.example.charly.firebasetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.charly.firebasetest.Objetos.FirebaseReferencias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private TextView txtValor;

    private static Notification.Builder builder;
    private static NotificationManager nManager;
    private static Notification notify;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Mensajes (Notify when changes)");
        nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        txtValor = (TextView) findViewById(R.id.txtValor);

        //Instancia (singleton de firebase)
        database = FirebaseDatabase.getInstance();
        //Referencia a la base de datos
        myRef = database.getReference(FirebaseReferencias.MENSAJES_REF);

        //----------------------------TUTO
        //Write to your database
        //write();
        //Read from your database
        read();
    }

    /**
     * Retrieve an instance of your database using getInstance() and reference the location you want to write to.
     * You can save a range of data types to the database this way, including Java objects. When you save an object the responses from any getters will be saved as children of this location.
     */
    private void write(){
        myRef.setValue("Hello, World!");
    }

    /**
     * To make your app data update in realtime, you should add a ValueEventListener to the reference you just created.

     The onDataChange() method in this class is triggered once when the listener is attached and again every time the data changes, including the children.
     */
    private void read(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Se obtiene valor y se le dice el tipo que regresará
                //IMPORTANTE: debemos tener cuidado con el tipo
                String value = dataSnapshot.getValue(String.class);
                //Se pone texto en pantalla
                txtValor.setText(value);
                if( isFirst )
                    isFirst = false;
                else
                    playNotify();
                Log.d("DATOS", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                txtValor.setText(error.toException().toString());
                Log.w("DATOS", "Failed to read value.", error.toException());
            }
        });
    }


    private void playNotify(){
        builder = new Notification.Builder( getApplicationContext() );
        setSettings(builder, "Mensaje", "Ha cambiado el valor de mensaje");
        notify = builder.build();
        nManager.notify(1, notify);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }

    private void setSettings(Notification.Builder builder, CharSequence titulo, CharSequence desc){
        //Icono
        // https://developer.android.com/guide/practices/ui_guidelines/icon_design_status_bar.html
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), android.R.drawable.alert_light_frame);
        //Ubicacion del tono de notificacion (default)
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder
                //sonido de Notificacion al mostrarse
                .setSound(sound)
                //Para que se quite solo al seleccionalrlo
                .setAutoCancel(true)
                //Nivel de propiedad lo que indica la posicion en la que se ponda
                .setPriority(Notification.PRIORITY_HIGH)

                //----------BARRA DE ESTADO CONTRAIDA
                //Icono que aparece en la barra de estado justo al notificarse
                .setSmallIcon(android.R.drawable.alert_light_frame)
                //Mensaje que aparece junto a icono justo al notificarse
                .setTicker("Msj barra de estado")
                //Muestra cuando paso (fecha en milisegundos
                .setWhen( System.currentTimeMillis() )

                //----------AL EXPANDIRSE BARRA DE ESTADO
                .setContentTitle(titulo)
                .setContentText(desc)
                //Informacion mostrada adicionalmente a lado de
                .setContentInfo("Informacion")
                .setLargeIcon(largeIcon);

        //Accion al realizar cuando se seleccione
//                .setContentIntent(doIntent)
        //Accion al realizar cuando se quite
//                .setDeleteIntent(deleteIntent);
    }

}

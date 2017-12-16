package com.example.carlos.notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityEstado extends AppCompatActivity {

    private Button btnNew, btnPerma, btnBigText, btnInbox, btnBigPicture;
    private Notification.Builder builder;
    private NotificationManager nManager;
    private Notification notify;
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);

        btnNew = (Button) findViewById(R.id.btnNew);
        btnPerma = (Button) findViewById(R.id.btnPerma);
        btnBigText = (Button) findViewById(R.id.btnBigText);
        btnBigPicture = (Button) findViewById(R.id.btnBigPicture);
        btnInbox = (Button) findViewById(R.id.btnInbox);

        //-------------Notificaciones
        //constructor de notificacion
        //builder = new Notification.Builder(getApplicationContext());
        //Manejador de notificaciones
        nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }




    public void newNotify(View v){
        builder = new Notification.Builder(getApplicationContext());
        setSettings(builder, "Normal", "Notificacion normal");
        notify = makeNewNotify(builder);
        active(notify);
    }

    public void permanentlyNotify(View v){
        builder = new Notification.Builder(getApplicationContext());
        setSettings(builder, "Permanente", "No se puede quitar");
        notify = makePermanentlyNotify(builder);
        active(notify);
    }


    public void bigTextStyle(View v){
        builder = new Notification.Builder(getApplicationContext());
        setSettings(builder, "BigText", "Texto expandido");
        CharSequence text = "Una descripcion muuuy larga para mostrar en esta parte ya que el BigTextStyle lo permite" +
                "generalmente este es un texto para mostrar el contenido de un MSN o un correo y solo se puede ver si hay espacio";
        notify = makeBigTextNotify(builder, "Titulo Big", text);
        active(notify);
    }

    public void bigPictureStyle(View v){
        builder = new Notification.Builder(getApplicationContext());
        setSettings(builder, "BigPicture", "");
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        notify = makeBigPictureNotify(builder, "Big Picture", icon);
        active(notify);
    }

    public void inboxStyle(View v){
        builder = new Notification.Builder(getApplicationContext());
        setSettings(builder, "Inbox Style", "Estilo para tipo lista de mensajes");
        notify = makeInboxNotify(builder, "Inbox");
        active(notify);
    }

    public void buttonStyle(View v){
        builder = new Notification.Builder(getApplicationContext());
        setSettings(builder, "Botones", "Botones, máximo 3");
        notify = makeInboxNotify(builder);
        active(notify);
    }

    public void remove(View v){
        cancel(notify);
    }





    //-----------------------
    private void cancel(Notification notify){
        nManager.cancel(id);
        Toast.makeText(getApplicationContext(), "Notificacion removida", Toast.LENGTH_SHORT).show();
    }


    private void active(Notification notify){
        nManager.notify(id, notify);
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


    private Notification makeNewNotify(Notification.Builder builder){
        return builder.build();
    }

    private Notification makePermanentlyNotify(Notification.Builder builder){
        //Para que se mantenga activo sin poder quitarse hasta que la app lo remueva
        builder.setOngoing(true);
        return builder.build();
    }

    private Notification makeBigTextNotify(Notification.Builder builder, CharSequence titulo, CharSequence desc){
        return new Notification.BigTextStyle(builder)
            .setBigContentTitle(titulo)
            .bigText(desc)
            .setSummaryText("Resumen...")
            .build();
    }

    private Notification makeBigPictureNotify(Notification.Builder builder, CharSequence titulo, Bitmap icon){
        return new Notification.BigPictureStyle(builder)
                .setBigContentTitle(titulo)
                //Imagen grande
                .bigPicture(icon)
                //Icono de notificacion
                .bigLargeIcon(icon)
                .setSummaryText("Resumen...")
                .build();
    }

    int cont = 1;
    private Notification makeInboxNotify(Notification.Builder builder, String titulo) {
        Notification.InboxStyle inbox = new Notification.InboxStyle(builder);
        inbox
                .setBigContentTitle(titulo)
                .setSummaryText("Resumen...");

        int i = 1;
        do {
            inbox.addLine("Linea +"+i);
            i++;
        }while ( i <= cont );

        if( cont == 7 )
            cont = 1;
        else
            cont++;

        return inbox.build();
    }


    private Notification makeInboxNotify(Notification.Builder builder) {
        //TODO: agregar funcion
        Intent deleteIntent = new Intent();
        Intent shareIntent = new Intent();

        PendingIntent pDeleteIntent = PendingIntent.getActivity(
                getBaseContext(),
                0,
                deleteIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        PendingIntent pShareIntent = PendingIntent.getActivity(
                getBaseContext(),
                0,
                shareIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        builder.addAction(android.R.drawable.ic_menu_delete, "Delete", pDeleteIntent);
        builder.addAction(android.R.drawable.ic_menu_share, "Share", pShareIntent);

        return builder.build();
    }




}

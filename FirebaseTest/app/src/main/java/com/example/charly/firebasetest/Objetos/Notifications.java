package com.example.charly.firebasetest.Objetos;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.content.Context;

public class Notifications {


    //private int id = 1;

    public static void playNotify(Context context, String title, String body, String id){

    }

    private void setSettings(Notification.Builder builder, CharSequence titulo, CharSequence desc){
        //Icono
        // https://developer.android.com/guide/practices/ui_guidelines/icon_design_status_bar.html
        //Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), android.R.drawable.alert_light_frame);
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
                .setContentInfo("Informacion");
                //.setLargeIcon(largeIcon);

        //Accion al realizar cuando se seleccione
//                .setContentIntent(doIntent)
        //Accion al realizar cuando se quite
//                .setDeleteIntent(deleteIntent);
    }

}

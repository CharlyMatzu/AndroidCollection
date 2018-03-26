package com.example.charly.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sManager;
    private Sensor acelerometro;
    private SensorEventListener eventListener;
    private TextView txtX, txtY, txtZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtX = (TextView) findViewById(R.id.txtX);
        txtY = (TextView) findViewById(R.id.txtY);
        txtZ = (TextView) findViewById(R.id.txtZ);

        //Se obtiene el manager de los sensores
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Se obtiene la referencia al acelerometro
        acelerometro = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Si no soporta (no tiene) dicho sensor
        if( acelerometro == null )
            finish();

        eventListener =  new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                txtX.setText("X: "+ x);
                txtY.setText("Y: "+ y);
                txtZ.setText("Z: "+ z);

                Log.i("ACE", "VALOR de X: "+x);
                Log.i("ACE", "VALOR de Y: "+y);
                Log.i("ACE", "VALOR de Z: "+z);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        //registrando listener
        //sManager.registerListener(eventListener, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(eventListener, acelerometro, SensorManager.SENSOR_DELAY_UI);
    }
}

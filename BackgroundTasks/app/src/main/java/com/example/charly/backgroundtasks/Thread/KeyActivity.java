package com.example.charly.backgroundtasks.Thread;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.charly.backgroundtasks.R;


public class KeyActivity extends AppCompatActivity {

    private TextView lblStatus, lblTime;
    private double milis = 0;
    private int seconds = 0;
    private boolean isPressed = false;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        //handler for thread
        mHandler = new Handler();

        lblStatus = (TextView) findViewById(R.id.lblStatus);
        lblTime = (TextView) findViewById(R.id.lblTime);
        checkIsPressed();
    }


    /**
     * Using handler to manage UI in a thread
     */
    public void updateTime(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //mProgressBar.setProgress(currentProgressCount);
                lblTime.setText(""+ (milis/10));
            }
        });
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_VOLUME_UP ){
            if( !isPressed ){
                lblStatus.setText("Presionado");
                Log.i("CONTADOR", "se presiono");

                //reset
                milis = 0;
                seconds = 0;
                isPressed = true;
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_VOLUME_UP ){
            isPressed = false;

            lblStatus.setText("Suelto");
//            if( seconds > 5 ){
//                Toast.makeText(getApplicationContext(), ""+seconds, Toast.LENGTH_LONG).show();
//                return true;
//            }
            Log.i("CONTADOR", "se solto");
        }

        return super.onKeyUp(keyCode, event);
    }

    private void checkIsPressed() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                while( isPressed ){
//                    try{
//                        seconds++;
//                        Log.i("CONTADOR", ""+seconds);
//                        Thread.sleep(10000);
//                    }catch(Exception ex){
//                        ex.printStackTrace();
//                    }
//                }

                //TODO: detectar cuando milisegundos se mantuvo y disminuir tiempo de sleep
                while( true ){
                    if( isPressed ){
                        try{ Thread.sleep(80); }catch(Exception ex){ ex.printStackTrace(); }
                        milis += 1;
                        //Log.i("CONTADOR_MILIS", ""+(milis/1000));

                        //Si es un ... de 10, se incrementa un segundo
                        if( milis%100 == 0 ) {
                            seconds += 1;
                            Log.i("CONTADOR", ""+ seconds);
                            //updateTime();
                        }
                        updateTime();
                    }
                }
            }
        }).start();
    }





//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        switch (keyCode){
//            case KeyEvent.KEYCODE_VOLUME_UP: lblStatus.setText("Volume UP"); return true;
//            case KeyEvent.KEYCODE_VOLUME_DOWN: lblStatus.setText("Volume Down"); return true;
//
//            default: lblStatus.setText("Other..."); break;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

}

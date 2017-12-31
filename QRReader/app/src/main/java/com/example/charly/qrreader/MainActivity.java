package com.example.charly.qrreader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private Button btnReadQr;
    private final boolean FOCUS_STATE = true;
    private final boolean FLASH_STATE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadQr = (Button) findViewById(R.id.btnReadQR);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
    }

    /**
     * When camera is ON
     */
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
        mScannerView.setAutoFocus(FOCUS_STATE);
        mScannerView.setFlash(FLASH_STATE);
    }

    /**
     * When de Camera is Paused
     */
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }



    @Override
    public void handleResult(Result result) {
        //Dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder
                .setTitle("Resultado")
                .setMessage( "Formato:"+ result.getBarcodeFormat().toString() + "\nValor: "+ result.getText() );

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                resume();
            }
        });

        builder.create().show();

        //Libera la cámara para poder escanear de nuevo
//        mScannerView.resumeCameraPreview( this );
//        mScannerView.stopCamera();
    }

    private void resume(){
        //Libera la cámara para poder escanear de nuevo
        mScannerView.resumeCameraPreview( this );
    }


    public void readQR(View v){
        setContentView(mScannerView);                // Set the scanner view as the content view
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

}

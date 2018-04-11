package com.example.charly.network.Sockets;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charly.network.R;

import java.net.InetAddress;
import java.net.Socket;

public class ReachAddressActivity extends AppCompatActivity {

    private EditText txtIP;
    private TextView lblStatus;

    //private ServerSocket server;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reach_address);

        //Para poder acceder a la red en el hilo principal sin excepciones (IlegalStateException)
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build());
    }

    public void connect(View v){
        String ip = txtIP.getText().toString();
        if( ip.isEmpty() )
            setToast("Ingrese una IP");
        else{

            try {
                lblStatus.setText("Verificando");
                InetAddress address = InetAddress.getByName(ip);
                if( !address.isReachable(2000) )
                    lblStatus.setText("No se pudo conectar");
                else{
                    lblStatus.setText("Conectando...");
                    socket = new Socket(ip, 9090);
                    lblStatus.setText("Conectado");
                }
            }catch (Exception e) {
                e.printStackTrace();
                setToast("Error: "+e.getMessage());
            }

        }
    }

    public void setToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}

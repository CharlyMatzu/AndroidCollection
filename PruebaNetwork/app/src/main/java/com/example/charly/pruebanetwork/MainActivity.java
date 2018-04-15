package com.example.charly.pruebanetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.socketasync.FSocket;
import com.example.socketasync.interfaces.IReceiver;
import com.example.socketasync.interfaces.ISender;

public class MainActivity extends AppCompatActivity implements IReceiver, ISender{


    private FSocket fSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializa
        fSocket = new FSocket();
        //Inicia servidor
        fSocket.startServer(this, getApplicationContext(), 9090);
        //Envia mensaje
        //fSocket.sendMessage(this, "Hola", "ip", 9090);
    }

    @Override
    public void sayMessageEntry(String s) {

    }

    @Override
    public void sayServerStarted(String s) {

    }

    @Override
    public void sayServerStopped() {

    }

    @Override
    public void sayServerError(String s) {

    }

    @Override
    public void sayClientError(String s) {

    }

    @Override
    public void sayMessageSent() {

    }
}

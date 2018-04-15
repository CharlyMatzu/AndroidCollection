package com.example.charly.network.Sockets.AsyncTask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charly.network.R;
import com.example.charly.network.Sockets.AsyncTask.classes.FSocket;
import com.example.charly.network.Sockets.AsyncTask.classes.interfaces.IReceiver;
import com.example.charly.network.Sockets.AsyncTask.classes.interfaces.ISender;


public class AsyncActivity extends AppCompatActivity implements IReceiver, ISender {

    private Button btnConnect, btnSearch, btnStart;
    private EditText txtIP, txtMessage;
    private TextView lblStatus, lblMessages;
    private FSocket fSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_activity);
        setTitle("Sockets");

        fSocket = new FSocket();

        txtIP = (EditText) findViewById(R.id.txtIP);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        btnConnect = (Button) findViewById(R.id.btnSend);
//        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnStart = (Button) findViewById(R.id.btnStart);
        lblStatus = (TextView) findViewById(R.id.lblStatus);
        lblMessages = (TextView) findViewById(R.id.lblMessages);
    }




    public void startServer(View view) {
        lblStatus.setText( "Iniciando server..." );
        fSocket.startServer(this, getApplicationContext(), 9090);
    }

    @Override
    public void sayMessageEntry(String message) {
        Toast.makeText(getApplicationContext(), "socket Message received", Toast.LENGTH_LONG).show();
        String history = lblMessages.getText().toString();
        lblMessages.setText(history +"\n"+ message);
    }

    @Override
    public void sayServerStarted(String ip) {
        //Toast.makeText(getApplicationContext(), "Server iniciado", Toast.LENGTH_LONG).show();
        //lblStatus.setText( server.getLocalIp() );
        lblStatus.setText( ip );
    }

    @Override
    public void sayServerStopped() {
        //Toast.makeText(getApplicationContext(), "Server detenido", Toast.LENGTH_LONG).show();
        lblStatus.setText( "Server detenido" );
    }

    @Override
    public void sayServerError(String msg) {
        Toast.makeText(getApplicationContext(), "Error: "+msg, Toast.LENGTH_LONG).show();
    }


    public void sendMessage(View view) {
        String msg = txtMessage.getText().toString();
        String ip = txtIP.getText().toString();
        fSocket.sendMessage(this, msg, ip, 9090);
    }

    @Override
    public void sayClientError(String msg) {

    }

    @Override
    public void sayMessageSent() {

    }
}

package com.example.charly.network.Sockets.AsyncTask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charly.network.R;
import com.example.charly.network.Sockets.AsyncTask.classes.Client;
import com.example.charly.network.Sockets.AsyncTask.classes.Receiver;
import com.example.charly.network.Sockets.AsyncTask.classes.Server;


public class AsyncActivity extends AppCompatActivity implements Receiver {

    private Button btnConnect, btnSearch, btnStart;
    private EditText txtIP;
    private TextView lblStatus, lblMessages;

    private Server server;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_activity);
        setTitle("Sockets");

//        txtIP = (EditText) findViewById(R.id.txtIP);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        //btnSearch = (Button) findViewById(R.id.btnSearch);
        btnStart = (Button) findViewById(R.id.btnStart);
        lblStatus = (TextView) findViewById(R.id.lblStatus);
//        lblMessages = (TextView) findViewById(R.id.lblMessages);
    }


    public void search(View view) {
//        Intent i = new Intent(getApplicationContext(), ServerListActivity.class);
//        startActivity(i);
    }

    public void startServer(View view) {
        lblStatus.setText( "Iniciando server..." );
        server = new Server(this, 9090);
        server.execute();
    }

    @Override
    public void sayMessageEntry(String message) {
        Toast.makeText(getApplicationContext(), "socket Message received", Toast.LENGTH_LONG).show();
        String history = lblMessages.getText().toString();
        lblMessages.setText(history +"\n"+ message);
    }

    @Override
    public void sayServerStarted() {
        //Toast.makeText(getApplicationContext(), "Server iniciado", Toast.LENGTH_LONG).show();
        //lblStatus.setText( server.getLocalIp() );
        lblStatus.setText( "Server iniciado" );
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
        client = new Client(txtIP.getText().toString(), 9090);
        client.send("Hola mundo");
    }
}

package com.example.charly.network.Sockets.AsyncTask.classes;

import android.os.AsyncTask;
import android.util.Log;

import com.example.charly.network.Sockets.AsyncTask.classes.interfaces.ISender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//TODO: make singleton
class Client extends AsyncTask<String, Integer, Void>{

    private Socket serverSocket;
    private DataOutputStream dataOutput;
    private ISender sender;

    private String host;
    private int port;


    protected Client (String ipHost, int port, ISender sender){
        this.host = ipHost;
        this.port = port;
        this.sender = sender;
    }


    private void sendMessage(String message) {
        try{

            Log.i("SOCKET", "Connecting...");
            serverSocket = new Socket(host, port);
            Log.i("SOCKET", "Sending...");
            dataOutput = new DataOutputStream( serverSocket.getOutputStream() );
            dataOutput.writeUTF(message);
            Log.i("SOCKET", "Send success");


            //close socket and stream
            serverSocket.close();
            dataOutput.close();
            Log.i("SOCKET", "Closed socket");

        }catch(IOException ex){
            Log.e("SERVER", ex.getMessage());
        }
    }


    @Override
    protected Void doInBackground(String... params) {
        sendMessage( params[0] );
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        sender.sayMessageSent();
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}

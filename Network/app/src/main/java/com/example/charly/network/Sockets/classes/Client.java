package com.example.charly.network.Sockets.classes;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//TODO: make singleton
public class Client extends Thread {

    private Socket serverSocket;
    private DataOutputStream dataOutput;
    private String message;

    private String ip;
    private int port;


    public Client (String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void send(String message){
        this.message = message;
        this.run();
    }


    /**
     * Run Thread, don not use this method directly,
     * use {@link #send(String)} method instead
     */
    @Override
    public void run() {
        sendMessage();
    }

    private void sendMessage() {
        try{

            Log.i("SOCKET", "Connecting...");
            serverSocket = new Socket(ip, port);
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
}

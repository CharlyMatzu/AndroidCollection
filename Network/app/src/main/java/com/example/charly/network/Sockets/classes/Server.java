package com.example.charly.network.Sockets.classes;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//TODO: make singleton
public class Server extends Thread {

    private ServerSocket server;
    private Socket clientSocket;
    private Receiver receiver;
    private int port;
    private boolean serverON;

    private String localIP = "";


    public Server(Receiver receiver, int port) {
        this.receiver = receiver;
        this.port = port;
    }


    /**
     * Start server threat to listen sockets
     */
    public void start(){
        this.serverON = true;
        this.run();
    }

    /**
     * Run Thread, don not use this method directly, use {@link #start()} method instead
     */
    @Override
    public void run() {
        while (serverON){
            listen();
        }
    }

    public String getLocalIp(){
        return localIP;
    }

    private void listen(){
        try{

            Log.i("SERVER", "Init server....");
            server = new ServerSocket(port);
            Log.i("SERVER", "Accept clients");

            //localIP = InetAddress.getLocalHost().getHostAddress();
            receiver.notifyInitServer();

            clientSocket = server.accept();



        }catch(IOException ex){
            Log.e("SERVER", ex.getMessage());
        }
    }
}

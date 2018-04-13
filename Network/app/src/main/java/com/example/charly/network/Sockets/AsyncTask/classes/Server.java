package com.example.charly.network.Sockets.AsyncTask.classes;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends AsyncTask<Void, Integer, Void> {

    private final String TAG = "SERVER";
    private ServerSocket server;
    private Socket clientSocket;
    private Receiver receiver;
    private boolean isServerON;
    private int port;
    private boolean serverON;
    private String localIP = "";
    private String errorMessage = "";

    private final int STARTED = 1;
    private final int STOPPED = 2;
    private final int ERROR = 3;



    //TODO: make singleton
    public Server(Receiver receiver, int port) {
        this.receiver = receiver;
        this.port = port;
    }


    public String getLocalIp(){
        return localIP;
    }

    public void stopServer(){
        this.isServerON = false;
    }

    private void startListen(){
        try{

            Log.i(TAG, "Init server....");
            server = new ServerSocket(port);
            Log.i(TAG, "Accept clients");

            localIP = InetAddress.getLocalHost().getHostAddress();
            publishProgress(this.STARTED);

            clientSocket = server.accept();

        }catch(IOException ex){
            errorMessage = ex.getMessage();
            Log.e(TAG, errorMessage);
            publishProgress(this.ERROR);
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "PreExecute");

        isServerON = true;

        super.onPreExecute();
    }


    /**
     * Utilizado para hacer el trabajo que no implica al hilo principal (UI)
     * y que para poder enviar dichos cambios a la UI, se debe llamar al metodo {#link: publishProgress}
     * con sus respectivos parametros
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {
        Log.i(TAG, "Background");

        while(isServerON){
            startListen();
        }

        return null;
    }

    /**
     * Utilizado para acceder al hilo principal y poder hacer cambios
     * en la UI de android
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {

        Log.i(TAG, "Update");

        switch ( values[0] ){
            case STARTED: receiver.sayServerStarted(); break;
            case STOPPED: receiver.sayServerStopped(); break;
            case ERROR: receiver.sayServerError( errorMessage ); break;

            default: break;
        }

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        Log.i(TAG, "Post - Finish");

        super.onPostExecute(aVoid);
    }
}

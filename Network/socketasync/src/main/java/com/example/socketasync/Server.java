package com.example.socketasync;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;


import com.example.socketasync.interfaces.IReceiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static android.content.Context.WIFI_SERVICE;


class Server extends AsyncTask<Void, Integer, Void> {

    private final String TAG = "SERVER";
    private ServerSocket server;
    private Socket clientSocket;
    private IReceiver receiver;
    private boolean isServerON;
    private int port;
    private String messsage;
    private boolean serverON;
    private Context context;
    private String localIP = "";
    private String errorMessage = "";

    private final int STARTED = 1;
    private final int STOPPED = 2;
    private final int RECEIVED = 3;
    private final int ERROR = 4;



    //TODO: make singleton
    protected Server(IReceiver receiver, Context context, int port) {
        this.receiver = receiver;
        this.port = port;
        this.context = context;
    }


    //TODO: debe usar el wifi
    protected String getLocalIp(){
        WifiManager wm = (WifiManager) this.context.getSystemService(WIFI_SERVICE);
        //TODO: actualizar
        localIP = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return localIP+":"+port;
    }

    protected void stopServer(){
        this.isServerON = false;
    }

    private void startListen(){
        try{

            Log.i(TAG, "Init server....");
            server = new ServerSocket(port);
            Log.i(TAG, "Accept clients");

            localIP = InetAddress.getLocalHost().getHostAddress()+":"+port;
            publishProgress(this.STARTED);
            DataInputStream input;

            while(isServerON){
                clientSocket = server.accept();

                input = new DataInputStream( clientSocket.getInputStream() );
                messsage = input.readUTF();
                input.close();

                publishProgress(this.RECEIVED);
            }

            server.close();


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

        startListen();

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
            case STARTED: receiver.sayServerStarted( getLocalIp() ); break;
            case STOPPED: receiver.sayServerStopped(); break;
            case RECEIVED: receiver.sayMessageEntry(messsage); break;
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

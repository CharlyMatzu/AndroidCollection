package com.example.charly.network.Sockets.AsyncTask.classes;

public interface Receiver {

    void sayMessageEntry(String message);

    void sayServerStarted();

    void sayServerStopped();

    void sayServerError(String msg);

}

package com.example.socketasync.interfaces;

public interface IReceiver {

    void sayMessageEntry(String message);

    void sayServerStarted(String ip);

    void sayServerStopped();

    void sayServerError(String msg);


}

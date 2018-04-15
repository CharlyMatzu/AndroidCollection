package com.example.socketasync.interfaces;


import android.content.Context;

public interface ISocket {

    public void startServer(IReceiver receiver, Context context, int port);

    public void stopServer();

    public void sendMessage(ISender sender, String message, String ip, int port);

}

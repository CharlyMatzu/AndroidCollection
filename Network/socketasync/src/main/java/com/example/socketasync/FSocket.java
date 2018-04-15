package com.example.socketasync;


import android.content.Context;

import com.example.socketasync.interfaces.IReceiver;
import com.example.socketasync.interfaces.ISender;
import com.example.socketasync.interfaces.ISocket;


public class FSocket implements ISocket {

    private Server server;
    private Client client;

    public FSocket() {}

    @Override
    public void startServer(IReceiver receiver, Context context, int port) {
        server = new Server(receiver, context, port);
        server.execute();
    }

    @Override
    public void stopServer() {
        server.stopServer();
    }

    @Override
    public void sendMessage(ISender sender, String message, String ip, int port) {
        client = new Client(ip, port, sender);
        client.execute(message);
    }
}

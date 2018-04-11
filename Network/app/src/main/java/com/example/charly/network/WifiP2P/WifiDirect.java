package com.example.charly.network.WifiP2P;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.charly.socketsandwifi.R;

public class WifiDirect extends AppCompatActivity implements WifiP2pManager.ActionListener{

    //------------Referencias
    //https://developer.android.com/guide/topics/connectivity/wifip2p.html#creating-app

    private IntentFilter mIntentFilter;
    private WifiP2pManager p2pManager;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        //Metodo para control de WIFI y el P2P
        p2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        Channel p2pChannel =  p2pManager.initialize(getApplicationContext(), getMainLooper(), null);
        //Clase creada
        mReceiver = new WifiDirectBroadcastReceiver(p2pManager, p2pChannel, this);

        p2pManager.discoverPeers(p2pChannel, this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    //----------------
    // WIFI P2P ActionListeners methods
    //----------------

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(int reason) {

    }
}

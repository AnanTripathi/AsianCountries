package com.project.asiancountries;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class InternetBroadcastReceiver extends BroadcastReceiver {
    ConnectivityChanged connectivityChanged;
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //this will give Throw exception if the activity has not defined connectivity changed
            //here we have no problem as it is used in only one activity
            //but take care of it if you use your own library
            connectivityChanged=(ConnectivityChanged) context;
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
                connectivityChanged.changeInNetworkStatus();
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement AddUnitDialogListener");
        }

    }
    public interface ConnectivityChanged{
        public void changeInNetworkStatus();
    }

}

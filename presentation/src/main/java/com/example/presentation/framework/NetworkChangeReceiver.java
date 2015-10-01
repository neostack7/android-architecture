package com.example.presentation.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;

import com.example.androidarchitecture.R;
import com.example.businesslogic.framework.IBaseView;


/**
 *
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private static Snackbar snackbar;

    private static boolean isNotConnected = true; //Used to avoid multiple call of onReceive();
    private IBaseView baseView;

    public NetworkChangeReceiver(IBaseView baseView) {
        this.baseView = baseView;
    }

    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (InternetDetector.isConnectedToInternet(context)) {
            isNotConnected = true;
            if (snackbar != null) {
                snackbar.dismiss();
            }
        } else {
            if (isNotConnected) {
                createSnackBar(context);
                snackbar.show();
                isNotConnected = false;
            }
        }
    }

    private void createSnackBar(Context context) {
        snackbar = Snackbar.make(baseView.getCurrentView(), context.getString(R.string.no_internet_message),
                Snackbar.LENGTH_LONG);
    }
}

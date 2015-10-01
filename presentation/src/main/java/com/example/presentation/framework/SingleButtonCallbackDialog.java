package com.example.presentation.framework;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.androidarchitecture.R;


public abstract class SingleButtonCallbackDialog extends SingleButtonDialog {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        alertBuilder.setPositiveButton(getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                OnDismissButtonClick();
            }
        });

        return alertBuilder.create();
    }

    public abstract void OnDismissButtonClick();

}

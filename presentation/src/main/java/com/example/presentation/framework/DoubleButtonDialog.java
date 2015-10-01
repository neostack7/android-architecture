package com.example.presentation.framework;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.androidarchitecture.R;


/**
 *
 */
public abstract class DoubleButtonDialog extends DialogFragment {

    public static final String TAG = DoubleButtonDialog.class.getSimpleName();

    private String title;
    private String description;

    private String positiveButtonLabel;
    private String negativeButtonLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(UIConstant.DIALOG_TITLE);
        description = getArguments().getString(UIConstant.DIALOG_DESCRIPTION);
        positiveButtonLabel = getArguments().getString(UIConstant.POSITIVE_BUTTON_LABEL);
        if (positiveButtonLabel == null) {
            positiveButtonLabel = getActivity().getString(R.string.ok);
        }

        negativeButtonLabel = getArguments().getString(UIConstant.NEGATIVE_BUTTON_LABEL);
        if (negativeButtonLabel == null) {
            negativeButtonLabel = getActivity().getString(R.string.cancel);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity(), R.style.style_material_alert_dialog);
        if (title != null) {
            alertBuilder.setTitle(title);
        }
        alertBuilder.setMessage(description);
        alertBuilder.setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                OnNegativeButtonClick();
            }
        });

        alertBuilder.setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                OnPositiveButtonClick();
            }
        });
        return alertBuilder.create();
    }

    public abstract void OnPositiveButtonClick();

    public abstract void OnNegativeButtonClick();
}

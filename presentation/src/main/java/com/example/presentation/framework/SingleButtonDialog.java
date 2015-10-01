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
public class SingleButtonDialog extends DialogFragment {

    public static final String TAG = SingleButtonDialog.class.getSimpleName();

    private String title;
    private String description;

    private String dismissButtonLabel;

    protected AlertDialog.Builder alertBuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(UIConstant.DIALOG_TITLE);
        description = getArguments().getString(UIConstant.DIALOG_DESCRIPTION);
        dismissButtonLabel = getArguments().getString(UIConstant.POSITIVE_BUTTON_LABEL);
        if (dismissButtonLabel == null) {
            dismissButtonLabel = getActivity().getString(R.string.ok);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        alertBuilder = new AlertDialog.Builder(getActivity(), R.style.style_material_alert_dialog);
        if (title != null) {
            alertBuilder.setTitle(title);
        }
        alertBuilder.setMessage(description);
        alertBuilder.setPositiveButton(dismissButtonLabel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });
        return alertBuilder.create();
    }
}

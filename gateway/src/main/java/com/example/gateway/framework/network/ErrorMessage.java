package com.example.gateway.framework.network;

import android.content.Context;

import com.example.gateway.R;


/**
 *
 */
public class ErrorMessage implements IResponseData {

    private FailureMessage errors;

    public FailureMessage getErrors() {
        return errors;
    }

    public void setErrors(FailureMessage errors) {
        this.errors = errors;
    }

    public static ErrorMessage makeErrorMessage(Context context, String details) {
        ErrorMessage result = new ErrorMessage();
        FailureMessage failureMessage = new FailureMessage();
        failureMessage.setMessage(context.getResources().getString(R.string.TITLE_ERROR));
        failureMessage.setDetails(details);
        result.setErrors(failureMessage);
        return result;
    }
}

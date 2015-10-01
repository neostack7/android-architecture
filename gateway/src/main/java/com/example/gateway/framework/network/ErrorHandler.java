package com.example.gateway.framework.network;

import android.content.Context;

import com.example.gateway.R;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 */
public class ErrorHandler {

    public static ErrorMessage getErrorMessage(Context context, RetrofitError error) {
        if (error == null) {
            return null;
        }

        // Assuming all error messages having same data structure.
        ErrorMessage errorMessage = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
        if (errorMessage != null) {
            return errorMessage;
        }

        FailureMessage failureMessage = new FailureMessage();
        failureMessage.setMessage(context.getResources().getString(R.string.TITLE_ERROR));
        String errorDetails = "";

        switch (error.getKind()) {
            case NETWORK:
                errorDetails = context.getResources().getString(R.string.MSG_NETWORK_ERROR);
                break;

            case CONVERSION:
                errorDetails = context.getResources().getString(R.string.MSG_PARSER_ERROR);
                break;

            case UNEXPECTED:
                errorDetails = context.getResources().getString(R.string.MSG_SERVER_ERROR);
                break;

            default:
                Response errorResponse = error.getResponse();
                switch (errorResponse.getStatus()) {
                    case 401:
                        break;

                    case 403:
                        break;

                    case 404:
                        break;

                    case 500:
                        break;
                }
        }
        failureMessage.setDetails(errorDetails);
        ErrorMessage customErrorMessage = new ErrorMessage();
        customErrorMessage.setErrors(failureMessage);
        return customErrorMessage;
    }
}

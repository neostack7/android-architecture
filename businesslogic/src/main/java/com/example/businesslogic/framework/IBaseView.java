package com.example.businesslogic.framework;

import android.content.Context;
import android.view.View;

/**
 *
 */
public interface IBaseView {

    void showProgressDialog(String message);

    void hideProgressDialog();

    void showProgressBar();

    void hideProgressBar();

    void showErrorMessage(String message);

    void showMessageDialog(String message);

    void showMessageDialog(String title, String message);

    void showToastMessage(String message);

    void showLongToastMessage(String message);

    void showSnackBarMessage(String message);

    void showLongSnackBarMessage(String message);

    boolean isInternetAvailable();

    void showNoInternetConnectionMessage(String message);

    void showNoInternetConnectionMessage();

    Context getContext();

    View getCurrentView();
}

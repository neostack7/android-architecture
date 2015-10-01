package com.example.businesslogic.framework;

import android.content.Context;

/**
 *
 */
public abstract class BasePresenter implements IBasePresenter {

    private IBaseView baseView;

    public BasePresenter(IBaseView baseView) {
        this.baseView = baseView;
    }

    public void showProgressDialog(String message) {
        baseView.showProgressDialog(message);
    }

    public void hideProgressDialog() {
        baseView.hideProgressDialog();
    }

    protected void showProgressBar() {
        baseView.showProgressBar();
    }

    protected void hideProgressBar() {
        baseView.hideProgressBar();
    }

    protected void showPbErrorMessage(String message) {
        baseView.showErrorMessage(message);
    }

    public void showMessageDialog(String message) {
        baseView.showMessageDialog(message);
    }

    public void showMessageDialog(String title, String message) {
        baseView.showMessageDialog(title, message);
    }

    public void showToastMessage(String message) {
        baseView.showToastMessage(message);
    }

    public void showLongToastMessage(String message) {
        baseView.showLongToastMessage(message);
    }

    public void showSnackBarMessage(String message) {
        baseView.showSnackBarMessage(message);
    }

    public void showLongSnackBarMessage(String message) {
        baseView.showLongSnackBarMessage(message);
    }

    protected Context getContext() {
        return baseView.getContext();
    }
}

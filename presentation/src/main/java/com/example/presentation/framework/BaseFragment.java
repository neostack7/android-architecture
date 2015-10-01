package com.example.presentation.framework;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidarchitecture.R;
import com.example.businesslogic.framework.IBaseView;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    private ProgressDialog progressDialog;
    private String screenTitle;
    private View rootView;
    private ProgressBarFragment progressBarFragment;
    private View contentContainer;

    protected abstract int getLayoutResource();

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkChangeReceiver = new NetworkChangeReceiver(this);

        progressBarFragment = ProgressBarFragment.newInstance();
        getChildFragmentManager().beginTransaction().add(R.id.progress_container, progressBarFragment, ProgressBarFragment.TAG).commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_base, container, false);
            ViewStub viewStub = (ViewStub) rootView.findViewById(R.id.content_stub);
            contentContainer = rootView.findViewById(R.id.content_container);

            if (viewStub != null) {
                viewStub.setInflatedId(View.NO_ID);
                viewStub.setLayoutResource(getLayoutResource());
                viewStub.inflate();
            }

            ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof BaseActivity) {
            IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            getActivity().registerReceiver(networkChangeReceiver, intentFilter);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    // to be called for dialog related fragment, to resolve issues from the nested fragment
    // situation
    protected FragmentManager getActivitySupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    protected void setTitle(int titleId) {
        String title = getResources().getString(titleId);
        this.screenTitle = title;
        setTitle(title);
    }

    protected void setSubtitle(int subtitleId) {
        String subtitle = getResources().getString(subtitleId);
        setSubtitle(subtitle);
    }

    @SuppressWarnings("ConstantConditions")
    protected void setTitle(CharSequence title) {
        getActivity().setTitle(title);
        this.screenTitle = title.toString();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @SuppressWarnings("ConstantConditions")
    protected void setSubtitle(CharSequence subtitle) {
        if (subtitle == null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(null);
        } else {
            CharSequence upperCase = subtitle.toString().toUpperCase();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(upperCase);
        }
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    protected ActionBar getSupportActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void showProgressDialog(String message) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(
                getResources().getColor(R.color.primary_dark), 6.0f);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminateDrawable(circularProgressDrawable);
        hideProgressDialog();
        if (progressDialog != null) {
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showProgressBar() {
        LinearLayout pbLinearLayout = (LinearLayout) rootView.findViewById(R.id.progress_container);
        pbLinearLayout.setVisibility(View.VISIBLE);
        progressBarFragment.clearMsg();
        progressBarFragment.pushBusy();
    }

    @Override
    public void hideProgressBar() {
        LinearLayout pbLinearLayout = (LinearLayout) rootView.findViewById(R.id.progress_container);
        pbLinearLayout.setVisibility(View.GONE);
        progressBarFragment.popBusy();
    }

    @Override
    public void showErrorMessage(String message) {
        contentContainer.setVisibility(View.GONE);
        progressBarFragment.setMsg(message);
    }

    @Override
    public void showMessageDialog(String message) {
        showSingleButtonDialog(null, message);
    }

    @Override
    public void showMessageDialog(String title, String message) {
        showSingleButtonDialog(title, message);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnackBarMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLongSnackBarMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public View getCurrentView() {
        return getView();
    }

    @Override
    public boolean isInternetAvailable() {
        return InternetDetector.isConnectedToInternet(getBaseActivity());
    }

    @Override
    public void showNoInternetConnectionMessage(String message) {
        if (!InternetDetector.isConnectedToInternet(getBaseActivity())) {
            showLongSnackBarMessage(message);
        }
    }

    @Override
    public void showNoInternetConnectionMessage() {
        showNoInternetConnectionMessage(getString(R.string.no_internet_message));
    }

    private void showSingleButtonDialog(String title, String message) {
        // Create and show the dialog.
        SingleButtonDialog singleButtonDialog = new SingleButtonDialog();
        Bundle bundle = new Bundle();
        if (title != null && !title.isEmpty()) {
            bundle.putString(UIConstant.DIALOG_TITLE, title);
        }
        bundle.putString(UIConstant.DIALOG_DESCRIPTION, message);
        singleButtonDialog.setArguments(bundle);
        singleButtonDialog.show(getTransaction(SingleButtonDialog.TAG), SingleButtonDialog.TAG);
    }

    protected FragmentTransaction getTransaction(String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    /**
     * Get top of child back stack fragments.
     *
     * @return
     */
    public Fragment getTopOfBackStack() {
        FragmentManager fm = getChildFragmentManager();
        final int entryCount = fm.getBackStackEntryCount();
        if (entryCount > 0) {
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(entryCount - 1);
            return fm.findFragmentByTag(entry.getName());
        }
        return null;
    }

    /**
     * Add the given fragment to this fragment child back stack.
     *
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    public void addToBackStack(final int containerViewId, final BaseFragment fragment, final String tag) {
        getChildFragmentManager().beginTransaction().replace(containerViewId, fragment, tag).addToBackStack(tag).commit();
    }
}

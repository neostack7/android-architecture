package com.example.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;

import com.example.androidarchitecture.R;
import com.example.presentation.framework.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            attachToActivity(LoginFragment.newInstance(), LoginFragment.TAG);
        }

        setTitle(Html.fromHtml(getString(R.string.app_name_title)));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // This is an ugly hack, because the fragment's onActivityResult is not called otherwise,
        // even though the EulaActivity is started from the login fragment, not from this activity.
        // See: http://stackoverflow.com/questions/6147884/onactivityresult-not-being-called-in-fragment
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}

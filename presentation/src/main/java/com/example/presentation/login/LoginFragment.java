package com.example.presentation.login;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidarchitecture.R;
import com.example.businesslogic.login.ILoginPresenter;
import com.example.businesslogic.login.ILoginView;
import com.example.businesslogic.login.LoginPresenter;
import com.example.presentation.framework.BaseFragment;
import com.example.presentation.utils.AndroidUtils;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

/**
 *
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    public static final String TAG = LoginFragment.class.getSimpleName();

    public static final int EULA_ACTIVITY_CODE = 1;

    @Bind(R.id.et_login_email)
    protected EditText emailEditText;

    @Bind(R.id.et_login_password)
    protected EditText passwordEditText;

    @Bind(R.id.btn_login_sign_in)
    protected Button signInButton;

    @Bind(R.id.inputLayout_login_email)
    protected TextInputLayout emailTextInputLayout;

    @Bind(R.id.inputLayout_login_password)
    protected TextInputLayout passwordTextInputLayout;

    private ILoginPresenter loginPresenter;

    private boolean isValidEmail;
    private boolean isValidPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        loginPresenter = new LoginPresenter(this);
    }

    @OnTextChanged(value = R.id.et_login_email, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onEmailTextChanged(Editable s) {
        isValidEmail = loginPresenter.isValidEmail(s.toString());
        loginPresenter.updateSignInButtonState();
    }

    @OnTextChanged(value = R.id.et_login_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onPasswordTextChanged(Editable s) {
        isValidPassword = loginPresenter.isValidPassword(s.toString());
        loginPresenter.updateSignInButtonState();
    }

    @OnFocusChange(value = R.id.et_login_email)
    protected void onEmailFocusChanged(View view, boolean hasFocus) {
        if (hasFocus) {
            emailTextInputLayout.setErrorEnabled(false);
        } else if (!isValidEmail) {
            emailTextInputLayout.setErrorEnabled(true);
            emailTextInputLayout.setError(getString(R.string.enter_valid_email));
        }
    }

    @OnFocusChange(value = R.id.et_login_password)
    protected void onPasswordFocusChanged(View view, boolean hasFocus) {
        if (hasFocus) {
            passwordTextInputLayout.setErrorEnabled(false);
        } else if (!isValidPassword) {
            passwordTextInputLayout.setErrorEnabled(true);
            passwordTextInputLayout.setError(getString(R.string.enter_valid_password));
        }
    }

    @OnClick(R.id.btn_login_sign_in)
    protected void onSignInButtonClicked() {
        AndroidUtils.hideKeyboard(getActivity());
        if (!isInternetAvailable()) {
            showNoInternetConnectionMessage();
            return;
        }
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginPresenter.doSignIn(email, password);
    }

    @Override
    public void updateSignInButtonState(boolean isSignInReady) {
        signInButton.setEnabled(isSignInReady);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginPresenter.releaseResources();
        loginPresenter = null;
    }

}


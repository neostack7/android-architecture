package com.example.presentation.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidarchitecture.R;


/**
 * Allows displaying and hiding of progressbar and messages to the user.
 * To use this fragment, instantiate the fragment and add to your view. Use the busy and message
 * methods to show hide progress bar and messages to the user.
 */
public class ProgressBarFragment extends Fragment {

    public static final String TAG = ProgressBarFragment.class.getSimpleName();

    private LinearLayout progressBarContainer;
    private ProgressBar progressBar;
    private TextView textView;

    private static class State {
        int busy;
        String msg;

        public boolean isBusy() {
            return busy > 0;
        }

        public boolean hasMsg() {
            return msg != null && !msg.trim().isEmpty();
        }

        public void clearMsg() {
            msg = null;
        }

        public void setMsg(final String msg) {
            this.msg = msg;
        }
    }

    private State state = new State();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ProgressBarFragment newInstance() {
        return new ProgressBarFragment();
    }

    public ProgressBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.view_progress_container, container, false);
        progressBarContainer = (LinearLayout) view.findViewById(R.id.progress_container);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        textView = (TextView) view.findViewById(R.id.progress_text);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        applyState();
    }

    private void applyState() {
        if (getView() != null) {
            progressBar.setVisibility(state.isBusy() ? View.VISIBLE : View.GONE);
            textView.setVisibility(state.hasMsg() ? View.VISIBLE : View.GONE);
            textView.setText(state.hasMsg() ? state.msg : "");

            FragmentManager fm = getParentFragment() == null ? getActivity().getSupportFragmentManager() : getChildFragmentManager();
            FragmentTransaction trans = fm.beginTransaction();
            if (state.isBusy() || state.hasMsg()) {
                trans.show(this);
            } else {
                trans.hide(this);
            }
            trans.commitAllowingStateLoss();
        }
    }

    public boolean isBusy() {
        return state.isBusy();
    }

    /**
     * Increment busy count. Must be balanced with #decrementBusy().
     */
    public void pushBusy() {
        progressBarContainer.setVisibility(View.VISIBLE);
        ++state.busy;
        applyState();
    }

    /**
     * Decrement busy count. Must be balanced with #pushBusy
     */
    public void popBusy() {
        --state.busy;
        applyState();
    }

    public void clearMsg() {
        state.clearMsg();
        applyState();
    }

    public void setMsg(final String msg) {
        --state.busy;
        state.setMsg(msg);
        applyState();
    }
}

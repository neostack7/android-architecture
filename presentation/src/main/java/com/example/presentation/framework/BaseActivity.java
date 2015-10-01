package com.example.presentation.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.androidarchitecture.R;


/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // Configure Action Bar
        configureActionBar();
    }

    /**
     * Exposed for those activities, who wants to override base layout.
     * Instead of calling setContentView, Override this method from sub class activity.
     * e.g: Navigation drawer Activities.
     *
     * @return
     */
    protected int getLayoutResourceId() {
        return R.layout.activity_base;
    }

    protected void configureActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(int titleId) {
        String title = getResources().getString(titleId);
        setTitle(title);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * Get activity top of back stack fragment.
     */
    protected BaseFragment getActiveFragment() {
        final FragmentManager fm = getSupportFragmentManager();
        final int entryCount = fm.getBackStackEntryCount();
        if (entryCount > 0) {
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(entryCount - 1);
            return (BaseFragment) fm.findFragmentByTag(entry.getName());
        }
        return null;
    }

    protected void popActiveFragment() {
        getSupportFragmentManager().popBackStack();
    }

    protected void popAllFragments() {
        final int entryCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < entryCount; ++i) {
            getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * Add the given fragment to the back stack.
     *
     * @param fragment
     * @param tag
     * @param replace  whether to replace current top of back stack
     */
    public void addToBackStack(final BaseFragment fragment, final String tag, final boolean replace) {
        final BaseFragment topOfBackStack = getActiveFragment();
        if (replace && topOfBackStack != null) {
            popActiveFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(getFragmentContainerId(), fragment, tag).addToBackStack(tag).commit();
    }

    public void addToBackStack(final BaseFragment fragment, final String tag) {
        addToBackStack(fragment, tag, false);
    }

    protected void replaceTopOfBackStack(final BaseFragment fragment, final String tag) {
        addToBackStack(fragment, tag, true);
    }

    protected void attachToActivity(final BaseFragment fragment, final String tag) {
        final BaseFragment topOfBackStack = getActiveFragment();
        if (topOfBackStack != null) {
            popActiveFragment();
        }
        getSupportFragmentManager().beginTransaction().add(getFragmentContainerId(), fragment, tag).commit();
    }

    /**
     * Get id for back stack fragment container.
     */
    private int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getActiveFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}

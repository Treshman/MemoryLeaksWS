package com.mustashioid.memoryleaksws.base;

import android.support.v4.app.Fragment;

import com.mustashioid.memoryleaksws.app.LeaksApp;

/**
 * Created by svarniem on 6/9/17.
 */

// TODO 5. Adding custom objects to watcher
public abstract class BaseFragment extends Fragment {

    abstract String getFragmentName();

    @Override
    public void onDestroy() {
        // TODO 5.1 Watch all Fragments
        LeaksApp.get(getContext()).leaksWatcher().watch(this, getFragmentName());
        super.onDestroy();
    }

}

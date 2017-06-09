package com.mustashioid.memoryleaksws;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import timber.log.Timber;

public class LeaksActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaks_list);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                Timber.d("onClick");
                startAsyncTask();
            }
        });
    }

    void startAsyncTask() {
        // This async task is an anonymous class and therefore has a hidden reference to the outer
        // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
        // the activity instance will leak.
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Timber.d("doInBackground start");
                // Do some slow work in background
                SystemClock.sleep(60000);
                Timber.d("doInBackground done");
                return null;
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        // it's optional since LeakCanary starts tracking Activities in
//        LeaksApp.get(this).leaksWatcher().watch(this, "LeaksActivity");
        super.onDestroy();
    }
}

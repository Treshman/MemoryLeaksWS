package com.mustashioid.memoryleaksws.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mustashioid.memoryleaksws.dev.IDevTools;
import com.mustashioid.memoryleaksws.dev.di.DevToolsProvider;
import com.mustashioid.memoryleaksws.dev.leakscanary.IMemoryLeaksWatcher;

import timber.log.Timber;

// TODO 4. Init Watcher in Application
// For more details https://github.com/square/leakcanary
public class LeaksApp extends Application {

    @NonNull
    public static LeaksApp get(@NonNull final Context context) {
        return (LeaksApp) context.getApplicationContext();
    }

    IMemoryLeaksWatcher leaksWatcher;
    IDevTools devTools;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App", "onCreate pid=" + android.os.Process.myPid());
        leaksWatcher = DevToolsProvider.provideLeakCanary(this);
        // TODO 4.1 Not init Application if it's not app proccess
        if (leaksWatcher.isInAnalyzerProcess()) {
            Log.d("App", "isInAnalyzerProcess! Don't init app.");
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        initDevTools();
        // Normal app init code...
    }

    public IMemoryLeaksWatcher leaksWatcher() {
        return leaksWatcher;
    }

    private void initDevTools() {
        // TODO 4.2 Init DevTools with StrictMode and Timber
        devTools = DevToolsProvider.provideDevTools();
        devTools.enableStrinctMode();
        devTools.plantTimber();
        Timber.d("initDevTools pid=%d, ", android.os.Process.myPid());
        // TODO 4.3 Init LeakCanaryProxy so it's ready to watch our Objects!
        // LeakCanary automatically will watch all your Activities
        // https://github.com/square/leakcanary/blob/master/leakcanary-android/src/main/java/com/squareup/leakcanary/ActivityRefWatcher.java
        leaksWatcher.init();
    }

}

package com.mustashioid.memoryleaksws.dev.di;

import android.app.Application;
import android.os.StrictMode;

import com.mustashioid.memoryleaksws.dev.IDevTools;
import com.mustashioid.memoryleaksws.dev.leakscanary.IMemoryLeaksWatcher;
import com.mustashioid.memoryleaksws.dev.leakscanary.LeakCanaryWatcher;

import timber.log.Timber;

/**
 * Created by svarniem on 6/8/17.
 */

public final class DevToolsProvider {

    private DevToolsProvider() {
    }

    /**
     * NoOps LeakCanary
     *
     * @return
     */
    public static IDevTools provideDevTools() {
        return new IDevTools() {

            @Override
            public void enableStrinctMode() {
                Timber.d("enableStrinctMode");
                // TODO 4.2 Init StrictMode. Very useful tool show leaked Activitie, db, cursors and many other informatin.
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        //.penaltyDeath()
                        .build());
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        //.penaltyDeath()
                        .build());
            }

            @Override
            public void plantTimber() {
                Timber.d("plantTimber enables logs");
                // TODO 4.2 Enable logs
                Timber.plant(new Timber.DebugTree());
            }
        };
    }

    /**
     * NoOps LeakCanary
     */
    public static IMemoryLeaksWatcher provideLeakCanary(final Application application) {
        return new LeakCanaryWatcher(application);
    }

}

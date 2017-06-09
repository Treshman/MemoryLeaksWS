package com.mustashioid.memoryleaksws.dev.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.mustashioid.memoryleaksws.dev.IDevTools;
import com.mustashioid.memoryleaksws.dev.leakscanary.IMemoryLeaksWatcher;

import timber.log.Timber;

/**
 * No-op provider
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
                // no-op
            }

            @Override
            public void plantTimber() {
                Timber.plant(new Timber.Tree() {

                    @Override
                    protected void log(final int i, final String s, final String s1, final Throwable throwable) {
                        // no-op
                    }
                });
            }
        };
    }

    /**
     * NoOps LeakCanary
     */
    public static IMemoryLeaksWatcher provideLeakCanary(final Application application) {
        return new IMemoryLeaksWatcher() {

            @Override
            public boolean isInAnalyzerProcess() {
                return false;
            }

            @Override
            public void init() {
                //no-op
            }

            @Override
            public void watch(@NonNull final Object object) {
                //no-op
            }

            @Override
            public void watch(@NonNull final Object object, @NonNull final String referenceName) {
                //no-op
            }
        };
    }

}
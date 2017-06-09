package com.mustashioid.memoryleaksws.dev.leakscanary;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * It's a Proxy for LeakCanary library
 */
public class LeakCanaryWatcher implements IMemoryLeaksWatcher {

    @NonNull
    private final Application app;

    @Nullable
    private RefWatcher refWatcher;

    public LeakCanaryWatcher(@NonNull final Application app) {
        this.app = app;
    }

    @Override
    public boolean isInAnalyzerProcess() {
        return LeakCanary.isInAnalyzerProcess(app.getApplicationContext());
    }

    @Override
    public void init() {
        Timber.d("init LeakCanary");
        refWatcher = LeakCanary.install(app);
    }

    @Override
    public void watch(@NonNull final Object object) {
        if (refWatcher != null) {
            refWatcher.watch(object);
        }
    }

    @Override
    public void watch(@NonNull final Object object, @NonNull final String referenceName) {
        if (refWatcher != null) {
            refWatcher.watch(object, referenceName);
        }
    }
}

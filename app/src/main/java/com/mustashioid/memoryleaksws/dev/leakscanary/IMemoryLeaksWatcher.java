package com.mustashioid.memoryleaksws.dev.leakscanary;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * It's a Proxy for LeakCanary library
 */
public interface IMemoryLeaksWatcher {

    boolean isInAnalyzerProcess();

    void init();

    void watch(@NonNull Object object);

    void watch(@NonNull Object object, @NonNull String referenceName);

}
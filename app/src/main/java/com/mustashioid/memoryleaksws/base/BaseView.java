package com.mustashioid.memoryleaksws.base;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mustashioid.memoryleaksws.app.LeaksApp;

/**
 * Created by svarniem on 6/9/17.
 */

public class BaseView extends FrameLayout {

    public BaseView(@NonNull final Context context) {
        super(context);
    }

    public BaseView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // IMPORTANT start all listeners/animations etc here
        // Stay symmetric!
    }

    @Override
    protected void onDetachedFromWindow() {
        // TODO 5.2 Watch Custom Views
        LeaksApp.get(getContext()).leaksWatcher().watch(this);
        super.onDetachedFromWindow();
        // IMPORTANT stop all listeners/animations etc here
        // Stay symmetric!
    }

}

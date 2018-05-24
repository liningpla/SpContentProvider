package com.pl.sphelper;

import android.database.ContentObserver;
import android.os.Handler;

/**
 * Created by lining on 2018/5/24.
 */

class MyContentObserver extends ContentObserver {
    SPHelper.DateChangeListener listener;
    public MyContentObserver(Handler handler, SPHelper.DateChangeListener listener) {
        super(handler);
        this.listener = listener;
    }
    @Override
    public void onChange(boolean selfChange) {
        if(listener != null){
            listener.onChange(selfChange);
        }
    }
}

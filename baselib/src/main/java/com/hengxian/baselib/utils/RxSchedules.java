package com.hengxian.baselib.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedules
{

    /**
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> ioToMain()
    {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

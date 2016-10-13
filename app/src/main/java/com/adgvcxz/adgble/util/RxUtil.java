package com.adgvcxz.adgble.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class RxUtil {

    public static <T> Observable.Transformer<T, T> rxScheduleHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io());
    }

}

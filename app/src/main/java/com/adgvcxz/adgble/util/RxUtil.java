package com.adgvcxz.adgble.util;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class RxUtil {

    public static <T> Observable.Transformer<T, T> rxScheduleHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T, R> Observable.Transformer<List<T>, List<R>> rxTransformList(Func1<T, R> func1) {
        return listObservable -> listObservable.flatMapIterable(ts -> ts).map(func1).toList();
    }
}

package com.adgvcxz.adgble.util;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import java.util.List;

import rx.AsyncEmitter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import static android.databinding.Observable.OnPropertyChangedCallback;


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

    public static Observable<Integer> toObservableInt(@NonNull final ObservableInt observableInt) {
        return Observable.fromAsync(asyncEmitter -> {

            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableInt) {
                        asyncEmitter.onNext(observableInt.get());
                    }
                }
            };
            observableInt.addOnPropertyChangedCallback(callback);
            asyncEmitter.setCancellation(() -> observableInt.removeOnPropertyChangedCallback(callback));
        }, AsyncEmitter.BackpressureMode.LATEST);
    }
}

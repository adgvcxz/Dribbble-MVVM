package com.adgvcxz.adgble.util;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.util.Log;

import com.adgvcxz.adgble.R;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableFromCallable;
import io.reactivex.schedulers.Schedulers;

import static android.databinding.Observable.OnPropertyChangedCallback;


/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class RxUtil {

    public static <T> ObservableTransformer<T, T> rxScheduleHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T, R> ObservableTransformer<List<T>, List<R>> rxTransformList(Function<T, R> function) {
        return listObservable -> listObservable.flatMapIterable(ts -> ts).map(function).toList().toObservable();
    }

    public static Flowable<Integer> toObservableInt(@NonNull final ObservableInt observableInt) {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                        if (dataBindingObservable == observableInt) {
                            emitter.onNext(observableInt.get());
                        }
                    }
                };
                observableInt.addOnPropertyChangedCallback(callback);
                emitter.setCancellable(() -> observableInt.removeOnPropertyChangedCallback(callback));
            }
        }, BackpressureStrategy.DROP);
    }


    public static <T> Flowable<T> toObservableField(@NonNull final ObservableField<T> observableField) {
        return Flowable.create(emitter -> {

            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableField) {
                        emitter.onNext(observableField.get());
                    }
                }
            };
            observableField.addOnPropertyChangedCallback(callback);
            emitter.setCancellable(() -> observableField.removeOnPropertyChangedCallback(callback));
        }, BackpressureStrategy.DROP);
    }

    public static Observable<Integer> toObservablePalette(Bitmap bitmap, int defaultColor) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Palette.PaletteAsyncListener listener = palette -> {
                    Palette.Swatch swatch = palette.getMutedSwatch();
                    if (swatch == null) {
                        swatch = palette.getDarkVibrantSwatch();
                    }
                    if (swatch == null) {
                        swatch = palette.getLightMutedSwatch();
                    }
                    if (swatch != null) {
                        e.onNext(swatch.getRgb());
                    } else {
                        e.onNext(defaultColor);
                    }
                    System.gc();
                    Runtime.getRuntime().gc();
                    e.onComplete();
                };
                Palette.from(bitmap).generate(listener);
            }
        });
    }
}

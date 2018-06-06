package com.teaching.android.miprimeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

;import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class ReactiveXActivity extends AppCompatActivity {

    private int contar = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive_x);

        //Observable integerObservable = Observable.create(new ObservableOnSubscribe() {
          //  @Override
            //public void subscribe(ObservableEmitter emitter) throws Exception {
             //   do{
               //     try {
                 //       emitter.onNext(contar);
                   //     Thread.sleep(1000);
                    //} catch (Exception e) {
                     //   e.printStackTrace();
                    //}
                   // Log.d("contar","valor:"+contar);
                //} while (contar++<10);

              // emitter.onComplete();
            //}
        //});

        Observable.just(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0).filter(new Predicate<Double>() {
            @Override
            public boolean test(Double aDouble) throws Exception {
                return aDouble%2==0;
            }
        }).map(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return Math.pow((double)o,2);
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
            Log.d("RxANDROID",o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}

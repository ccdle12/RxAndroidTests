package com.example.christophercoverdale.rxandroidtest;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 1. */
        //Observable emits "Hello, World!"
        rx.Observable<String> myObservable = rx.Observable.just("Hello, World!");



        /* 2. */
        //Observer to consume above emitted data
        rx.Observer myObserver = new rx.Observer<String>()
        {
            @Override
            public void onCompleted()
            {
                //Callback when no more data to emit
            }

            @Override
            public void onError(Throwable e)
            {
                //Callback when error
            }

            @Override
            public void onNext(String emit)
            {
                //Callback on each Data Emitted
                Log.d("My Obsever: ",  emit);
            }
        };

        // Subscribing observer to observable
         Subscription mySubscription = myObservable.subscribe(myObserver);




        /* 3. */
        Action1<String> myAction = new Action1<String>()
        {
            @Override
            public void call(String emit)
            {
                Log.d("My Action: ", emit);
            }
        };

        // Subscribing Action1 to observable
        Subscription actionSubscription = myObservable.subscribe(myAction);

        // Removing subscriptions
        mySubscription.unsubscribe();
        actionSubscription.unsubscribe();



        /* 4. */
        /* Observable from */
        //Apply map function on the array and square each integer
        //When we log print after subscribing all the integers are squared
        rx.Observable<Integer> myArrayObservable = rx.Observable.from(new Integer[]{1, 2, 3, 4, 5, 6}); // Emits each integer

        myArrayObservable = myArrayObservable.map(new Func1<Integer, Integer>()
        {

            @Override
            public Integer call(Integer integer)
            {
                return integer * integer;
            }
        });

        myArrayObservable.subscribe(new Action1<Integer>()
        {
            @Override
            public void call(Integer integer)
            {
                Log.d("My array observ: ", "int: " + integer);
            }
        });




        /* 5. */
        //Use filter to skip over the first 2 items
        //Only print even numbers
        myArrayObservable
                .skip(2)
                .filter(new Func1<Integer, Boolean>()
                {
                    @Override
                    public Boolean call(Integer integer)
                    {
                        Log.d("My Filter: ", "Filtered: " + integer);
                        return integer % 2 == 0;
                    }
                });

    }
}

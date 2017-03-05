package com.boc.lfj.httpdemo.rx;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;

import com.boc.lfj.httpdemo.common.log.Logger;
import com.boc.lfj.httpdemo.rx.bean.Book;
import com.boc.lfj.httpdemo.rx.bean.Course;
import com.boc.lfj.httpdemo.rx.bean.Student;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/2/24.
 */
//Observable (可观察者，即被观察者)、 Observer (观察者)、 subscribe (订阅)
public class RxTest {
    public static final String tag = "LOG";
    Observer<String> observer;
    Subscriber<String> subscriber;
    Observable observable;

    //1、创建 Observer
    public void createObserver() {
        observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        //Subscriber 是observer的抽象类
        subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                Logger.d(s);
            }
        };
    }

    //2、创建 Observable
    public void createObservable() {
        observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        });

        observable = Observable.just("hello", "new jop");

        String[] words = {"Hello", "Hi", "Aloha"};
        observable = Observable.from(words);
    }

    //订阅
    public void subscribe() {
        observable.subscribe(observer);
        observable.subscribe(subscriber);


        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d(tag, "completed");
            }
        };

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }


    public void observer() {
        if (subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }



    public static void main(String[] args){
        Observable observable = Observable.just("hello", "new jop");
        Observer<String> oberver = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(oberver);

        String[] words = {"Hello", "Hi", "Aloha"};

        Observable.from(words)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });


        Observable.just("/path")
                .map(new Func1<String,Bitmap>(){
                    @Override
                    public Bitmap call(String s) {
                        return getBitmapFromPath(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        showBitmap(bitmap);
                    }
                });

        Student[] students = null;
        //打印学生姓名
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .flatMap(new Func1<Course, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(Course course) {
                        return Observable.from(course.getBooks());
                    }
                })
                .map(new Func1<Book, String>() {
                    @Override
                    public String call(Book book) {
                        return book.getName();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
    }


    public static Bitmap getBitmapFromPath(String path){

        return null;
    }

    public static void showBitmap(Bitmap b){

    }
}

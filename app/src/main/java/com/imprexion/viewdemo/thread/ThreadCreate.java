package com.imprexion.viewdemo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @author : gongh
 * @date : 2020/7/20 14:39
 * @desc : TODO
 */
class ThreadCreate {

    public static void main(String[] args) {
//        threadCreateDemo1();
//        threadCreateDemo2();
//        threadCreateDemo3();
//        threadCreateDemo4();
        threadCreateDemo5();
    }

    private static void threadCreateDemo5() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("thread = " + Thread.currentThread().getName());
                Thread.sleep(2000);
                return "hello";
            }
        };
        Future<String> submit = executorService.submit(callable);
        try {
            String s = submit.get();
            System.out.println("result= " + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    // Executor 开启
    private static void threadCreateDemo4() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(0);
        scheduledExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread = " + Thread.currentThread().getName());
            }
        });
    }

    // ThreadFactory 开启
    private static void threadCreateDemo3() {
        ThreadFactory factory = new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable r) {
                count++;
                return new Thread(r, "Thread- " + count);
            }
        };
        factory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread = " + Thread.currentThread().getName());
            }
        }).start();

        factory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread = " + Thread.currentThread().getName());
            }
        }).start();
    }

    // 传入一个runnable
    private static void threadCreateDemo2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread = " + Thread.currentThread().getName());
            }
        }).start();
    }

    // new Thread 开启新线程
    private static void threadCreateDemo1() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("thread = " + Thread.currentThread().getName());
            }
        }.start();
    }
}

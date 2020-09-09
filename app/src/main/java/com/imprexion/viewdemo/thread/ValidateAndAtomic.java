package com.imprexion.viewdemo.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : gongh
 * @date : 2020/7/20 15:38
 * @desc : TODO
 */
class ValidateAndAtomic {
    static volatile boolean flag = false;

    static volatile AtomicInteger count = new AtomicInteger(0);
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    public static int getNewCount() {
        try {
            readLock.lock();
            return newCount;
        } finally {
            readLock.unlock();
        }

    }

    public static void setNewCount() {
        try {
            writeLock.lock();
            newCount++;
        } finally {
            writeLock.unlock();
        }

    }

    static int newCount = 0;

    public static void main(String[] args) {
//        validateDemo();

//        atomicDemo();

        lockDemo();
    }

    private static void lockDemo() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1000000; i++) {
                    setNewCount();
                    System.out.println("ThreadName- " + Thread.currentThread().getName() + " " + getNewCount());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1000000; i++) {
                    setNewCount();
                    System.out.println("ThreadName- " + Thread.currentThread().getName() + " " + getNewCount());
                }
            }
        }.start();
    }

    private static void atomicDemo() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1000000; i++) {
                    count.getAndIncrement();
                    System.out.println("ThreadName- " + Thread.currentThread().getName() + " " + count);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1000000; i++) {
                    count.getAndIncrement();
                    System.out.println("ThreadName- " + Thread.currentThread().getName() + " " + count);
                }
            }
        }.start();
    }

    private static void validateDemo() {
        new Thread() {
            @Override
            public void run() {
                while (!flag) {
                }
                System.out.println("over");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                flag = true;
            }
        }.start();
    }
}

package com.imprexion.viewdemo.thread;

/**
 * @author : gongh
 * @date : 2020/7/20 16:01
 * @desc : TODO
 */
class WaitAndNotify {
    public static String value;


    public synchronized static void initValue() {
        value = "initValue";
//        notifyAll();
    }

    public synchronized static void printValue() {
        while (value == null) {
            try {
//                wait();
            } catch (Exception e) {

            }
        }
        System.out.println("value= " + value);
    }

    public static void main(String[] args) {
        waitAndNotifyDemo();
    }

    /**
     * 测试wait 和 notify
     */
    private static void waitAndNotifyDemo() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                initValue();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                printValue();
            }
        }.start();
    }
}

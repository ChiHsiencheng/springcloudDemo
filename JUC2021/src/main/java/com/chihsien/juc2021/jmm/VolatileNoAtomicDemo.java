package com.chihsien.juc2021.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author ChiHsien<br>
 * @version
 */
class MyNumber {
    volatile int number = 0;

    public void addPlusPlus() {
        number++;
    }
}

public class VolatileNoAtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + myNumber.number);
    }
}


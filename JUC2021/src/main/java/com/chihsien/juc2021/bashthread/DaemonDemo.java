package com.chihsien.juc2021.bashthread;

import java.util.concurrent.TimeUnit;

/***
 * @describe
 *
 * @return 演示守护线程和用户线程
 * @author ChiHsien<br>
 * @version
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in：\t"
                    + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {

            }
        }, "a");
        a.setDaemon(true);
        a.start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + " ----task is over");
    }
}

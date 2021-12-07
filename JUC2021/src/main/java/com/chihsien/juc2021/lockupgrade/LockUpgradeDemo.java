package com.chihsien.juc2021.lockupgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author chihsiencheng
 */
public class LockUpgradeDemo {
    public static void main(String[] args) {
        //-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
        Object o = new Object();


        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                synchronized (o) {
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }
            }).start();
        }
    }

    public static void noLock() {
        Object o = new Object();
        System.out.println(o.hashCode());
        System.out.println(Integer.toHexString(o.hashCode()));

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}

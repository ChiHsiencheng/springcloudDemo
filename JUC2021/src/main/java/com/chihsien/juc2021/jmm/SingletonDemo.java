package com.chihsien.juc2021.jmm;

/***
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class SingletonDemo {
    private SingletonDemo() {
    }

    private static class SingletonDemoHandler {
        private static SingletonDemo instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance() {
        return SingletonDemoHandler.instance;
    }
}

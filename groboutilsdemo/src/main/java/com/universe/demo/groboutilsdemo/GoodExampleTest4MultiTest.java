package com.universe.demo.groboutilsdemo;

import junit.framework.TestCase;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * 
 * @author Ethan Chen
 * 
 */
public class GoodExampleTest4MultiTest extends TestCase {
    private Runnable runnable;

    public class DelayedHello extends TestRunnable {
        private int count;
        private Thread worker;

        private DelayedHello(int count) {
            this.count = count;
            this.worker = new Thread(this);
            this.worker.start();
        }

        @Override
        public void runTest() {
            try {
                Thread.sleep(this.count);
                System.out.println("Delayed Hello World");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testExampleThread() throws Throwable {
        System.out.println("Hello, World"); // 1
        this.runnable = new DelayedHello(5000); // 2
        System.out.println("Goodbye, World"); // 3
    }

    public static void main(String[] args) {
        String[] name = {BadExampleTest4Multithread.class.getName()};
        junit.textui.TestRunner.main(name);
    }

}

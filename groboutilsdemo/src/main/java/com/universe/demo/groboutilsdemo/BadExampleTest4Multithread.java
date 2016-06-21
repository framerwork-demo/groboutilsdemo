package com.universe.demo.groboutilsdemo;

import junit.framework.TestCase;

/**
 * TextHellWorld()方法像你期望的那样运行和结束。它既没有发出任何有关线程的异常，也不会接受到来自线程的返回信息。
 * 
 * 注意，你不会看到“Delayed Hello World”。为什么？因为线程还在激活状态的时候，
 * Junit已经执行完成。问题发生在下面这行，使线程执行结束的时候，
 * 你的测试不能反映出它的执行结果。这个问题行是在Junit的TestRunner中。
 * 它没有被设计成搜寻Runnable实例，并且等待这些线程发出报告，它只是执行它们并且忽略了它们的存在。
 * 因为这个原因，几乎不可能在Junit中编写和维护多线程的单元测试
 * 
 * @author Ethan Chen
 * 
 */
public class BadExampleTest4Multithread extends TestCase {
    private Runnable runnable;

    public class DelayedHello implements Runnable {
        private int count;
        private Thread worker;

        private DelayedHello(int count) {
            this.count = count;
            this.worker = new Thread(this);
            this.worker.start();
        }

        public void run() {
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


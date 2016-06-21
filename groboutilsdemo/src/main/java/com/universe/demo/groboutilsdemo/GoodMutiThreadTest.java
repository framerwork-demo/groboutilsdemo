package com.universe.demo.groboutilsdemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

public class GoodMutiThreadTest {

    static String[] path = new String[] {""};
    static Map<String, String> countMap = new Hashtable<String, String>();
    static Map<String, String> countMap2 = new Hashtable<String, String>();
    static Set<String> countSet = new HashSet<String>();
    static List<String> list = new ArrayList<String>();

    @Test
    public void testThreadJunit() throws Throwable {
        // Runner数组，想当于并发多少个。
        TestRunnable[] trs = new TestRunnable[3];
        for (int i = 0; i < 3; i++) {
            trs[i] = new ThreadA();
        }

        // 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);

        // 开发并发执行数组里定义的内容
        mttr.runTestRunnables();

    }

    private class ThreadA extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            // 测试内容
            myCommMethod2();
        }
    }

    public void myCommMethod2() throws Exception {
        System.out.println("===" + Thread.currentThread().getId() + "begin to execute myCommMethod2");
        for (int i = 0; i < 10; i++) {
            int a = i * 5;
            System.out.println(a);
        }
        System.out.println("===" + Thread.currentThread().getId() + "end to execute myCommMethod2");
    }
}

package com.wzd.core.utils;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class TcpScanner implements Runnable {
    private static final String host = "127.0.0.1"; // 扫描Ip地址
    public static final int MIN_PORT_NUMBER = 1;// 端口号起
    public static final int MAX_PORT_NUMBER = 65536;// 端口号结束
    public static final int threadCnt = 200;// 线程数


    /**
     * 数据执行
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        int totalPortCnt = MAX_PORT_NUMBER - MIN_PORT_NUMBER;// 待处理端口数
        int threadDealCnt = totalPortCnt / threadCnt;// 每一个线程处理的数目
        int lastThreadDealCnt = totalPortCnt % threadCnt;// 最后一个线程处理数目
        System.out.println("待处理数" + totalPortCnt);
        System.out.println("每一个线程处理的数目" + threadDealCnt);
        System.out.println("余数为 " + lastThreadDealCnt);
        CountDownLatch countDownLatch = new CountDownLatch(threadCnt);


        int moreLastCnt = MIN_PORT_NUMBER - 1;
        // 线程分发
        for (int threadIndex = 1; threadIndex <= threadCnt; threadIndex++) {
            int startPort = 0;
            int endPort = 0;
            // 余数分布到每一个线程里面去处理
            startPort = moreLastCnt + 1;
            if (threadIndex <= lastThreadDealCnt && 0 != lastThreadDealCnt) {
                endPort = startPort + threadDealCnt;
            } else {
                endPort = startPort + (threadDealCnt - 1);
            }
            moreLastCnt = endPort;
            new TcpScanner(startPort, endPort, countDownLatch).start();
        }
        countDownLatch.await();
    }



    // 主要业务处理逻辑
    private int begIndex;
    private int endIndex;
    private Thread thread;
    private CountDownLatch countDownLatch;


    public TcpScanner(int begIndex, int endIndex, CountDownLatch countDownLatch) {
        super();
        this.begIndex = begIndex;
        this.endIndex = endIndex;
        this.countDownLatch = countDownLatch;
        thread = new Thread(this);
    }


    /**
     * 线程启动
     */
    public TcpScanner start() {
        thread.start();
        System.out.println(thread.getName() + "deal [" + this.begIndex + "----" + this.endIndex + "]"
                + "[" + (this.endIndex - this.begIndex + 1) + "]");
        return this;
    }


    // 线程执行
    @Override
    public void run() {
        for (int i = begIndex; i <= endIndex; i++) {
            if (scan(i, 5000)) {
                System.out.println("port Listener " + i);
            }
        }
        System.out.println("thread has deal finished " + thread.getName());
        countDownLatch.countDown();
    }


    // 端口扫描
    public boolean scan(int port, int timeOut) {
        boolean flag = false;
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(timeOut);
            flag = true;
            System.out.println("扫描到端口:" + port );  //解决的问题是每扫描完一个建立连接非常消耗资源 所以一定要关闭
            //socket.close();
        } catch (IOException e) {
            //System.out.println("端口连接不成功！");
        } finally {
            try {
                if (socket != null) {
                    System.out.println("连接关闭");
                    socket.close();
                }
            } catch (Exception e) {
            }
        }
        return flag;
    }

}


/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.xuidemo.fragment.expands.print.gpprint;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator
 *
 * @author 猿史森林
 *         Date: 2017/11/2
 *         Class description:
 */
public class ThreadPool {

    private static ThreadPool threadPool;
    /**
     * java线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 最大线程数
     */
    private final static int MAX_POOL_COUNTS = 20;

    /**
     * 线程存活时间
     */
    private final static long ALIVETIME = 200L;

    /**
     * 核心线程数
     */
    private final static int CORE_POOL_SIZE = 20;

    /**
     * 线程池缓存队列
     */
    private BlockingQueue<Runnable> mWorkQueue = new ArrayBlockingQueue<>(CORE_POOL_SIZE);

    private ThreadFactory threadFactory = new ThreadFactoryBuilder("ThreadPool");

    private ThreadPool() {
        //初始化线程池 核心线程数为20，最大线程数30，线程存活200L，线程队列mWorkQueue,
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_COUNTS, ALIVETIME, TimeUnit.SECONDS, mWorkQueue, threadFactory);
    }

    public static ThreadPool getInstantiation() {
        if (threadPool == null) {
            threadPool = new ThreadPool();
        }
        return threadPool;
    }
    //为线程池添加任务
    public void addTask(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("addTask(Runnable runnable)传入参数为空");
        }
        if (threadPoolExecutor != null && threadPoolExecutor.getActiveCount() < MAX_POOL_COUNTS) {
            threadPoolExecutor.execute(runnable);
        }
    }

    public void stopThreadPool() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
            threadPool = null;
        }
    }
}
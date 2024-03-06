package lhweb.asia.LHTomCat.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器
 * 单例 饿汉式
 * @author 罗汉
 * @date 2024/03/06
 */
public class ThreadPoolManager {
    private static final ThreadPoolManager instance=new ThreadPoolManager();
    private final ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolManager() {//单例模式 构造函数 私有 禁止别人创建
        //线程池的执行对象
         threadPoolExecutor=
                // new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,         handler);
                //                        核心线程数      最大线程数         激活时间       时间单位  排队的集合   创建线程的方式:默认工厂   拒绝策略
                new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(20), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }


    public static ThreadPoolManager getInstance() {
        return instance;
    }
    public ThreadPoolExecutor getThreadPoolExecutor(){
        return instance.getThreadPoolExecutor();
    }
    public void execute(Runnable task){
        threadPoolExecutor.execute(task);
    }
}

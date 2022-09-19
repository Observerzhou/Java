package com.example.template.common.config.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 线程池配置类
 * @author: zhouhuihui
 * @date: 2022/9/7
 **/
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {

    /**
     * 通用线程池配置
     * 使用时在需要使用线程池执行的方法上加注解：@Async(“commonThreadPool”)
     *
     * @return java.util.concurrent.Executor
     */
    @Bean
    public Executor commonThreadPool() {
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        int core = Runtime.getRuntime().availableProcessors();
        // 核心线程数
        executor.setCorePoolSize(core);
        // 最大线程数
        executor.setMaxPoolSize(core * 2 + 1);
        // 任务队列大小
        executor.setQueueCapacity(10000);
        // 线程池中的线程的名称前缀
        executor.setThreadNamePrefix("[Common-ThreadTool]");
        // 拒绝策略：由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}

package cn.apkr.framework.config;

import cn.apkr.common.utils.ThreadUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

	// 核心线程池大小
	private final int corePoolSize = 50;

	// 最大可创建的线程数
	private final int maxPoolSize = 200;

	// 队列最大长度
	private final int queueCapacity = 1000;

	// 线程池维护线程所允许的空闲时间
	private final int keepAliveSeconds = 300;

	/**
	 * 线程池对象
	 */
	@Bean(name = "threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(maxPoolSize);
		executor.setCorePoolSize(corePoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		// 线程池对拒绝任务（无线程可用）的处理策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	/**
	 * 执行周期性或定时任务
	 */
	@Bean(name = "scheduledExecutorService")
	protected ScheduledExecutorService scheduledExecutorService() {
		return new ScheduledThreadPoolExecutor(corePoolSize,
				new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
				new ThreadPoolExecutor.CallerRunsPolicy()) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				ThreadUtils.printException(r, t);
			}
		};
	}
}

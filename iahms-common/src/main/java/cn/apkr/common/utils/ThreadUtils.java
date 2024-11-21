package cn.apkr.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadUtils {

	private static final Logger log = LoggerFactory.getLogger(ThreadUtils.class);

	/**
	 * 线程等待
	 * @param milliseconds 等待毫秒数
	 */
	public static void sleep(long milliseconds) {
		try	{
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			log.error("线程睡眠操作异常: {}", e.getMessage(), e);
			return;
		}
	}

	/**
	 * 停止线程池
	 * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
	 * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
	 * 如果仍然超時，則強制退出.
	 * 另对在shutdown时线程本身被调用中断做了处理.
	 */
	public static void shutdownAndAwaitTermination(ExecutorService pool) {
		if (pool != null && !pool.isShutdown()) {
			pool.shutdown();
			try {
				if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
					pool.shutdownNow();
					if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
						log.info("Pool did not terminate");
					}
				}
			} catch (InterruptedException e) {
				pool.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * 打印线程异常信息
	 * 该方法主要用于捕获和打印线程执行过程中的异常信息
	 * 如果线程执行被取消或因其他异常而终止，它会尝试获取异常的原因并打印
	 * @param r 可运行对象，可能是一个执行了异步任务的Future
	 * @param t 可能发生的异常，如果为null且r是一个Future，则会尝试从Future中获取异常
	 */
	public static void printException(Runnable r, Throwable t) {
		// 检查异常是否为null，且可运行对象是否为Future类型
		if (t == null && r instanceof Future<?>) {
			try {
				Future<?> future = (Future<?>) r;
				// 检查Future任务是否已完成
				if (future.isDone()) {
					// 尝试获取任务结果，可能会抛出异常
					future.get();
				}
			} catch (CancellationException ce) {
				// 如果任务被取消，更新异常为CancellationException
				t = ce;
			} catch (ExecutionException ee) {
				// 如果任务执行失败，获取失败的原因作为异常
				t = ee.getCause();
			} catch (InterruptedException ie) {
				// 如果线程被中断，恢复中断状态
				Thread.currentThread().interrupt();
			}
		}
		// 如果有异常，打印错误日志
		if (t != null) {
			log.error(t.getMessage(), t);
		}
	}
}

package cn.apkr.common.utils;

import cn.apkr.common.core.redis.RedisCache;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CacheUtils {

	private static final Logger log = LoggerFactory.getLogger(CacheUtils.class);

	public static final CacheManager CACHE_MANAGER = initCacheManager();

	/**
	 * 获取CacheManager
	 */
	private static CacheManager initCacheManager() {
		return SpringUtils.getBean(CacheManager.class);
	}

	/**
	 * 获取缓存
	 * @param cacheName 缓存的名称
	 * @return 缓存对象
	 */
	public static Cache getCache(String cacheName) {
		return CACHE_MANAGER.getCache(cacheName);
	}

	/**
	 * 获取缓存键列表
	 * @param cacheName 缓存名称
	 * @return 缓存键列表
	 */
	public static Set<String> getKeys(String cacheName) {
		// 由于 springcache不支持获取所有key,所以直接调用RedisCache
		RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
		return redisCache.getCacheKeys(cacheName);
	}

	/**
	 * 缓存数据
	 * @param cacheName 缓存的名称
	 * @param key 缓存项的键名
	 * @param value 缓存的值
	 */
	public static <T> void put(String cacheName, String key, T value) {
		put(cacheName, key, value, 0, null);
	}

	/**
	 * 缓存数据
	 * @param cacheName 缓存的名称
	 * @param key 缓存项的键名
	 * @param value 缓存的值
	 * @param timeout 超时时间
	 * @param unit 时间颗粒度
	 */
	public static <T> void put(String cacheName, String key, T value, long timeout, TimeUnit unit) {
		Cache cache = CACHE_MANAGER.getCache(cacheName);
		assert cache != null;
		if (cache instanceof JCacheCache jCacheCache) {
			jCacheCache.put(key, value);
		} else if (cache instanceof TransactionAwareCacheDecorator) {
			RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
			if (timeout != 0 && unit != null) {
				redisCache.setCacheObject(cacheName, key, value, timeout, unit);
			} else {
				redisCache.setCacheObject(cacheName, key, value);
			}
		} else {
			cache.put(key, value);
		}
	}

	/**
	 * 获取缓存中的值
	 * @param cacheName 缓存的名称，用于指定要操作的缓存区域
	 * @param key 		缓存项的键，用于唯一标识缓存中的项
	 * @return 返回缓存项的值包装器，如果缓存中没有对应的键，则返回null
	 */
	public static Cache.ValueWrapper get(String cacheName, String key) {
		return CACHE_MANAGER.getCache(cacheName).get(key);
	}

	/**
	 * 从缓存中获取指定键名的数据
	 * @param cacheName 缓存名
	 * @param key 键名
	 * @param type 缓存值类型
	 * @return 结果
	 */
	public static <T> T get(String cacheName, String key, @Nullable Class<T> type) {
		return CACHE_MANAGER.getCache(cacheName).get(key, type);
	}

	/**
	 * 移除指定缓存键名的数据
	 * @param cacheName 缓存名
	 * @param key 键名
	 */
	public static void remove(String cacheName, String key) {
		CACHE_MANAGER.getCache(cacheName).evict(key);
		log.debug("Cache entry with key {} evicted from cache: {}", key, cacheName);
	}

	/**
	 * 清除指定缓存名的所有缓存数据
	 * @param cacheName 缓存名
	 */
	public static void clear(String cacheName) {
		CACHE_MANAGER.getCache(cacheName).clear();
		log.debug("All entries cleared from cache: {}", cacheName);
	}

}

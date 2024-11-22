package cn.apkr.common.core.redis;

import cn.apkr.common.utils.SpringUtils;
import cn.apkr.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * spring redis 工具
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
@ConditionalOnProperty(prefix = "spring.cache", name = { "type" }, havingValue = "redis", matchIfMissing = false)
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param cacheName 缓存键名的前缀
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String cacheName, final String key, final T value) {
        redisTemplate.opsForValue().set(cacheName + ":" + key, value);
    }

    /**
     * 缓存基本的对象、Integer、String、实体类等
     * @param cacheName 缓存键名的前缀
     * @param key 缓存的键名
     * @param value 缓存的值
     * @param timeout 超时时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String cacheName, final String key, final T value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(cacheName + ":" + key, value, timeout, timeUnit);
    }

   /**
     * 设置有效时间
     * @param key 缓存的键
     * @param timeout 有效时间
     * @return true 设置成功，false 设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     * @param key 缓存的键
     * @param timeout 有效时间
     * @param unit 时间单位
     * @return true 设置成功，false 设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 根据 key 获取过期时间
     * @param key 缓存的键
     * @return 有效时间
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key 是否存在
     * @param key 缓存的键
     * @return true 存在，false 不存在
     */
    public boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等并设置超时时间。
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度(可以简单的看成时间单位)
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等。
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获得缓存的基本对象
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     * @param key 待删除对象的键
     * @return 是否删除成功
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param collection 对象集合
     * @return 是否删除成功
     */
    public boolean deleteObject(final Collection collection) {
        return redisTemplate.delete(collection) > 0;
    }

    /**
     * 缓存List数据
     * @param key 缓存的键
     * @param dataList 待缓存的List集合
     * @return 返回缓存的元素数量如果返回0，可能是因为key不存在或者dataList为空
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null ? 0 : count;
	}

    /**
     * 获取List缓存
     * @param key 缓存的键
     * @return 缓存数据的对象
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set数据
     * @param key 缓存的键
     * @param dataSet 待缓存的Set集合
     * @return 返回缓存操作对象，方便后续操作
     */
    public <T>BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
		for (T t : dataSet) {
			setOperations.add(t);
		}
        return setOperations;
    }

    /**
     * 获取Set缓存
     * @param key 缓存的键
     * @return 缓存数据的对象
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map数据
     * @param key 缓存的键
     * @param dataMap 待缓存的Map集合
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获取Map缓存
     * @param key 缓存的键
     * @return 缓存数据的对象
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash对象中的值
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     * @param key Redis键
     * @param hKeys Hash键
     * @return Hash对象中值的集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的某条数据
     * @param key Redis键
     * @param hKey Hash键
     * @return 是否成功删除
     */
    public <T> boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 获取缓存的基本对象列表
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 获取缓存的所有键名
     * @param cache 缓存
     * @return 结果
     */
    public Set<String> getCacheKeys(Cache cache) {
        // 用模板取出所有指定前缀的缓存键名
        Set<Object> keyset = redisTemplate.keys(cache.getName() + "*");
        return keyset.stream()
                // 去除缓存键名的前缀
                .map(o -> StringUtils.replace(o.toString(), cache.getName() + ":", ""))
                // 使用 new HashSet()的形式将所有数据收集至一个新的 HashSet中
                .collect(Collectors.toCollection(HashSet::new));
    }
}

package cn.apkr.common.core.redis;

import cn.apkr.common.utils.SpringUtils;
import cn.apkr.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
     * 缓存基本的对象，Integer、String、实体类等。
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

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
     * 删除单个对象
     * @param key
     * @return
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
     * 获得缓存的基本对象
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 根据键名前缀获取所有键名
     * @param cacheName 键名前缀
     * @return 结果
     */
    public Set<String> getCacheKeys(String cacheName) {
        // 用模板取出所有指定前缀的缓存键名
        Set<Object> objectSet = redisTemplate.keys(cacheName + "*");
        return objectSet == null ? new HashSet<>() : objectSet.stream()
                // 去除缓存键名的前缀
                .map(o -> StringUtils.replace(o.toString(), cacheName + ":", ""))
                // 使用 new HashSet()的形式将所有数据收集至一个新的 HashSet中
                .collect(Collectors.toCollection(HashSet::new));
    }
}

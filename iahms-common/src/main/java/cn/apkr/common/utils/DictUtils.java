package cn.apkr.common.utils;

import cn.apkr.common.constant.CacheConstants;
import cn.apkr.common.core.domain.entity.SysDictData;
import cn.apkr.common.core.redis.RedisCache;
import org.springframework.cache.Cache;

import java.util.List;

/**
 * 字典工具类
 */
public class DictUtils {

	public static final String SEPARATOR = ",";

	/**
	 * 获取字典缓存
	 * @return 字典缓存 Cache
	 */
	public static Cache getDictCacheKey() {
		return CacheUtils.getCache(CacheConstants.SYS_DICT_KEY);
	}

	/**
	 * 设置字典缓存
	 * @param key 参数键
	 * @param dictDataList 字典数据列表
	 */
	public static void setDictCache(String key, List<SysDictData> dictDataList) {
//		SpringUtils.getBean(RedisCache.class).setCacheList(key, dictDataList);
		getDictCacheKey().put(key, dictDataList);
	}


	/**
	 * 获取字典缓存
	 * @param key 参数键
	 * @return dictDataList 字典数据列表
	 */
	@SuppressWarnings("unchecked")
	public static List<SysDictData> getDictCache(String key) {
		List<SysDictData> dictDataList = (List<SysDictData>) getDictCacheKey().get(key, List.class);
		if (StringUtils.isNotNull(dictDataList)) {
			return dictDataList;
		}
		return null;
	}

	/**
	 * 根据字典类型和字典值获取字典标签
	 * @param dictType 字典类型
	 * @param dictValue 字典值
	 * @return 字典标签
	 */
	public static String getDictLabel(String dictType, String dictValue) {
		return getDictLabel(dictType, dictValue, SEPARATOR);
	}

	/**
	 * 根据字典类型和字典值获取字典标签
	 * @param dictType 字典标签
	 * @param dictValue 字典值
	 * @param separator 分隔符
	 * @return 字典标签
	 */
	public static String getDictLabel(String dictType, String dictValue, String separator) {
		StringBuilder stringBuilder = new StringBuilder();
		List<SysDictData> dictDataList = getDictCache(dictType);
		if (StringUtils.isNotNull(dictDataList)) {
			if (StringUtils.containsAny(separator, dictValue)) {
				for (SysDictData dictData : dictDataList) {
					for (String value : dictValue.split(separator)) {
						if (value.equals(dictData.getDictValue())) {
							stringBuilder.append(dictData.getDictLabel()).append(separator);
							break;
						}
					}
				}
			} else {
				for (SysDictData dictData : dictDataList) {
					if (dictValue.equals(dictData.getDictValue())) {
						return dictData.getDictLabel();
					}
				}
			}
		}
		return StringUtils.stripEnd(stringBuilder.toString(), separator);
	}

	/**
	 * 根据字典类型和字典标签获取字典值
	 * @param dictType 字典类型
	 * @param dictLabel 字典标签
	 * @return 字典值
	 */
	public static String getDictValue(String dictType, String dictLabel) {
		return getDictValue(dictType, dictLabel, SEPARATOR);
	}

	/**
	 * 根据字典类型和字典标签获取字典值
	 * @param dictType 字典类型
	 * @param dictLabel 字典标签
	 * @param separator 分隔符
	 * @return 字典值
	 */
	public static String getDictValue(String dictType, String dictLabel, String separator) {
		StringBuilder stringBuilder = new StringBuilder();
		List<SysDictData> dictDataList = getDictCache(dictType);
		if (StringUtils.isNotNull(dictDataList)) {
			if (StringUtils.containsAny(separator, dictLabel) && StringUtils.isNotEmpty(dictDataList)) {
				for (SysDictData dictData : dictDataList) {
					for (String label : dictLabel.split(separator)) {
						if (label.equals(dictData.getDictLabel())) {
							stringBuilder.append(dictData.getDictValue()).append(separator);
							break;
						}
					}
				}
			} else {
				for (SysDictData dictData : dictDataList) {
					if (dictLabel.equals(dictData.getDictLabel())) {
						return dictData.getDictValue();
					}
				}
			}
		}
		return StringUtils.stripEnd(stringBuilder.toString(), separator);
	}

	/**
	 * 删除指定字典缓存
	 * @param key 字典键
	 */
	public static void removeDictCache(String key) {
		getDictCacheKey().evict(key);
	}

	/**
	 * 清空字典缓存
	 */
	public static void clearDictCache() {
		getDictCacheKey().clear();
	}
}

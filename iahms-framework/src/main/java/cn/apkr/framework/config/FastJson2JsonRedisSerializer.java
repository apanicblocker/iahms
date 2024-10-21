package cn.apkr.framework.config;

import cn.apkr.common.constant.Constants;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	static final Filter AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(Constants.JSON_WHITELIST_STR);
	
	private Class<T> clazz;
	
	public FastJson2JsonRedisSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}
	
	@Override
	public byte[] serialize(T value) throws SerializationException {
		if (value == null) {
			return new byte[0];
		}
		return JSON.toJSONString(value, JSONWriter.Feature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		String str = new String(bytes, DEFAULT_CHARSET);
		
		return JSON.parseObject(str, clazz, AUTO_TYPE_FILTER);
	}
}

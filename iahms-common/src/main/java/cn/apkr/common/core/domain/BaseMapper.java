package cn.apkr.common.core.domain;

import java.util.List;

@Deprecated
public interface BaseMapper<T> {

	List<T> selectList(T t);

	T selectById(Long id);

	int insert(T t);

	int update(T t);

	int deleteById(Long id);

	int deleteByIds(Long[] ids);

}

package cn.apkr.system.service;

import cn.apkr.common.core.domain.entity.SysDictData;
import cn.apkr.common.core.domain.entity.SysDictType;

import java.util.List;

public interface ISysDictTypeService {

	List<SysDictType> selectDictTypeList(SysDictType dictType);

	List<SysDictType> selectDictTypeAll();

	List<SysDictData> selectDictDataByType(String dictType);

	SysDictType selectDictTypeById(Long dictId);

	SysDictType selectDictTypeByType(String dictType);

	void deleteDictTypeByIds(Long[] dictIds);

	void loadingDictCache();

	void clearDictCache();

	void resetDictCache();

	int insertDictType(SysDictType dictType);

	int updateDictType(SysDictType dictType);

	boolean checkDictTypeUnique(SysDictType dictType);
}

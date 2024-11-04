package cn.apkr.system.service;

import cn.apkr.common.core.domain.entity.SysDictData;

import java.util.List;

public interface SysDictDataService {

	List<SysDictData> selectDictDataList(SysDictData dictData);

	String selectDictLabel(String dictType, String dictValue);

	SysDictData selectDictDataById(Long dictCode);

	int insertDictData(SysDictData dictData);

	int updateDictData(SysDictData dictData);

	void deleteDictDataByIds(Long[] dictCodes);
}

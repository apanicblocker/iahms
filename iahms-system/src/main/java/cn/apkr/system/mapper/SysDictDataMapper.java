package cn.apkr.system.mapper;

import cn.apkr.common.core.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictDataMapper {

	List<SysDictData> selectDictDataList(SysDictData dictData);

	List<SysDictData> selectDictDataByType(String dictType);

	String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

	SysDictData selectDictDataById(Long dictCode);

	int countDictDataByType(String dictType);

	int insertDictData(SysDictData dictData);

	int updateDictData(SysDictData dictData);

	int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);

	int deleteDictDataById(Long dictCode);

	int deleteDictDataByIds(Long[] dictCodes);

}

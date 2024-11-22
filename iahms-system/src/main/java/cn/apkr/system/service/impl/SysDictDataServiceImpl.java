package cn.apkr.system.service.impl;

import cn.apkr.common.core.domain.entity.SysDictData;
import cn.apkr.common.core.domain.entity.SysDictType;
import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.DictUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.system.mapper.SysDictDataMapper;
import cn.apkr.system.mapper.SysDictTypeMapper;
import cn.apkr.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictDataServiceImpl implements ISysDictDataService {

	@Autowired
	private SysDictDataMapper dictDataMapper;
	
	@Autowired
	private SysDictTypeMapper dictTypeMapper;

	/**
	 * 查询字典数据列表
	 * @param dictData 字典数据
	 * @return 字典数据集合
	 */
	@Override
	public List<SysDictData> selectDictDataList(SysDictData dictData) {
		return dictDataMapper.selectDictDataList(dictData);
	}

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 * @param dictType 字典类型
	 * @param dictValue 字典键值
	 * @return 字典标签
	 */
	@Override
	public String selectDictLabel(String dictType, String dictValue) {
		return dictDataMapper.selectDictLabel(dictType, dictValue);
	}

	/**
	 * 根据字典数据ID获取字典数据
	 * @param dictCode 字典数据ID
	 * @return 字典数据
	 */
	@Override
	public SysDictData selectDictDataById(Long dictCode) {
		return dictDataMapper.selectDictDataById(dictCode);
	}

	/**
	 * 新增字典数据
	 * @param dictData 字典数据
	 * @return 结果
	 */
	@Override
	public int insertDictData(SysDictData dictData) {
		int row = dictDataMapper.insertDictData(dictData);
		if (row > 0) {
			List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictData.getDictType());
			DictUtils.setDictCache(dictData.getDictType(), dictDataList);
		}
		return row;
	}

	/**
	 * 修改字典数据
	 * @param dictData 字典数据
	 * @return 结果
	 */
	@Override
	public int updateDictData(SysDictData dictData) {
		String oldDictType = dictDataMapper.selectDictDataById(dictData.getDictCode()).getDictType();
		String newDictType = dictData.getDictType();
		if (newDictType != null && StringUtils.isNull(dictTypeMapper.checkDictTypeUnique(newDictType))) {
			throw new ServiceException("修改失败，修改后的字典类型未定义！");
		}
		int row = dictDataMapper.updateDictData(dictData);
		if (row > 0) {
			// 更新新的字典缓存
			List<SysDictData> newDictDataList = dictDataMapper.selectDictDataByType(newDictType);
			DictUtils.setDictCache(newDictType, newDictDataList);
			// 更新旧的字典缓存
			List<SysDictData> oldDictDataList = dictDataMapper.selectDictDataByType(oldDictType);
			DictUtils.setDictCache(oldDictType, oldDictDataList);
		}
		return row;
	}

	/**
	 * 删除字典数据
	 * @param dictCodes 字典数据ID数组
	 */
	@Override
	public void deleteDictDataByIds(Long[] dictCodes) {
		for (Long dictCode : dictCodes) {
			SysDictData dictData = selectDictDataById(dictCode);
			dictDataMapper.deleteDictDataById(dictCode);
			List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictData.getDictType());
			DictUtils.setDictCache(dictData.getDictType(), dictDataList);
		}
	}

}

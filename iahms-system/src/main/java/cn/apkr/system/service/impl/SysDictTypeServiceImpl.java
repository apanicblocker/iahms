package cn.apkr.system.service.impl;

import cn.apkr.common.constant.UserConstants;
import cn.apkr.common.core.domain.entity.SysDictData;
import cn.apkr.common.core.domain.entity.SysDictType;
import cn.apkr.common.utils.DictUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.system.mapper.SysDictDataMapper;
import cn.apkr.system.mapper.SysDictTypeMapper;
import cn.apkr.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

	@Autowired
	private SysDictTypeMapper dictTypeMapper;

	@Autowired
	private SysDictDataMapper dictDataMapper;

	/**
	 * 查询字典类型列表
	 * @param dictType 字典类型
	 * @return 字典类型集合
	 */
	@Override
	public List<SysDictType> selectDictTypeList(SysDictType dictType) {
		return dictTypeMapper.selectDictTypeList(dictType);
	}

	/**
	 * 查询所有字典类型
	 * @return 字典类型集合
	 */
	@Override
	public List<SysDictType> selectDictTypeAll() {
		return dictTypeMapper.selectDictTypeAll();
	}

	/**
	 * 根据字典类型查询字典数据
	 * @param dictType 字典类型
	 * @return 字典类型集合
	 */
	@Override
	public List<SysDictData> selectDictDataByType(String dictType) {
		List<SysDictData> dictDataList = DictUtils.getDictCache(dictType);
		if (StringUtils.isNotEmpty(dictDataList)) {
			return dictDataList;
		}
		dictDataList = dictDataMapper.selectDictDataByType(dictType);
		if (StringUtils.isNotEmpty(dictDataList)) {
			DictUtils.setDictCache(dictType, dictDataList);
			return dictDataList;
		}
		return null;
	}

	/**
	 * 根据字典ID查询信息
	 * @param dictId 字典类型ID
	 * @return 字典类型
	 */
	@Override
	public SysDictType selectDictTypeById(Long dictId) {
		return dictTypeMapper.selectDictTypeById(dictId);
	}

	/**
	 * 根据字典类型查询信息
	 * @param dictType 字典类型
	 * @return 字典类型
	 */
	@Override
	public SysDictType selectDictTypeByType(String dictType) {
		return dictTypeMapper.selectDictTypeByType(dictType);
	}

	/**
	 * 批量删除字典类型信息
	 * @param dictIds 需要删除的字典ID
	 */
	@Override
	public void deleteDictTypeByIds(Long[] dictIds) {
		for (Long dictId : dictIds) {
			SysDictType dictType = selectDictTypeById(dictId);
			if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
				throw new RuntimeException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
			}
			dictTypeMapper.deleteDictTypeById(dictId);
			DictUtils.removeDictCache(dictType.getDictType());
		}
	}

	/**
	 * 加载字典缓存数据
	 */
	@Override
	public void loadingDictCache() {
		SysDictData dictData = new SysDictData();
		dictData.setStatus(false);
		Map<String, List<SysDictData>> dictDataMap = dictDataMapper.selectDictDataList(dictData).stream()
				.collect(Collectors.groupingBy(SysDictData::getDictType));
		for (Map.Entry<String, List<SysDictData>> entry : dictDataMap.entrySet()) {
			DictUtils.setDictCache(entry.getKey(), entry.getValue().stream()
					.sorted(Comparator.comparing(SysDictData::getDictSort))
					.collect(Collectors.toList()));
		}
	}

	/**
	 * 清空字典缓存数据
	 */
	@Override
	public void clearDictCache() {
		DictUtils.clearDictCache();
	}

	/**
	 * 重置字典缓存数据
	 */
	@Override
	public void resetDictCache() {
		clearDictCache();
		loadingDictCache();
	}

	/**
	 * 新增字典类型信息
	 * @param dictType 字典类型信息
	 * @return 结果
	 */
	@Override
	public int insertDictType(SysDictType dictType) {
		int row = dictTypeMapper.insertDictType(dictType);
		if (row > 0) {
			DictUtils.setDictCache(dictType.getDictType(), null);
		}
		return row;
	}

	/**
	 * 修改字典类型信息
	 * @param dictType 字典类型信息
	 * @return 结果
	 */
	@Override
	public int updateDictType(SysDictType dictType) {
		SysDictType oldDictType = dictTypeMapper.selectDictTypeById(dictType.getDictId());
		dictDataMapper.updateDictDataType(oldDictType.getDictType(), dictType.getDictType());
		int row = dictTypeMapper.updateDictType(dictType);
		if (row > 0) {
			List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictType.getDictType());
			DictUtils.setDictCache(dictType.getDictType(), dictDataList);
		}
		return row;
	}

	/**
	 * 校验字典类型(的名称)是否唯一
	 * @param dictType 字典类型
	 * @return 结果
	 */
	@Override
	public boolean checkDictTypeUnique(SysDictType dictType) {
		Long dictTypeId = StringUtils.isNull(dictType.getDictType()) ? -1L : dictType.getDictId();
		SysDictType dictTypeUnique = dictTypeMapper.checkDictTypeUnique(dictType.getDictType());
		if (StringUtils.isNotNull(dictTypeUnique) && dictTypeUnique.getDictId().longValue() != dictTypeId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		} else {
			return UserConstants.UNIQUE;
		}
	}

}

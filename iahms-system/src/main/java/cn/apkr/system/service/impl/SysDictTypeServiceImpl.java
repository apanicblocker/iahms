package cn.apkr.system.service.impl;

import cn.apkr.common.core.domain.entity.SysDictData;
import cn.apkr.common.core.domain.entity.SysDictType;
import cn.apkr.system.mapper.SysDictDataMapper;
import cn.apkr.system.mapper.SysDictTypeMapper;
import cn.apkr.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

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
		// TODO: 从缓存中获取
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
			// TODO: 从缓存中删除
		}
	}


}

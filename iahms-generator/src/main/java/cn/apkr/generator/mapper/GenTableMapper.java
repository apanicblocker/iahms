package cn.apkr.generator.mapper;

import cn.apkr.generator.domain.GenTable;

import java.util.List;

public interface GenTableMapper {

	List<GenTable> selectGenTableList(GenTable genTable);

	List<GenTable> selectDbTableList(GenTable genTable);

	List<GenTable> selectDbTableListByNames(String[] tableNames);

	List<GenTable> selectGenTableAll();

	GenTable selectGenTableById(Long id);

	GenTable selectGenTableByName(String tableName);

	int insertGenTable(GenTable gentable);

	int updateGenTable(GenTable genTable);

	int deleteGenTableByIds(Long[] ids);

	int deleteAll();

}

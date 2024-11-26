package cn.apkr.generator.service;

import cn.apkr.generator.domain.GenTable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IGenTableService {


	List<GenTable> selectDbTableList(GenTable genTable);

	List<GenTable> selectDbTableListByNames(String[] tableNames);

	List<GenTable> selectGenTableList(GenTable genTable);

	List<GenTable> selectGenTableAll();

	GenTable selectGenTableById(Long id);

	@Transactional
	void updateGenTable(GenTable genTable);

	void deleteGenTableByIds(Long[] tableIds);

	@Transactional
	void importGenTable(List<GenTable> tableList);

	Map<String, String> previewCode(Long tableId);

	byte[] downloadCode(String tableName);

	void generatorCode(String tableName);

	@Transactional
	void synchDb(String tableName);

	byte[] downloadCode(String[] tableNames);

	void validateEdit(GenTable genTable);
}

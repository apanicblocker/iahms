package cn.apkr.generator.service;

import cn.apkr.generator.domain.GenTableColumn;

import java.util.List;

public interface IGenTableColumnService {
	List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

	int insertGenTableColumn(GenTableColumn column);

	int updateGenTableColumn(GenTableColumn column);

	int deleteGenTableColumns(List<GenTableColumn> genTableColumns);
}

package cn.apkr.generator.mapper;

import cn.apkr.generator.domain.GenTableColumn;

import java.util.List;

public interface GenTableColumnMapper {

	List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

	List<GenTableColumn> selectDbTableColumnsByName(String tableName);

	int insertGenTableColumn(GenTableColumn genTableColumn);

	int updateGenTableColumn(GenTableColumn genTableColumn);

	int deleteGenTableColumnByIds(Long[] ids);

	int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

}

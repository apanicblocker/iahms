package cn.apkr.generator.service;

import cn.apkr.generator.domain.GenTableColumn;
import cn.apkr.generator.mapper.GenTableColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {

	@Autowired
	private GenTableColumnMapper tableColumnMapper;

	@Override
	public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
		return tableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}

	@Override
	public int insertGenTableColumn(GenTableColumn column) {
		return tableColumnMapper.insertGenTableColumn(column);
	}

	@Override
	public int updateGenTableColumn(GenTableColumn column) {
		return tableColumnMapper.updateGenTableColumn(column);
	}

	@Override
	public int deleteGenTableColumns(List<GenTableColumn> genTableColumns) {
		return tableColumnMapper.deleteGenTableColumns(genTableColumns);
	}

}

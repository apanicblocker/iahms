package cn.apkr.generator.controller;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.config.IahmsConfig;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.core.text.Convert;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.common.utils.SpringUtils;
import cn.apkr.generator.domain.GenTable;
import cn.apkr.generator.domain.GenTableColumn;
import cn.apkr.generator.service.IGenDevService;
import cn.apkr.generator.service.IGenTableColumnService;
import cn.apkr.generator.service.IGenTableService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {
	@Autowired
	private IGenTableService genTableService;

	@Autowired
	private IGenTableColumnService genTableColumnService;

	@Autowired
	private IGenDevService genDevService;

	/**
	 * 查询GenTable列表
	 */
	@GetMapping("/list")
	public TableDataInfo listGenTables(GenTable genTable) {
		startPage();
		List<GenTable> list = genTableService.selectGenTableList(genTable);
		return getDataTable(list);
	}

	/**
	 * 根据表ID查询代码生成业务信息
	 */
	@GetMapping("/{tableId}")
	public AjaxResult getGenTableByTableId(@PathVariable("tableId") Long tableId) {
		GenTable table = genTableService.selectGenTableById(tableId);
		List<GenTable> tables = genTableService.selectGenTableAll();
		List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", table);
		map.put("rows", list);
		map.put("tables", tables);
		return success(map);
	}

	/**
	 * 修改代码生成业务信息
	 */
	@Log(title = "代码生成", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody GenTable genTable) {
		genTableService.validateEdit(genTable);
		genTableService.updateGenTable(genTable);
		return success();
	}

	/**
	 * 删除代码生成业务信息
	 */
	@Log(title = "代码生成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
	public AjaxResult remove(@PathVariable("tableIds") Long[] tableIds) {
		genTableService.deleteGenTableByIds(tableIds);
		return success();
	}

	/**
	 * 查询数据表字段列表
	 */
	@GetMapping("/column/{tableId}")
	public TableDataInfo listGenColumnsByTableId(@PathVariable("tableId") Long tableId) {
		TableDataInfo dataInfo = new TableDataInfo();
		List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
		dataInfo.setRows(list);
		dataInfo.setTotal(list.size());
		return dataInfo;
	}

	/**
	 * 查询数据库所有表信息
	 */
	@GetMapping("/db/list")
	public TableDataInfo listFromDb(GenTable genTable) {
		startPage();
		List<GenTable> list = genTableService.selectDbTableList(genTable);
		return getDataTable(list);
	}

	/**
	 * 从数据库导入表
	 */
	@Log(title = "代码生成", businessType = BusinessType.IMPORT)
	@PostMapping("/import")
	public AjaxResult importFromDb(@RequestParam("tableNames") String tableNames) {
		String[] tableNameArray = Convert.toStrArray(tableNames);
		// 查询表信息
		List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNameArray);
		genTableService.importGenTable(tableList);
		return success();
	}

	/**
	 * 同步数据库指定表的信息
	 */
	@Log(title = "代码生成", businessType = BusinessType.UPDATE)
	@GetMapping("/syncDb/{tableName}")
	public AjaxResult syncFromDbByTableName(@PathVariable("tableName") String tableName) {
		genTableService.synchDb(tableName);
		return success();
	}

	/**
	 * 预览代码
	 */
	@GetMapping("/preview/{tableId}")
	public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException {
		Map<String, String> dataMap = genTableService.previewCode(tableId);
		return success(dataMap);
	}

	/**
	 * 生成代码至自定义路径
	 */
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/genCode/{tableName}")
	public AjaxResult genCode(@PathVariable("tableName") String tableName) {
		genTableService.generatorCode(tableName);
		return success();
	}

	/**
	 * 根据模块名批量导入并生成代码
	 */
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/genCode/dev/{modelName}")
	public AjaxResult batchGenCodeByModelName(@PathVariable("modelName") String modelName) {
		genDevService.genCodeByModelName(modelName);
		return success();
	}

	/**
	 * 生成代码并下载
	 */
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/genCode/download/{tableName}")
	public void genCodeAndDownload(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
		byte[] data = genTableService.downloadCode(tableName);
		genCode(response, data);
	}

	/**
	 * 批量生成代码并下载
	 */
	@Log(title = "代码生成", businessType = BusinessType.GENCODE)
	@GetMapping("/genCode/download")
	public void batchGenCodeAndDownload(HttpServletResponse response, @RequestParam("tableNames") String tableNames) throws IOException {
		String[] tableNameArray = Convert.toStrArray(tableNames);
		byte[] data = genTableService.downloadCode(tableNameArray);
		genCode(response, data);
	}

	/**
	 * 生成zip文件
	 */
	private void genCode(HttpServletResponse response, byte[] data) throws IOException {
		response.reset();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		response.setHeader("Content-Disposition", "attachment; filename=\"iahms.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}
}

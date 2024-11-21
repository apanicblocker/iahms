package cn.apkr.web.controller.system;

import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.entity.SysDictData;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.system.service.ISysDictDataService;
import cn.apkr.system.service.ISysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 */
@Tag(name = "数据字典信息（数据）")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {

	@Autowired
	private ISysDictDataService dictDataService;

	@Autowired
	private ISysDictTypeService dictTypeService;

	@Operation(summary = "查询字典数据列表")
	@GetMapping("/list")
	public TableDataInfo list(SysDictData dictData) {
		startPage();
		List<SysDictData> dictDataList = dictDataService.selectDictDataList(dictData);
		return getDataTable(dictDataList);
	}

	@Operation(summary = "查询字典数据编码查询字典数据值")
	@GetMapping("/{dictCode}")
	public AjaxResult getDictData(@PathVariable(name = "dictCode") Long dictCode) {
		return success(dictDataService.selectDictDataById(dictCode));
	}

	@Operation(summary = "根据字典类型查询字典数据列表")
	@GetMapping("/type/{dictType}")
	public AjaxResult getDictType(@PathVariable(name = "dictType") String dictType) {
		List<SysDictData> dictDataList = dictTypeService.selectDictDataByType(dictType);
		if (StringUtils.isNull(dictDataList)) {
			dictDataList = new ArrayList<>();
		}
		return success(dictDataList);
	}

	@Operation(summary = "新增字典数据")
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysDictData dictData) {
		dictData.setCreateBy(getUserId());
		return toAjax(dictDataService.insertDictData((dictData)));
	}

	@Operation(summary = "修改字典数据")
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysDictData dictData) {
		dictData.setUpdateBy(getUserId());
		return toAjax(dictDataService.updateDictData(dictData));
	}

	@Operation(summary = "删除字典数据")
	@DeleteMapping("/{dictCodes}")
	public AjaxResult remove(@PathVariable(name = "dictCodes") Long[] dictCodes) {
		dictDataService.deleteDictDataByIds(dictCodes);
		return success();
	}

}

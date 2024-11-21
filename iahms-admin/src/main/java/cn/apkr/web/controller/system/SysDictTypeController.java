package cn.apkr.web.controller.system;

import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.entity.SysDictType;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.system.service.ISysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {

	@Autowired
	private ISysDictTypeService dictTypeService;

	@Operation(summary = "查询字典类型列表")
	@GetMapping("/list")
	public TableDataInfo list(SysDictType dictType) {
		startPage();
		List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
		return getDataTable(list);
	}

	@Operation(summary = "查询字典类型详细")
	@GetMapping("/{dictId}")
	public AjaxResult getInfo(@PathVariable(name = "dictId") Long dictId) {
		return success(dictTypeService.selectDictTypeById(dictId));
	}

	@Operation(summary = "新增字典类型")
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysDictType dictType) {
		if (!dictTypeService.checkDictTypeUnique(dictType)) {
			return error("新增字典'" + dictType.getDictName() + "'失败，字典类型已存在");
		}
		dictType.setCreateBy(getUserId());
		return toAjax(dictTypeService.insertDictType(dictType));
	}

	@Operation(summary = "修改字典类型")
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysDictType dictType) {
		if (!dictTypeService.checkDictTypeUnique(dictType)) {
			return error("修改字典'" + dictType.getDictName() + "'失败，字典类型已存在");
		}
		dictType.setUpdateBy(getUserId());
		return toAjax(dictTypeService.updateDictType(dictType));
	}

	@Operation(summary = "删除字典类型")
	@DeleteMapping("/{dictIds}")
	public AjaxResult remove(@PathVariable(name = "dictIds") Long[] dictIds) {
		dictTypeService.deleteDictTypeByIds(dictIds);
		return success();
	}

	@Operation(summary = "刷新字典缓存")
	@DeleteMapping("/refreshCache")
	public AjaxResult refreshCache() {
		dictTypeService.resetDictCache();
		return success();
	}

	@Operation(summary = "获取字典选择框列表")
	@GetMapping("/option_select")	// 命名有点捞哦，到时候看看用处是什么再改改吧
	public AjaxResult optionSelect() {
		List<SysDictType> dictTypeList = dictTypeService.selectDictTypeAll();
		return success(dictTypeList);
	}

}

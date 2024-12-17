package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelProductRecord;
import cn.apkr.hotel.service.IHotelProductRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物记录Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "购物记录")
@RestController
@RequestMapping("/hotel/productRecord")
public class HotelProductRecordController extends BaseController {

    @Autowired
    private IHotelProductRecordService hotelProductRecordService;

    /**
     * 查询购物记录列表
     */
    @Operation(summary = "查询购物记录列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelProductRecord hotelProductRecord) {
        startPage();
        List<HotelProductRecord> hotelProductRecordList = hotelProductRecordService.selectHotelProductRecordList(hotelProductRecord);
        return getDataTable(hotelProductRecordList);
    }

    /**
     * 获取购物记录详细信息
     */
    @Operation(summary = "获取购物记录详细信息")
    @GetMapping(value = "/{productRecordId}")
    public AjaxResult getByProductRecordId(@PathVariable("productRecordId") Long productRecordId) {
        return success(hotelProductRecordService.selectHotelProductRecordByProductRecordId(productRecordId));
    }

    /**
     * 新增购物记录
     */
    @Operation(summary = "新增购物记录")
    @Log(title = "购物记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelProductRecord hotelProductRecord) {
        return toAjax(hotelProductRecordService.insertHotelProductRecord(hotelProductRecord));
    }

    /**
     * 修改购物记录
     */
    @Operation(summary = "修改购物记录")
    @Log(title = "购物记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelProductRecord hotelProductRecord) {
        return toAjax(hotelProductRecordService.updateHotelProductRecord(hotelProductRecord));
    }

    /**
     * 删除购物记录
     */
    @Operation(summary = "删除购物记录")
    @Log(title = "购物记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productRecordIds}")
    public AjaxResult remove(@PathVariable("productRecordIds") Long[] productRecordIds) {
        return toAjax(hotelProductRecordService.deleteHotelProductRecordByProductRecordIds(productRecordIds));
    }
}

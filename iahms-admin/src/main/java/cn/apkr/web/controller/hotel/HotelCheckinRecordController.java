package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelCheckinRecord;
import cn.apkr.hotel.service.IHotelCheckinRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入住记录Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "入住记录")
@RestController
@RequestMapping("/hotel/checkinRecord")
public class HotelCheckinRecordController extends BaseController {

    @Autowired
    private IHotelCheckinRecordService hotelCheckinRecordService;

    /**
     * 查询入住记录列表
     */
    @Operation(summary = "查询入住记录列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelCheckinRecord hotelCheckinRecord) {
        startPage();
        List<HotelCheckinRecord> hotelCheckinRecordList = hotelCheckinRecordService.selectHotelCheckinRecordList(hotelCheckinRecord);
        return getDataTable(hotelCheckinRecordList);
    }

    /**
     * 获取入住记录详细信息
     */
    @Operation(summary = "获取入住记录详细信息")
    @GetMapping(value = "/{checkinRecordId}")
    public AjaxResult getByCheckinRecordId(@PathVariable("checkinRecordId") Long checkinRecordId) {
        return success(hotelCheckinRecordService.selectHotelCheckinRecordByCheckinRecordId(checkinRecordId));
    }

    /**
     * 新增入住记录
     */
    @Operation(summary = "新增入住记录")
    @Log(title = "入住记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelCheckinRecord hotelCheckinRecord) {
        return toAjax(hotelCheckinRecordService.insertHotelCheckinRecord(hotelCheckinRecord));
    }

    /**
     * 修改入住记录
     */
    @Operation(summary = "修改入住记录")
    @Log(title = "入住记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelCheckinRecord hotelCheckinRecord) {
        return toAjax(hotelCheckinRecordService.updateHotelCheckinRecord(hotelCheckinRecord));
    }

    /**
     * 删除入住记录
     */
    @Operation(summary = "删除入住记录")
    @Log(title = "入住记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{checkinRecordIds}")
    public AjaxResult remove(@PathVariable("checkinRecordIds") Long[] checkinRecordIds) {
        return toAjax(hotelCheckinRecordService.deleteHotelCheckinRecordByCheckinRecordIds(checkinRecordIds));
    }
}

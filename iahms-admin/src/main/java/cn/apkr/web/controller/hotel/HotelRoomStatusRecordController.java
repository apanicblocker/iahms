package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelRoomStatusRecord;
import cn.apkr.hotel.service.IHotelRoomStatusRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房态记录Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "房态记录")
@RestController
@RequestMapping("/hotel/roomStatusRecord")
public class HotelRoomStatusRecordController extends BaseController {

    @Autowired
    private IHotelRoomStatusRecordService hotelRoomStatusRecordService;

    /**
     * 查询房态记录列表
     */
    @Operation(summary = "查询房态记录列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelRoomStatusRecord hotelRoomStatusRecord) {
        startPage();
        List<HotelRoomStatusRecord> hotelRoomStatusRecordList = hotelRoomStatusRecordService.selectHotelRoomStatusRecordList(hotelRoomStatusRecord);
        return getDataTable(hotelRoomStatusRecordList);
    }

    /**
     * 获取房态记录详细信息
     */
    @Operation(summary = "获取房态记录详细信息")
    @GetMapping(value = "/{roomStatusRecordId}")
    public AjaxResult getByRoomStatusRecordId(@PathVariable("roomStatusRecordId") Long roomStatusRecordId) {
        return success(hotelRoomStatusRecordService.selectHotelRoomStatusRecordByRoomStatusRecordId(roomStatusRecordId));
    }

    /**
     * 新增房态记录
     */
    @Operation(summary = "新增房态记录")
    @Log(title = "房态记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelRoomStatusRecord hotelRoomStatusRecord) {
        return toAjax(hotelRoomStatusRecordService.insertHotelRoomStatusRecord(hotelRoomStatusRecord));
    }

    /**
     * 修改房态记录
     */
    @Operation(summary = "修改房态记录")
    @Log(title = "房态记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelRoomStatusRecord hotelRoomStatusRecord) {
        return toAjax(hotelRoomStatusRecordService.updateHotelRoomStatusRecord(hotelRoomStatusRecord));
    }

    /**
     * 删除房态记录
     */
    @Operation(summary = "删除房态记录")
    @Log(title = "房态记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomStatusRecordIds}")
    public AjaxResult remove(@PathVariable("roomStatusRecordIds") Long[] roomStatusRecordIds) {
        return toAjax(hotelRoomStatusRecordService.deleteHotelRoomStatusRecordByRoomStatusRecordIds(roomStatusRecordIds));
    }
}

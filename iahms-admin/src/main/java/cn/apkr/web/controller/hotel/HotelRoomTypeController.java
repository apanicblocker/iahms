package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelRoomType;
import cn.apkr.hotel.service.IHotelRoomTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房型Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "房型")
@RestController
@RequestMapping("/hotel/roomType")
public class HotelRoomTypeController extends BaseController {

    @Autowired
    private IHotelRoomTypeService hotelRoomTypeService;

    /**
     * 查询房型列表
     */
    @Operation(summary = "查询房型列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelRoomType hotelRoomType) {
        startPage();
        List<HotelRoomType> hotelRoomTypeList = hotelRoomTypeService.selectHotelRoomTypeList(hotelRoomType);
        return getDataTable(hotelRoomTypeList);
    }

    /**
     * 获取房型详细信息
     */
    @Operation(summary = "获取房型详细信息")
    @GetMapping(value = "/{roomTypeId}")
    public AjaxResult getByRoomTypeId(@PathVariable("roomTypeId") Long roomTypeId) {
        return success(hotelRoomTypeService.selectHotelRoomTypeByRoomTypeId(roomTypeId));
    }

    /**
     * 新增房型
     */
    @Operation(summary = "新增房型")
    @Log(title = "房型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelRoomType hotelRoomType) {
        return toAjax(hotelRoomTypeService.insertHotelRoomType(hotelRoomType));
    }

    /**
     * 修改房型
     */
    @Operation(summary = "修改房型")
    @Log(title = "房型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelRoomType hotelRoomType) {
        return toAjax(hotelRoomTypeService.updateHotelRoomType(hotelRoomType));
    }

    /**
     * 删除房型
     */
    @Operation(summary = "删除房型")
    @Log(title = "房型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomTypeIds}")
    public AjaxResult remove(@PathVariable("roomTypeIds") Long[] roomTypeIds) {
        return toAjax(hotelRoomTypeService.deleteHotelRoomTypeByRoomTypeIds(roomTypeIds));
    }
}

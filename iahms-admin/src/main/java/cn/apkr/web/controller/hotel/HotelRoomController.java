package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelRoom;
import cn.apkr.hotel.service.IHotelRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房间Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "房间")
@RestController
@RequestMapping("/hotel/room")
public class HotelRoomController extends BaseController {

    @Autowired
    private IHotelRoomService hotelRoomService;

    /**
     * 查询房间列表
     */
    @Operation(summary = "查询房间列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelRoom hotelRoom) {
        startPage();
        List<HotelRoom> hotelRoomList = hotelRoomService.selectHotelRoomList(hotelRoom);
        return getDataTable(hotelRoomList);
    }

    /**
     * 获取房间详细信息
     */
    @Operation(summary = "获取房间详细信息")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getByRoomId(@PathVariable("roomId") Long roomId) {
        return success(hotelRoomService.selectHotelRoomByRoomId(roomId));
    }

    /**
     * 新增房间
     */
    @Operation(summary = "新增房间")
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelRoom hotelRoom) {
        return toAjax(hotelRoomService.insertHotelRoom(hotelRoom));
    }

    /**
     * 修改房间
     */
    @Operation(summary = "修改房间")
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelRoom hotelRoom) {
        return toAjax(hotelRoomService.updateHotelRoom(hotelRoom));
    }

    /**
     * 删除房间
     */
    @Operation(summary = "删除房间")
    @Log(title = "房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable("roomIds") Long[] roomIds) {
        return toAjax(hotelRoomService.deleteHotelRoomByRoomIds(roomIds));
    }
}

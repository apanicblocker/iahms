package cn.apkr.web.controller.hotel;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelRoom;
import cn.apkr.hotel.service.IHotelRoomService;
import cn.apkr.common.core.page.TableDataInfo;

/**
 * 房间Controller
 *
 * @author apkr
 * @date 2024-12-06
 */
@RestController
@RequestMapping("/hotel/room")
public class HotelRoomController extends BaseController {

    @Autowired
    private IHotelRoomService hotelRoomService;

    /**
     * 查询房间列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HotelRoom hotelRoom) {
        startPage();
        List<HotelRoom> hotelRoomList = hotelRoomService.selectHotelRoomList(hotelRoom);
        return getDataTable(hotelRoomList);
    }

    /**
     * 获取房间详细信息
     */
    @GetMapping(value = "/{roomId}")
    public AjaxResult getByRoomId(@PathVariable("roomId") Long roomId) {
        return success(hotelRoomService.selectHotelRoomByRoomId(roomId));
    }

    /**
     * 新增房间
     */
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelRoom hotelRoom) {
        return toAjax(hotelRoomService.insertHotelRoom(hotelRoom));
    }

    /**
     * 修改房间
     */
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelRoom hotelRoom) {
        return toAjax(hotelRoomService.updateHotelRoom(hotelRoom));
    }

    /**
     * 删除房间
     */
    @Log(title = "房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds) {
        return toAjax(hotelRoomService.deleteHotelRoomByRoomIds(roomIds));
    }
}

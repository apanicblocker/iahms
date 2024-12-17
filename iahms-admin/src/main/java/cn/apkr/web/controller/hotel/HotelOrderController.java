package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelOrder;
import cn.apkr.hotel.service.IHotelOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "订单")
@RestController
@RequestMapping("/hotel/order")
public class HotelOrderController extends BaseController {

    @Autowired
    private IHotelOrderService hotelOrderService;

    /**
     * 查询订单列表
     */
    @Operation(summary = "查询订单列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelOrder hotelOrder) {
        startPage();
        List<HotelOrder> hotelOrderList = hotelOrderService.selectHotelOrderList(hotelOrder);
        return getDataTable(hotelOrderList);
    }

    /**
     * 获取订单详细信息
     */
    @Operation(summary = "获取订单详细信息")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getByOrderId(@PathVariable("orderId") Long orderId) {
        return success(hotelOrderService.selectHotelOrderByOrderId(orderId));
    }

    /**
     * 新增订单
     */
    @Operation(summary = "新增订单")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelOrder hotelOrder) {
        return toAjax(hotelOrderService.insertHotelOrder(hotelOrder));
    }

    /**
     * 修改订单
     */
    @Operation(summary = "修改订单")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelOrder hotelOrder) {
        return toAjax(hotelOrderService.updateHotelOrder(hotelOrder));
    }

    /**
     * 删除订单
     */
    @Operation(summary = "删除订单")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable("orderIds") Long[] orderIds) {
        return toAjax(hotelOrderService.deleteHotelOrderByOrderIds(orderIds));
    }
}

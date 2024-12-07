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
import cn.apkr.hotel.domain.HotelOrder;
import cn.apkr.hotel.service.IHotelOrderService;
import cn.apkr.common.core.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author apkr
 * @date 2024-12-06
 */
@RestController
@RequestMapping("/hotel/order")
public class HotelOrderController extends BaseController {

    @Autowired
    private IHotelOrderService hotelOrderService;

    /**
     * 查询订单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HotelOrder hotelOrder) {
        startPage();
        List<HotelOrder> hotelOrderList = hotelOrderService.selectHotelOrderList(hotelOrder);
        return getDataTable(hotelOrderList);
    }

    /**
     * 获取订单详细信息
     */
    @GetMapping(value = "/{orderId}")
    public AjaxResult getByOrderId(@PathVariable("orderId") Long orderId) {
        return success(hotelOrderService.selectHotelOrderByOrderId(orderId));
    }

    /**
     * 新增订单
     */
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelOrder hotelOrder) {
        return toAjax(hotelOrderService.insertHotelOrder(hotelOrder));
    }

    /**
     * 修改订单
     */
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelOrder hotelOrder) {
        return toAjax(hotelOrderService.updateHotelOrder(hotelOrder));
    }

    /**
     * 删除订单
     */
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds) {
        return toAjax(hotelOrderService.deleteHotelOrderByOrderIds(orderIds));
    }
}

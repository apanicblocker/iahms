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
import cn.apkr.hotel.domain.HotelCustomer;
import cn.apkr.hotel.service.IHotelCustomerService;
import cn.apkr.common.core.page.TableDataInfo;

/**
 * 客户;该是 user 的一个属性扩展Controller
 *
 * @author apkr
 * @date 2024-12-06
 */
@RestController
@RequestMapping("/hotel/customer")
public class HotelCustomerController extends BaseController {

    @Autowired
    private IHotelCustomerService hotelCustomerService;

    /**
     * 查询客户;该是 user 的一个属性扩展列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HotelCustomer hotelCustomer) {
        startPage();
        List<HotelCustomer> hotelCustomerList = hotelCustomerService.selectHotelCustomerList(hotelCustomer);
        return getDataTable(hotelCustomerList);
    }

    /**
     * 获取客户;该是 user 的一个属性扩展详细信息
     */
    @GetMapping(value = "/{customerId}")
    public AjaxResult getByCustomerId(@PathVariable("customerId") Long customerId) {
        return success(hotelCustomerService.selectHotelCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户;该是 user 的一个属性扩展
     */
    @Log(title = "客户;该是 user 的一个属性扩展", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelCustomer hotelCustomer) {
        return toAjax(hotelCustomerService.insertHotelCustomer(hotelCustomer));
    }

    /**
     * 修改客户;该是 user 的一个属性扩展
     */
    @Log(title = "客户;该是 user 的一个属性扩展", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelCustomer hotelCustomer) {
        return toAjax(hotelCustomerService.updateHotelCustomer(hotelCustomer));
    }

    /**
     * 删除客户;该是 user 的一个属性扩展
     */
    @Log(title = "客户;该是 user 的一个属性扩展", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds) {
        return toAjax(hotelCustomerService.deleteHotelCustomerByCustomerIds(customerIds));
    }
}

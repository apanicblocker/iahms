package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.hotel.domain.HotelCustomer;
import cn.apkr.hotel.domain.HotelTagCustomer;
import cn.apkr.hotel.service.IHotelCustomerService;
import cn.apkr.hotel.service.IHotelTagCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户Controller
 *
 * @author apkr
 * @date 2024-12-09
 */
@Tag(name = "客户")
@RestController
@RequestMapping("/hotel/customer")
public class HotelCustomerController extends BaseController {

    @Autowired
    private IHotelCustomerService hotelCustomerService;

    @Autowired
    private IHotelTagCustomerService tagCustomerService;

    /**
     * 查询客户列表
     */
    @Operation(summary = "查询客户列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelCustomer hotelCustomer) {
        startPage();
        List<HotelCustomer> hotelCustomerList = hotelCustomerService.selectHotelCustomerList(hotelCustomer);
        return getDataTable(hotelCustomerList);
    }

    /**
     * 获取客户详细信息
     */
    @Operation(summary = "获取客户详细信息")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getByCustomerId(@PathVariable("customerId") Long customerId) {
        return success(hotelCustomerService.selectHotelCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户
     */
    @Operation(summary = "新增客户")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelCustomer hotelCustomer) {
        hotelCustomer.setCreateBy(SecurityUtils.getUserId());
        return toAjax(hotelCustomerService.insertHotelCustomer(hotelCustomer));
    }

    /**
     * 修改客户
     */
    @Operation(summary = "修改客户")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelCustomer hotelCustomer) {
        hotelCustomer.setUpdateBy(SecurityUtils.getUserId());
        return toAjax(hotelCustomerService.updateHotelCustomer(hotelCustomer));
    }

    /**
     * 删除客户
     */
    @Operation(summary = "删除客户")
    @Log(title = "客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable("customerIds") Long[] customerIds) {
        return toAjax(hotelCustomerService.deleteHotelCustomerByCustomerIds(customerIds));
    }

    @Operation(summary = "批量设置黑名单")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping("/batch/setBlackFlag")
    public AjaxResult batchSetBlackFlag(@RequestParam("customerIds") Long[] customerIds,
                                        @RequestParam("blackFlag") Boolean blackFlag) {
    	return AjaxResult.success(hotelCustomerService.batchSetBlackFlag(customerIds, blackFlag));
    }


    @Operation(summary = "批量加标签")
    @Log(title = "客户&标签", businessType = BusinessType.UPDATE)
    @PutMapping("/batch/setTags")
    public AjaxResult batchTagCustomer(@RequestParam("customerIds") Long[] customerIds,
                                       @RequestParam("tagIds") Long[] tagIds) {
        List<HotelTagCustomer> tagCustomerList = new ArrayList<>();
        HotelTagCustomer tempTagCustomer = new HotelTagCustomer();
        for (long customerId : customerIds) {
            tempTagCustomer.setCustomerId(customerId);
            for (long tagId : tagIds) {
                tempTagCustomer.setTagId(tagId);
                tagCustomerList.add(tempTagCustomer);
            }
        }
    	return AjaxResult.success(tagCustomerService.batchTagCustomer(tagCustomerList));
    }
}

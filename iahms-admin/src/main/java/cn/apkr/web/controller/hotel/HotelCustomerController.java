package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.hotel.domain.HotelCustomer;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.service.IHotelCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "客户管理")
@RestController
@RequestMapping("/hotel/customer")
public class HotelCustomerController extends BaseController {

	@Autowired
	private IHotelCustomerService customerService;

	@Operation(summary = "获取客户列表")
	@GetMapping("/list")
	public TableDataInfo list(HotelCustomer customer) {
		startPage();
		List<HotelCustomer> list = customerService.selectCustomerList(customer);
		return getDataTable(list);
	}

	@Operation(summary = "新增用户")
	@Log(title = "客户管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody HotelCustomer customer) {
		customer.setCreateBy(getUserId());	// 设置更新者
		return toAjax(customerService.insertCustomer(customer));
	}

	@Operation(summary = "修改客户")
	@Log(title = "客户管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody HotelCustomer customer) {
		customer.setUpdateBy(getUserId());
		return toAjax(customerService.updateCustomer(customer));
	}

	@Operation(summary = "删除客户")
	@Log(title = "客户管理" , businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
	public AjaxResult remove(@PathVariable(name = "customerIds") Long[] customerIds) {
		return toAjax(customerService.deleteCustomerByIds(customerIds));
	}

//	/**
//	 * 查询客户历史订单
//	 * @return
//	 */
//	@Operation(summary = "查询历史账单")
//	@GetMapping("/history")
//	public TableDataInfo history() {
//
//	}

}

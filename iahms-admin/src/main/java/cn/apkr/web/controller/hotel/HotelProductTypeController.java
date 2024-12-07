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
import cn.apkr.hotel.domain.HotelProductType;
import cn.apkr.hotel.service.IHotelProductTypeService;
import cn.apkr.common.core.page.TableDataInfo;

/**
 * 商品类型Controller
 *
 * @author apkr
 * @datetime 2024-12-06
 */
@RestController
@RequestMapping("/hotel/product/type")
public class HotelProductTypeController extends BaseController {

    @Autowired
    private IHotelProductTypeService hotelProductTypeService;

    /**
     * 查询商品类型列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HotelProductType hotelProductType) {
        startPage();
        List<HotelProductType> hotelProductTypeList = hotelProductTypeService.selectHotelProductTypeList(hotelProductType);
        return getDataTable(hotelProductTypeList);
    }

    /**
     * 获取商品类型详细信息
     */
    @GetMapping(value = "/{productTypeId}")
    public AjaxResult getByProductTypeId(@PathVariable("productTypeId") Long productTypeId) {
        return success(hotelProductTypeService.selectHotelProductTypeByProductTypeId(productTypeId));
    }

    /**
     * 新增商品类型
     */
    @Log(title = "商品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelProductType hotelProductType) {
        return toAjax(hotelProductTypeService.insertHotelProductType(hotelProductType));
    }

    /**
     * 修改商品类型
     */
    @Log(title = "商品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelProductType hotelProductType) {
        return toAjax(hotelProductTypeService.updateHotelProductType(hotelProductType));
    }

    /**
     * 删除商品类型
     */
    @Log(title = "商品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productTypeIds}")
    public AjaxResult remove(@PathVariable Long[] productTypeIds) {
        return toAjax(hotelProductTypeService.deleteHotelProductTypeByProductTypeIds(productTypeIds));
    }
}

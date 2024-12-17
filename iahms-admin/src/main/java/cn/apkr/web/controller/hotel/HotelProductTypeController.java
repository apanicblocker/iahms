package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelProductType;
import cn.apkr.hotel.service.IHotelProductTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类型Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "商品类型")
@RestController
@RequestMapping("/hotel/productType")
public class HotelProductTypeController extends BaseController {

    @Autowired
    private IHotelProductTypeService hotelProductTypeService;

    /**
     * 查询商品类型列表
     */
    @Operation(summary = "查询商品类型列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelProductType hotelProductType) {
        startPage();
        List<HotelProductType> hotelProductTypeList = hotelProductTypeService.selectHotelProductTypeList(hotelProductType);
        return getDataTable(hotelProductTypeList);
    }

    /**
     * 获取商品类型详细信息
     */
    @Operation(summary = "获取商品类型详细信息")
    @GetMapping(value = "/{productTypeId}")
    public AjaxResult getByProductTypeId(@PathVariable("productTypeId") Long productTypeId) {
        return success(hotelProductTypeService.selectHotelProductTypeByProductTypeId(productTypeId));
    }

    /**
     * 新增商品类型
     */
    @Operation(summary = "新增商品类型")
    @Log(title = "商品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelProductType hotelProductType) {
        return toAjax(hotelProductTypeService.insertHotelProductType(hotelProductType));
    }

    /**
     * 修改商品类型
     */
    @Operation(summary = "修改商品类型")
    @Log(title = "商品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelProductType hotelProductType) {
        return toAjax(hotelProductTypeService.updateHotelProductType(hotelProductType));
    }

    /**
     * 删除商品类型
     */
    @Operation(summary = "删除商品类型")
    @Log(title = "商品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productTypeIds}")
    public AjaxResult remove(@PathVariable("productTypeIds") Long[] productTypeIds) {
        return toAjax(hotelProductTypeService.deleteHotelProductTypeByProductTypeIds(productTypeIds));
    }
}

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
import cn.apkr.hotel.domain.HotelProduct;
import cn.apkr.hotel.service.IHotelProductService;
import cn.apkr.common.core.page.TableDataInfo;

/**
 * 商品Controller
 *
 * @author apkr
 * @date 2024-12-06
 */
@RestController
@RequestMapping("/hotel/product")
public class HotelProductController extends BaseController {

    @Autowired
    private IHotelProductService hotelProductService;

    /**
     * 查询商品列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HotelProduct hotelProduct) {
        startPage();
        List<HotelProduct> hotelProductList = hotelProductService.selectHotelProductList(hotelProduct);
        return getDataTable(hotelProductList);
    }

    /**
     * 获取商品详细信息
     */
    @GetMapping(value = "/{productId}")
    public AjaxResult getByProductId(@PathVariable("productId") Long productId) {
        return success(hotelProductService.selectHotelProductByProductId(productId));
    }

    /**
     * 新增商品
     */
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelProduct hotelProduct) {
        return toAjax(hotelProductService.insertHotelProduct(hotelProduct));
    }

    /**
     * 修改商品
     */
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelProduct hotelProduct) {
        return toAjax(hotelProductService.updateHotelProduct(hotelProduct));
    }

    /**
     * 删除商品
     */
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds) {
        return toAjax(hotelProductService.deleteHotelProductByProductIds(productIds));
    }
}

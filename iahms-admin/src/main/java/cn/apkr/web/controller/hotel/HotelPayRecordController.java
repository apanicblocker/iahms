package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.hotel.domain.HotelPayRecord;
import cn.apkr.hotel.service.IHotelPayRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 支付记录Controller
 *
 * @author apkr
 * @date 2024-12-16
 */
@Tag(name = "支付记录")
@RestController
@RequestMapping("/hotel/payRecord")
public class HotelPayRecordController extends BaseController {

    @Autowired
    private IHotelPayRecordService hotelPayRecordService;

    /**
     * 查询支付记录列表
     */
    @Operation(summary = "查询支付记录列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelPayRecord hotelPayRecord) {
        startPage();
        List<HotelPayRecord> hotelPayRecordList = hotelPayRecordService.selectHotelPayRecordList(hotelPayRecord);
        return getDataTable(hotelPayRecordList);
    }

    /**
     * 获取支付记录详细信息
     */
    @Operation(summary = "获取支付记录详细信息")
    @GetMapping(value = "/{payRecordId}")
    public AjaxResult getByPayRecordId(@PathVariable("payRecordId") Long payRecordId) {
        return success(hotelPayRecordService.selectHotelPayRecordByPayRecordId(payRecordId));
    }

    /**
     * 新增支付记录
     */
    @Operation(summary = "新增支付记录")
    @Log(title = "支付记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelPayRecord hotelPayRecord) {
        return toAjax(hotelPayRecordService.insertHotelPayRecord(hotelPayRecord));
    }

    /**
     * 修改支付记录
     */
    @Operation(summary = "修改支付记录")
    @Log(title = "支付记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelPayRecord hotelPayRecord) {
        return toAjax(hotelPayRecordService.updateHotelPayRecord(hotelPayRecord));
    }

    /**
     * 删除支付记录
     */
    @Operation(summary = "删除支付记录")
    @Log(title = "支付记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{payRecordIds}")
    public AjaxResult remove(@PathVariable("payRecordIds") Long[] payRecordIds) {
        return toAjax(hotelPayRecordService.deleteHotelPayRecordByPayRecordIds(payRecordIds));
    }
}

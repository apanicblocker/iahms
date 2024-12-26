package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.service.IHotelTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签Controller
 *
 * @author apkr
 * @date 2024-12-21
 */
@Tag(name = "标签")
@RestController
@RequestMapping("/hotel/tag")
public class HotelTagController extends BaseController {

    @Autowired
    private IHotelTagService hotelTagService;

    /**
     * 查询标签列表
     */
    @Operation(summary = "查询标签列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelTag hotelTag) {
        startPage();
        List<HotelTag> hotelTagList = hotelTagService.selectHotelTagList(hotelTag);
        return getDataTable(hotelTagList);
    }

    /**
     * 获取标签详细信息
     */
    @Operation(summary = "获取标签详细信息")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getByTagId(@PathVariable("tagId") Long tagId) {
        return success(hotelTagService.selectHotelTagByTagId(tagId));
    }

    /**
     * 新增标签
     */
    @Operation(summary = "新增标签")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HotelTag hotelTag) {
        hotelTag.setCreateBy(SecurityUtils.getUserId());
        hotelTag.setCreateName(SecurityUtils.getUsername());
        return toAjax(hotelTagService.insertHotelTag(hotelTag));
    }

    /**
     * 修改标签
     */
    @Operation(summary = "修改标签")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody HotelTag hotelTag) {
        hotelTag.setUpdateBy(SecurityUtils.getUserId());
        return toAjax(hotelTagService.updateHotelTag(hotelTag));
    }

    /**
     * 删除标签
     */
    @Operation(summary = "删除标签")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable("tagIds") Long[] tagIds) {
        return toAjax(hotelTagService.deleteHotelTagByTagIds(tagIds));
    }
}

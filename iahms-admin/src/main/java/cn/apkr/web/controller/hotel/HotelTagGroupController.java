package cn.apkr.web.controller.hotel;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.page.TableDataInfo;
import cn.apkr.common.enums.BusinessType;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.hotel.domain.HotelTagGroup;
import cn.apkr.hotel.service.IHotelTagGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签组Controller
 *
 * @author apkr
 * @date 2024-12-21
 */
@Tag(name = "标签组")
@RestController
@RequestMapping("/hotel/tagGroup")
public class HotelTagGroupController extends BaseController {

    @Autowired
    private IHotelTagGroupService hotelTagGroupService;

    /**
     * 查询标签组列表
     */
    @Operation(summary = "查询标签组列表")
    @GetMapping("/list")
    public TableDataInfo list(HotelTagGroup hotelTagGroup) {
        startPage();
        List<HotelTagGroup> hotelTagGroupList = hotelTagGroupService.selectHotelTagGroupList(hotelTagGroup);
        return getDataTable(hotelTagGroupList);
    }

    /**
     * 获取标签组详细信息
     */
    @Operation(summary = "获取标签组详细信息")
    @GetMapping(value = "/{tagGroupId}")
    public AjaxResult getByTagGroupId(@PathVariable("tagGroupId") Long tagGroupId) {
        return success(hotelTagGroupService.selectHotelTagGroupByTagGroupId(tagGroupId));
    }

    /**
     * 新增标签组
     */
    @Operation(summary = "新增标签组")
    @Log(title = "标签组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HotelTagGroup hotelTagGroup) {
        hotelTagGroup.setCreateBy(SecurityUtils.getUserId());
        hotelTagGroup.setCreateName(SecurityUtils.getUsername());
        return toAjax(hotelTagGroupService.insertHotelTagGroup(hotelTagGroup));
    }

    /**
     * 修改标签组
     */
    @Operation(summary = "修改标签组")
    @Log(title = "标签组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody HotelTagGroup hotelTagGroup) {
        hotelTagGroup.setUpdateBy(SecurityUtils.getUserId());
        return toAjax(hotelTagGroupService.updateHotelTagGroup(hotelTagGroup));
    }

    /**
     * 删除标签组
     */
    @Operation(summary = "删除标签组")
    @Log(title = "标签组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagGroupIds}")
    public AjaxResult remove(@PathVariable("tagGroupIds") Long[] tagGroupIds) {
        return toAjax(hotelTagGroupService.deleteHotelTagGroupByTagGroupIds(tagGroupIds));
    }
}

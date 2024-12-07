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
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.service.IHotelTagService;
import cn.apkr.common.core.page.TableDataInfo;

/**
 * 标签Controller
 *
 * @author apkr
 * @date 2024-12-06
 */
@RestController
@RequestMapping("/hotel/tag")
public class HotelTagController extends BaseController {

    @Autowired
    private IHotelTagService hotelTagService;

    /**
     * 查询标签列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HotelTag hotelTag) {
        startPage();
        List<HotelTag> hotelTagList = hotelTagService.selectHotelTagList(hotelTag);
        return getDataTable(hotelTagList);
    }

    /**
     * 获取标签详细信息
     */
    @GetMapping(value = "/{tagId}")
    public AjaxResult getByTagId(@PathVariable("tagId") Long tagId) {
        return success(hotelTagService.selectHotelTagByTagId(tagId));
    }

    /**
     * 新增标签
     */
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HotelTag hotelTag) {
        return toAjax(hotelTagService.insertHotelTag(hotelTag));
    }

    /**
     * 修改标签
     */
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HotelTag hotelTag) {
        return toAjax(hotelTagService.updateHotelTag(hotelTag));
    }

    /**
     * 删除标签
     */
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds) {
        return toAjax(hotelTagService.deleteHotelTagByTagIds(tagIds));
    }
}

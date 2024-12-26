package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelTagGroup;

/**
 * 标签组Service接口
 * 
 * @author apkr
 * @date 2024-12-21
 */
public interface IHotelTagGroupService {

    /**
     * 查询标签组
     * 
     * @param tagGroupId 标签组主键
     * @return 标签组
     */
    public HotelTagGroup selectHotelTagGroupByTagGroupId(Long tagGroupId);

    /**
     * 查询标签组列表
     * 
     * @param hotelTagGroup 标签组
     * @return 标签组集合
     */
    public List<HotelTagGroup> selectHotelTagGroupList(HotelTagGroup hotelTagGroup);

    /**
     * 新增标签组
     * 
     * @param hotelTagGroup 标签组
     * @return 结果
     */
    public int insertHotelTagGroup(HotelTagGroup hotelTagGroup);

    /**
     * 修改标签组
     * 
     * @param hotelTagGroup 标签组
     * @return 结果
     */
    public int updateHotelTagGroup(HotelTagGroup hotelTagGroup);

    /**
     * 批量删除标签组
     * 
     * @param tagGroupIds 需要删除的标签组主键集合
     * @return 结果
     */
    public int deleteHotelTagGroupByTagGroupIds(Long[] tagGroupIds);

    /**
     * 删除标签组信息
     * 
     * @param tagGroupId 标签组主键
     * @return 结果
     */
    public int deleteHotelTagGroupByTagGroupId(Long tagGroupId);
}

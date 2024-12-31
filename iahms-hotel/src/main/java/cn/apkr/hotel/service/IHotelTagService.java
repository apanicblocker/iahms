package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelTag;

/**
 * 标签Service接口
 * 
 * @author apkr
 * @date 2024-12-21
 */
public interface IHotelTagService {

    /**
     * 查询标签列表
     *
     * @param hotelTag 标签
     * @return 标签集合
     */
    public List<HotelTag> selectHotelTagList(HotelTag hotelTag);

    /**
     * 查询标签
     * 
     * @param tagId 标签主键
     * @return 标签
     */
    public HotelTag selectHotelTagByTagId(Long tagId);

    /**
     * 根据标签组ID查询标签列表
     * @param tagGroupId 标签组ID
     * @return 标签集合
     */
    List<HotelTag> selectHotelTagByTagGroupId(Long tagGroupId);

    /**
     * 新增标签
     * 
     * @param hotelTag 标签
     * @return 结果
     */
    public int insertHotelTag(HotelTag hotelTag);

    /**
     * 修改标签
     * 
     * @param hotelTag 标签
     * @return 结果
     */
    public int updateHotelTag(HotelTag hotelTag);

    /**
     * 更新标签关联客户数量
     * @param tagId 标签ID
     * @param number 更改数量
     * @return 结果
     */
    int updateRelCusNum(Long tagId, Integer number);

    /**
     * 标签关联客户数量+1
     * @param tagId 标签ID
     * @return 结果
     */
    int incrementRelCusNum(Long tagId);

    /**
     * 标签关联客户数量-1
     * @param tagId 标签ID
     * @return 结果
     */
    int decrementRelCusNum(Long tagId);

    /**
     * 批量删除标签
     * 
     * @param tagIds 需要删除的标签主键集合
     * @return 结果
     */
    public int deleteHotelTagByTagIds(Long[] tagIds);

    /**
     * 删除标签信息
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    public int deleteHotelTagByTagId(Long tagId);
}

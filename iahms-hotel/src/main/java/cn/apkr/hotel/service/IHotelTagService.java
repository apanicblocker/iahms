package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelTag;

/**
 * 标签Service接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface IHotelTagService {

    /**
     * 查询标签
     * 
     * @param tagId 标签主键
     * @return 标签
     */
    public HotelTag selectHotelTagByTagId(Long tagId);

    /**
     * 查询标签列表
     * 
     * @param hotelTag 标签
     * @return 标签集合
     */
    public List<HotelTag> selectHotelTagList(HotelTag hotelTag);

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

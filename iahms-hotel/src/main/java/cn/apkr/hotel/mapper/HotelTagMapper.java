package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelTag;
import org.apache.ibatis.annotations.Param;

/**
 * 标签Mapper接口
 * 
 * @author apkr
 * @date 2024-12-21
 */
public interface HotelTagMapper {

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
     * 检查标签是否存在
     * @param tagId 标签ID
     * @return 结果
     */
    boolean checkTagIdExists(@Param("tagId") Long tagId);

    /**
     * 检查标签是否存在
     * @param label 标签名
     * @return 结果
     */
    boolean checkLabelExists(@Param("label") String label);

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
     * 设置标签关联客户数量
     * @param tagId
     * @param number
     * @return
     */
    int updateRelCusNum(@Param("tagId") Long tagId, @Param("number") Integer number);

    /**
     * 客户关联数量+1
     * @param tagId 标签ID
     * @return 结果
     */
    int incrementRelCusNum(@Param("tagId") Long tagId);

    /**
     * 客户关联数量-1
     * @param tagId 标签ID
     * @return 结果
     */
    int decrementRelCusNum(@Param("tagId") Long tagId);

    /**
     * 删除标签
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    public int deleteHotelTagByTagId(Long tagId);

    /**
     * 批量删除标签
     * 
     * @param tagIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelTagByTagIds(Long[] tagIds);
}

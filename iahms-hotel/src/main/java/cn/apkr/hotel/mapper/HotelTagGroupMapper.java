package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelTagGroup;
import org.apache.ibatis.annotations.Param;

/**
 * 标签组Mapper接口
 * 
 * @author apkr
 * @date 2024-12-21
 */
public interface HotelTagGroupMapper {

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
     * 检查标签组是否存在
     * @param tagGroupId 标签组ID
     * @return 结果
     */
    boolean checkTagGroupIdExists(@Param("tagGroupId") Long tagGroupId);

    /**
     * 检查标签组是否存在
     * @param name 标签组名称
     * @return 结果
     */
    boolean checkNameExists(@Param("name") String name);

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
     * 标签组关联标签数量+1
     * @param tagGroupId 标签组ID
     * @return 结果
     */
    int incrementRelTagNum(@Param("tagGroupId") Long tagGroupId);

    /**
     * 标签组关联标签数量-1
     * @param tagGroupId 标签组ID
     * @return 结果
     */
    int decrementRelTagNum(@Param("tagGroupId") Long tagGroupId);

    /**
     * 删除标签组
     * 
     * @param tagGroupId 标签组主键
     * @return 结果
     */
    public int deleteHotelTagGroupByTagGroupId(Long tagGroupId);

    /**
     * 批量删除标签组
     * 
     * @param tagGroupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelTagGroupByTagGroupIds(Long[] tagGroupIds);
}

package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelTagGroupMapper;
import cn.apkr.hotel.domain.HotelTagGroup;
import cn.apkr.hotel.service.IHotelTagGroupService;

/**
 * 标签组Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-21
 */
@Service
public class HotelTagGroupServiceImpl implements IHotelTagGroupService {

    @Autowired
    private HotelTagGroupMapper hotelTagGroupMapper;

    /**
     * 查询标签组
     * 
     * @param tagGroupId 标签组主键
     * @return 标签组
     */
    @Override
    public HotelTagGroup selectHotelTagGroupByTagGroupId(Long tagGroupId) {
        return hotelTagGroupMapper.selectHotelTagGroupByTagGroupId(tagGroupId);
    }

    /**
     * 查询标签组列表
     * 
     * @param hotelTagGroup 标签组
     * @return 标签组
     */
    @Override
    public List<HotelTagGroup> selectHotelTagGroupList(HotelTagGroup hotelTagGroup) {
        return hotelTagGroupMapper.selectHotelTagGroupList(hotelTagGroup);
    }

    /**
     * 新增标签组
     * 
     * @param hotelTagGroup 标签组
     * @return 结果
     */
    @Override
    public int insertHotelTagGroup(HotelTagGroup hotelTagGroup) {
        hotelTagGroup.setCreateTime(DateUtils.getNowDate());
        return hotelTagGroupMapper.insertHotelTagGroup(hotelTagGroup);
    }

    /**
     * 修改标签组
     * 
     * @param hotelTagGroup 标签组
     * @return 结果
     */
    @Override
    public int updateHotelTagGroup(HotelTagGroup hotelTagGroup) {
        hotelTagGroup.setUpdateTime(DateUtils.getNowDate());
        return hotelTagGroupMapper.updateHotelTagGroup(hotelTagGroup);
    }

    /**
     * 批量删除标签组
     * 
     * @param tagGroupIds 需要删除的标签组主键
     * @return 结果
     */
    @Override
    public int deleteHotelTagGroupByTagGroupIds(Long[] tagGroupIds) {
        return hotelTagGroupMapper.deleteHotelTagGroupByTagGroupIds(tagGroupIds);
    }

    /**
     * 删除标签组信息
     * 
     * @param tagGroupId 标签组主键
     * @return 结果
     */
    @Override
    public int deleteHotelTagGroupByTagGroupId(Long tagGroupId) {
        return hotelTagGroupMapper.deleteHotelTagGroupByTagGroupId(tagGroupId);
    }
}

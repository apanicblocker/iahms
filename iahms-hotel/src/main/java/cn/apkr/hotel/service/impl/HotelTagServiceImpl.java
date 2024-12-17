package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelTagMapper;
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.service.IHotelTagService;

/**
 * 标签Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Service
public class HotelTagServiceImpl implements IHotelTagService {

    @Autowired
    private HotelTagMapper hotelTagMapper;

    /**
     * 查询标签
     * 
     * @param tagId 标签主键
     * @return 标签
     */
    @Override
    public HotelTag selectHotelTagByTagId(Long tagId) {
        return hotelTagMapper.selectHotelTagByTagId(tagId);
    }

    /**
     * 查询标签列表
     * 
     * @param hotelTag 标签
     * @return 标签
     */
    @Override
    public List<HotelTag> selectHotelTagList(HotelTag hotelTag) {
        return hotelTagMapper.selectHotelTagList(hotelTag);
    }

    /**
     * 新增标签
     * 
     * @param hotelTag 标签
     * @return 结果
     */
    @Override
    public int insertHotelTag(HotelTag hotelTag) {
        hotelTag.setCreateTime(DateUtils.getNowDate());
        return hotelTagMapper.insertHotelTag(hotelTag);
    }

    /**
     * 修改标签
     * 
     * @param hotelTag 标签
     * @return 结果
     */
    @Override
    public int updateHotelTag(HotelTag hotelTag) {
        hotelTag.setUpdateTime(DateUtils.getNowDate());
        return hotelTagMapper.updateHotelTag(hotelTag);
    }

    /**
     * 批量删除标签
     * 
     * @param tagIds 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteHotelTagByTagIds(Long[] tagIds) {
        return hotelTagMapper.deleteHotelTagByTagIds(tagIds);
    }

    /**
     * 删除标签信息
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    @Override
    public int deleteHotelTagByTagId(Long tagId) {
        return hotelTagMapper.deleteHotelTagByTagId(tagId);
    }
}

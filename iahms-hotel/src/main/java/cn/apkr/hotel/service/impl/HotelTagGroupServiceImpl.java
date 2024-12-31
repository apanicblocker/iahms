package cn.apkr.hotel.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.DateUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.mapper.HotelTagMapper;
import cn.apkr.hotel.service.IHotelTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelTagGroupMapper;
import cn.apkr.hotel.domain.HotelTagGroup;
import cn.apkr.hotel.service.IHotelTagGroupService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private HotelTagMapper hotelTagMapper;

    @Autowired
    private IHotelTagService tagService;

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
        // 验证标签名是否冲突
        String name = hotelTagGroup.getName();
        if (hotelTagGroupMapper.checkNameExists(name)) {
            throw new ServiceException(StringUtils.format("标签组名 “{}” 已存在", name));
        }
        // 插入标签组
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
        HotelTagGroup oldTagGroup = hotelTagGroupMapper.selectHotelTagGroupByTagGroupId(hotelTagGroup.getTagGroupId());
        // 判断被修改标签否存在
        if (oldTagGroup == null) {
            throw new ServiceException("标签组不存在");
        }
        String newTagName = hotelTagGroup.getName();
        if (!newTagName.equals(oldTagGroup.getName()) && hotelTagGroupMapper.checkNameExists(newTagName)) {
            throw new ServiceException(StringUtils.format("标签名 “{}” 已存在", newTagName));
        }
        return hotelTagGroupMapper.updateHotelTagGroup(hotelTagGroup);
    }

    /**
     * 批量删除标签组
     * 
     * @param tagGroupIds 需要删除的标签组主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelTagGroupByTagGroupIds(Long[] tagGroupIds) {
        for (Long tagGroupId : tagGroupIds) {
            if (hotelTagGroupMapper.checkTagGroupIdExists(tagGroupId)) {
                // 删除标签组下的标签
                List<HotelTag> tagList = tagService.selectHotelTagByTagGroupId(tagGroupId);
                if (StringUtils.isNotEmpty(tagList)) {
                    List<Long> tagIdList = new ArrayList<>();
                    for (HotelTag tag : tagList) {
                        tagIdList.add(tag.getTagId());
                    }
                    Long[] tagIds = tagIdList.toArray(new Long[0]);
                    tagService.deleteHotelTagByTagIds(tagIds);
                }
            } else {
                throw new ServiceException(StringUtils.format("标签组ID {} 不存在", tagGroupId));
            }
        }
        return hotelTagGroupMapper.deleteHotelTagGroupByTagGroupIds(tagGroupIds);
    }

    /**
     * 删除标签组信息
     * 
     * @param tagGroupId 标签组主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelTagGroupByTagGroupId(Long tagGroupId) {
        if (hotelTagGroupMapper.checkTagGroupIdExists(tagGroupId)) {
            // 删除标签组下的标签
            List<HotelTag> tagList = tagService.selectHotelTagByTagGroupId(tagGroupId);
            if (StringUtils.isNotEmpty(tagList)) {
                List<Long> tagIdList = new ArrayList<>();
                for (HotelTag tag : tagList) {
                    tagIdList.add(tag.getTagId());
                }
                Long[] tagIds = tagIdList.toArray(new Long[0]);
                tagService.deleteHotelTagByTagIds(tagIds);
            }
        } else {
            throw new ServiceException(StringUtils.format("标签组ID {} 不存在", tagGroupId));
        }
        return hotelTagGroupMapper.deleteHotelTagGroupByTagGroupId(tagGroupId);
    }
}

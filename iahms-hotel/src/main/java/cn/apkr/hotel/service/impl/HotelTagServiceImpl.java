package cn.apkr.hotel.service.impl;

import java.util.List;

import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.DateUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.hotel.domain.HotelTagGroup;
import cn.apkr.hotel.mapper.HotelTagGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelTagMapper;
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.service.IHotelTagService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 标签Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-21
 */
@Service
public class HotelTagServiceImpl implements IHotelTagService {

    private static final Logger log = LoggerFactory.getLogger(HotelTagServiceImpl.class);

    @Autowired
    private HotelTagMapper hotelTagMapper;

    @Autowired
    private HotelTagGroupMapper hotelTagGroupMapper;

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
    @Transactional
    public int insertHotelTag(HotelTag hotelTag) {
        int result = 0;
        // 验证标签名是否冲突
        if (hotelTagMapper.checkLabelExists(hotelTag.getLabel())) {
            throw new ServiceException("标签名已存在");
        }
        if (!hotelTagGroupMapper.checkTagGroupIdExists(hotelTag.getTagGroupId())) {
            throw new ServiceException("标签组不存在");
        }
        result = hotelTagMapper.insertHotelTag(hotelTag);
        if (result > 0) {
            hotelTagGroupMapper.incrementRelTagNum(hotelTag.getTagGroupId());
        }
        return result;
    }

    /**
     * 修改标签
     *
     * @param hotelTag 标签
     * @return 结果
     */
    @Override
    @Transactional
    public int updateHotelTag(HotelTag hotelTag) {
        int result = 0;
        HotelTag oldTag = hotelTagMapper.selectHotelTagByTagId(hotelTag.getTagId());
        // 判断被修改标签否存在
        if (oldTag == null) {
            throw new ServiceException("标签不存在");
        }
        String newTagLabel = hotelTag.getLabel();
        if (!newTagLabel.equals(oldTag.getLabel()) && hotelTagMapper.checkLabelExists(newTagLabel)) {
            throw new ServiceException(StringUtils.format("标签名 “{}” 已存在", newTagLabel));
        }
        // 更新标签
        result = hotelTagMapper.updateHotelTag(hotelTag);
        if (result > 0) {
            // 更新成功，则更新标签组关联标签数量
            Long oldTagGroupId = oldTag.getTagGroupId();
            Long newTagGroupId = hotelTag.getTagGroupId();
            if (!hotelTagGroupMapper.checkTagGroupIdExists(newTagGroupId)) {
                throw new ServiceException("标签组不存在");
            }
            if (!oldTagGroupId.equals(newTagGroupId)) {
                hotelTagGroupMapper.decrementRelTagNum(oldTagGroupId);
                hotelTagGroupMapper.incrementRelTagNum(newTagGroupId);
            }
        }
        return result;
    }

    /**
     * 批量删除标签
     *
     * @param tagIds 需要删除的标签主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelTagByTagIds(Long[] tagIds) {
        for (Long tagId : tagIds) {
            Long tagGroupId = hotelTagMapper.selectHotelTagByTagId(tagId).getTagGroupId();
            if (hotelTagGroupMapper.checkTagGroupIdExists(tagGroupId)) {
                hotelTagGroupMapper.decrementRelTagNum(tagGroupId);
            }
        }
        return hotelTagMapper.deleteHotelTagByTagIds(tagIds);
    }

    /**
     * 删除标签信息
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelTagByTagId(Long tagId) {
        Long tagGroupId = hotelTagMapper.selectHotelTagByTagId(tagId).getTagGroupId();
        if (hotelTagGroupMapper.checkTagGroupIdExists(tagGroupId)) {
            hotelTagGroupMapper.decrementRelTagNum(tagGroupId);
        }
        return hotelTagMapper.deleteHotelTagByTagId(tagId);
    }
}

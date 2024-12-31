package cn.apkr.hotel.service.impl;

import java.util.List;

import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.hotel.mapper.HotelTagGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelTagMapper;
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.service.IHotelTagService;
import org.springframework.transaction.annotation.Transactional;

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
     * 根据标签组ID查询标签列表
     * @param tagGroupId 标签组ID
     * @return 标签集合
     */
    @Override
    public List<HotelTag> selectHotelTagByTagGroupId(Long tagGroupId) {
        return hotelTagMapper.selectHotelTagByTagGroupId(tagGroupId);
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
        String label = hotelTag.getLabel();
        if (hotelTagMapper.checkLabelExists(label)) {
            throw new ServiceException(StringUtils.format("标签名 “{}” 已存在", label));
        }
        // 插入标签
        result = hotelTagMapper.insertHotelTag(hotelTag);
        if (result > 0) {
            // 插入成功，则更新标签组关联标签数量
            Long tagGroupId = hotelTag.getTagGroupId();
            if (tagGroupId != 0 && !hotelTagGroupMapper.checkTagGroupIdExists(tagGroupId)) {
                throw new ServiceException("标签组不存在");
            }
            hotelTagGroupMapper.incrementRelTagNum(tagGroupId);
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
            if (newTagGroupId != 0 && !hotelTagGroupMapper.checkTagGroupIdExists(newTagGroupId)) {
                throw new ServiceException(StringUtils.format("标签组ID {} 不存在", newTagGroupId));
            }
            if (!oldTagGroupId.equals(newTagGroupId)) {
                hotelTagGroupMapper.decrementRelTagNum(oldTagGroupId);
                hotelTagGroupMapper.incrementRelTagNum(newTagGroupId);
            }
        }
        return result;
    }

    /**
     * 更新标签关联客户数量
     * @param tagId 标签ID
     * @param number 客户数量
     * @return 结果
     */
    @Override
    public int updateRelCusNum(Long tagId, Integer number) {
        if (number == null) {
            number = 0;
        }
        return hotelTagMapper.updateRelCusNum(tagId, number);
    }

    /**
     * 客户关联数量+1
     * @param tagId 标签ID
     * @return 结果
     */
    @Override
    public int incrementRelCusNum(Long tagId) {
        return hotelTagMapper.incrementRelCusNum(tagId);
    }

    /**
     * 客户关联数量-1
     * @param tagId 标签ID
     * @return 结果
     */
    @Override
    public int decrementRelCusNum(Long tagId) {
        return hotelTagMapper.decrementRelCusNum(tagId);
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
            HotelTag tag = hotelTagMapper.selectHotelTagByTagId(tagId);
            if (tag == null) {
                throw new ServiceException(StringUtils.format("标签ID {} 不存在", tagId));
            }
            hotelTagGroupMapper.decrementRelTagNum(tag.getTagGroupId());
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
        HotelTag tag = hotelTagMapper.selectHotelTagByTagId(tagId);
        if (tag == null) {
            throw new ServiceException(StringUtils.format("标签ID {} 不存在", tagId));
        }
        hotelTagGroupMapper.decrementRelTagNum(tag.getTagGroupId());
        return hotelTagMapper.deleteHotelTagByTagId(tagId);
    }
}

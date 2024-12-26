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
        // 验证标签组是否合规并插入标签
        Long tagGroupId = hotelTag.getTagGroupId();
        // 判断新标签组ID是否合法
        if (tagGroupId == 0) {
            result = hotelTagMapper.insertHotelTag(hotelTag);
        } else if (hotelTagGroupMapper.checkTagGroupIdExists(tagGroupId)) {
            result = hotelTagMapper.insertHotelTag(hotelTag);
            // 插入成功则更新标签组关联标签数量
            if (result > 0) {
                hotelTagGroupMapper.incrementRelTagNum(tagGroupId);
            } else {
                throw new ServiceException("新增标签失败");
            }
        } else {
            throw new ServiceException("标签组不存在");
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
        Long oldTagGroupId = oldTag.getTagGroupId();
        Long newTagGroupId = hotelTag.getTagGroupId();
        // 判断新标签组ID是否合法
        if (newTagGroupId == 0) {
            log.info("0表示未分组标签，任何想要把标签组ID重新设置为0的都跳过");
            return 1;
        }
        if (!hotelTagGroupMapper.checkTagGroupIdExists(newTagGroupId)) {
            throw new ServiceException("标签组不存在");
        }
        // 修改标签
        result = hotelTagMapper.updateHotelTag(hotelTag);
        if (result > 0) {
            // 判断标签组是否发生变更
            if (!oldTagGroupId.equals(newTagGroupId)) {
                if (oldTagGroupId != 0) {
                    hotelTagGroupMapper.decrementRelTagNum(oldTagGroupId);
                }
                hotelTagGroupMapper.incrementRelTagNum(newTagGroupId);
            }
        } else {
            throw new ServiceException("新增标签失败");
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
        // TODO
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
        // TODO
        return hotelTagMapper.deleteHotelTagByTagId(tagId);
    }
}

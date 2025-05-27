package cn.apkr.hotel.service.impl;

import java.util.List;

import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelRoomTypeMapper;
import cn.apkr.hotel.domain.HotelRoomType;
import cn.apkr.hotel.service.IHotelRoomTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 房型Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Slf4j
@Service
public class HotelRoomTypeServiceImpl implements IHotelRoomTypeService {

    @Autowired
    private HotelRoomTypeMapper hotelRoomTypeMapper;

    /**
     * 查询房型
     * 
     * @param roomTypeId 房型主键
     * @return 房型
     */
    @Override
    public HotelRoomType selectHotelRoomTypeByRoomTypeId(Long roomTypeId) {
        HotelRoomType roomType = hotelRoomTypeMapper.selectHotelRoomTypeByRoomTypeId(roomTypeId);
        if (roomType == null) {
            throw new ServiceException("查询失败: 编号为 " + roomTypeId + " 的房型不存在");
        }
        return roomType;
    }

    /**
     * 查询房型列表
     * 
     * @param hotelRoomType 房型
     * @return 房型
     */
    @Override
    public List<HotelRoomType> selectHotelRoomTypeList(HotelRoomType hotelRoomType) {
        return hotelRoomTypeMapper.selectHotelRoomTypeList(hotelRoomType);
    }

    /**
     * 新增房型
     * 
     * @param hotelRoomType 房型
     * @return 结果
     */
    @Override
    public int insertHotelRoomType(HotelRoomType hotelRoomType) {
        hotelRoomType.setQuantity(0);   // 房间数量默认为0
        hotelRoomType.setCreateTime(DateUtils.getNowDate());
        return hotelRoomTypeMapper.insertHotelRoomType(hotelRoomType);
    }

    /**
     * 修改房型
     * 
     * @param hotelRoomType 房型
     * @return 结果
     */
    @Override
    @Transactional
    public int updateHotelRoomType(HotelRoomType hotelRoomType) {
        hotelRoomType.setUpdateTime(DateUtils.getNowDate());
        return hotelRoomTypeMapper.updateHotelRoomType(hotelRoomType);
    }

    /**
     * 批量删除房型
     * 
     * @param roomTypeIds 需要删除的房型主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelRoomTypeByRoomTypeIds(Long[] roomTypeIds) {
        if (roomTypeIds.length == 0) {
            throw new ServiceException("请选择要删除的房型");
        }

        // 删除数据行数
        int row = 0;
        for (Long roomTypeId : roomTypeIds) {
            row += deleteHotelRoomTypeByRoomTypeId(roomTypeId);
        }
        log.info("共批量删除了 {} 行房型数据", row);
        return row;
    }

    /**
     * 删除房型信息
     * 
     * @param roomTypeId 房型主键
     * @return 结果
     */
    @Override
    public int deleteHotelRoomTypeByRoomTypeId(Long roomTypeId) {
        // 检查当前房型是否存在
        HotelRoomType roomType = selectHotelRoomTypeByRoomTypeId(roomTypeId);
        // 检查当前房型下是否有房间
        if (roomType.getQuantity() > 0) {
            throw new ServiceException("房型删除失败: 编号为 " + roomTypeId + " 的房型下有房间，请先删除房间");
        }
        return hotelRoomTypeMapper.deleteHotelRoomTypeByRoomTypeId(roomTypeId);
    }

    /**
     * 更新房型关联房间数量
     *
     * @param roomTypeId 房型编号
     * @param offset 数量偏差值
     * @return 结果
     */
    @Override
    public int updateRoomTypeQuantity(Long roomTypeId, int offset) {
        int newQuantity = selectHotelRoomTypeByRoomTypeId(roomTypeId).getQuantity() + offset;
        int row = hotelRoomTypeMapper.updateHotelRoomTypeQuantity(roomTypeId, newQuantity);

        char sign = offset >= 0 ? '+' : '-';
        log.info("房型编号 {} 的房型数量已 {}{}", roomTypeId, sign, offset);
        return row;
    }

}

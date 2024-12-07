package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelRoomTypeMapper;
import cn.apkr.hotel.domain.HotelRoomType;
import cn.apkr.hotel.service.IHotelRoomTypeService;

/**
 * 房型Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-06
 */
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
        return hotelRoomTypeMapper.selectHotelRoomTypeByRoomTypeId(roomTypeId);
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
    public int deleteHotelRoomTypeByRoomTypeIds(Long[] roomTypeIds) {
        return hotelRoomTypeMapper.deleteHotelRoomTypeByRoomTypeIds(roomTypeIds);
    }

    /**
     * 删除房型信息
     * 
     * @param roomTypeId 房型主键
     * @return 结果
     */
    @Override
    public int deleteHotelRoomTypeByRoomTypeId(Long roomTypeId) {
        return hotelRoomTypeMapper.deleteHotelRoomTypeByRoomTypeId(roomTypeId);
    }
}

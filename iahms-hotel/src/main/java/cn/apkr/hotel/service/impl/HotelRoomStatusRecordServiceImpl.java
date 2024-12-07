package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelRoomStatusRecordMapper;
import cn.apkr.hotel.domain.HotelRoomStatusRecord;
import cn.apkr.hotel.service.IHotelRoomStatusRecordService;

/**
 * 房态记录Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-06
 */
@Service
public class HotelRoomStatusRecordServiceImpl implements IHotelRoomStatusRecordService {

    @Autowired
    private HotelRoomStatusRecordMapper hotelRoomStatusRecordMapper;

    /**
     * 查询房态记录
     * 
     * @param roomStatusRecordId 房态记录主键
     * @return 房态记录
     */
    @Override
    public HotelRoomStatusRecord selectHotelRoomStatusRecordByRoomStatusRecordId(Long roomStatusRecordId) {
        return hotelRoomStatusRecordMapper.selectHotelRoomStatusRecordByRoomStatusRecordId(roomStatusRecordId);
    }

    /**
     * 查询房态记录列表
     * 
     * @param hotelRoomStatusRecord 房态记录
     * @return 房态记录
     */
    @Override
    public List<HotelRoomStatusRecord> selectHotelRoomStatusRecordList(HotelRoomStatusRecord hotelRoomStatusRecord) {
        return hotelRoomStatusRecordMapper.selectHotelRoomStatusRecordList(hotelRoomStatusRecord);
    }

    /**
     * 新增房态记录
     * 
     * @param hotelRoomStatusRecord 房态记录
     * @return 结果
     */
    @Override
    public int insertHotelRoomStatusRecord(HotelRoomStatusRecord hotelRoomStatusRecord) {
        hotelRoomStatusRecord.setCreateTime(DateUtils.getNowDate());
        return hotelRoomStatusRecordMapper.insertHotelRoomStatusRecord(hotelRoomStatusRecord);
    }

    /**
     * 修改房态记录
     * 
     * @param hotelRoomStatusRecord 房态记录
     * @return 结果
     */
    @Override
    public int updateHotelRoomStatusRecord(HotelRoomStatusRecord hotelRoomStatusRecord) {
        hotelRoomStatusRecord.setUpdateTime(DateUtils.getNowDate());
        return hotelRoomStatusRecordMapper.updateHotelRoomStatusRecord(hotelRoomStatusRecord);
    }

    /**
     * 批量删除房态记录
     * 
     * @param roomStatusRecordIds 需要删除的房态记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelRoomStatusRecordByRoomStatusRecordIds(Long[] roomStatusRecordIds) {
        return hotelRoomStatusRecordMapper.deleteHotelRoomStatusRecordByRoomStatusRecordIds(roomStatusRecordIds);
    }

    /**
     * 删除房态记录信息
     * 
     * @param roomStatusRecordId 房态记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelRoomStatusRecordByRoomStatusRecordId(Long roomStatusRecordId) {
        return hotelRoomStatusRecordMapper.deleteHotelRoomStatusRecordByRoomStatusRecordId(roomStatusRecordId);
    }
}

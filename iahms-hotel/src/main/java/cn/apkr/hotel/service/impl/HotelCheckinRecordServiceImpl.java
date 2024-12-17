package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelCheckinRecordMapper;
import cn.apkr.hotel.domain.HotelCheckinRecord;
import cn.apkr.hotel.service.IHotelCheckinRecordService;

/**
 * 入住记录Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Service
public class HotelCheckinRecordServiceImpl implements IHotelCheckinRecordService {

    @Autowired
    private HotelCheckinRecordMapper hotelCheckinRecordMapper;

    /**
     * 查询入住记录
     * 
     * @param checkinRecordId 入住记录主键
     * @return 入住记录
     */
    @Override
    public HotelCheckinRecord selectHotelCheckinRecordByCheckinRecordId(Long checkinRecordId) {
        return hotelCheckinRecordMapper.selectHotelCheckinRecordByCheckinRecordId(checkinRecordId);
    }

    /**
     * 查询入住记录列表
     * 
     * @param hotelCheckinRecord 入住记录
     * @return 入住记录
     */
    @Override
    public List<HotelCheckinRecord> selectHotelCheckinRecordList(HotelCheckinRecord hotelCheckinRecord) {
        return hotelCheckinRecordMapper.selectHotelCheckinRecordList(hotelCheckinRecord);
    }

    /**
     * 新增入住记录
     * 
     * @param hotelCheckinRecord 入住记录
     * @return 结果
     */
    @Override
    public int insertHotelCheckinRecord(HotelCheckinRecord hotelCheckinRecord) {
        hotelCheckinRecord.setCreateTime(DateUtils.getNowDate());
        return hotelCheckinRecordMapper.insertHotelCheckinRecord(hotelCheckinRecord);
    }

    /**
     * 修改入住记录
     * 
     * @param hotelCheckinRecord 入住记录
     * @return 结果
     */
    @Override
    public int updateHotelCheckinRecord(HotelCheckinRecord hotelCheckinRecord) {
        hotelCheckinRecord.setUpdateTime(DateUtils.getNowDate());
        return hotelCheckinRecordMapper.updateHotelCheckinRecord(hotelCheckinRecord);
    }

    /**
     * 批量删除入住记录
     * 
     * @param checkinRecordIds 需要删除的入住记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelCheckinRecordByCheckinRecordIds(Long[] checkinRecordIds) {
        return hotelCheckinRecordMapper.deleteHotelCheckinRecordByCheckinRecordIds(checkinRecordIds);
    }

    /**
     * 删除入住记录信息
     * 
     * @param checkinRecordId 入住记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelCheckinRecordByCheckinRecordId(Long checkinRecordId) {
        return hotelCheckinRecordMapper.deleteHotelCheckinRecordByCheckinRecordId(checkinRecordId);
    }
}

package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelCheckinRecord;

/**
 * 入住记录Mapper接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface HotelCheckinRecordMapper {

    /**
     * 查询入住记录
     * 
     * @param checkinRecordId 入住记录主键
     * @return 入住记录
     */
    public HotelCheckinRecord selectHotelCheckinRecordByCheckinRecordId(Long checkinRecordId);

    /**
     * 查询入住记录列表
     * 
     * @param hotelCheckinRecord 入住记录
     * @return 入住记录集合
     */
    public List<HotelCheckinRecord> selectHotelCheckinRecordList(HotelCheckinRecord hotelCheckinRecord);

    /**
     * 新增入住记录
     * 
     * @param hotelCheckinRecord 入住记录
     * @return 结果
     */
    public int insertHotelCheckinRecord(HotelCheckinRecord hotelCheckinRecord);

    /**
     * 修改入住记录
     * 
     * @param hotelCheckinRecord 入住记录
     * @return 结果
     */
    public int updateHotelCheckinRecord(HotelCheckinRecord hotelCheckinRecord);

    /**
     * 删除入住记录
     * 
     * @param checkinRecordId 入住记录主键
     * @return 结果
     */
    public int deleteHotelCheckinRecordByCheckinRecordId(Long checkinRecordId);

    /**
     * 批量删除入住记录
     * 
     * @param checkinRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelCheckinRecordByCheckinRecordIds(Long[] checkinRecordIds);
}

package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelRoomStatusRecord;

/**
 * 房态记录Service接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface IHotelRoomStatusRecordService {

    /**
     * 查询房态记录
     * 
     * @param roomStatusRecordId 房态记录主键
     * @return 房态记录
     */
    public HotelRoomStatusRecord selectHotelRoomStatusRecordByRoomStatusRecordId(Long roomStatusRecordId);

    /**
     * 查询房态记录列表
     * 
     * @param hotelRoomStatusRecord 房态记录
     * @return 房态记录集合
     */
    public List<HotelRoomStatusRecord> selectHotelRoomStatusRecordList(HotelRoomStatusRecord hotelRoomStatusRecord);

    /**
     * 新增房态记录
     * 
     * @param hotelRoomStatusRecord 房态记录
     * @return 结果
     */
    public int insertHotelRoomStatusRecord(HotelRoomStatusRecord hotelRoomStatusRecord);

    /**
     * 修改房态记录
     * 
     * @param hotelRoomStatusRecord 房态记录
     * @return 结果
     */
    public int updateHotelRoomStatusRecord(HotelRoomStatusRecord hotelRoomStatusRecord);

    /**
     * 批量删除房态记录
     * 
     * @param roomStatusRecordIds 需要删除的房态记录主键集合
     * @return 结果
     */
    public int deleteHotelRoomStatusRecordByRoomStatusRecordIds(Long[] roomStatusRecordIds);

    /**
     * 删除房态记录信息
     * 
     * @param roomStatusRecordId 房态记录主键
     * @return 结果
     */
    public int deleteHotelRoomStatusRecordByRoomStatusRecordId(Long roomStatusRecordId);
}
package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelRoomStatusRecord;

/**
 * 房态记录Mapper接口
 * 
 * @author apkr
 * @date 2024-12-06
 */
public interface HotelRoomStatusRecordMapper {

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
     * 删除房态记录
     * 
     * @param roomStatusRecordId 房态记录主键
     * @return 结果
     */
    public int deleteHotelRoomStatusRecordByRoomStatusRecordId(Long roomStatusRecordId);

    /**
     * 批量删除房态记录
     * 
     * @param roomStatusRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelRoomStatusRecordByRoomStatusRecordIds(Long[] roomStatusRecordIds);
}

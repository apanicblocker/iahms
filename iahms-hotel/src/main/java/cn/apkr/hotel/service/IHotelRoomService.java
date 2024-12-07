package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelRoom;

/**
 * 房间Service接口
 * 
 * @author apkr
 * @date 2024-12-06
 */
public interface IHotelRoomService {

    /**
     * 查询房间
     * 
     * @param roomId 房间主键
     * @return 房间
     */
    public HotelRoom selectHotelRoomByRoomId(Long roomId);

    /**
     * 查询房间列表
     * 
     * @param hotelRoom 房间
     * @return 房间集合
     */
    public List<HotelRoom> selectHotelRoomList(HotelRoom hotelRoom);

    /**
     * 新增房间
     * 
     * @param hotelRoom 房间
     * @return 结果
     */
    public int insertHotelRoom(HotelRoom hotelRoom);

    /**
     * 修改房间
     * 
     * @param hotelRoom 房间
     * @return 结果
     */
    public int updateHotelRoom(HotelRoom hotelRoom);

    /**
     * 批量删除房间
     * 
     * @param roomIds 需要删除的房间主键集合
     * @return 结果
     */
    public int deleteHotelRoomByRoomIds(Long[] roomIds);

    /**
     * 删除房间信息
     * 
     * @param roomId 房间主键
     * @return 结果
     */
    public int deleteHotelRoomByRoomId(Long roomId);
}

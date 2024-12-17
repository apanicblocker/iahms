package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelRoomMapper;
import cn.apkr.hotel.domain.HotelRoom;
import cn.apkr.hotel.service.IHotelRoomService;

/**
 * 房间Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Service
public class HotelRoomServiceImpl implements IHotelRoomService {

    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    /**
     * 查询房间
     * 
     * @param roomId 房间主键
     * @return 房间
     */
    @Override
    public HotelRoom selectHotelRoomByRoomId(Long roomId) {
        return hotelRoomMapper.selectHotelRoomByRoomId(roomId);
    }

    /**
     * 查询房间列表
     * 
     * @param hotelRoom 房间
     * @return 房间
     */
    @Override
    public List<HotelRoom> selectHotelRoomList(HotelRoom hotelRoom) {
        return hotelRoomMapper.selectHotelRoomList(hotelRoom);
    }

    /**
     * 新增房间
     * 
     * @param hotelRoom 房间
     * @return 结果
     */
    @Override
    public int insertHotelRoom(HotelRoom hotelRoom) {
        hotelRoom.setCreateTime(DateUtils.getNowDate());
        return hotelRoomMapper.insertHotelRoom(hotelRoom);
    }

    /**
     * 修改房间
     * 
     * @param hotelRoom 房间
     * @return 结果
     */
    @Override
    public int updateHotelRoom(HotelRoom hotelRoom) {
        hotelRoom.setUpdateTime(DateUtils.getNowDate());
        return hotelRoomMapper.updateHotelRoom(hotelRoom);
    }

    /**
     * 批量删除房间
     * 
     * @param roomIds 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteHotelRoomByRoomIds(Long[] roomIds) {
        return hotelRoomMapper.deleteHotelRoomByRoomIds(roomIds);
    }

    /**
     * 删除房间信息
     * 
     * @param roomId 房间主键
     * @return 结果
     */
    @Override
    public int deleteHotelRoomByRoomId(Long roomId) {
        return hotelRoomMapper.deleteHotelRoomByRoomId(roomId);
    }
}

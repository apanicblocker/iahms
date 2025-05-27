package cn.apkr.hotel.service.impl;

import java.util.List;

import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.DateUtils;
import cn.apkr.hotel.domain.HotelRoomType;
import cn.apkr.hotel.mapper.HotelRoomTypeMapper;
import cn.apkr.hotel.service.IHotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelRoomMapper;
import cn.apkr.hotel.domain.HotelRoom;
import cn.apkr.hotel.service.IHotelRoomService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 房间Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Slf4j
@Service
public class HotelRoomServiceImpl implements IHotelRoomService {

    @Autowired
    private IHotelRoomTypeService roomTypeService;

    @Autowired
    private HotelRoomMapper roomMapper;

    /**
     * 查询房间
     * 
     * @param roomId 房间主键
     * @return 房间
     */
    @Override
    public HotelRoom selectHotelRoomByRoomId(Long roomId) {
        HotelRoom room = roomMapper.selectHotelRoomByRoomId(roomId);
        if (room == null) {
            throw new ServiceException("查询失败: 编号为 " + roomId + " 的房间不存在");
        }
        return room;
    }

    /**
     * 查询房间列表
     * 
     * @param hotelRoom 房间
     * @return 房间
     */
    @Override
    public List<HotelRoom> selectHotelRoomList(HotelRoom hotelRoom) {
        return roomMapper.selectHotelRoomList(hotelRoom);
    }

    /**
     * 新增房间
     * 
     * @param hotelRoom 房间
     * @return 结果
     */
    @Override
    @Transactional
    public int insertHotelRoom(HotelRoom hotelRoom) {
        // 验证房型
        Long roomTypeId = hotelRoom.getRoomTypeId();
        HotelRoomType roomType = roomTypeService.selectHotelRoomTypeByRoomTypeId(roomTypeId);
        roomType.setQuantity(roomType.getQuantity() + 1);   // 房间数量加 1
        roomTypeService.updateHotelRoomType(roomType);      // 更新房型数量

        // 房间编号不能重复
        HotelRoom tempRoom = new HotelRoom();
        tempRoom.setNumber(hotelRoom.getNumber());
        List<HotelRoom> numberSelectResult = roomMapper.selectHotelRoomList(tempRoom);
        if (!numberSelectResult.isEmpty()) {
            throw new ServiceException("房间创建失败: 房间编号 " + hotelRoom.getNumber() + " 已存在");
        }

        // 设置默认值
        if (hotelRoom.getPrice() == null) {
            hotelRoom.setPrice(roomType.getPrice());
        }
        hotelRoom.setDirtyFlag(false);
        hotelRoom.setCreateTime(DateUtils.getNowDate());

        return roomMapper.insertHotelRoom(hotelRoom);
    }

    /**
     * 修改房间
     * 
     * @param hotelRoom 房间
     * @return 结果
     */
    @Override
    @Transactional
    public int updateHotelRoom(HotelRoom hotelRoom) {
        // 检验房型
        Long newRoomTypeId = hotelRoom.getRoomTypeId();
        if (newRoomTypeId != null) {
            Long oldRoomTypeId = selectHotelRoomByRoomId(hotelRoom.getRoomId()).getRoomTypeId();
            roomTypeService.updateRoomTypeQuantity(oldRoomTypeId, -1);
            roomTypeService.updateRoomTypeQuantity(newRoomTypeId, +1);
        }

        // 检验编号
        String newRoomNumber = hotelRoom.getNumber();
        if (roomMapper.checkRoomNumberExist(newRoomNumber)) {
            throw new ServiceException("房间信息修改失败: 房间编号 " + newRoomNumber + " 已存在");
        }

        hotelRoom.setUpdateTime(DateUtils.getNowDate());
        return roomMapper.updateHotelRoom(hotelRoom);
    }

    /**
     * 批量删除房间
     * 
     * @param roomIds 需要删除的房间主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelRoomByRoomIds(Long[] roomIds) {
        int row = 0;
        for (Long roomId : roomIds) {
            row += deleteHotelRoomByRoomId(roomId);
        }
        return row;
    }

    /**
     * 删除房间信息
     * 
     * @param roomId 房间主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHotelRoomByRoomId(Long roomId) {
        // 验证房间
        HotelRoom room = selectHotelRoomByRoomId(roomId);
        // 对应房型数量减 1
        roomTypeService.updateRoomTypeQuantity(room.getRoomTypeId(), -1);

        return roomMapper.deleteHotelRoomByRoomId(roomId);
    }
}

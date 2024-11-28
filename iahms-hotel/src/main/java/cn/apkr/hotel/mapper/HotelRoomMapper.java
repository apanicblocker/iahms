package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelRoom;

import java.util.List;

public interface HotelRoomMapper {

	List<HotelRoom> selectRoomList(HotelRoom room);

	HotelRoom selectRoomById(Long roomId);

	int insertRoom(HotelRoom room);

	int updateRoom(HotelRoom room);

	int deleteRoomById(Long roomId);

	int deleteRoomByIds(Long[] roomIds);

}

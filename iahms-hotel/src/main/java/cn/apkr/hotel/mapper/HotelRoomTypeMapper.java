package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelRoomType;

import java.util.List;

public interface HotelRoomTypeMapper {

	List<HotelRoomType> selectRoomTypeList(HotelRoomType roomType);

	HotelRoomType selectRoomTypeById(Long roomTypeId);

	int insertRoomType(HotelRoomType roomType);

	int updateRoomType(HotelRoomType roomType);

	int deleteRoomTypeById(Long roomTypeId);

	int deleteRoomTypeByIds(Long[] roomTypeIds);
	
}

package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelRoomStateRecord;

import java.util.List;

public interface HotelRoomStateRecordMapper {

	List<HotelRoomStateRecord> selectRoomStateRecordList(HotelRoomStateRecord roomStateRecord);

	HotelRoomStateRecord selectRoomStateRecordById(Long roomStateRecordStateRecordId);

	int insertRoomStateRecord(HotelRoomStateRecord roomStateRecord);

	int updateRoomStateRecord(HotelRoomStateRecord roomStateRecord);

	int deleteRoomStateRecordById(Long roomStateRecordId);

	int deleteRoomStateRecordByIds(Long[] roomStateRecordIds);

}

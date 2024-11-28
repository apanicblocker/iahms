package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelCheckinRecord;

import java.util.List;

public interface HotelCheckinRecordMapper {

	List<HotelCheckinRecord> selectCheckinRecordList(HotelCheckinRecord checkinRecord);

	HotelCheckinRecord selectCheckinRecordById(Long checkinRecordId);

	int insertCheckinRecord(HotelCheckinRecord checkinRecord);

	int updateCheckinRecord(HotelCheckinRecord checkinRecord);

	int deleteCheckinRecordById(Long checkinRecordId);

	int deleteCheckinRecordByIds(Long[] checkinRecordIds);

}

package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelPayRecord;

import java.util.List;

public interface HotelPayRecordMapper {

	List<HotelPayRecord> selectPayRecordList(HotelPayRecord payRecord);

	HotelPayRecord selectPayRecordById(Long payRecordId);

	int insertPayRecord(HotelPayRecord payRecord);

	int updatePayRecord(HotelPayRecord payRecord);

	int deletePayRecordById(Long payRecordId);

	int deletePayRecordByIds(Long[] payRecordIds);

}

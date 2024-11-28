package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelProductRecord;

import java.util.List;

public interface HotelProductRecordMapper {

	List<HotelProductRecord> selectProductRecordList(HotelProductRecord productRecord);

	HotelProductRecord selectProductRecordById(Long productRecordId);

	int insertProductRecord(HotelProductRecord productRecord);

	int updateProductRecord(HotelProductRecord productRecord);

	int deleteProductRecordById(Long productRecordId);

	int deleteProductRecordByIds(Long[] productRecordIds);
	
}

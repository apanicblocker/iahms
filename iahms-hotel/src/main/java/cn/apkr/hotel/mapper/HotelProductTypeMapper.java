package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelProductType;

import java.util.List;

public interface HotelProductTypeMapper {

	List<HotelProductType> selectProductTypeList(HotelProductType productType);

	HotelProductType selectProductTypeById(Long productTypeId);

	int insertProductType(HotelProductType productType);

	int updateProductType(HotelProductType productType);

	int deleteProductTypeById(Long productTypeId);

	int deleteProductTypeByIds(Long[] productTypeIds);
	
}

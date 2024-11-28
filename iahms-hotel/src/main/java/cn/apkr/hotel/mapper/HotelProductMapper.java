package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelProduct;

import java.util.List;

public interface HotelProductMapper {

	List<HotelProduct> selectProductList(HotelProduct product);

	HotelProduct selectProductById(Long productId);

	int insertProduct(HotelProduct product);

	int updateProduct(HotelProduct product);

	int deleteProductById(Long productId);

	int deleteProductByIds(Long[] productIds);
	
}

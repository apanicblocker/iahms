package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelTagCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelTagCustomerMapper {

	int countTagCustomerByCustomerId(Long customerId);

	int batchTagCustomer(List<HotelTagCustomer> tagCustomerList);

	int deleteTagCustomerByTagId(Long tagId);

	int deleteTagCustomerByTagIds(Long[] tagIds);

	int deleteTagCustomerInfo(HotelTagCustomer tagCustomer);

	int deleteTagCustomerInfos(@Param("tagId") Long tagId,
							   @Param("customerIds") Long[] customerIds);

}

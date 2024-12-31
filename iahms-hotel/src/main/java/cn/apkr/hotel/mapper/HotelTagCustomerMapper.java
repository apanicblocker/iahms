package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.domain.HotelTagCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelTagCustomerMapper {

	List<HotelTag> selectHotelTagByCustomerId(Long CustomerId);

	int countTagCustomerByCustomerId(Long customerId);

	int countTagCustomerByTagId(Long tagId);

	int batchTagCustomer(List<HotelTagCustomer> tagCustomerList);

	int deleteTagCustomerByCustomerId(Long customerId);

	int deleteTagCustomerByCustomerIds(Long[] customerIds);

	int deleteTagCustomerByTagId(Long tagId);

	int deleteTagCustomerByTagIds(Long[] tagIds);

	int deleteTagCustomerInfo(HotelTagCustomer tagCustomer);

	int deleteTagCustomerForCustomer(@Param("customer") Long customerId,
									 @Param("tagIds") Long[] tagIds);

	int deleteTagCustomerTag(@Param("tagId") Long tagId,
							 @Param("customerIds") Long[] customerIds);

}

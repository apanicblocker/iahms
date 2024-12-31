package cn.apkr.hotel.service;

import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.domain.HotelTagCustomer;

import java.util.List;

public interface IHotelTagCustomerService {

	List<HotelTag> selectHotelTagByCustomerId(Long CustomerId);

	int countTagCustomerByCustomerId(Long customerId);

	int countTagCustomerByTagId(Long tagId);

	int batchTagCustomer(List<HotelTagCustomer> tagCustomerList);

	int updateTagCustomerForCustomer(Long[] customerIds, Long[] tagIds);

	int deleteTagCustomerByCustomerId(Long customerId);

	int deleteTagCustomerByCustomerIds(Long[] customerIds);

	int deleteTagCustomerByTagId(Long tagId);

	int deleteTagCustomerByTagIds(Long[] tagIds);

	int deleteTagCustomerInfo(HotelTagCustomer tagCustomer);

	int deleteTagCustomerForCustomer(Long customerId, Long[] tagIds);

	int deleteTagCustomerForTag(Long tagId, Long[] customerIds);

	void recalculateTagCustomerCounts();
}

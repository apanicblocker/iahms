package cn.apkr.hotel.service;

import cn.apkr.hotel.domain.HotelTagCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHotelTagCustomerService {

	int countTagCustomerByCustomerId(Long customerId);

	int batchTagCustomer(List<HotelTagCustomer> tagCustomerList);

	int deleteTagCustomerByTagId(Long tagId);

	int deleteTagCustomerByTagIds(Long[] tagIds);

	int deleteTagCustomerInfo(HotelTagCustomer tagCustomer);

	int deleteTagCustomerInfos(Long tagId, Long[] customerIds);

}

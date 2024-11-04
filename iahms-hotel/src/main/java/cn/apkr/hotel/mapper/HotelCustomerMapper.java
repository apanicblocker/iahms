package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelCustomer;

import java.util.List;

public interface HotelCustomerMapper {

	List<HotelCustomer> selectCustomerList(HotelCustomer customer);

	int insertCustomer(HotelCustomer customer);

	int updateCustomer(HotelCustomer customer);

	int deleteCustomerById(Long customerId);

	int deleteCustomerByIds(Long[] customerIds);
}

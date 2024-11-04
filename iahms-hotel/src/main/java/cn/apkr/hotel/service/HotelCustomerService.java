package cn.apkr.hotel.service;

import cn.apkr.hotel.domain.HotelCustomer;

import java.util.List;

public interface HotelCustomerService {

	List<HotelCustomer> selectCustomerList(HotelCustomer customer);

	int insertCustomer(HotelCustomer customer);

	int updateCustomer(HotelCustomer customer);

	int deleteCustomerById(Long customerId);

	int deleteCustomerByIds(Long[] customerIds);
}

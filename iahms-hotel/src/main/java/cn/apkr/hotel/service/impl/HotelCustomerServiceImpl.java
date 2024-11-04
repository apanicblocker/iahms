package cn.apkr.hotel.service.impl;

import cn.apkr.hotel.domain.HotelCustomer;
import cn.apkr.hotel.mapper.HotelCustomerMapper;
import cn.apkr.hotel.service.HotelCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelCustomerServiceImpl implements HotelCustomerService {

	@Autowired
	private HotelCustomerMapper customerMapper;

	@Override
	public List<HotelCustomer> selectCustomerList(HotelCustomer customer) {
		return customerMapper.selectCustomerList(customer);
	}

	@Override
	public int insertCustomer(HotelCustomer customer) {
		return customerMapper.insertCustomer(customer);
	}

	@Override
	public int updateCustomer(HotelCustomer customer) {
		return customerMapper.updateCustomer(customer);
	}

	@Override
	public int deleteCustomerById(Long customerId) {
		return customerMapper.deleteCustomerById(customerId);
	}

	@Override
	public int deleteCustomerByIds(Long[] customerIds) {
		return customerMapper.deleteCustomerByIds(customerIds);
	}

}

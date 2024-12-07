package cn.apkr.hotel.service.impl;

import cn.apkr.hotel.domain.HotelTagCustomer;
import cn.apkr.hotel.mapper.HotelTagCustomerMapper;
import cn.apkr.hotel.service.IHotelTagCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelTagCustomerServiceImpl implements IHotelTagCustomerService {

	@Autowired
	private HotelTagCustomerMapper tagCustomerMapper;

	@Override
	public int countTagCustomerByCustomerId(Long customerId) {
		return tagCustomerMapper.countTagCustomerByCustomerId(customerId);
	}

	@Override
	public int batchTagCustomer(List<HotelTagCustomer> tagCustomerList) {
		return tagCustomerMapper.batchTagCustomer(tagCustomerList);
	}

	@Override
	public int deleteTagCustomerByTagId(Long tagId) {
		return tagCustomerMapper.deleteTagCustomerByTagId(tagId);
	}

	@Override
	public int deleteTagCustomerByTagIds(Long[] tagIds) {
		return tagCustomerMapper.deleteTagCustomerByTagIds(tagIds);
	}

	@Override
	public int deleteTagCustomerInfo(HotelTagCustomer tagCustomer) {
		return tagCustomerMapper.deleteTagCustomerInfo(tagCustomer);
	}

	@Override
	public int deleteTagCustomerInfos(Long tagId, Long[] customerIds) {
		return tagCustomerMapper.deleteTagCustomerInfos(tagId, customerIds);
	}
}

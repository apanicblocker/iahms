package cn.apkr.hotel.service.impl;

import cn.apkr.hotel.domain.HotelCheckinCustomer;
import cn.apkr.hotel.mapper.HotelCheckinCustomerMapper;
import cn.apkr.hotel.service.IHotelCheckinCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelCheckinCustomerServiceImpl implements IHotelCheckinCustomerService {

	@Autowired
	private HotelCheckinCustomerMapper checkinCustomerMapper;

	@Override
	public int countCheckinCustomerByCustomerId(Long customerId) {
		return checkinCustomerMapper.countCheckinCustomerByCustomerId(customerId);
	}

	@Override
	public int batchCheckinCustomer(List<HotelCheckinCustomer> checkinCustomerList) {
		return checkinCustomerMapper.batchCheckinCustomer(checkinCustomerList);
	}

	@Override
	public int deleteCheckinCustomerByCheckinRecordId(Long checkinRecordId) {
		return checkinCustomerMapper.deleteCheckinCustomerByCheckinRecordId(checkinRecordId);
	}

	@Override
	public int deleteCheckinCustomerByCheckinRecordIds(Long[] checkinRecordIds) {
		return checkinCustomerMapper.deleteCheckinCustomerByCheckinRecordIds(checkinRecordIds);
	}

	@Override
	public int deleteCheckinCustomerInfo(HotelCheckinCustomer checkinCustomer) {
		return checkinCustomerMapper.deleteCheckinCustomerInfo(checkinCustomer);
	}

	@Override
	public int deleteCheckinCustomerInfos(Long checkinRecordId, Long[] customerIds) {
		return checkinCustomerMapper.deleteCheckinCustomerInfos(checkinRecordId, customerIds);
	}
}

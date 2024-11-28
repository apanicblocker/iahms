package cn.apkr.hotel.service.impl;

import cn.apkr.hotel.mapper.HotelOrderMapper;
import cn.apkr.hotel.service.IHotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelOrderServiceImpl implements IHotelOrderService {

	@Autowired
	private HotelOrderMapper orderMapper;

}

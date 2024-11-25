package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelOrder;

import java.util.List;

public interface HotelOrderMapper {

	List<HotelOrder> selectOrderList(HotelOrder order);

	int insertOrder(HotelOrder order);

	int updateOrder(HotelOrder order);

	int deleteOrderById(Long orderId);

	int deleteOrderByIds(Long[] orderIds);
}

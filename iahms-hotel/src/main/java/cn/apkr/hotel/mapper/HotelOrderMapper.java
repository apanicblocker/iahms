package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelOrder;

import java.util.List;

public interface HotelOrderMapper {

	List<HotelOrder> selectOrderList(HotelOrder order);

	HotelOrder selectOrderById(Long orderId);

	List<HotelOrder> selectOrderListByNumber(String number);

	int insertOrder(HotelOrder order);

	int updateOrder(HotelOrder order);

	int deleteOrderById(Long orderId);

	int deleteOrderByIds(Long[] orderIds);
}

package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelOrder;

/**
 * 订单Service接口
 * 
 * @author apkr
 * @date 2024-12-06
 */
public interface IHotelOrderService {

    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    public HotelOrder selectHotelOrderByOrderId(Long orderId);

    /**
     * 查询订单列表
     * 
     * @param hotelOrder 订单
     * @return 订单集合
     */
    public List<HotelOrder> selectHotelOrderList(HotelOrder hotelOrder);

    /**
     * 新增订单
     * 
     * @param hotelOrder 订单
     * @return 结果
     */
    public int insertHotelOrder(HotelOrder hotelOrder);

    /**
     * 修改订单
     * 
     * @param hotelOrder 订单
     * @return 结果
     */
    public int updateHotelOrder(HotelOrder hotelOrder);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteHotelOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteHotelOrderByOrderId(Long orderId);
}

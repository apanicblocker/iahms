package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelOrderMapper;
import cn.apkr.hotel.domain.HotelOrder;
import cn.apkr.hotel.service.IHotelOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-06
 */
@Service
public class HotelOrderServiceImpl implements IHotelOrderService {

    @Autowired
    private HotelOrderMapper hotelOrderMapper;

    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public HotelOrder selectHotelOrderByOrderId(Long orderId) {
        return hotelOrderMapper.selectHotelOrderByOrderId(orderId);
    }

    /**
     * 查询订单列表
     * 
     * @param hotelOrder 订单
     * @return 订单
     */
    @Override
    public List<HotelOrder> selectHotelOrderList(HotelOrder hotelOrder) {
        return hotelOrderMapper.selectHotelOrderList(hotelOrder);
    }

    /**
     * 新增订单
     * 
     * @param hotelOrder 订单
     * @return 结果
     */
    @Override
    public int insertHotelOrder(HotelOrder hotelOrder) {
        hotelOrder.setCreateTime(DateUtils.getNowDate());
        return hotelOrderMapper.insertHotelOrder(hotelOrder);
    }

    /**
     * 修改订单
     * 
     * @param hotelOrder 订单
     * @return 结果
     */
    @Override
    public int updateHotelOrder(HotelOrder hotelOrder) {
        hotelOrder.setUpdateTime(DateUtils.getNowDate());
        return hotelOrderMapper.updateHotelOrder(hotelOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteHotelOrderByOrderIds(Long[] orderIds) {
        return hotelOrderMapper.deleteHotelOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteHotelOrderByOrderId(Long orderId) {
        return hotelOrderMapper.deleteHotelOrderByOrderId(orderId);
    }
}

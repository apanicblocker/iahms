package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelCustomerMapper;
import cn.apkr.hotel.domain.HotelCustomer;
import cn.apkr.hotel.service.IHotelCustomerService;

/**
 * 客户;该是 user 的一个属性扩展Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-06
 */
@Service
public class HotelCustomerServiceImpl implements IHotelCustomerService {

    @Autowired
    private HotelCustomerMapper hotelCustomerMapper;

    /**
     * 查询客户;该是 user 的一个属性扩展
     * 
     * @param customerId 客户;该是 user 的一个属性扩展主键
     * @return 客户;该是 user 的一个属性扩展
     */
    @Override
    public HotelCustomer selectHotelCustomerByCustomerId(Long customerId) {
        return hotelCustomerMapper.selectHotelCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户;该是 user 的一个属性扩展列表
     * 
     * @param hotelCustomer 客户;该是 user 的一个属性扩展
     * @return 客户;该是 user 的一个属性扩展
     */
    @Override
    public List<HotelCustomer> selectHotelCustomerList(HotelCustomer hotelCustomer) {
        return hotelCustomerMapper.selectHotelCustomerList(hotelCustomer);
    }

    /**
     * 新增客户;该是 user 的一个属性扩展
     * 
     * @param hotelCustomer 客户;该是 user 的一个属性扩展
     * @return 结果
     */
    @Override
    public int insertHotelCustomer(HotelCustomer hotelCustomer) {
        hotelCustomer.setCreateTime(DateUtils.getNowDate());
        return hotelCustomerMapper.insertHotelCustomer(hotelCustomer);
    }

    /**
     * 修改客户;该是 user 的一个属性扩展
     * 
     * @param hotelCustomer 客户;该是 user 的一个属性扩展
     * @return 结果
     */
    @Override
    public int updateHotelCustomer(HotelCustomer hotelCustomer) {
        hotelCustomer.setUpdateTime(DateUtils.getNowDate());
        return hotelCustomerMapper.updateHotelCustomer(hotelCustomer);
    }

    /**
     * 批量删除客户;该是 user 的一个属性扩展
     * 
     * @param customerIds 需要删除的客户;该是 user 的一个属性扩展主键
     * @return 结果
     */
    @Override
    public int deleteHotelCustomerByCustomerIds(Long[] customerIds) {
        return hotelCustomerMapper.deleteHotelCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户;该是 user 的一个属性扩展信息
     * 
     * @param customerId 客户;该是 user 的一个属性扩展主键
     * @return 结果
     */
    @Override
    public int deleteHotelCustomerByCustomerId(Long customerId) {
        return hotelCustomerMapper.deleteHotelCustomerByCustomerId(customerId);
    }
}

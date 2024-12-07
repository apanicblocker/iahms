package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelCustomer;

/**
 * 客户;该是 user 的一个属性扩展Service接口
 * 
 * @author apkr
 * @date 2024-12-06
 */
public interface IHotelCustomerService {

    /**
     * 查询客户;该是 user 的一个属性扩展
     * 
     * @param customerId 客户;该是 user 的一个属性扩展主键
     * @return 客户;该是 user 的一个属性扩展
     */
    public HotelCustomer selectHotelCustomerByCustomerId(Long customerId);

    /**
     * 查询客户;该是 user 的一个属性扩展列表
     * 
     * @param hotelCustomer 客户;该是 user 的一个属性扩展
     * @return 客户;该是 user 的一个属性扩展集合
     */
    public List<HotelCustomer> selectHotelCustomerList(HotelCustomer hotelCustomer);

    /**
     * 新增客户;该是 user 的一个属性扩展
     * 
     * @param hotelCustomer 客户;该是 user 的一个属性扩展
     * @return 结果
     */
    public int insertHotelCustomer(HotelCustomer hotelCustomer);

    /**
     * 修改客户;该是 user 的一个属性扩展
     * 
     * @param hotelCustomer 客户;该是 user 的一个属性扩展
     * @return 结果
     */
    public int updateHotelCustomer(HotelCustomer hotelCustomer);

    /**
     * 批量删除客户;该是 user 的一个属性扩展
     * 
     * @param customerIds 需要删除的客户;该是 user 的一个属性扩展主键集合
     * @return 结果
     */
    public int deleteHotelCustomerByCustomerIds(Long[] customerIds);

    /**
     * 删除客户;该是 user 的一个属性扩展信息
     * 
     * @param customerId 客户;该是 user 的一个属性扩展主键
     * @return 结果
     */
    public int deleteHotelCustomerByCustomerId(Long customerId);
}

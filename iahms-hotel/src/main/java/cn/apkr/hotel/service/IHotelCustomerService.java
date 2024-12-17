package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelCustomer;

/**
 * 客户Service接口
 * 
 * @author apkr
 * @date 2024-12-09
 */
public interface IHotelCustomerService {

    /**
     * 查询客户
     * 
     * @param customerId 客户主键
     * @return 客户
     */
    public HotelCustomer selectHotelCustomerByCustomerId(Long customerId);

    /**
     * 查询客户列表
     * 
     * @param hotelCustomer 客户
     * @return 客户集合
     */
    public List<HotelCustomer> selectHotelCustomerList(HotelCustomer hotelCustomer);

    /**
     * 新增客户
     * 
     * @param hotelCustomer 客户
     * @return 结果
     */
    public int insertHotelCustomer(HotelCustomer hotelCustomer);

    /**
     * 修改客户
     * 
     * @param hotelCustomer 客户
     * @return 结果
     */
    public int updateHotelCustomer(HotelCustomer hotelCustomer);

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的客户主键集合
     * @return 结果
     */
    public int deleteHotelCustomerByCustomerIds(Long[] customerIds);

    /**
     * 删除客户信息
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    public int deleteHotelCustomerByCustomerId(Long customerId);

	int batchSetBlackFlag(Long[] customerIds, Boolean blackFlag);
}

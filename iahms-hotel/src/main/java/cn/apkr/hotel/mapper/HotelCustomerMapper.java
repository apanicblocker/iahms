package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelCustomer;
import org.apache.ibatis.annotations.Param;

/**
 * 客户Mapper接口
 * 
 * @author apkr
 * @date 2024-12-09
 */
public interface HotelCustomerMapper {

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
     * 删除客户
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    public int deleteHotelCustomerByCustomerId(Long customerId);

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelCustomerByCustomerIds(Long[] customerIds);

    /**
     * 批量设置黑名单标识
     * @param customerIds 客户ID数组
     * @param blackFlag 黑名单标识
     * @return 结果
     */
    public int batchSetBlackFlag(@Param("customerIds") Long[] customerIds,
                                 @Param("blackFlag") Boolean blackFlag,
                                 @Param("updateBy") Long updateBy);
}

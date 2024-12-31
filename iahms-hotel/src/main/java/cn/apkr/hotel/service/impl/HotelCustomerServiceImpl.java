package cn.apkr.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.apkr.common.utils.DateUtils;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.hotel.domain.HotelTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelCustomerMapper;
import cn.apkr.hotel.domain.HotelCustomer;
import cn.apkr.hotel.service.IHotelCustomerService;

/**
 * 客户Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-09
 */
@Service
public class HotelCustomerServiceImpl implements IHotelCustomerService {

    @Autowired
    private HotelCustomerMapper hotelCustomerMapper;

    /**
     * 查询客户
     * 
     * @param customerId 客户主键
     * @return 客户
     */
    @Override
    public HotelCustomer selectHotelCustomerByCustomerId(Long customerId) {
        return hotelCustomerMapper.selectHotelCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户列表
     * 
     * @param hotelCustomer 客户
     * @return 客户
     */
    @Override
    public List<HotelCustomer> selectHotelCustomerList(HotelCustomer hotelCustomer) {
        List<HotelCustomer> customerList = hotelCustomerMapper.selectHotelCustomerList(hotelCustomer);
        // 过滤标签
        Long[] tagIds = hotelCustomer.getTagIds();
        if (tagIds != null && tagIds.length > 0) {
            List<Long> tagIdList = List.of(tagIds); // 牺牲一点性能换可读性
            List<HotelCustomer> filterTagsCustomerList = new ArrayList<>();
            for (HotelCustomer customer : customerList) {
                Set<HotelTag> tags = customer.getTags();
                if (tags != null && !tags.isEmpty()) {
                    for (HotelTag tag : tags) {
                        if (tagIdList.contains(tag.getTagId())) {
                            filterTagsCustomerList.add(customer);
                        }
                    }
                }

            }
            customerList = filterTagsCustomerList;
        }
        return customerList;
    }

    /**
     * 新增客户
     * 
     * @param hotelCustomer 客户
     * @return 结果
     */
    @Override
    public int insertHotelCustomer(HotelCustomer hotelCustomer) {
        hotelCustomer.setCreateTime(DateUtils.getNowDate());
        return hotelCustomerMapper.insertHotelCustomer(hotelCustomer);
    }

    /**
     * 修改客户
     * 
     * @param hotelCustomer 客户
     * @return 结果
     */
    @Override
    public int updateHotelCustomer(HotelCustomer hotelCustomer) {
        hotelCustomer.setUpdateTime(DateUtils.getNowDate());
        return hotelCustomerMapper.updateHotelCustomer(hotelCustomer);
    }

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteHotelCustomerByCustomerIds(Long[] customerIds) {
        return hotelCustomerMapper.deleteHotelCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户信息
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    @Override
    public int deleteHotelCustomerByCustomerId(Long customerId) {
        return hotelCustomerMapper.deleteHotelCustomerByCustomerId(customerId);
    }

    /**
     * 批量设置黑名单标识
     * @param customerIds 客户ID数组
     * @param blackFlag 黑名单标识
     * @return 结果
     */
    @Override
    public int batchSetBlackFlag(Long[] customerIds, Boolean blackFlag) {
        return hotelCustomerMapper.batchSetBlackFlag(customerIds, blackFlag, SecurityUtils.getUserId());
    }
}

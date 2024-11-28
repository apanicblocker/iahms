package cn.apkr.hotel.mapper;

import cn.apkr.hotel.domain.HotelCheckinCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelCheckinCustomerMapper {

	/**
	 * 根据客户ID查询入住记录和客户关联信息数量
	 * @param customerId 客户ID
	 * @return 数量
	 */
	int countCheckinCustomerByCustomerId(Long customerId);

	/**
	 * 批量插入入住记录和客户关联信息
	 * @param checkinCustomerList 入住记录和客户关联信息列表
	 * @return 插入数量
	 */
	int batchCheckinCustomer(List<HotelCheckinCustomer> checkinCustomerList);

	/**
	 * 根据入住记录ID删除入住记录和客户关联信息
	 * @param checkinRecordId 入住记录ID
	 * @return 删除数量
	 */
	int deleteCheckinCustomerByCheckinRecordId(Long checkinRecordId);

	/**
	 * 根据入住记录ID批量删除入住记录和客户关联信息
	 * @param checkinRecordIds 入住记录ID数组
	 * @return 删除数量
	 */
	int deleteCheckinCustomerByCheckinRecordIds(Long[] checkinRecordIds);

	/**
	 * 删除入住记录和客户关联信息
	 * @param checkinCustomer 入住记录和客户关联信息
	 * @return 删除数量
	 */
	int deleteCheckinCustomerInfo(HotelCheckinCustomer checkinCustomer);

	/**
	 * 批量删除入住记录和客户关联信息
	 * @param checkinRecordId 入住记录ID
	 * @param customerIds 客户ID数组
	 * @return 删除数量
	 */
	int deleteCheckinCustomerInfos(@Param("checkinRecordId") Long checkinRecordId,
								   @Param("customerIds") Long[] customerIds);

}

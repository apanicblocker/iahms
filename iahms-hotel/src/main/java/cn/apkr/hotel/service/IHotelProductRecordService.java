package cn.apkr.hotel.service;

import java.util.List;
import cn.apkr.hotel.domain.HotelProductRecord;

/**
 * 购物记录Service接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface IHotelProductRecordService {

    /**
     * 查询购物记录
     * 
     * @param productRecordId 购物记录主键
     * @return 购物记录
     */
    public HotelProductRecord selectHotelProductRecordByProductRecordId(Long productRecordId);

    /**
     * 查询购物记录列表
     * 
     * @param hotelProductRecord 购物记录
     * @return 购物记录集合
     */
    public List<HotelProductRecord> selectHotelProductRecordList(HotelProductRecord hotelProductRecord);

    /**
     * 新增购物记录
     * 
     * @param hotelProductRecord 购物记录
     * @return 结果
     */
    public int insertHotelProductRecord(HotelProductRecord hotelProductRecord);

    /**
     * 修改购物记录
     * 
     * @param hotelProductRecord 购物记录
     * @return 结果
     */
    public int updateHotelProductRecord(HotelProductRecord hotelProductRecord);

    /**
     * 批量删除购物记录
     * 
     * @param productRecordIds 需要删除的购物记录主键集合
     * @return 结果
     */
    public int deleteHotelProductRecordByProductRecordIds(Long[] productRecordIds);

    /**
     * 删除购物记录信息
     * 
     * @param productRecordId 购物记录主键
     * @return 结果
     */
    public int deleteHotelProductRecordByProductRecordId(Long productRecordId);
}

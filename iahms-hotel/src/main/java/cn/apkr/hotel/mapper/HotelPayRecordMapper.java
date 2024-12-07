package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelPayRecord;

/**
 * 支付记录Mapper接口
 * 
 * @author apkr
 * @date 2024-12-06
 */
public interface HotelPayRecordMapper {

    /**
     * 查询支付记录
     * 
     * @param payRecordId 支付记录主键
     * @return 支付记录
     */
    public HotelPayRecord selectHotelPayRecordByPayRecordId(Long payRecordId);

    /**
     * 查询支付记录列表
     * 
     * @param hotelPayRecord 支付记录
     * @return 支付记录集合
     */
    public List<HotelPayRecord> selectHotelPayRecordList(HotelPayRecord hotelPayRecord);

    /**
     * 新增支付记录
     * 
     * @param hotelPayRecord 支付记录
     * @return 结果
     */
    public int insertHotelPayRecord(HotelPayRecord hotelPayRecord);

    /**
     * 修改支付记录
     * 
     * @param hotelPayRecord 支付记录
     * @return 结果
     */
    public int updateHotelPayRecord(HotelPayRecord hotelPayRecord);

    /**
     * 删除支付记录
     * 
     * @param payRecordId 支付记录主键
     * @return 结果
     */
    public int deleteHotelPayRecordByPayRecordId(Long payRecordId);

    /**
     * 批量删除支付记录
     * 
     * @param payRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelPayRecordByPayRecordIds(Long[] payRecordIds);
}

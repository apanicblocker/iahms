package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelPayRecordMapper;
import cn.apkr.hotel.domain.HotelPayRecord;
import cn.apkr.hotel.service.IHotelPayRecordService;

/**
 * 支付记录Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Service
public class HotelPayRecordServiceImpl implements IHotelPayRecordService {

    @Autowired
    private HotelPayRecordMapper hotelPayRecordMapper;

    /**
     * 查询支付记录
     * 
     * @param payRecordId 支付记录主键
     * @return 支付记录
     */
    @Override
    public HotelPayRecord selectHotelPayRecordByPayRecordId(Long payRecordId) {
        return hotelPayRecordMapper.selectHotelPayRecordByPayRecordId(payRecordId);
    }

    /**
     * 查询支付记录列表
     * 
     * @param hotelPayRecord 支付记录
     * @return 支付记录
     */
    @Override
    public List<HotelPayRecord> selectHotelPayRecordList(HotelPayRecord hotelPayRecord) {
        return hotelPayRecordMapper.selectHotelPayRecordList(hotelPayRecord);
    }

    /**
     * 新增支付记录
     * 
     * @param hotelPayRecord 支付记录
     * @return 结果
     */
    @Override
    public int insertHotelPayRecord(HotelPayRecord hotelPayRecord) {
        hotelPayRecord.setCreateTime(DateUtils.getNowDate());
        return hotelPayRecordMapper.insertHotelPayRecord(hotelPayRecord);
    }

    /**
     * 修改支付记录
     * 
     * @param hotelPayRecord 支付记录
     * @return 结果
     */
    @Override
    public int updateHotelPayRecord(HotelPayRecord hotelPayRecord) {
        hotelPayRecord.setUpdateTime(DateUtils.getNowDate());
        return hotelPayRecordMapper.updateHotelPayRecord(hotelPayRecord);
    }

    /**
     * 批量删除支付记录
     * 
     * @param payRecordIds 需要删除的支付记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelPayRecordByPayRecordIds(Long[] payRecordIds) {
        return hotelPayRecordMapper.deleteHotelPayRecordByPayRecordIds(payRecordIds);
    }

    /**
     * 删除支付记录信息
     * 
     * @param payRecordId 支付记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelPayRecordByPayRecordId(Long payRecordId) {
        return hotelPayRecordMapper.deleteHotelPayRecordByPayRecordId(payRecordId);
    }
}

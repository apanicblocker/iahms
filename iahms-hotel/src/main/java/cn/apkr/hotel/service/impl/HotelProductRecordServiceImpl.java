package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelProductRecordMapper;
import cn.apkr.hotel.domain.HotelProductRecord;
import cn.apkr.hotel.service.IHotelProductRecordService;

/**
 * 购物记录Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-06
 */
@Service
public class HotelProductRecordServiceImpl implements IHotelProductRecordService {

    @Autowired
    private HotelProductRecordMapper hotelProductRecordMapper;

    /**
     * 查询购物记录
     * 
     * @param productRecordId 购物记录主键
     * @return 购物记录
     */
    @Override
    public HotelProductRecord selectHotelProductRecordByProductRecordId(Long productRecordId) {
        return hotelProductRecordMapper.selectHotelProductRecordByProductRecordId(productRecordId);
    }

    /**
     * 查询购物记录列表
     * 
     * @param hotelProductRecord 购物记录
     * @return 购物记录
     */
    @Override
    public List<HotelProductRecord> selectHotelProductRecordList(HotelProductRecord hotelProductRecord) {
        return hotelProductRecordMapper.selectHotelProductRecordList(hotelProductRecord);
    }

    /**
     * 新增购物记录
     * 
     * @param hotelProductRecord 购物记录
     * @return 结果
     */
    @Override
    public int insertHotelProductRecord(HotelProductRecord hotelProductRecord) {
        hotelProductRecord.setCreateTime(DateUtils.getNowDate());
        return hotelProductRecordMapper.insertHotelProductRecord(hotelProductRecord);
    }

    /**
     * 修改购物记录
     * 
     * @param hotelProductRecord 购物记录
     * @return 结果
     */
    @Override
    public int updateHotelProductRecord(HotelProductRecord hotelProductRecord) {
        hotelProductRecord.setUpdateTime(DateUtils.getNowDate());
        return hotelProductRecordMapper.updateHotelProductRecord(hotelProductRecord);
    }

    /**
     * 批量删除购物记录
     * 
     * @param productRecordIds 需要删除的购物记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelProductRecordByProductRecordIds(Long[] productRecordIds) {
        return hotelProductRecordMapper.deleteHotelProductRecordByProductRecordIds(productRecordIds);
    }

    /**
     * 删除购物记录信息
     * 
     * @param productRecordId 购物记录主键
     * @return 结果
     */
    @Override
    public int deleteHotelProductRecordByProductRecordId(Long productRecordId) {
        return hotelProductRecordMapper.deleteHotelProductRecordByProductRecordId(productRecordId);
    }
}

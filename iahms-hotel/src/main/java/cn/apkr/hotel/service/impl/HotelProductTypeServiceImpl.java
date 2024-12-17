package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelProductTypeMapper;
import cn.apkr.hotel.domain.HotelProductType;
import cn.apkr.hotel.service.IHotelProductTypeService;

/**
 * 商品类型Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-16
 */
@Service
public class HotelProductTypeServiceImpl implements IHotelProductTypeService {

    @Autowired
    private HotelProductTypeMapper hotelProductTypeMapper;

    /**
     * 查询商品类型
     * 
     * @param productTypeId 商品类型主键
     * @return 商品类型
     */
    @Override
    public HotelProductType selectHotelProductTypeByProductTypeId(Long productTypeId) {
        return hotelProductTypeMapper.selectHotelProductTypeByProductTypeId(productTypeId);
    }

    /**
     * 查询商品类型列表
     * 
     * @param hotelProductType 商品类型
     * @return 商品类型
     */
    @Override
    public List<HotelProductType> selectHotelProductTypeList(HotelProductType hotelProductType) {
        return hotelProductTypeMapper.selectHotelProductTypeList(hotelProductType);
    }

    /**
     * 新增商品类型
     * 
     * @param hotelProductType 商品类型
     * @return 结果
     */
    @Override
    public int insertHotelProductType(HotelProductType hotelProductType) {
        hotelProductType.setCreateTime(DateUtils.getNowDate());
        return hotelProductTypeMapper.insertHotelProductType(hotelProductType);
    }

    /**
     * 修改商品类型
     * 
     * @param hotelProductType 商品类型
     * @return 结果
     */
    @Override
    public int updateHotelProductType(HotelProductType hotelProductType) {
        hotelProductType.setUpdateTime(DateUtils.getNowDate());
        return hotelProductTypeMapper.updateHotelProductType(hotelProductType);
    }

    /**
     * 批量删除商品类型
     * 
     * @param productTypeIds 需要删除的商品类型主键
     * @return 结果
     */
    @Override
    public int deleteHotelProductTypeByProductTypeIds(Long[] productTypeIds) {
        return hotelProductTypeMapper.deleteHotelProductTypeByProductTypeIds(productTypeIds);
    }

    /**
     * 删除商品类型信息
     * 
     * @param productTypeId 商品类型主键
     * @return 结果
     */
    @Override
    public int deleteHotelProductTypeByProductTypeId(Long productTypeId) {
        return hotelProductTypeMapper.deleteHotelProductTypeByProductTypeId(productTypeId);
    }
}

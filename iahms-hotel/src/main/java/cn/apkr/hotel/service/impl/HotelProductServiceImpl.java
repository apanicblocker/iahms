package cn.apkr.hotel.service.impl;

import java.util.List;
import cn.apkr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.apkr.hotel.mapper.HotelProductMapper;
import cn.apkr.hotel.domain.HotelProduct;
import cn.apkr.hotel.service.IHotelProductService;

/**
 * 商品Service业务层处理
 * 
 * @author apkr
 * @date 2024-12-06
 */
@Service
public class HotelProductServiceImpl implements IHotelProductService {

    @Autowired
    private HotelProductMapper hotelProductMapper;

    /**
     * 查询商品
     * 
     * @param productId 商品主键
     * @return 商品
     */
    @Override
    public HotelProduct selectHotelProductByProductId(Long productId) {
        return hotelProductMapper.selectHotelProductByProductId(productId);
    }

    /**
     * 查询商品列表
     * 
     * @param hotelProduct 商品
     * @return 商品
     */
    @Override
    public List<HotelProduct> selectHotelProductList(HotelProduct hotelProduct) {
        return hotelProductMapper.selectHotelProductList(hotelProduct);
    }

    /**
     * 新增商品
     * 
     * @param hotelProduct 商品
     * @return 结果
     */
    @Override
    public int insertHotelProduct(HotelProduct hotelProduct) {
        hotelProduct.setCreateTime(DateUtils.getNowDate());
        return hotelProductMapper.insertHotelProduct(hotelProduct);
    }

    /**
     * 修改商品
     * 
     * @param hotelProduct 商品
     * @return 结果
     */
    @Override
    public int updateHotelProduct(HotelProduct hotelProduct) {
        hotelProduct.setUpdateTime(DateUtils.getNowDate());
        return hotelProductMapper.updateHotelProduct(hotelProduct);
    }

    /**
     * 批量删除商品
     * 
     * @param productIds 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteHotelProductByProductIds(Long[] productIds) {
        return hotelProductMapper.deleteHotelProductByProductIds(productIds);
    }

    /**
     * 删除商品信息
     * 
     * @param productId 商品主键
     * @return 结果
     */
    @Override
    public int deleteHotelProductByProductId(Long productId) {
        return hotelProductMapper.deleteHotelProductByProductId(productId);
    }
}

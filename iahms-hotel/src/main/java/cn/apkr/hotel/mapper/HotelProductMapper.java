package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelProduct;

/**
 * 商品Mapper接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface HotelProductMapper {

    /**
     * 查询商品
     * 
     * @param productId 商品主键
     * @return 商品
     */
    public HotelProduct selectHotelProductByProductId(Long productId);

    /**
     * 查询商品列表
     * 
     * @param hotelProduct 商品
     * @return 商品集合
     */
    public List<HotelProduct> selectHotelProductList(HotelProduct hotelProduct);

    /**
     * 新增商品
     * 
     * @param hotelProduct 商品
     * @return 结果
     */
    public int insertHotelProduct(HotelProduct hotelProduct);

    /**
     * 修改商品
     * 
     * @param hotelProduct 商品
     * @return 结果
     */
    public int updateHotelProduct(HotelProduct hotelProduct);

    /**
     * 删除商品
     * 
     * @param productId 商品主键
     * @return 结果
     */
    public int deleteHotelProductByProductId(Long productId);

    /**
     * 批量删除商品
     * 
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelProductByProductIds(Long[] productIds);
}

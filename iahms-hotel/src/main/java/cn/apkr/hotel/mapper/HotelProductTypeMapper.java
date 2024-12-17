package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelProductType;

/**
 * 商品类型Mapper接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface HotelProductTypeMapper {

    /**
     * 查询商品类型
     * 
     * @param productTypeId 商品类型主键
     * @return 商品类型
     */
    public HotelProductType selectHotelProductTypeByProductTypeId(Long productTypeId);

    /**
     * 查询商品类型列表
     * 
     * @param hotelProductType 商品类型
     * @return 商品类型集合
     */
    public List<HotelProductType> selectHotelProductTypeList(HotelProductType hotelProductType);

    /**
     * 新增商品类型
     * 
     * @param hotelProductType 商品类型
     * @return 结果
     */
    public int insertHotelProductType(HotelProductType hotelProductType);

    /**
     * 修改商品类型
     * 
     * @param hotelProductType 商品类型
     * @return 结果
     */
    public int updateHotelProductType(HotelProductType hotelProductType);

    /**
     * 删除商品类型
     * 
     * @param productTypeId 商品类型主键
     * @return 结果
     */
    public int deleteHotelProductTypeByProductTypeId(Long productTypeId);

    /**
     * 批量删除商品类型
     * 
     * @param productTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelProductTypeByProductTypeIds(Long[] productTypeIds);
}

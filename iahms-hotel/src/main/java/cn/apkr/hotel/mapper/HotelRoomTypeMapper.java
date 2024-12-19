package cn.apkr.hotel.mapper;

import java.util.List;
import cn.apkr.hotel.domain.HotelRoomType;

/**
 * 房型Mapper接口
 * 
 * @author apkr
 * @date 2024-12-16
 */
public interface HotelRoomTypeMapper {

    /**
     * 查询房型
     * 
     * @param roomTypeId 房型主键
     * @return 房型
     */
    public HotelRoomType selectHotelRoomTypeByRoomTypeId(Long roomTypeId);

    /**
     * 查询房型列表
     * 
     * @param hotelRoomType 房型
     * @return 房型集合
     */
    public List<HotelRoomType> selectHotelRoomTypeList(HotelRoomType hotelRoomType);

    /**
     * 新增房型
     * 
     * @param hotelRoomType 房型
     * @return 结果
     */
    public int insertHotelRoomType(HotelRoomType hotelRoomType);

    /**
     * 修改房型
     * 
     * @param hotelRoomType 房型
     * @return 结果
     */
    public int updateHotelRoomType(HotelRoomType hotelRoomType);

    /**
     * 删除房型
     * 
     * @param roomTypeId 房型主键
     * @return 结果
     */
    public int deleteHotelRoomTypeByRoomTypeId(Long roomTypeId);

    /**
     * 批量删除房型
     * 
     * @param roomTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelRoomTypeByRoomTypeIds(Long[] roomTypeIds);
}
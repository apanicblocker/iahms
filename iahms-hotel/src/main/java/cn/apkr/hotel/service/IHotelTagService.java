package cn.apkr.hotel.service;

import cn.apkr.hotel.domain.HotelTag;

import java.util.List;

public interface IHotelTagService {
	List<HotelTag> selectTagList(HotelTag tag);

	int insertTag(HotelTag tag);

	int updateTag(HotelTag tag);

	int deleteTagById(Long tagId);

	int deleteTagByIds(Long[] tagIds);
}

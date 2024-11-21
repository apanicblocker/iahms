package cn.apkr.hotel.service.impl;

import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.mapper.HotelTagMapper;
import cn.apkr.hotel.service.IHotelTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelTagServiceImpl implements IHotelTagService {

	@Autowired
	private HotelTagMapper tagMapper;

	@Override
	public List<HotelTag> selectTagList(HotelTag tag) {
		return tagMapper.selectTagList(tag);
	}

	@Override
	public int insertTag(HotelTag tag) {
		return tagMapper.insertTag(tag);
	}

	@Override
	public int updateTag(HotelTag tag) {
		return tagMapper.updateTag(tag);
	}

	@Override
	public int deleteTagById(Long tagId) {
		return tagMapper.deleteTagById(tagId);
	}

	@Override
	public int deleteTagByIds(Long[] tagIds) {
		return tagMapper.deleteTagByIds(tagIds);
	}

}

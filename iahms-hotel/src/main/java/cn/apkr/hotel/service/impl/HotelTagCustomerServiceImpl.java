package cn.apkr.hotel.service.impl;

import cn.apkr.common.exception.ServiceException;
import cn.apkr.hotel.domain.HotelTag;
import cn.apkr.hotel.domain.HotelTagCustomer;
import cn.apkr.hotel.mapper.HotelTagCustomerMapper;
import cn.apkr.hotel.mapper.HotelTagMapper;
import cn.apkr.hotel.service.IHotelTagCustomerService;
import cn.apkr.hotel.service.IHotelTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelTagCustomerServiceImpl implements IHotelTagCustomerService {

	@Autowired
	private HotelTagCustomerMapper tagCustomerMapper;

	@Autowired
	private IHotelTagService tagService;

	/**
	 * 根据客户ID查询客户标签
	 * @param customerId 客户ID
	 * @return 标签信息列表
	 */
	@Override
	public List<HotelTag> selectHotelTagByCustomerId(Long customerId) {
		return tagCustomerMapper.selectHotelTagByCustomerId(customerId);
	}

	/**
	 * 统计指定客户关联的标签数量
	 * @param customerId 客户ID
	 * @return 结果
	 */
	@Override
	public int countTagCustomerByCustomerId(Long customerId) {
		return tagCustomerMapper.countTagCustomerByCustomerId(customerId);
	}

	/**
	 * 统计指定标签关联的客户数量
	 * @param tagId 标签ID
	 * @return 结果
	 */
	@Override
	public int countTagCustomerByTagId(Long tagId) {
		return tagCustomerMapper.countTagCustomerByTagId(tagId);
	}

	/**
	 * 批量添加客户标签关联信息
	 * @param tagCustomerList 客户标签关联信息列表
	 * @return 结果
	 */
	@Override
	public int batchTagCustomer(List<HotelTagCustomer> tagCustomerList) {
		return tagCustomerMapper.batchTagCustomer(tagCustomerList);
	}

	/**
	 * 批量更新客户标签
	 * @param customerIds 客户ID列表
	 * @param tagIds 标签ID列表
	 * @return 结果
	 */
	@Override
	public int updateTagCustomerForCustomer(Long[] customerIds, Long[] tagIds) {
		int result = 0;
		if (customerIds.length == 0) {
			throw new ServiceException("客户ID列表不能为空");
		}
		tagCustomerMapper.deleteTagCustomerByCustomerIds(customerIds);
		if (tagIds.length != 0) {
			List<HotelTagCustomer> tagCustomerList = new ArrayList<>();
			for (long customerId : customerIds) {
				for (long tagId : tagIds) {
					HotelTagCustomer tagCustomer = new HotelTagCustomer();
					tagCustomer.setCustomerId(customerId);
					tagCustomer.setTagId(tagId);
					tagCustomerList.add(tagCustomer);
				}
			}
			result = tagCustomerMapper.batchTagCustomer(tagCustomerList);
			// 更新标签统计数据
			for (long tagId : tagIds) {
				int count = tagCustomerMapper.countTagCustomerByTagId(tagId);
				tagService.updateRelCusNum(tagId, count);
			}
		} else {
			// 重新统计所有标签数量
			recalculateTagCustomerCounts();
			result = 1;
		}
		return result;
	}

	/**
	 * 删除指定客户的所有标签
	 * @param customerId 客户ID
	 * @return 结果
	 */
	@Override
	@Transactional
	public int deleteTagCustomerByCustomerId(Long customerId) {
		int result = 0;
		// 判断客户标签数量
		List<HotelTag> tagList = selectHotelTagByCustomerId(customerId);
		if (!tagList.isEmpty()) {
			// 执行删除操作
			result = tagCustomerMapper.deleteTagCustomerByCustomerId(customerId);
			for (HotelTag tag : tagList) {
				// 统计数量-1
				tagService.decrementRelCusNum(tag.getTagId());
			}
		}
		return result;
	}

	/**
	 * 批量删除指定客户的所有标签
	 * @param customerIds 客户ID列表
	 * @return 结果
	 */
	@Override
	@Transactional
	public int deleteTagCustomerByCustomerIds(Long[] customerIds) {
		int result = tagCustomerMapper.deleteTagCustomerByCustomerIds(customerIds);
		if (result > 0) {
			// 重新统计所有标签数量
			recalculateTagCustomerCounts();
		}
		return result;
	}

	/**
	 * 删除指定标签的所有客户关联信息
	 * @param tagId 标签ID
	 * @return 结果
	 */
	@Override
	public int deleteTagCustomerByTagId(Long tagId) {
		int result = tagCustomerMapper.deleteTagCustomerByTagId(tagId);
		if (result > 0) {
			tagService.updateRelCusNum(tagId, 0);
		}
		return result;
	}

	/**
	 * 批量删除指定标签的所有客户关联信息
	 * @param tagIds 标签ID列表
	 * @return 结果
	 */
	@Override
	public int deleteTagCustomerByTagIds(Long[] tagIds) {
		int result = tagCustomerMapper.deleteTagCustomerByTagIds(tagIds);
		if (result > 0) {
			for (Long tagId : tagIds) {
				tagService.updateRelCusNum(tagId, 0);
			}
		}
		return result;
	}

	/**
	 * 删除指定客户指定标签的关联信息
	 * @param tagCustomer 客户标签关联信息
	 * @return 结果
	 */
	@Override
	public int deleteTagCustomerInfo(HotelTagCustomer tagCustomer) {
		int result = tagCustomerMapper.deleteTagCustomerInfo(tagCustomer);
		if (result > 0) {
			tagService.decrementRelCusNum(tagCustomer.getTagId());
		}
		return result;
	}

	/**
	 * 删除指定客户的指定标签关联信息
	 * @param customerId 客户ID
	 * @param tagIds 标签ID列表
	 * @return 结果
	 */
	@Override
	public int deleteTagCustomerForCustomer(Long customerId, Long[] tagIds) {
		int result = tagCustomerMapper.deleteTagCustomerForCustomer(customerId, tagIds);
		if (result > 0) {
			for (long tagId : tagIds) {
				tagService.decrementRelCusNum(tagId);
			}
		}
		return result;
	}

	/**
	 * 删除指定标签的指定客户关联信息
	 * @param tagId 标签ID
	 * @param customerIds 客户ID列表
	 * @return 结果
	 */
	@Override
	public int deleteTagCustomerForTag(Long tagId, Long[] customerIds) {
		int result = tagCustomerMapper.deleteTagCustomerTag(tagId, customerIds);
		if (result > 0) {
			int count = countTagCustomerByTagId(tagId);
			tagService.updateRelCusNum(tagId, count);
		}
		return result;
	}

	/**
	 * 重新计算所有标签关联的客户数量
	 */
	@Override
	public void recalculateTagCustomerCounts() {
		// 获取所有标签
		List<HotelTag> tagList = tagService.selectHotelTagList(null);
		for (HotelTag tag : tagList) {
			// 重新设置标签关联的客户数量
			int count = tagCustomerMapper.countTagCustomerByTagId(tag.getTagId());
			tag.setRelCusNum(count);
			tagService.updateRelCusNum(tag.getTagId(), count);
		}
	}
}

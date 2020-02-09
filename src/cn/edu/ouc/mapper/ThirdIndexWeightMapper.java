package cn.edu.ouc.mapper;

import java.util.List;
import java.util.Map;

import cn.edu.ouc.pojo.dto.IndexData;

public interface ThirdIndexWeightMapper {

	/**
	 * 根据二级指标id获取所有的三级级指标id以及权重
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午1:33:11
	 * @param thirdIndexId
	 * @return
	 */
	List<IndexData> listWeightsBySecondIndexId(Integer secondIndexId);
	
	/**
	 * 删除所有的计算数据
	 * 
	 * @author 高杨
	 * @date 2018年1月28日 下午5:13:37
	 */
	void deletAll();
}

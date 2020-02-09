package cn.edu.ouc.mapper.province;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.edu.ouc.pojo.dto.IndexData;
import cn.edu.ouc.pojo.dto.IndexWeight;

public interface FourthIndexWeightMapper {

	/**
	 * 删除所有的计算数据
	 * 
	 * @author 高杨
	 * @date 2018年1月28日 下午5:13:37
	 */
	void deletAll();

	/**
	 * 根据三级指标id获取所有的四级指标id以及权重
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午1:33:11
	 * @param thirdIndexId
	 * @return
	 */
	List<IndexData> listWeightsByThirdIndexId(Integer thirdIndexId);

	/**
	 * 获取所有指标的id，名称，权重值
	 * 
	 * @return
	 */
	List<IndexWeight> listFourthIndexWeightValue();

	/**
	 * 修改四级指标的权重
	 * 
	 * @return
	 */
	Integer updateFourthIndexWeightById(@Param("id") Integer id, @Param("weightValue") Double weightValue);
}

package cn.edu.ouc.mapper.city;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.ouc.pojo.dto.IndexData;
import cn.edu.ouc.pojo.dto.IndexWeight;

public interface CityFirstIndexWeightMapper {
	/**
	 * 删除所有的计算数据
	 * 
	 * @author 高杨
	 * @date 2018年1月28日 下午5:13:37
	 */
	void deletAll();

	/**
	 * 获取所有指标的id，名称，权重值
	 * 
	 * @return
	 */
	List<IndexWeight> listFirstIndexWeightValue();

	/**
	 * 修改四级指标的权重
	 * 
	 * @return
	 */
	Integer updateFirstIndexWeightById(@Param("id") Integer id, @Param("weightValue") Double weightValue);

	/**
	 * 获取所有的权重
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午2:26:23
	 * @return
	 */
	List<IndexData> listWeight();
}

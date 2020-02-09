package cn.edu.ouc.mapper.city;

import java.util.List;

import cn.edu.ouc.pojo.dto.GrowthRate;

public interface CityGrowthRateMapper {

	/**
	 * 查询出所有的增长率
	 * 
	 * @author 高杨
	 * @date 2018年1月27日 下午10:19:00
	 * @return
	 */
	List<GrowthRate> listGrowthRates();
}

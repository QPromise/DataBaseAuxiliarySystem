package cn.edu.ouc.mapper.city;

import java.util.List;

import cn.edu.ouc.pojo.dto.IndexData;
import cn.edu.ouc.pojo.dto.ThirdIndexData;

/**
 * 一级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface CityFirstIndexDataMapper {

	/**
	 * 插入一级指标
	 */
	void insertFirstIndexData(ThirdIndexData thirdIndexData);

	/**
	 * 根据指标名称查询某指标的所有数据
	 * 
	 * @return
	 */
	List<ThirdIndexData> listFirstIndexDataByDesignation(String designation);

	/**
	 * 获取所有一级指标的平均数据
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午1:57:25
	 * @return
	 */
	List<IndexData> getAVG();

	/**
	 * 查询出所有的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午2:01:41
	 * @return
	 */
	List<String> listYears();

	/**
	 * 根据年份查询出所有的数据一级id
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午2:18:49
	 * @param year
	 * @return
	 */
	List<IndexData> listValue(String year);

	// 2018年3月 高杨
	// 添加-----------------------------------------------------------------
	/***
	 * 删除修改表数据**
	 * 
	 * @author 高杨
	 * @date 2018年2月28日 下午10:08:15
	 */

	void deletAllAlter();

	/**
	 * 获取所有一级指标的平均数据
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午1:57:25
	 * @return
	 */
	List<IndexData> getAVGAlter();

	/**
	 * 查询出所有的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午2:01:41
	 * @return
	 */
	List<String> listYearsAlter();

	/**
	 * 根据年份查询出所有的数据一级id
	 * 
	 * @author 高杨
	 * @date 2018年1月30日 下午2:18:49
	 * @param year
	 * @return
	 */
	List<IndexData> listValueAlter(String year);

	/**
	 * 根据指标名称查询某指标的所有数据 修改数据表
	 * 
	 * @return
	 */
	List<ThirdIndexData> listFirstIndexDataByDesignationAlter(String designation);
	
	/**
	 * 插入一级指标
	 */
	void insertFirstIndexDataAlter(ThirdIndexData thirdIndexData);
}

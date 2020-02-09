package cn.edu.ouc.mapper;

import java.util.List;
import java.util.Map;

import cn.edu.ouc.pojo.dto.FourthIndexData;
import cn.edu.ouc.pojo.dto.IndexData;

public interface FourthIndexDataMapper {

	/**
	 * 添加数据
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午11:25:25
	 * @param fourthIndexData
	 */
	void insertFourthIndexData(FourthIndexData fourthIndexData);

	/**
	 * 根据四级指标id获取所有年份的均值
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午11:14:41
	 * @param indexId
	 * @return
	 */
	List<IndexData> getAVGById(Integer indexId);

	/**
	 * 查询出所有的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午1:03:10
	 * @return
	 */
	List<String> listYears();

	/**
	 * 根据三级指标id和年份获取其四级指标的id和数值
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午1:11:58
	 * @param thirdIndexId
	 * @param year
	 * @return
	 */
	List<IndexData> listValue(Integer thirdIndexId, String year);

	List<FourthIndexData> listFourthIndexDataByDesignation(String designation);

	/**
	 * 根据年份和指标名称获取数据
	 * 
	 * @author 高杨
	 * @date 2018年1月27日 下午10:26:12
	 * @param year
	 * @param designation
	 * @return
	 */
	Double getValueByYearAndDesignation(String year, String designation);
}

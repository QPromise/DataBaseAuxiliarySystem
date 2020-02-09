package cn.edu.ouc.mapper;

import java.util.List;

import cn.edu.ouc.pojo.dto.FirstIndexMetadata;
import cn.edu.ouc.pojo.dto.IndexData;
import cn.edu.ouc.pojo.dto.SecondIndexMetadata;
import cn.edu.ouc.pojo.dto.ThirdIndexData;

/**
 * 一级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface SecondIndexDataMapper {

	/**
	 * 插入二级指标
	 */
	void insertSecondIndexData(ThirdIndexData thirdIndexData);

	/**
	 * 根据二级指标id获取所有年份的均值
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午11:14:41
	 * @param indexId
	 * @return
	 */
	List<IndexData> getAVGById(Integer indexId);

	/**
	 * 查询所有的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午9:56:50
	 * @return
	 */
	List<String> listYears();

	/**
	 * 根据二级指标id和年份获取其三级指标的id和数值
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午1:11:58
	 * @param thirdIndexId
	 * @param year
	 * @return
	 */
	List<IndexData> listValue(Integer secondIndexId, String year);
}

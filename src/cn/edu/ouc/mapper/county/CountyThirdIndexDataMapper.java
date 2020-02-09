package cn.edu.ouc.mapper.county;

import java.util.List;

import cn.edu.ouc.pojo.dto.IndexData;
import cn.edu.ouc.pojo.dto.ThirdIndexData;
import cn.edu.ouc.pojo.dto.ThirdIndexMetadata;

/**
 * 三级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface CountyThirdIndexDataMapper {

	/**
	 * 插入三级指标
	 */
	void insertThirdIndexData(ThirdIndexData thirdIndexData);

	/**
	 * 根据三级级指标id获取所有年份的均值
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

	/**
	 * 根据指标名称查询某指标的所有数据
	 * 
	 * @return
	 */
	List<ThirdIndexData> listThirdIndexDataByDesignation(String designation);

	// ------------------------------------------------------------
	/**
	 * 插入三级指标
	 */
	void insertThirdIndexDataAlter(ThirdIndexData thirdIndexData);

	/**
	 * 删除修改表数据
	 * 
	 * @author 高杨
	 * @date 2018年2月28日 下午10:08:15
	 */
	void deletAllAlter();

	/**
	 * 根据三级级指标id获取所有年份的均值
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午11:14:41
	 * @param indexId
	 * @return
	 */
	List<IndexData> getAVGByIdAlter(Integer indexId);

	/**
	 * 查询所有的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午9:56:50
	 * @return
	 */
	List<String> listYearsAlter();

	/**
	 * 根据二级指标id和年份获取其三级指标的id和数值
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午1:11:58
	 * @param thirdIndexId
	 * @param year
	 * @return
	 */
	List<IndexData> listValueAlter(Integer secondIndexId, String year);

	/**
	 * 根据指标名称查询某指标的所有数据 修改数据表
	 * 
	 * @return
	 */
	List<ThirdIndexData> listThirdIndexDataByDesignationAlter(String designation);
}

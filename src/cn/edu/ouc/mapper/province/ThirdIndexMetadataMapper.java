package cn.edu.ouc.mapper.province;

import java.util.List;

import cn.edu.ouc.pojo.dto.SecondIndexMetadata;
import cn.edu.ouc.pojo.dto.ThirdIndexMetadata;

/**
 * 一级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface ThirdIndexMetadataMapper {

	/**
	 * 插入三级指标元数据
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:02:50
	 * @param firstIndexMetadata
	 */
	void insertThirdIndexMetadata(ThirdIndexMetadata thirdIndexMetadata);

	/**
	 * 根据指标名称获取指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:03:58
	 * @param designation
	 * @return
	 */
	Integer getThirdIndexMetadataIdByDesignation(String designation);

	/**
	 * 获取所有的三级指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午11:01:53
	 * @return
	 */
	List<Integer> listThirdIndexIds();

	/**
	 * 根据二级指标名称列出下面的三级指标
	 * 
	 * @return
	 */
	List<String> listThirdIndexMetadata(String designation);
}

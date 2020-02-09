package cn.edu.ouc.mapper.province;

import java.util.List;

import cn.edu.ouc.pojo.dto.FirstIndexMetadata;
import cn.edu.ouc.pojo.dto.SecondIndexMetadata;

/**
 * 一级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface SecondIndexMetadataMapper {

	/**
	 * 插入二级指标元数据
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:02:50
	 * @param firstIndexMetadata
	 */
	void insertSecondIndexMetadata(SecondIndexMetadata secondIndexMetadata);

	/**
	 * 根据指标名称获取指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:03:58
	 * @param designation
	 * @return
	 */
	Integer getSecondIndexMetadataIdByDesignation(String designation);

	/**
	 * 列出所有的二级指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午9:48:03
	 * @return
	 */
	List<Integer> listSecondIndexIds();

	/**
	 * 根据一级指标名称列出下面的二级指标
	 * 
	 * @return
	 */
	List<String> listSecondIndexDesignation(String designation);
}

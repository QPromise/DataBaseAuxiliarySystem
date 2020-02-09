package cn.edu.ouc.mapper.county;

import java.util.List;

import cn.edu.ouc.pojo.dto.FourthIndexMetadata;
import cn.edu.ouc.pojo.dto.SecondIndexMetadata;
import cn.edu.ouc.pojo.dto.ThirdIndexMetadata;

/**
 * 一级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface CountyFourthIndexMetadataMapper {

	/**
	 * 插入四级指标元数据
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:02:50
	 * @param firstIndexMetadata
	 */
	void insertFourthIndexMetadata(FourthIndexMetadata fourthIndexMetadata);

	/**
	 * 根据指标名称获取指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:03:58
	 * @param designation
	 * @return
	 */
	Integer getFourthIndexMetadataIdByDesignation(String designation);

	/**
	 * 根据三级指标名称列出下面的四级指标
	 * 
	 * @return
	 */
	List<String> listFourthIndexMetadata(String designation);

	/**
	 * 根据区域名称查询已经存在数据的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月25日 下午2:47:24
	 * @param district
	 * @return
	 */
	List<String> listYearByDistrict(String district);
}

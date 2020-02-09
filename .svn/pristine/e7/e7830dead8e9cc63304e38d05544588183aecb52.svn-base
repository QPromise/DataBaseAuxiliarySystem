package cn.edu.ouc.mapper.county;

import java.util.List;

import cn.edu.ouc.pojo.dto.FirstIndexMetadata;

/**
 * 一级指标dao
 * 
 * @author 高杨
 * @date 2018年1月21日 上午10:01:51
 */
public interface CountyFirstIndexMetadataMapper {

	/**
	 * 插入一级指标元数据
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:02:50
	 * @param firstIndexMetadata
	 */
	void insertFirstIndexMetadata(FirstIndexMetadata firstIndexMetadata);

	/**
	 * 根据指标名称获取指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月21日 上午10:03:58
	 * @param designation
	 * @return
	 */
	Integer getFirstIndexMetadataIdByDesignation(String designation);

	/**
	 * 列出所有的一级指标id
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 下午9:48:03
	 * @return
	 */
	List<Integer> listFIndexIds();

	/**
	 * 列出所有一级指标名称
	 * 
	 * @return
	 */
	List<String> listFirstIndexDesignation();

	/**
	 * 根据区域名称查询已经存在数据的年份
	 * 
	 * @author 高杨
	 * @date 2018年1月25日 下午2:47:24
	 * @param district
	 * @return
	 */
	List<String> listYearByDistrict(String district);

	/**
	 * 根据区域名查询存在的数目
	 * 
	 * @author 高杨
	 * @date 2018年1月26日 下午5:45:50
	 * @param district
	 * @return
	 */
	int listCountByDistrict(String district);
}

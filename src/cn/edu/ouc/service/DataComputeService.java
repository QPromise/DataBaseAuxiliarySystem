package cn.edu.ouc.service;

import java.io.File;

public interface DataComputeService {

	/**
	 * 三级指标数据计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:30:09
	 * @throws Exception
	 */
	void thirdIndexDataCompute() throws Exception;

	/**
	 * 二级指标数据计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:30:26
	 * @throws Exception
	 */
	void secondIndexDataCompute() throws Exception;

	/**
	 * 一级指标数据计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:30:40
	 * @throws Exception
	 */
	void firstIndexDataCompute() throws Exception;

	/**
	 * 综合数据指标计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:31:00
	 * @throws Exception
	 */
	void synthesizeDataCompute() throws Exception;

	void deletAll();

	/**
	 * 清空一级二级三级修改表数据
	 * 
	 * @author 高杨
	 * @date 2018年2月28日 下午10:06:49
	 */
	void deletAllAlter();

	/**
	 * 修改三级指标数据计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:30:09
	 * @throws Exception
	 */
	void thirdIndexDataComputeAlter() throws Exception;

	/**
	 * 修改二级指标数据计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:30:26
	 * @throws Exception
	 */
	void secondIndexDataComputeAlter() throws Exception;

	/**
	 * 修改一级指标数据计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:30:40
	 * @throws Exception
	 */
	void firstIndexDataComputeAlter() throws Exception;

	/**
	 * 修改综合数据指标计算
	 * 
	 * @author 高杨
	 * @date 2018年1月24日 上午10:31:00
	 * @throws Exception
	 */
	void synthesizeDataComputeAlter() throws Exception;
}

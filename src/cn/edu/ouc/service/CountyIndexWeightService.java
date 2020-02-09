package cn.edu.ouc.service;

import java.util.List;

import cn.edu.ouc.pojo.dto.IndexWeight;

public interface CountyIndexWeightService {

	//查询所有权重值
	List<IndexWeight> listWeightValueByIndex(Integer id);
	
	//修改权重值
	String updateIndexWeightById(Integer index, Integer id, Double weightValue);
}

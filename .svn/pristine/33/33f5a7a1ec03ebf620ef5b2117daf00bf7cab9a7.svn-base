package cn.edu.ouc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ouc.mapper.county.CountyFirstIndexWeightMapper;
import cn.edu.ouc.mapper.county.CountyFourthIndexWeightMapper;
import cn.edu.ouc.mapper.county.CountySecondIndexWeightMapper;
import cn.edu.ouc.mapper.county.CountyThirdIndexWeightMapper;
import cn.edu.ouc.pojo.dto.IndexWeight;
import cn.edu.ouc.service.CountyIndexWeightService;

@Service
public class CountyIndexWeightServiceImpl implements CountyIndexWeightService {

	@Autowired
	private CountyFourthIndexWeightMapper fourthIndexWeightMapper;

	@Autowired
	private CountyThirdIndexWeightMapper thirdIndexWeightMapper;

	@Autowired
	private CountySecondIndexWeightMapper secondIndexWeightMapper;

	@Autowired
	private CountyFirstIndexWeightMapper firstIndexWeightMapper;

	@Override
	public List<IndexWeight> listWeightValueByIndex(Integer id) {
		System.out.println("查询的指标类型：" + id);
		List<IndexWeight> indexWeights = null;
		if (id == 4) {
			indexWeights = fourthIndexWeightMapper.listFourthIndexWeightValue();
		} else if (id == 3) {
			indexWeights = thirdIndexWeightMapper.listThirdIndexWeightValue();
		} else if (id == 2) {
			indexWeights = secondIndexWeightMapper.listSecondIndexWeightValue();
		} else if (id == 1) {
			indexWeights = firstIndexWeightMapper.listFirstIndexWeightValue();
		}
		System.out.println("查询的数据为：" + indexWeights.toString());
		return indexWeights;
	}

	@Override
	public String updateIndexWeightById(Integer index, Integer id, Double weightValue) {
		Integer resultRow = 0;
		if (index == 4) {
			resultRow = fourthIndexWeightMapper.updateFourthIndexWeightById(id, weightValue);
		} else if (index == 3) {
			resultRow = thirdIndexWeightMapper.updateThirdIndexWeightById(id, weightValue);
		} else if (index == 2) {
			resultRow = secondIndexWeightMapper.updateSecondIndexWeightById(id, weightValue);
		} else if (index == 1) {
			resultRow = firstIndexWeightMapper.updateFirstIndexWeightById(id, weightValue);
		}
		System.out.println("受影响的行数："+resultRow);
		if(resultRow == 1){
			return "修改成功";
		}
		return "修改失败";
	}

}

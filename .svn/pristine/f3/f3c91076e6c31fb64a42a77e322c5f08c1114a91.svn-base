package cn.edu.ouc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ouc.mapper.city.CityFirstIndexDataMapper;
import cn.edu.ouc.mapper.city.CityFirstIndexMetadataMapper;
import cn.edu.ouc.mapper.city.CityFirstIndexWeightMapper;
import cn.edu.ouc.mapper.city.CityFourthIndexDataMapper;
import cn.edu.ouc.mapper.city.CityFourthIndexWeightMapper;
import cn.edu.ouc.mapper.city.CitySecondIndexDataMapper;
import cn.edu.ouc.mapper.city.CitySecondIndexMetadataMapper;
import cn.edu.ouc.mapper.city.CitySecondIndexWeightMapper;
import cn.edu.ouc.mapper.city.CitySynthesizeDataMapper;
import cn.edu.ouc.mapper.city.CityThirdIndexDataMapper;
import cn.edu.ouc.mapper.city.CityThirdIndexMetadataMapper;
import cn.edu.ouc.mapper.city.CityThirdIndexWeightMapper;
import cn.edu.ouc.pojo.dto.IndexData;
import cn.edu.ouc.pojo.dto.SecondIndexMetadata;
import cn.edu.ouc.pojo.dto.SynthesizeData;
import cn.edu.ouc.pojo.dto.ThirdIndexData;
import cn.edu.ouc.service.CityDataComputeService;
import cn.edu.ouc.service.DataComputeService;

@Service
public class CityDataComputeServiceImpl implements CityDataComputeService {

	@Autowired
	private CityThirdIndexMetadataMapper thirdIndexMetadataMapper;
	@Autowired
	private CityFourthIndexDataMapper fourthIndexDataMapper;
	@Autowired
	private CityFourthIndexWeightMapper fourthIndexWeightMapper;
	@Autowired
	private CityThirdIndexDataMapper thirdIndexDataMapper;
	@Autowired
	private CitySecondIndexMetadataMapper secondIndexMetadataMapper;
	@Autowired
	private CityThirdIndexWeightMapper thirdIndexWeightMapper;
	@Autowired
	private CitySecondIndexDataMapper secondIndexDataMapper;
	@Autowired
	private CityFirstIndexMetadataMapper firstIndexMetadataMapper;
	@Autowired
	private CitySecondIndexWeightMapper secondIndexWeightMapper;
	@Autowired
	private CityFirstIndexDataMapper firstIndexDataMapper;
	@Autowired
	private CityFirstIndexWeightMapper firstIndexWeightMapper;
	@Autowired
	private CitySynthesizeDataMapper synthesizeDataMapper;

	@Override
	public void thirdIndexDataCompute() throws Exception {
		// 1.先查询出所有的三级指标id
		List<Integer> thirdIndexIds = thirdIndexMetadataMapper.listThirdIndexIds();

		// 2.遍历所有的三级指标id
		for (Integer id : thirdIndexIds) {

			// 3.计算出该三级指标id下的所有四级指标id的均值放map
			List<IndexData> avgDatas = fourthIndexDataMapper.getAVGById(id);

			// 4.查询出所有的年份
			List<String> years = fourthIndexDataMapper.listYears();

			// 5.遍历所有的年份
			for (String year : years) {
				// 6.根据三级指标id以及年份查询出其所有的四级指标id以数据值
				List<IndexData> yearValues = fourthIndexDataMapper.listValue(id, year);

				// 7.数据预处理：数据除以均值
				if (yearValues != null && yearValues.size() != 0) {
					Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
					for (IndexData indexData : yearValues) {
						// 均值
						double avgDataValue = 0;
						double yearDataValue = indexData.getValue();

						// 根据index找到value
						for (IndexData indexData2 : avgDatas) {
							if (indexData.getIndex().equals(indexData2.getIndex())) {
								avgDataValue = indexData2.getValue();
							}
						}
						if (avgDataValue != 0) {
							Double value = yearDataValue / avgDataValue;
							lastYearValues.put(indexData.getIndex(), value);
						}
					}

					// 8.根据三级指标id获取其所有的四级指标id的权重
					List<IndexData> listWeights = fourthIndexWeightMapper.listWeightsByThirdIndexId(id);
					Map<Integer, Double> weights = new HashMap<Integer, Double>();
					for (IndexData data : listWeights) {
						weights.put(data.getIndex(), data.getValue());
					}
					System.out.println("权重：" + weights);
					// 9.对三级指标数据进行计算
					// 先计算数据乘以权重之和
					double sum = 0;
					double sumWeight = 0;
					for (Integer indexId : lastYearValues.keySet()) {
						System.out.println("indexid:" + indexId);
						sum += lastYearValues.get(indexId) * weights.get(indexId);
					}

					// 再计算和除以权重
					for (Integer indexId : lastYearValues.keySet()) {
						sumWeight += weights.get(indexId);
					}
					if (sumWeight != 0) {
						System.out.println("年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";三级指标id:" + id
								+ ";最终运算结果：" + sum / sumWeight);
						// 将计算结果插入到数据库
						thirdIndexDataMapper.insertThirdIndexData(new ThirdIndexData(id, year, sum / sumWeight));
					}
				}

			}
		}

	}

	@Override
	public void secondIndexDataCompute() throws Exception {
		// 1.先查询出所有的二级指标id
		List<Integer> secondIndexIds = secondIndexMetadataMapper.listSecondIndexIds();

		// 2.遍历所有的二级指标id
		for (Integer id : secondIndexIds) {

			// 3.计算出该二级指标id下的所有三级级指标id的均值放map
			List<IndexData> avgDatas = thirdIndexDataMapper.getAVGById(id);

			// 4.查询出所有的年份
			List<String> years = thirdIndexDataMapper.listYears();

			// 5.遍历所有的年份
			for (String year : years) {
				// 6.根据二级指标id以及年份查询出其所有的三级指标id以数据值
				List<IndexData> yearValues = thirdIndexDataMapper.listValue(id, year);

				// 7.数据预处理：数据除以均值
				Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
				for (IndexData indexData : yearValues) {
					// 均值
					double avgDataValue = 0;
					double yearDataValue = indexData.getValue();

					// 根据index找到value
					for (IndexData indexData2 : avgDatas) {
						if (indexData.getIndex().equals(indexData2.getIndex())) {
							avgDataValue = indexData2.getValue();
						}
					}
					if (avgDataValue != 0) {
						Double value = yearDataValue / avgDataValue;
						lastYearValues.put(indexData.getIndex(), value);
					}
				}

				// 8.根据二级指标id获取其所有的三级指标id的权重
				List<IndexData> listWeights = thirdIndexWeightMapper.listWeightsBySecondIndexId(id);
				Map<Integer, Double> weights = new HashMap<Integer, Double>();
				for (IndexData data : listWeights) {
					weights.put(data.getIndex(), data.getValue());
				}

				// 9.对三级指标数据进行计算
				// 先计算数据乘以权重之和
				double sum = 0;
				double sumWeight = 0;
				for (Integer indexId : lastYearValues.keySet()) {
					sum += lastYearValues.get(indexId) * weights.get(indexId);
				}

				// 再计算和除以权重
				for (Integer indexId : lastYearValues.keySet()) {
					sumWeight += weights.get(indexId);
				}
				if (sumWeight != 0) {
					System.out.println("年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";二级指标id:" + id
							+ ";最终运算结果：" + sum / sumWeight);
					// 将计算结果插入到数据库
					secondIndexDataMapper.insertSecondIndexData(new ThirdIndexData(id, year, sum / sumWeight));
				}

			}
		}

	}

	@Override
	public void firstIndexDataCompute() throws Exception {
		// 1.先查询出所有的二级指标id
		List<Integer> firstIndexIds = firstIndexMetadataMapper.listFIndexIds();

		// 2.遍历所有的二级指标id
		for (Integer id : firstIndexIds) {

			// 3.计算出该一级指标id下的所有二级指标id的均值放map
			List<IndexData> avgDatas = secondIndexDataMapper.getAVGById(id);

			// 4.查询出所有的年份
			List<String> years = secondIndexDataMapper.listYears();

			// 5.遍历所有的年份
			for (String year : years) {
				// 6.根据一级指标id以及年份查询出其所有的二级指标id以数据值
				List<IndexData> yearValues = secondIndexDataMapper.listValue(id, year);

				// 7.数据预处理：数据除以均值
				Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
				for (IndexData indexData : yearValues) {
					// 均值
					double avgDataValue = 0;
					double yearDataValue = indexData.getValue();

					// 根据index找到value
					for (IndexData indexData2 : avgDatas) {
						if (indexData.getIndex().equals(indexData2.getIndex())) {
							avgDataValue = indexData2.getValue();
						}
					}
					if (avgDataValue != 0) {
						Double value = yearDataValue / avgDataValue;
						lastYearValues.put(indexData.getIndex(), value);
					}
				}

				// 8.根据一级指标id获取其所有的二级指标id的权重
				List<IndexData> listWeights = secondIndexWeightMapper.listWeightsByFirstIndexId(id);
				Map<Integer, Double> weights = new HashMap<Integer, Double>();
				for (IndexData data : listWeights) {
					weights.put(data.getIndex(), data.getValue());
				}
				System.out.println("权重：" + weights);
				// 9.对三级指标数据进行计算
				// 先计算数据乘以权重之和
				double sum = 0;
				double sumWeight = 0;
				for (Integer indexId : lastYearValues.keySet()) {
					System.out.println("indexid:" + indexId);
					sum += lastYearValues.get(indexId) * weights.get(indexId);
				}

				// 再计算和除以权重
				for (Integer indexId : lastYearValues.keySet()) {
					sumWeight += weights.get(indexId);
				}
				if (sumWeight != 0) {
					System.out.println("年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";一级指标id:" + id
							+ ";最终运算结果：" + sum / sumWeight);
					// 将计算结果插入到数据库
					firstIndexDataMapper.insertFirstIndexData(new ThirdIndexData(id, year, sum / sumWeight));
				}

			}
		}

	}

	@Override
	public void synthesizeDataCompute() throws Exception {
		// 3.计算出该一级指标所有的均值
		List<IndexData> avgDatas = firstIndexDataMapper.getAVG();

		// 4.查询出所有的年份
		List<String> years = firstIndexDataMapper.listYears();

		// 5.遍历所有的年份
		for (String year : years) {
			// 6.根据一级指标id以及年份查询数据值
			List<IndexData> yearValues = firstIndexDataMapper.listValue(year);

			// 7.数据预处理：数据除以均值
			Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
			for (IndexData indexData : yearValues) {
				// 均值
				double avgDataValue = 0;
				double yearDataValue = indexData.getValue();

				// 根据index找到value
				for (IndexData indexData2 : avgDatas) {
					if (indexData.getIndex().equals(indexData2.getIndex())) {
						avgDataValue = indexData2.getValue();
					}
				}
				if (avgDataValue != 0) {
					Double value = yearDataValue / avgDataValue;
					lastYearValues.put(indexData.getIndex(), value);
				}
			}

			// 8.获取所有一级指标的权重
			List<IndexData> listWeights = firstIndexWeightMapper.listWeight();
			Map<Integer, Double> weights = new HashMap<Integer, Double>();
			for (IndexData data : listWeights) {
				weights.put(data.getIndex(), data.getValue());
			}
			System.out.println("权重：" + weights);
			// 9.对一级指标数据进行计算
			// 先计算数据乘以权重之和
			double sum = 0;
			double sumWeight = 0;
			for (Integer indexId : lastYearValues.keySet()) {
				System.out.println("indexid:" + indexId);
				sum += lastYearValues.get(indexId) * weights.get(indexId);
			}

			// 再计算和除以权重
			for (Integer indexId : lastYearValues.keySet()) {
				sumWeight += weights.get(indexId);
			}
			if (sumWeight != 0) {
				System.out.println(
						"年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";最终运算结果：" + sum / sumWeight);
				// 将计算结果插入到数据库
				synthesizeDataMapper.insertSynthesizeData(new SynthesizeData(year, sum / sumWeight));
			}
		}

	}

	@Override
	public void deletAll() {
		firstIndexWeightMapper.deletAll();
		secondIndexWeightMapper.deletAll();
		thirdIndexWeightMapper.deletAll();
	}

	// 2018年2月添加
	// 高杨---------------------------------------------------------------------
	@Override
	public void deletAllAlter() {
		firstIndexDataMapper.deletAllAlter();
		secondIndexDataMapper.deletAllAlter();
		thirdIndexDataMapper.deletAllAlter();
		synthesizeDataMapper.deletAllAlter();
	}

	@Override
	public void thirdIndexDataComputeAlter() throws Exception {
		// 1.先查询出所有的三级指标id
		List<Integer> thirdIndexIds = thirdIndexMetadataMapper.listThirdIndexIds();

		// 2.遍历所有的三级指标id
		for (Integer id : thirdIndexIds) {

			// 3.计算出该三级指标id下的所有四级指标id的均值放map
			List<IndexData> avgDatas = fourthIndexDataMapper.getAVGByIdAlter(id);

			// 4.查询出所有的年份
			List<String> years = fourthIndexDataMapper.listYearsAlter();

			// 5.遍历所有的年份
			for (String year : years) {
				// 6.根据三级指标id以及年份查询出其所有的四级指标id以数据值
				List<IndexData> yearValues = fourthIndexDataMapper.listValueAlter(id, year);

				// 7.数据预处理：数据除以均值
				if (yearValues != null && yearValues.size() != 0) {
					Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
					for (IndexData indexData : yearValues) {
						// 均值
						double avgDataValue = 0;
						double yearDataValue = indexData.getValue();

						// 根据index找到value
						for (IndexData indexData2 : avgDatas) {
							if (indexData.getIndex().equals(indexData2.getIndex())) {
								avgDataValue = indexData2.getValue();
							}
						}
						if (avgDataValue != 0) {
							Double value = yearDataValue / avgDataValue;
							lastYearValues.put(indexData.getIndex(), value);
						}
					}

					// 8.根据三级指标id获取其所有的四级指标id的权重
					List<IndexData> listWeights = fourthIndexWeightMapper.listWeightsByThirdIndexId(id);
					Map<Integer, Double> weights = new HashMap<Integer, Double>();
					for (IndexData data : listWeights) {
						weights.put(data.getIndex(), data.getValue());
					}
					System.out.println("权重：" + weights);
					// 9.对三级指标数据进行计算
					// 先计算数据乘以权重之和
					double sum = 0;
					double sumWeight = 0;
					for (Integer indexId : lastYearValues.keySet()) {
						System.out.println("indexid:" + indexId);
						sum += lastYearValues.get(indexId) * weights.get(indexId);
					}

					// 再计算和除以权重
					for (Integer indexId : lastYearValues.keySet()) {
						sumWeight += weights.get(indexId);
					}
					if (sumWeight != 0) {
						System.out.println("年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";三级指标id:" + id
								+ ";最终运算结果：" + sum / sumWeight);
						// 将计算结果插入到数据库
						thirdIndexDataMapper.insertThirdIndexDataAlter(new ThirdIndexData(id, year, sum / sumWeight));
					}
				}

			}
		}
	}

	@Override
	public void secondIndexDataComputeAlter() throws Exception {
		// 1.先查询出所有的二级指标id
		List<Integer> secondIndexIds = secondIndexMetadataMapper.listSecondIndexIds();

		// 2.遍历所有的二级指标id
		for (Integer id : secondIndexIds) {

			// 3.计算出该二级指标id下的所有三级级指标id的均值放map
			List<IndexData> avgDatas = thirdIndexDataMapper.getAVGByIdAlter(id);

			// 4.查询出所有的年份
			List<String> years = thirdIndexDataMapper.listYearsAlter();

			// 5.遍历所有的年份
			for (String year : years) {
				// 6.根据二级指标id以及年份查询出其所有的三级指标id以数据值
				List<IndexData> yearValues = thirdIndexDataMapper.listValueAlter(id, year);

				// 7.数据预处理：数据除以均值
				Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
				for (IndexData indexData : yearValues) {
					// 均值
					double avgDataValue = 0;
					double yearDataValue = indexData.getValue();

					// 根据index找到value
					for (IndexData indexData2 : avgDatas) {
						if (indexData.getIndex().equals(indexData2.getIndex())) {
							avgDataValue = indexData2.getValue();
						}
					}
					if (avgDataValue != 0) {
						Double value = yearDataValue / avgDataValue;
						lastYearValues.put(indexData.getIndex(), value);
					}
				}

				// 8.根据二级指标id获取其所有的三级指标id的权重
				List<IndexData> listWeights = thirdIndexWeightMapper.listWeightsBySecondIndexId(id);
				Map<Integer, Double> weights = new HashMap<Integer, Double>();
				for (IndexData data : listWeights) {
					weights.put(data.getIndex(), data.getValue());
				}

				// 9.对三级指标数据进行计算
				// 先计算数据乘以权重之和
				double sum = 0;
				double sumWeight = 0;
				for (Integer indexId : lastYearValues.keySet()) {
					sum += lastYearValues.get(indexId) * weights.get(indexId);
				}

				// 再计算和除以权重
				for (Integer indexId : lastYearValues.keySet()) {
					sumWeight += weights.get(indexId);
				}
				if (sumWeight != 0) {
					System.out.println("年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";二级指标id:" + id
							+ ";最终运算结果：" + sum / sumWeight);
					// 将计算结果插入到数据库
					secondIndexDataMapper.insertSecondIndexDataAlter(new ThirdIndexData(id, year, sum / sumWeight));
				}

			}
		}
	}

	@Override
	public void firstIndexDataComputeAlter() throws Exception {
		// 1.先查询出所有的一级指标id
		List<Integer> firstIndexIds = firstIndexMetadataMapper.listFIndexIds();

		// 2.遍历所有的二级指标id
		for (Integer id : firstIndexIds) {

			// 3.计算出该一级指标id下的所有二级指标id的均值放map
			List<IndexData> avgDatas = secondIndexDataMapper.getAVGByIdAlter(id);

			// 4.查询出所有的年份
			List<String> years = secondIndexDataMapper.listYearsAlter();

			// 5.遍历所有的年份
			for (String year : years) {
				// 6.根据一级指标id以及年份查询出其所有的二级指标id以数据值
				List<IndexData> yearValues = secondIndexDataMapper.listValueAlter(id, year);

				// 7.数据预处理：数据除以均值
				Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
				for (IndexData indexData : yearValues) {
					// 均值
					double avgDataValue = 0;
					double yearDataValue = indexData.getValue();

					// 根据index找到value
					for (IndexData indexData2 : avgDatas) {
						if (indexData.getIndex().equals(indexData2.getIndex())) {
							avgDataValue = indexData2.getValue();
						}
					}
					if (avgDataValue != 0) {
						Double value = yearDataValue / avgDataValue;
						lastYearValues.put(indexData.getIndex(), value);
					}
				}

				// 8.根据一级指标id获取其所有的二级指标id的权重
				List<IndexData> listWeights = secondIndexWeightMapper.listWeightsByFirstIndexId(id);
				Map<Integer, Double> weights = new HashMap<Integer, Double>();
				for (IndexData data : listWeights) {
					weights.put(data.getIndex(), data.getValue());
				}
				System.out.println("权重：" + weights);
				// 9.对三级指标数据进行计算
				// 先计算数据乘以权重之和
				double sum = 0;
				double sumWeight = 0;
				for (Integer indexId : lastYearValues.keySet()) {
					System.out.println("indexid:" + indexId);
					sum += lastYearValues.get(indexId) * weights.get(indexId);
				}

				// 再计算和除以权重
				for (Integer indexId : lastYearValues.keySet()) {
					sumWeight += weights.get(indexId);
				}
				if (sumWeight != 0) {
					System.out.println("年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";一级指标id:" + id
							+ ";最终运算结果：" + sum / sumWeight);
					// 将计算结果插入到数据库
					firstIndexDataMapper.insertFirstIndexDataAlter(new ThirdIndexData(id, year, sum / sumWeight));
				}

			}
		}

	}

	@Override
	public void synthesizeDataComputeAlter() throws Exception {
		// 3.计算出该一级指标所有的均值
		List<IndexData> avgDatas = firstIndexDataMapper.getAVGAlter();

		// 4.查询出所有的年份
		List<String> years = firstIndexDataMapper.listYearsAlter();

		// 5.遍历所有的年份
		for (String year : years) {
			// 6.根据一级指标id以及年份查询数据值
			List<IndexData> yearValues = firstIndexDataMapper.listValueAlter(year);

			// 7.数据预处理：数据除以均值
			Map<Integer, Double> lastYearValues = new HashMap<Integer, Double>();
			for (IndexData indexData : yearValues) {
				// 均值
				double avgDataValue = 0;
				double yearDataValue = indexData.getValue();

				// 根据index找到value
				for (IndexData indexData2 : avgDatas) {
					if (indexData.getIndex().equals(indexData2.getIndex())) {
						avgDataValue = indexData2.getValue();
					}
				}
				if (avgDataValue != 0) {
					Double value = yearDataValue / avgDataValue;
					lastYearValues.put(indexData.getIndex(), value);
				}
			}

			// 8.获取所有一级指标的权重
			List<IndexData> listWeights = firstIndexWeightMapper.listWeight();
			Map<Integer, Double> weights = new HashMap<Integer, Double>();
			for (IndexData data : listWeights) {
				weights.put(data.getIndex(), data.getValue());
			}
			System.out.println("权重：" + weights);
			// 9.对一级指标数据进行计算
			// 先计算数据乘以权重之和
			double sum = 0;
			double sumWeight = 0;
			for (Integer indexId : lastYearValues.keySet()) {
				System.out.println("indexid:" + indexId);
				sum += lastYearValues.get(indexId) * weights.get(indexId);
			}

			// 再计算和除以权重
			for (Integer indexId : lastYearValues.keySet()) {
				sumWeight += weights.get(indexId);
			}
			if (sumWeight != 0) {
				System.out.println(
						"年份：" + year + ";数据乘以权重之和：" + sum + ";权重和：" + sumWeight + ";最终运算结果：" + sum / sumWeight);
				// 将计算结果插入到数据库
				synthesizeDataMapper.insertSynthesizeDataAlter(new SynthesizeData(year, sum / sumWeight));
			}
		}

	}

}

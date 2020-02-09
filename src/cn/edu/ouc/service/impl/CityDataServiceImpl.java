package cn.edu.ouc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.edu.ouc.mapper.city.CityFirstIndexDataMapper;
import cn.edu.ouc.mapper.city.CityFirstIndexMetadataMapper;
import cn.edu.ouc.mapper.city.CityFourthIndexDataMapper;
import cn.edu.ouc.mapper.city.CityFourthIndexMetadataMapper;
import cn.edu.ouc.mapper.city.CityGrowthRateMapper;
import cn.edu.ouc.mapper.city.CitySecondIndexDataMapper;
import cn.edu.ouc.mapper.city.CitySecondIndexMetadataMapper;
import cn.edu.ouc.mapper.city.CitySynthesizeDataMapper;
import cn.edu.ouc.mapper.city.CityThirdIndexDataMapper;
import cn.edu.ouc.mapper.city.CityThirdIndexMetadataMapper;
import cn.edu.ouc.pojo.dto.FirstIndexMetadata;
import cn.edu.ouc.pojo.dto.FourthIndexData;
import cn.edu.ouc.pojo.dto.FourthIndexMetadata;
import cn.edu.ouc.pojo.dto.GrowthRate;
import cn.edu.ouc.pojo.dto.SecondIndexMetadata;
import cn.edu.ouc.pojo.dto.SynthesizeData;
import cn.edu.ouc.pojo.dto.ThirdIndexData;
import cn.edu.ouc.pojo.dto.ThirdIndexMetadata;
import cn.edu.ouc.pojo.vo.CheckoutVO;
import cn.edu.ouc.pojo.vo.FoldLineDiagram;
import cn.edu.ouc.pojo.vo.ReviseDataVO;
import cn.edu.ouc.pojo.vo.Select2;
import cn.edu.ouc.service.CityDataService;
import cn.edu.ouc.util.ArithUtil;

@Service
public class CityDataServiceImpl implements CityDataService {

	@Autowired
	private CityFirstIndexMetadataMapper firstIndexMetadataMapper;

	@Autowired
	private CityFirstIndexDataMapper firstIndexDataMapper;

	// 存储二级指标
	@Autowired
	private CitySecondIndexMetadataMapper secondIndexMetadataMapper;

	@Autowired
	private CitySecondIndexDataMapper secondIndexDataMapper;

	// 三级指标
	@Autowired
	private CityThirdIndexMetadataMapper thirdIndexMetadataMapper;

	@Autowired
	private CityThirdIndexDataMapper thirdIndexDataMapper;

	@Autowired
	private CityFourthIndexMetadataMapper fourthIndexMetadataMapper;

	@Autowired
	private CityFourthIndexDataMapper fourthIndexDataMapper;
	@Autowired
	private CityGrowthRateMapper growthRateMapper;
	@Autowired
	private CitySynthesizeDataMapper synthesizeDataMapper;

	@Override
	public List<CheckoutVO> importData(File file) throws Exception {
		List<CheckoutVO> checkoutVOs = new ArrayList<CheckoutVO>();
		// 1.创建要读入的文件
		InputStream inputStream = new FileInputStream(file);
		// 2.得到Excel工作簿对象
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		// 获取文件名称
		String fileName = file.getName();
		String[] strings = fileName.split("\\.");
		// System.out.println("文件名称：" + strings[0]);

		// 3.得到Excel工作表对象
		XSSFSheet sheet = wb.getSheetAt(0);
		// 4.得到Excel工作表的行数、列数
		int rowNumber = sheet.getLastRowNum();
		int columNumber = sheet.getRow(0).getPhysicalNumberOfCells();

		// 5.创建临时变量
		// 名称
		String designation = "";
		// 重要程度
		String importance = "";
		// 临时一级指标名称
		String firstDesignation = "";
		// 临时二级指标名称
		String secondDesignation = "";
		// 临时三级指标名称
		String thirdDesignation = "";

		// 6.遍历所有的行列
		for (int i = 1; i < rowNumber + 1; i++) {
			// 处理第0列
			String currentDesignation = sheet.getRow(i).getCell(0).getStringCellValue();
			if (currentDesignation != "") {
				// 获取一级指标名称
				designation = currentDesignation.trim();
				// 设置临时变量供二级指标使用
				firstDesignation = designation;
				// 存储第一级指标
				FirstIndexMetadata firstIndexMetadata = new FirstIndexMetadata(designation, strings[0]);

				firstIndexMetadataMapper.insertFirstIndexMetadata(firstIndexMetadata);
			}

			// 处理第一列第二列：二级指标
			currentDesignation = sheet.getRow(i).getCell(1).getStringCellValue();
			// 二级指标不为空时再处理
			if (currentDesignation != "") {
				// 获取二级指标名称
				designation = currentDesignation.trim();
				// 获取二级指标重要度
				importance = sheet.getRow(i).getCell(2).getStringCellValue().trim();
				// 设置临时变量供三级指标使用
				secondDesignation = designation;
				// 存储第二级指标
				// 获取该二级指标对应的一级指标的id
				Integer firstIndexId = firstIndexMetadataMapper.getFirstIndexMetadataIdByDesignation(firstDesignation);

				secondIndexMetadataMapper.insertSecondIndexMetadata(
						new SecondIndexMetadata(firstIndexId, designation, importance, strings[0]));
			}

			// 遍历第3、4列：处理三级指标
			currentDesignation = sheet.getRow(i).getCell(3).getStringCellValue();
			if (currentDesignation != "") {
				// 获取三级指标名称
				designation = currentDesignation.trim();
				// 获取三级指标重要程度
				importance = sheet.getRow(i).getCell(4).getStringCellValue().trim();
				// 设置临时变量供四级指标使用
				thirdDesignation = designation;

				// 存储三级指标
				Integer secondIndex = secondIndexMetadataMapper
						.getSecondIndexMetadataIdByDesignation(secondDesignation);
				// 存储三级指标
				thirdIndexMetadataMapper.insertThirdIndexMetadata(
						new ThirdIndexMetadata(secondIndex, designation, importance, strings[0]));
			}

			// 遍历5、6、7列：处理四级指标数据
			currentDesignation = sheet.getRow(i).getCell(5).getStringCellValue();
			if (currentDesignation != "") {
				// 获取四级指标名称
				designation = currentDesignation.trim();
				// 获取四级指标重要度
				importance = sheet.getRow(i).getCell(7).getStringCellValue().trim();
				// 获取单位
				String unit = sheet.getRow(i).getCell(6).getStringCellValue().trim();

				// 存储四级指标
				// 获取四级指标对应的三级指标的id
				Integer thirdIndex = thirdIndexMetadataMapper.getThirdIndexMetadataIdByDesignation(thirdDesignation);

				// 存储四级指标
				fourthIndexMetadataMapper.insertFourthIndexMetadata(
						new FourthIndexMetadata(thirdIndex, designation, importance, strings[0]));

				// 获取所有的增长率
				List<GrowthRate> growthRates = growthRateMapper.listGrowthRates();

				// 处理四级指标的数据
				// for (int j = 8; j < columNumber - 1; j++) {
				// // 获取年份
				// int year = (int)
				// sheet.getRow(0).getCell(j).getNumericCellValue();
				//
				// // 根据年份和指标名称获取 前一年的数据
				// Double prevValue =
				// fourthIndexDataMapper.getValueByYearAndDesignation(String.valueOf(year
				// - 1),
				// designation);
				//
				// XSSFCell cell = sheet.getRow(i).getCell(j);
				//
				// if (cell == null || cell.equals("") || cell.getCellType() ==
				// Cell.CELL_TYPE_BLANK) {
				// System.out.println("指标名称：" + designation + ";年份：" + year +
				// ";数据为空");
				// } else {
				// cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				// double data = cell.getNumericCellValue();
				// // 存库
				// // 获取四级指标对应的id
				// Integer fourthIndex = fourthIndexMetadataMapper
				// .getFourthIndexMetadataIdByDesignation(designation);
				// // 存库
				// fourthIndexDataMapper
				// .insertFourthIndexData(new FourthIndexData(fourthIndex,
				// String.valueOf(year), data));
				//
				// // 四级指标存入修改表
				// fourthIndexDataMapper.insertFourthIndexDataAlter(
				// new FourthIndexData(fourthIndex, String.valueOf(year),
				// data));
				// if (prevValue != null) {
				// // 计算增长率
				// double rate = (data - prevValue) / prevValue * 100;
				// // 根据指标名称查找增长率上下限
				// for (GrowthRate growthRate : growthRates) {
				// if (growthRate.getDesignation().equals(designation)) {
				// int state = 0;
				// // 判定增长率是大了还是小了
				// if (rate > growthRate.getUpper()) {
				// checkoutVOs.add(new CheckoutVO(designation, "高", year));
				// state = 1;
				// System.out.println("指标名称：" + designation + ";" + year +
				// "年增长率高了");
				// } else if (rate < growthRate.getLower()) {
				// checkoutVOs.add(new CheckoutVO(designation, "低", year));
				// state = 0;
				// System.out.println("指标名称：" + designation + ";" + year +
				// "年增长率低了");
				// } else {
				// state = 2;
				// System.out.println("指标名称：" + designation + ";" + year +
				// "年增长率正常");
				// }
				// // 添加数据库
				// fourthIndexDataMapper.updateState(currentDesignation,
				// String.valueOf(year), state);
				// }
				// }
				// } else {
				// System.out.println("指标名称：" + designation + ";没有前一年的数据：" +
				// String.valueOf(year - 1));
				// }
				// }
				//
				// }

				// 创建数组用于存储增长率
				List<Double> rates = new ArrayList<Double>();
				// 替换上面的for循环，改为剔除数据在计算 2018年5月27日
				for (int j = 8; j < columNumber - 1; j++) {
					// 获取年份
					int year = (int) sheet.getRow(0).getCell(j).getNumericCellValue();

					// 根据年份和指标名称获取 前一年的数据
					Double prevValue = fourthIndexDataMapper.getValueByYearAndDesignation(String.valueOf(year - 1),
							designation);

					XSSFCell cell = sheet.getRow(i).getCell(j);

					if (cell == null || cell.equals("") || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						System.out.println("指标名称：" + designation + ";年份：" + year + ";数据为空；剔除数据");
						break;
					}
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					if (cell.getNumericCellValue() == 0) {
						System.out.println("指标名称：" + designation + ";年份：" + year + ";数据为0；剔除数据");
						break;
					} else {
						double data = cell.getNumericCellValue();
						// 存库
						// 获取四级指标对应的id
						Integer fourthIndex = fourthIndexMetadataMapper
								.getFourthIndexMetadataIdByDesignation(designation);
						// 四级指标存入修改表
						fourthIndexDataMapper.insertFourthIndexDataAlter(
								new FourthIndexData(fourthIndex, String.valueOf(year), data));

						// 存库
						fourthIndexDataMapper
								.insertFourthIndexData(new FourthIndexData(fourthIndex, String.valueOf(year), data));

						if (prevValue != null) {
							// 计算增长率
							double rate = (data - prevValue) / prevValue;
							// 将增长率存入list
							rates.add(rate);

							double rate100 = rate * 100;
							for (GrowthRate growthRate : growthRates) {
								if (growthRate.getDesignation().equals(designation)) {
									int state = 0;
									// 判定增长率是大了还是小了
									if (rate100 > growthRate.getUpper()) {
										checkoutVOs.add(new CheckoutVO(designation, "高", year));
										state = 1;
										System.out.println("指标名称：" + designation + ";" + year + "年增长率高了");
									} else if (rate100 < growthRate.getLower()) {
										checkoutVOs.add(new CheckoutVO(designation, "低", year));
										state = 0;
										System.out.println("指标名称：" + designation + ";" + year + "年增长率低了");
									} else {
										state = 2;
										System.out.println("指标名称：" + designation + ";" + year + "年增长率正常");
									}
									// 添加数据库
									fourthIndexDataMapper.updateState(currentDesignation, String.valueOf(year), state);
								}
							}
						} else {
							System.out.println("指标名称：" + designation + ";没有前一年的数据：" + String.valueOf(year - 1));
						}
					}

				}
				// 对增长率进行处理
				// System.out.println("列数：" + (columNumber - 10));
				// System.out.println("rates.size()："+ rates.size());
				if (rates.size() == (columNumber - 10)) {
					// 计算标准差
					double result = standardDiviation(rates);
					System.out.println("标准差：" + result);
					// 如果标准差小于等于0.3，该组数据存入修改表
					if (result <= 0.299999) {
						for (int k = 8; k < columNumber - 1; k++) {
							// 获取年份
							int year = (int) sheet.getRow(0).getCell(k).getNumericCellValue();
							XSSFCell cell = sheet.getRow(i).getCell(k);
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							double data = cell.getNumericCellValue();
							// 存库
							// 获取四级指标对应的id
							Integer fourthIndex = fourthIndexMetadataMapper
									.getFourthIndexMetadataIdByDesignation(designation);
							// 四级指标数据存入计算表
							fourthIndexDataMapper.insertFourthIndexDataCompute(
									new FourthIndexData(fourthIndex, String.valueOf(year), data));
							System.out.println("计算数据：" + designation + ";" + year + ";" + data);
						}
					}
				}

			}
		}

		// // 7.关闭流
		inputStream.close();

		return checkoutVOs;

	}

	@Override
	public FoldLineDiagram listFourthIndexDataByDesignation(String designation, Integer id) throws Exception {
		// 1. 创建用于接受查询数据的对象，four是四级专用，third是一级、二级、三级通用
		List<FourthIndexData> listDataByDesignationForth = null;
		List<ThirdIndexData> listDataByDesignation = null;

		// 2. 根据指标类型查询指标数据
		if (id == 4) {
			listDataByDesignationForth = fourthIndexDataMapper.listFourthIndexDataByDesignation(designation);
			System.out.println(listDataByDesignationForth.toString());
		} else if (id == 3) {
			listDataByDesignation = thirdIndexDataMapper.listThirdIndexDataByDesignation(designation);
			System.out.println(listDataByDesignation.toString());
		} else if (id == 2) {
			listDataByDesignation = secondIndexDataMapper.listSecondIndexDataByDesignation(designation);
			System.out.println(listDataByDesignation.toString());
		} else if (id == 1) {
			listDataByDesignation = firstIndexDataMapper.listFirstIndexDataByDesignation(designation);
			System.out.println(listDataByDesignation.toString());
		}

		// 3. 创建用于接受接线图数据的对象
		FoldLineDiagram foldLineDiagram = new FoldLineDiagram();
		// 3.1 获取数据大小
		int size = listDataByDesignationForth != null ? listDataByDesignationForth.size()
				: listDataByDesignation.size();
		// 3.2 用于存储X轴数据
		String[] transverseValues = new String[size];
		// 3.3 用于存放Y轴数据
		Double[] values = new Double[size];

		// 4. 遍历数据存储到折线图对象中
		if (listDataByDesignationForth != null) {

			// 出现没有数据的情况
			if (listDataByDesignationForth.size() == 0) {
				System.out.println("没有数据");
				return null;
			}

			// 市级以2011年为基准
			// Double benchmark = 0.0;
			// for (FourthIndexData fid : listDataByDesignationForth) {
			// if("2011".equals(fid.getYear())){
			// benchmark = fid.getValue();
			// }
			// }
			//
			// if (benchmark == 0) {
			// System.out.println("出现数据为0的情况或者没有11年的数据");
			// return null;
			// }

			// 倍数的计算
			// Double multiple = 100 / benchmark;
			for (int i = 0; i < listDataByDesignationForth.size(); i++) {
				transverseValues[i] = listDataByDesignationForth.get(i).getYear();
				values[i] = ArithUtil.round(listDataByDesignationForth.get(i).getValue(), 2);
				// if("2011".equals(listDataByDesignationForth.get(i).getYear())){
				// transverseValues[i] =
				// listDataByDesignationForth.get(i).getYear();
				// values[i] = ArithUtil.round(100, 2);
				// }else{
				// transverseValues[i] =
				// listDataByDesignationForth.get(i).getYear();
				// values[i] =
				// ArithUtil.round(listDataByDesignationForth.get(i).getValue()
				// * multiple, 2);
				// }
			}
		} else if (listDataByDesignation != null) {

			// 出现没有数据的情况
			if (listDataByDesignation.size() == 0) {
				System.out.println("没有数据");
				return null;
			}

			// 市级以2011年为基准
			Double benchmark = 0.0;
			for (ThirdIndexData tid : listDataByDesignation) {
				if ("2011".equals(tid.getYear())) {
					benchmark = tid.getValue();
				}
			}

			// 出现第一个数据位0的情况
			if (benchmark == 0) {
				System.out.println("出现数据为0的情况或者没有11年的数据");
				return null;
			}

			// 倍数的计算
			Double multiple = 100 / benchmark;
			for (int i = 0; i < listDataByDesignation.size(); i++) {
				if ("2011".equals(listDataByDesignation.get(i).getYear())) {
					transverseValues[i] = listDataByDesignation.get(i).getYear();
					values[i] = ArithUtil.round(100, 2);
				} else {
					transverseValues[i] = listDataByDesignation.get(i).getYear();
					values[i] = ArithUtil.round(listDataByDesignation.get(i).getValue() * multiple, 2);
				}
			}
		}

		// 5. 封装数据
		foldLineDiagram.setTitle(designation);
		foldLineDiagram.setTransverseValues(transverseValues);
		foldLineDiagram.setValues(values);

		return foldLineDiagram;
	}

	@Override
	public FoldLineDiagram comprehensiveIndexQuery() throws Exception {
		List<SynthesizeData> comprehensiveIndexQuery = synthesizeDataMapper.comprehensiveIndexQuery();

		if (comprehensiveIndexQuery != null) {
			System.out.println("查询的数据为" + comprehensiveIndexQuery.toString());

			// 3. 创建用于接受接线图数据的对象
			FoldLineDiagram foldLineDiagram = new FoldLineDiagram();
			// 3.1 获取数据大小
			int size = comprehensiveIndexQuery.size();
			// 3.2 用于存储X轴数据
			String[] transverseValues = new String[size];
			// 3.3 用于存放Y轴数据
			Double[] values = new Double[size];

			// 出现没有数据的情况
			if (comprehensiveIndexQuery.size() == 0) {
				System.out.println("没有数据");
				return null;
			}

			// 市级以11年为基准
			Double benchmark = 0.0;
			for (SynthesizeData sd : comprehensiveIndexQuery) {
				if ("2011".equals(sd.getYear())) {
					benchmark = sd.getValue();
				}
			}

			// 出现第一个数据位0的情况
			if (benchmark == 0) {
				System.out.println("出现数据为0的情况或者没有11年的数据");
				return null;
			}

			// 倍数的计算
			Double multiple = 100 / benchmark;
			for (int i = 0; i < comprehensiveIndexQuery.size(); i++) {
				if ("2011".equals(comprehensiveIndexQuery.get(i).getYear())) {
					transverseValues[i] = comprehensiveIndexQuery.get(i).getYear();
					values[i] = ArithUtil.round(100, 2);
				} else {
					transverseValues[i] = comprehensiveIndexQuery.get(i).getYear();
					values[i] = ArithUtil.round(comprehensiveIndexQuery.get(i).getValue() * multiple, 2);
				}
			}

			// for (int i = 0; i < comprehensiveIndexQuery.size(); i++) {
			// transverseValues[i] = comprehensiveIndexQuery.get(i).getYear();
			// values[i] = comprehensiveIndexQuery.get(i).getValue();
			// }

			// 5. 封装数据
			foldLineDiagram.setTitle("综合指标查询");
			foldLineDiagram.setTransverseValues(transverseValues);
			foldLineDiagram.setValues(values);

			return foldLineDiagram;

		}
		System.out.println("查询数据为空");
		return null;
	};

	@Override
	public List<Select2> listFirstIndexMetadata() throws Exception {
		List<String> designations = firstIndexMetadataMapper.listFirstIndexDesignation();
		List<Select2> select2Designations = new ArrayList<Select2>();

		select2Designations.add(new Select2(0, "一级指标"));

		for (int i = 1; i <= designations.size(); i++) {
			Select2 select2 = new Select2(i, designations.get(i - 1));
			select2Designations.add(select2);
		}

		System.out.println(select2Designations.toString());

		return select2Designations;
	}

	@Override
	public List<Select2> listSecondIndexMetadata(String designation) throws Exception {
		List<String> designations = secondIndexMetadataMapper.listSecondIndexDesignation(designation);
		List<Select2> select2Designations = new ArrayList<Select2>();

		select2Designations.add(new Select2(0, "二级指标"));

		for (int i = 1; i <= designations.size(); i++) {
			Select2 select2 = new Select2(i, designations.get(i - 1));
			select2Designations.add(select2);
		}

		System.out.println(select2Designations.toString());

		return select2Designations;
	}

	@Override
	public List<Select2> listThirdIndexMetadata(String designation) throws Exception {
		List<String> designations = thirdIndexMetadataMapper.listThirdIndexMetadata(designation);
		List<Select2> select2Designations = new ArrayList<Select2>();

		select2Designations.add(new Select2(0, "三级指标"));

		for (int i = 1; i <= designations.size(); i++) {
			Select2 select2 = new Select2(i, designations.get(i - 1));
			select2Designations.add(select2);
		}

		System.out.println(select2Designations.toString());

		return select2Designations;
	}

	@Override
	public List<Select2> listFourthIndexMetadata(String designation) throws Exception {
		List<String> designations = fourthIndexMetadataMapper.listFourthIndexMetadata(designation);
		List<Select2> select2Designations = new ArrayList<Select2>();

		select2Designations.add(new Select2(0, "四级指标"));

		for (int i = 1; i <= designations.size(); i++) {
			Select2 select2 = new Select2(i, designations.get(i - 1));
			select2Designations.add(select2);
		}

		System.out.println(select2Designations.toString());

		return select2Designations;
	}

	@Override
	public List<CheckoutVO> importDataAgain(File file) throws Exception {
		List<CheckoutVO> checkoutVOs = new ArrayList<CheckoutVO>();
		// 1.创建要读入的文件
		InputStream inputStream = new FileInputStream(file);
		// 2.得到Excel工作簿对象
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		// 获取文件名称
		String fileName = file.getName();
		String[] strings = fileName.split("\\.");
		// System.out.println("文件名称：" + strings[0]);

		// 3.得到Excel工作表对象
		XSSFSheet sheet = wb.getSheetAt(0);
		// 4.得到Excel工作表的行数、列数
		int rowNumber = sheet.getLastRowNum();
		int columNumber = sheet.getRow(0).getPhysicalNumberOfCells();

		// 6.遍历所有的行列
		for (int i = 1; i < rowNumber + 1; i++) {
			String currentDesignation = "";
			// 遍历5、6、7列：处理四级指标数据
			currentDesignation = sheet.getRow(i).getCell(5).getStringCellValue().trim();
			if (currentDesignation != "") {
				// 处理四级指标的数据
				// for (int j = 8; j < columNumber - 1; j++) {
				// // 获取年份
				// int year = (int)
				// sheet.getRow(0).getCell(j).getNumericCellValue();
				// // 获取年份对应的值
				// if (sheet.getRow(i).getCell(j) != null) {
				// sheet.getRow(i).getCell(j).setCellType(Cell.CELL_TYPE_NUMERIC);
				// }
				// double data =
				// sheet.getRow(i).getCell(j).getNumericCellValue();
				//
				// // 存库
				// // 获取四级指标对应的id
				// Integer fourthIndex = fourthIndexMetadataMapper
				// .getFourthIndexMetadataIdByDesignation(currentDesignation);
				//
				// // 存库
				// if (fourthIndex != null) {
				// fourthIndexDataMapper
				// .insertFourthIndexData(new FourthIndexData(fourthIndex,
				// String.valueOf(year), data));
				// System.out.println("四级指标id:" + fourthIndex + ";年份：" +
				// String.valueOf(year) + ";数据：" + data);
				// }
				// }
				// 获取所有的增长率
				List<GrowthRate> growthRates = growthRateMapper.listGrowthRates();
				for (int j = 8; j < columNumber - 1; j++) {
					// 获取年份
					int year = (int) sheet.getRow(0).getCell(j).getNumericCellValue();

					// 根据年份和指标名称获取 前一年的数据
					Double prevValue = fourthIndexDataMapper.getValueByYearAndDesignation(String.valueOf(year - 1),
							currentDesignation);

					XSSFCell cell = sheet.getRow(i).getCell(j);

					if (cell == null || cell.equals("") || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						System.out.println("指标名称：" + currentDesignation + ";年份：" + year + ";数据为空");
					} else {
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						double data = cell.getNumericCellValue();
						// 存库
						// 获取四级指标对应的id
						Integer fourthIndex = fourthIndexMetadataMapper
								.getFourthIndexMetadataIdByDesignation(currentDesignation);
						// 存库
						fourthIndexDataMapper
								.insertFourthIndexData(new FourthIndexData(fourthIndex, String.valueOf(year), data));
						// 存库
						fourthIndexDataMapper.insertFourthIndexDataAlter(
								new FourthIndexData(fourthIndex, String.valueOf(year), data));

						if (prevValue != null) {
							// 计算增长率
							double rate = (data - prevValue) / prevValue * 100;
							// 根据指标名称查找增长率上下限
							for (GrowthRate growthRate : growthRates) {
								if (growthRate.getDesignation().equals(currentDesignation)) {
									// 判定增长率是大了还是小了
									if (rate > growthRate.getUpper()) {
										checkoutVOs.add(new CheckoutVO(currentDesignation, "高", year));
										System.out.println("指标名称：" + currentDesignation + ";" + year + "年增长率高了");
									} else if (rate < growthRate.getLower()) {
										checkoutVOs.add(new CheckoutVO(currentDesignation, "低", year));
										System.out.println("指标名称：" + currentDesignation + ";" + year + "年增长率低了");
									} else {
										System.out.println("指标名称：" + currentDesignation + ";" + year + "年增长率正常");
									}
								}
							}
						} else {
							System.out.println("指标名称：" + currentDesignation + ";没有前一年的数据：" + String.valueOf(year - 1));
						}
					}

				}
			}

		}

		// // 7.关闭流
		inputStream.close();
		return checkoutVOs;
	}

	@Override
	public List<String> checkingData(File file) throws Exception {
		// 1.创建要读入的文件
		InputStream inputStream = new FileInputStream(file);
		// 2.得到Excel工作簿对象
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		// 3.得到Excel工作表对象
		XSSFSheet sheet = wb.getSheetAt(0);
		// 4.得到Excel工作表的列数
		int columNumber = sheet.getRow(0).getPhysicalNumberOfCells();

		// 5.获取文件名称
		String fileName = file.getName();
		String[] strings = fileName.split("\\.");
		System.out.println("文件名称：" + strings[0]);

		// 6.获取年份
		List<String> years = new ArrayList<String>();
		for (int j = 8; j < columNumber - 1; j++) {
			// 获取年份
			int year = (int) sheet.getRow(0).getCell(j).getNumericCellValue();
			years.add(String.valueOf(year));
		}

		// 7.查询一级指标数据中是否存在该省份的这些年份的数据
		List<String> result = new ArrayList<String>();
		List<String> yearsExist = fourthIndexMetadataMapper.listYearByDistrict(strings[0]);
		System.out.println("====长度：" + yearsExist.size());
		if (yearsExist != null && yearsExist.size() != 0) {
			for (String year : yearsExist) {
				if (years.contains(year)) {
					result.add(year);
				}
			}
			System.out.println("重复的年份：" + result);
			// return result;
		}
		return result;
	}

	// ----------------------------------------------------------------------------
	@Override
	public FoldLineDiagram listFourthIndexDataByDesignationAlter(String designation, Integer id) throws Exception {
		// 1. 创建用于接受查询数据的对象，four是四级专用，third是一级、二级、三级通用
		List<FourthIndexData> listDataByDesignationForth = null;
		List<ThirdIndexData> listDataByDesignation = null;

		// 2. 根据指标类型查询指标数据
		if (id == 4) {
			listDataByDesignationForth = fourthIndexDataMapper.listFourthIndexDataByDesignationAlter(designation);
			System.out.println(listDataByDesignationForth.toString());
		} else if (id == 3) {
			listDataByDesignation = thirdIndexDataMapper.listThirdIndexDataByDesignationAlter(designation);
			System.out.println(listDataByDesignation.toString());
		} else if (id == 2) {
			listDataByDesignation = secondIndexDataMapper.listSecondIndexDataByDesignationAlter(designation);
			System.out.println(listDataByDesignation.toString());
		} else if (id == 1) {
			listDataByDesignation = firstIndexDataMapper.listFirstIndexDataByDesignationAlter(designation);
			System.out.println(listDataByDesignation.toString());
		}

		// 3. 创建用于接受接线图数据的对象
		FoldLineDiagram foldLineDiagram = new FoldLineDiagram();
		// 3.1 获取数据大小
		int size = listDataByDesignationForth != null ? listDataByDesignationForth.size()
				: listDataByDesignation.size();
		// 3.2 用于存储X轴数据
		String[] transverseValues = new String[size];
		// 3.3 用于存放Y轴数据
		Double[] values = new Double[size];

		// 4. 遍历数据存储到折线图对象中
		if (listDataByDesignationForth != null) {

			// 出现没有数据的情况
			if (listDataByDesignationForth.size() == 0) {
				System.out.println("没有数据");
				return null;
			}

			// 倍数的计算
			// Double multiple = 100 / benchmark;
			for (int i = 0; i < listDataByDesignationForth.size(); i++) {
				transverseValues[i] = listDataByDesignationForth.get(i).getYear();
				values[i] = ArithUtil.round(listDataByDesignationForth.get(i).getValue(), 2);
			}
		} else if (listDataByDesignation != null) {

			// 出现没有数据的情况
			if (listDataByDesignation.size() == 0) {
				System.out.println("没有数据");
				return null;
			}

			// 省级以09年为基准
			Double benchmark = 0.0;
			for (ThirdIndexData tid : listDataByDesignation) {
				if ("2009".equals(tid.getYear())) {
					benchmark = tid.getValue();
				}
			}

			// 出现第一个数据位0的情况
			if (benchmark == 0) {
				System.out.println("出现数据为0的情况或者没有09年的数据");
				return null;
			}

			// 倍数的计算
			Double multiple = 100 / benchmark;
			for (int i = 0; i < listDataByDesignation.size(); i++) {
				if ("2009".equals(listDataByDesignation.get(i).getYear())) {
					transverseValues[i] = listDataByDesignation.get(i).getYear();
					values[i] = ArithUtil.round(100, 2);
				} else {
					transverseValues[i] = listDataByDesignation.get(i).getYear();
					values[i] = ArithUtil.round(listDataByDesignation.get(i).getValue() * multiple, 2);
				}
			}
		}

		// 5. 封装数据
		foldLineDiagram.setTitle(designation);
		foldLineDiagram.setTransverseValues(transverseValues);
		foldLineDiagram.setValues(values);

		return foldLineDiagram;
	}

	@Override
	public FoldLineDiagram comprehensiveIndexQueryAlter() throws Exception {
		List<SynthesizeData> comprehensiveIndexQuery = synthesizeDataMapper.comprehensiveIndexQuery();

		if (comprehensiveIndexQuery != null) {
			System.out.println("查询的数据为" + comprehensiveIndexQuery.toString());

			// 3. 创建用于接受接线图数据的对象
			FoldLineDiagram foldLineDiagram = new FoldLineDiagram();
			// 3.1 获取数据大小
			int size = comprehensiveIndexQuery.size();
			// 3.2 用于存储X轴数据
			String[] transverseValues = new String[size];
			// 3.3 用于存放Y轴数据
			Double[] values = new Double[size];

			// 出现没有数据的情况
			if (comprehensiveIndexQuery.size() == 0) {
				System.out.println("没有数据");
				return null;
			}

			// 省级以09年为基准
			Double benchmark = 0.0;
			for (SynthesizeData sd : comprehensiveIndexQuery) {
				if ("2009".equals(sd.getYear())) {
					benchmark = sd.getValue();
				}
			}

			// 出现第一个数据位0的情况
			if (benchmark == 0) {
				System.out.println("出现数据为0的情况或者没有09年的数据");
				return null;
			}

			// 倍数的计算
			Double multiple = 100 / benchmark;
			for (int i = 0; i < comprehensiveIndexQuery.size(); i++) {
				if ("2009".equals(comprehensiveIndexQuery.get(i).getYear())) {
					transverseValues[i] = comprehensiveIndexQuery.get(i).getYear();
					values[i] = ArithUtil.round(100, 2);
				} else {
					transverseValues[i] = comprehensiveIndexQuery.get(i).getYear();
					values[i] = ArithUtil.round(comprehensiveIndexQuery.get(i).getValue() * multiple, 2);
				}
			}

			// 5. 封装数据
			foldLineDiagram.setTitle("综合指标查询");
			foldLineDiagram.setTransverseValues(transverseValues);
			foldLineDiagram.setValues(values);

			return foldLineDiagram;

		}
		System.out.println("查询数据为空");
		return null;
	}

	@Override
	public List<ReviseDataVO> getFourthIndexDataAndReviseDataByFirstIndex(String designation) throws Exception {
		return fourthIndexDataMapper.getFourthIndexDataAndReviseDataByFirstIndex(designation);
	}

	@Override
	public List<ReviseDataVO> getFourthIndexDataAndReviseDataBySecondIndex(String designation) throws Exception {
		return fourthIndexDataMapper.getFourthIndexDataAndReviseDataBySecondIndex(designation);
	}

	@Override
	public List<ReviseDataVO> getFourthIndexDataAndReviseDataByThirdIndex(String designation) throws Exception {
		return fourthIndexDataMapper.getFourthIndexDataAndReviseDataByThirdIndex(designation);
	}

	@Override
	public List<ReviseDataVO> getFourthIndexDataAndReviseDataByFourthIndex(String designation) throws Exception {
		return fourthIndexDataMapper.getFourthIndexDataAndReviseDataByFourthIndex(designation);
	}

	@Transactional
	@Override
	public Boolean updateFourthIndexDataAlter(List<ReviseDataVO> reviseDataVOs) throws Exception {
		Integer resultRow = 0;
		for (ReviseDataVO reviseDataVO : reviseDataVOs) {
			resultRow += fourthIndexDataMapper.updateFourthIndexDataAlterById(reviseDataVO.getIndexId(),
					reviseDataVO.getYear(), reviseDataVO.getReviseValue(), reviseDataVO.getIsExclude());
		}
		System.out.println(resultRow);
		System.out.println(reviseDataVOs.toString());
		if (resultRow == reviseDataVOs.size()) {
			return true;
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 事务回滚
			return false;
		}
	}

	private double standardDiviation(List<Double> x) {
		int m = x.size();
		double sum = 0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x.get(i);
		}
		double dAve = sum / m;// 求平均值
		double dVar = 0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x.get(i) - dAve) * (x.get(i) - dAve);
		}
		return Math.sqrt(dVar / m);
	}
}

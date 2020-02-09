package cn.edu.ouc.pojo.dto;

/**
 * 二级指标元信息
 * 
 * @author 高杨
 * @date 2018年1月21日 上午9:48:51
 */
public class ThirdIndexData {
	// 1.id
	private Integer indexId;
	// 2.年分
	private String year;
	// 3.数据值
	private Double value;

	public ThirdIndexData() {
	}

	public ThirdIndexData(Integer indexId, String year, Double value) {
		this.indexId = indexId;
		this.year = year;
		this.value = value;
	}

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}

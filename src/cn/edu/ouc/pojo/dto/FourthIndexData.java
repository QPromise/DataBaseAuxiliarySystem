package cn.edu.ouc.pojo.dto;

/**
 * 四级指标数据
 * 
 * @author 高杨
 * @date 2018年1月21日 上午9:54:46
 */
public class FourthIndexData {

	// 1.id
	private Integer id;
	// 2.四级指标id
	private Integer indexId;
	// 3.年份
	private String year;
	// 4.数据值
	private double value;

	public FourthIndexData() {
		super();
	}

	public FourthIndexData(Integer indexId, String year, double value) {
		super();
		this.indexId = indexId;
		this.year = year;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FourthIndexData [id=");
		builder.append(id);
		builder.append(", indexId=");
		builder.append(indexId);
		builder.append(", year=");
		builder.append(year);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}

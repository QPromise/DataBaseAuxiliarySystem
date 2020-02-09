package cn.edu.ouc.pojo.dto;

/**
 * 二级指标元信息
 * 
 * @author 高杨
 * @date 2018年1月21日 上午9:48:51
 */
public class FourthIndexMetadata {
	// 1.id
	private Integer id;
	// 2.一级指标id
	private Integer thirdIndexId;
	// 3.指标名称
	private String designation;
	// 4.单位
	private String unit;
	// 5.重要程度
	private String importance;
	// 6.省份
	private String district;

	public FourthIndexMetadata() {
		super();
	}

	// public FourthIndexMetadata(Integer thirdIndexId, String designation,
	// String importance) {
	// this.thirdIndexId = thirdIndexId;
	// this.designation = designation;
	// this.importance = importance;
	// }

	public Integer getId() {
		return id;
	}

	public FourthIndexMetadata(Integer thirdIndexId, String designation, String importance, String district) {
		super();
		this.thirdIndexId = thirdIndexId;
		this.designation = designation;
		this.importance = importance;
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getThirdIndexId() {
		return thirdIndexId;
	}

	public void setThirdIndexId(Integer thirdIndexId) {
		this.thirdIndexId = thirdIndexId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FourthIndexMetadata [id=");
		builder.append(id);
		builder.append(", thirdIndexId=");
		builder.append(thirdIndexId);
		builder.append(", designation=");
		builder.append(designation);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", importance=");
		builder.append(importance);
		builder.append("]");
		return builder.toString();
	}
}

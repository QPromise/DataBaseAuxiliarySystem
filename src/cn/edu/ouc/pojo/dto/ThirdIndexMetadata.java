package cn.edu.ouc.pojo.dto;

/**
 * 二级指标元信息
 * 
 * @author 高杨
 * @date 2018年1月21日 上午9:48:51
 */
public class ThirdIndexMetadata {
	// 1.id
	private Integer id;
	// 2.一级指标id
	private Integer secondIndexId;
	// 3.指标名称
	private String designation;
	// 4.单位
	private String unit;
	// 5.重要程度
	private String importance;
	// 6.省份
	private String district;

//	public ThirdIndexMetadata(Integer secondIndexId, String designation, String importance) {
//		super();
//		this.secondIndexId = secondIndexId;
//		this.designation = designation;
//		this.importance = importance;
//	}

	public ThirdIndexMetadata(Integer secondIndexId, String designation, String importance, String district) {
		this.secondIndexId = secondIndexId;
		this.designation = designation;
		this.importance = importance;
		this.district = district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSecondIndexId() {
		return secondIndexId;
	}

	public void setSecondIndexId(Integer secondIndexId) {
		this.secondIndexId = secondIndexId;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ThirdIndexMetadata [id=");
		builder.append(id);
		builder.append(", secondIndexId=");
		builder.append(secondIndexId);
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
